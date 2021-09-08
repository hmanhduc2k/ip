package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.DateOfCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TaskCommand;
import duke.exceptions.DukeSyntaxErrorException;
import duke.task.Task;

public class Parser {

    /**
     * Convert YYYY-MM-DD format to MMM dd YYY format (for representing time in Duke)
     * @param date date in (yyyy-mm-dd) format
     * @return date in MMM dd YYY format
     */
    public static String convert(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Convert MMM dd YYY format to YYYY-MM-DD format (for saving time to file)
     * @param date Date entered in (MMM dd yyyy) format
     * @return Date in (YYYY-MM-DD) format
     */
    public static String reverse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Parse and analyze user input, handling proper respond by redirecting
     * Duke to the appropriate Command object
     * @param command command being parsed
     * @param taskList task list to be modified
     * @return appropriate command to be shown to users
     */
    public static Command parse(String command, TaskList taskList) {
        String[] components = command.split(" ", 2);
        String type = components[0].toLowerCase().trim();
        String description = (components.length < 2) ? "" : components[1].trim();
        switch (type) {
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand(taskList);
        case "done":
            return new DoneCommand(description, taskList);
        case "todo":
            return new TaskCommand(description, taskList, Task.Type.TODO);
        case "deadline":
            return new TaskCommand(description, taskList, Task.Type.DEADLINE);
        case "event":
            return new TaskCommand(description, taskList, Task.Type.EVENT);
        case "delete":
            return new DeleteCommand(description, taskList);
        case "find":
            return new FindCommand(description, taskList);
        case "date-of":
            return new DateOfCommand(description, taskList);
        default:
            throw new DukeSyntaxErrorException(type);
        }
    }
}
