/**
 * Created by johnseol on 1/26/18.
 */
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseGrades {
    private Course course;

    // Converts all json files into a ArrayList of type Course
    // @param fileList is the json file names in a String List
    // @return List of Courses in type Course
    public static List<Course> filesToList(List<String> fileList) {
        Gson gson = new Gson();
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            String data = Data.getFileContentsAsString(fileList.get(i));
            Course[] array = gson.fromJson(data, Course[].class);
            List<Course> fileContent = new ArrayList<>(Arrays.asList(array));
            courseList.addAll(fileContent);
        }
        return courseList;
    }

    // @param courseList is the list of all courses
    // @param subjectName is the name of the subject
    // @return ArrayList of courses that correspond with the subjectName
    public static List<Course> getSubjectCourses(List<Course> courseList, String subjectName) {
        List<Course> subjectClassList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getSubject().equals(subjectName)) {
                subjectClassList.add(courseList.get(i));
            }
        }
        return subjectClassList;
    }
    
    public static List<Course> getInstructorCourses(List<Course> courseList, String instructorName) {
        List<Course> instructorClassList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getInstructor().equals(instructorName)) {
                instructorClassList.add(courseList.get(i));
            }
        }
        return instructorClassList;
    }

    public static List<Course> getNumCoursesInRange(List<Course> courseList,
                                                    int fromThisNumber, int toThisNumber) {
        List<Course> coursesInRange = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getNumber() >= fromThisNumber
                    && courseList.get(i).getNumber() <= toThisNumber) {
                coursesInRange.add(courseList.get(i));
            }
        }
        return coursesInRange;
    }

    public static List<Course> getNumStudentsWithinRange(List<Course> courseList,
                                                         int fromRange, int toRange) {
        List<Course> coursesInRange = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getNumStudents() >= fromRange
                    && courseList.get(i).getNumStudents() <= toRange) {
                coursesInRange.add(courseList.get(i));
            }
        }
        return coursesInRange;
    }


}

