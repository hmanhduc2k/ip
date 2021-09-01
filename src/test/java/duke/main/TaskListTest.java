package duke.main;

import duke.exceptions.DukeException;
import duke.exceptions.DukeOutOfBoundException;
import duke.exceptions.DukeWrongCommandException;
import duke.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        this.taskList = new TaskList();
        Storage.loadData(new File("taskFile/testFile1.txt"), taskList);
    }

    @Test
    public void testSize() {
        assertEquals(3, taskList.size());
        taskList.addTask("play the piano", Task.Type.TODO);
        assertEquals(4, taskList.size());
        taskList.delete(4);
        assertEquals(3, taskList.size());
    }

    @Test
    public void testDone() {
        assertThrows(DukeException.class, () -> taskList.done(10));
        assertThrows(DukeOutOfBoundException.class, () -> taskList.done(-1));
        taskList.done(1);
        assertTrue(taskList.get(1).isCompleted());
        taskList.doneAll();
        assertTrue(taskList.get(2).isCompleted());
        assertTrue(taskList.get(3).isCompleted());
    }

    @Test
    public void testDelete() {
        assertThrows(DukeException.class, () -> taskList.delete(10));
        assertThrows(DukeOutOfBoundException.class, () -> taskList.delete(-1));
        taskList.delete(1);
        assertEquals(2, taskList.size());
        taskList.deleteAll();
        assertEquals(0, taskList.size());
    }

    @Test
    public void testAddTask() {
        taskList.addTask("walk 10000 steps", Task.Type.TODO);
        assertEquals(4, taskList.size());
        assertFalse(taskList.get(4).isCompleted());
        taskList.addTask("cs2100 tutorial /by 2021-08-29", Task.Type.DEADLINE);
        assertFalse(taskList.get(5).isCompleted());
        assertEquals(5, taskList.size());
        taskList.addTask("cs2101 OP 1 /at 2021-08-30", Task.Type.EVENT);
        assertEquals(6, taskList.size());
        assertThrows(DukeWrongCommandException.class,
                () -> taskList.addTask("play Liszt", Task.Type.DEADLINE));
        assertThrows(DukeWrongCommandException.class,
                () -> taskList.addTask("play Liszt", Task.Type.EVENT));
    }
}
