import java.util.*;

class Student {
    private String name;
    private ArrayList<Object> assessments = new ArrayList<Object>();
    private final Map<Object, Boolean> mapCorrectAssessments = new HashMap<Object, Boolean>();
    private final ArrayList<Command> commands = new ArrayList<Command>();
    Student(String str) {
        name = str;
    }
    Student(String name, ArrayList<Object> assessments) {
        this.name = name;
        this.assessments = assessments;
    }
    Student(String name, ArrayList<Object> assessments, ArrayList<Object> correctAssessments) {
        this.name = name;
        for (Object correctAssessment : correctAssessments) {
            mapCorrectAssessments.put(correctAssessment, true);
        }
        for(Object assessment : assessments) {
            try{
                checkAssessment(assessment);
            } catch (AssessmentException e) {
                IllegalArgumentException e1 = new IllegalArgumentException();
                e1.initCause(e);
                throw(e1);
            }
        }
        this.assessments = assessments;
    }
    void setName(String name) {
        String oldName = this.name;
        this.name = name;
        commands.add(new Command("SetName", new ArrayList<>(List.of(oldName))));
    }
    String getName() {
        return name;
    }
    void addAssessment(Object assessment) {
        try {
            checkAssessment(assessment);
            assessments.add(assessment);
            commands.add(new Command("AddAssessment", new ArrayList<>(List.of(assessments.size() - 1))));
        } catch (AssessmentException e) {
            IllegalArgumentException e1 = new IllegalArgumentException();
            e1.initCause(e);
            throw(e1);
        }
    }

    void addAssessmentToPos(Object assessment, Integer pos) {
        try {
            checkAssessment(assessment);
            assessments.add(pos, assessment);
            commands.add(new Command("AddAssessmentToPos", new ArrayList<>(List.of(pos))));
        } catch (AssessmentException e) {
            IllegalArgumentException e1 = new IllegalArgumentException();
            e1.initCause(e);
            throw(e1);
        }
    }
    void removeAssessment(Integer index) {
        Object obj = assessments.get(index);
        assessments.remove((int)index);
        commands.add(new Command("RemoveAssessment", new ArrayList<>(List.of(obj, index))));
    }
    void removeLastAssessmentByValue(Object assessment) {
        for(var i = assessments.size() - 1; i >= 0; i--) {
            if(assessments.get(i).equals(assessment)) {
                Object obj = assessments.get(i);
                assessments.remove(i);
                commands.add(new Command("RemoveLastAssessmentByValue", new ArrayList<>(List.of(obj, i))));
                return;
            }
        }
    }
    void removeAllAssessments() {
        assessments.clear();
    }
    void showAssessments() {
        System.out.print(assessments);
    }
    void showInfo() {
        System.out.print(name + ": ");
        showAssessments();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(assessments, student.assessments);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, assessments, mapCorrectAssessments, commands);
    }

    ArrayList<Object> getAssessments() {
        return assessments;
    }
    public static boolean comparisonEquality(Student first, Student second) {
        if(!Objects.equals(first.getName(), second.getName())) {
            return false;
        }

        if(first.getAssessments().size() != second.getAssessments().size()){
            return false;
        }
        for(var i = 0; i < first.getAssessments().size(); i++) {
            if(!Objects.equals(first.getAssessments().get(i), second.getAssessments().get(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean checkAssessment(Object assessment) throws AssessmentException {
        if(mapCorrectAssessments.isEmpty()) {
            return true;
        }
        if(!mapCorrectAssessments.containsKey(assessment)) {
            throw new AssessmentException("Incorrect assessment", assessment);
        }
        return true;
    }
    void cancelAction() {
        if(commands.isEmpty()) {
            return;
        }
        Command cm = commands.get(commands.size() - 1);
        String name = cm.getName();
        switch (name) {
            case ("SetName") -> {
                setName((String) cm.getInfo().get(0));
                commands.remove(commands.size() - 1);
                commands.remove(commands.size() - 1);
            }
            case ("AddAssessment"), ("AddAssessmentToPos") -> {
                removeAssessment((Integer) cm.getInfo().get(0));
                commands.remove(commands.size() - 1);
                commands.remove(commands.size() - 1);
            }
            case ("RemoveAssessment"), ("RemoveLastAssessmentByValue") -> {
                addAssessmentToPos(cm.getInfo().get(0), (Integer) cm.getInfo().get(1));
                commands.remove(commands.size() - 1);
                commands.remove(commands.size() - 1);
            }
        }
    }
}
class AssessmentException extends Exception{
    private final Object object;
    public Object getObject(){return object;}
    public AssessmentException(String message, Object obj){
        super(message);
        object=obj;
    }
}
