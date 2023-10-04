import java.util.ArrayList;

public class Command {
    private String name;
    private ArrayList<Object> info;

    Command(String name, ArrayList<Object> info) {
        this.name = name;
        this.info = info;
    }

    String getName() {
        return name;
    }

    ArrayList<Object> getInfo() {
        return info;
    }
}
