import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        studentManager.start();
        while (true) {
            studentManager.commandReadExecute();
        }
        //Student s1 = new Student("Sasha", new ArrayList<>(List.of(1, 2, "pass")), new ArrayList<>(List.of(1, 2, "pass")));
    }
}