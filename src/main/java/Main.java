import Duke.Main.Duke;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke("taskFile/taskList.txt");
        duke.run();
    }
}
