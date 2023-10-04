import java.util.Scanner;

public class StudentManager {
    Student student;
    Scanner in = new Scanner(System.in);
    void createStudent(String name) {
        student = new Student(name);
    }

    void addAssessment(Object assessment) {
        student.addAssessment(assessment);
    }

    void removeLastAssessmentByValue(Object assessment) {

        student.removeLastAssessmentByValue(assessment);
    }

    void showInfo() {
        student.showInfo();
    }

    void start() {
        System.out.print("Enter student name:\n");
        createStudent(in.next());
    }
    void commandReadExecute() {
        System.out.print("Choose a command:\n");
        System.out.print("1) Create a new student.\n");
        System.out.print("2) Add a assessment.\n");
        System.out.print("3) Delete the last rating with this value.\n");
        System.out.print("4) Display student information.\n");
        System.out.print("5) Finish work.\n");
        int command = in.nextInt();
        switch (command) {
            case 1:
                System.out.print("Enter student name:\n");
                createStudent(in.next());
                break;
            case 2:
                System.out.print("Enter a assessment:\n");
                addAssessment(in.next());
                break;
            case 3:
                System.out.print("Enter the rating you want to delete:\n");
                removeLastAssessmentByValue((Object)in.next());
                break;
            case 4:
                showInfo();
                System.out.print('\n');
                break;
            case 5:
                in.close();
                System.exit(0);
                break;
            default:
                System.out.print("Invalid command\n");
        }
    }
}
