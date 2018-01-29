import com.google.gson.Gson;

public class Course {
    private int CRN;
    private static String Subject;
    private int Number;
    private String Title;
    private String Section;
    private String Type;
    private int Term;
    private static String Instructor;
    private int[] Grades;
    private double Average;

    public static String getSubject() {
        return Subject;
    }

    public int getCRN() {
        return CRN;
    }

    public int getNumber() {
        return Number;
    }

    public String getTitle() {
        return Title;
    }

    public String getSection() {
        return Section;
    }

    public String getType() {
        return Type;
    }

    public int getTerm() {
        return Term;
    }

    public static String getInstructor() {
        return Instructor;
    }

    public int[] getGrades() {
        return Grades;
    }

    public double getAverage() {
        return Average;
    }
}
