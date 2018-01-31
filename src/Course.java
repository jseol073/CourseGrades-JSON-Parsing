import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;


public class Course {
    @SerializedName("CRN")
    private int CRN;
    @SerializedName("Subject")
    private String subject;
    @SerializedName("Number")
    private int number;
    @SerializedName("Title")
    private String title;
    @SerializedName("Section")
    private String section;
    @SerializedName("Type")
    private String type;
    @SerializedName("Term")
    private int term;
    @SerializedName("Instructor")
    private String instructor;
    @SerializedName("Grades")
    private int[] grades;
    @SerializedName("Average")
    private double average;
    private int numStudents;

    //Default Constructor
    public Course(String subject) {

    }

    public String getSubject() {
        return subject;
    }

    public int getCRN() {
        return CRN;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getType() {
        return type;
    }

    public int getTerm() {
        return term;
    }

    public String getInstructor() {
        return instructor;
    }

    public int[] getGrades() {
        return grades;
    }

    public double getAverage() {
        return average;
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
                ", Subject='" + subject + '\'' +
                ", Number=" + number +
                ", Title='" + title + '\'' +
                ", Section='" + section + '\'' +
                ", Type='" + type + '\'' +
                ", Term=" + term +
                ", Instructor='" + instructor + '\'' +
                ", Grades=" + Arrays.toString(grades) +
                ", Average=" + average +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return CRN == course.CRN &&
                number == course.number &&
                term == course.term &&
                Double.compare(course.average, average) == 0 &&
                Objects.equals(subject, course.subject) &&
                Objects.equals(title, course.title) &&
                Objects.equals(section, course.section) &&
                Objects.equals(type, course.type) &&
                Objects.equals(instructor, course.instructor) &&
                Arrays.equals(grades, course.grades);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(CRN, subject, number, title, section, type, term, instructor, average);
        result = 31 * result + Arrays.hashCode(grades);
        return result;
    }
}
