import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;


public class Course {
    @SerializedName("CRN")
    private int CRN;
    private String Subject;
    private int Number;
    private String Title;
    private String Section;
    private String Type;
    private int Term;
    private String Instructor;
    private int[] Grades;
    private int Numbers;
    private double Average;
    private int numStudents;

    //Default Constructor
    public Course(String subject) {

    }

    public String getSubject() {
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

    public String getInstructor() {
        return Instructor;
    }

    public int[] getGrades() {
        return Grades;
    }

    public int getNumbers() {
        return Numbers;
    }

    public double getAverage() {
        return Average;
    }

    // Get amount of students by finding the sum of the int[] gradeArray
    public int getNumStudents() {
        int[] gradeOfStudents = this.getGrades();
        // Got code below from https://stackoverflow.com/questions/4550662/how-do-you-find-the-sum-of-all-the-numbers-in-an-array-in-java
        int sumOfGrades = IntStream.of(gradeOfStudents).sum();
        return sumOfGrades;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CRN=" + CRN +
                ", Subject='" + Subject + '\'' +
                ", Number=" + Number +
                ", Title='" + Title + '\'' +
                ", Section='" + Section + '\'' +
                ", Type='" + Type + '\'' +
                ", Term=" + Term +
                ", Instructor='" + Instructor + '\'' +
                ", Grades=" + Arrays.toString(Grades) +
                ", Average=" + Average +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return CRN == course.CRN &&
                Number == course.Number &&
                Term == course.Term &&
                Double.compare(course.Average, Average) == 0 &&
                Objects.equals(Subject, course.Subject) &&
                Objects.equals(Title, course.Title) &&
                Objects.equals(Section, course.Section) &&
                Objects.equals(Type, course.Type) &&
                Objects.equals(Instructor, course.Instructor) &&
                Arrays.equals(Grades, course.Grades);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(CRN, Subject, Number, Title, Section, Type, Term, Instructor, Average);
        result = 31 * result + Arrays.hashCode(Grades);
        return result;
    }
}
