/**
 * Created by johnseol on 1/26/18.
 */

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseGrades {

    //STEP 2: Loading JSON from Files **********************************************************************************

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

    // STEP 3: Filtering Methods ***************************************************************************************

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

    // @param courseList is the list of all courses
    // @param instructorName is the name of the instructor
    // @return ArrayList of courses that correspond with the instructor's name
    public static List<Course> getInstructorCourses(List<Course> courseList, String instructorName) {
        List<Course> instructorClassList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getInstructor().equals(instructorName)) {
                instructorClassList.add(courseList.get(i));
            }
        }
        return instructorClassList;
    }

    // @param courseList is the list of all courses
    // @param fromThisNumber is an int value of the min course number
    // @param toThisNumber is an int value of max course number
    // @return ArrayList of courses that correspond within the range
    public static List<Course> getNumberCoursesInRange(List<Course> courseList,
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

    // @param courseList is the list of all courses
    // @param fromRange is the int amount of students from starting range
    // @param toRange is the int amount of students to ending range
    // @return ArrayList of courses that correspond within the range of the amount of students
    public static List<Course> getAmountStudentsWithinRange(List<Course> courseList,
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

    // @param courseList is the list of all courses
    // @param courseTitle is the name of the course
    // @return ArrayList of courses that correspond with the course's title
    public static List<Course> getTitleCourses(List<Course> courseList, String courseTitle) {
        List<Course> courseTitleList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getTitle().equals(courseTitle)) {
                courseTitleList.add(courseList.get(i));
            }
        }
        return courseTitleList;
    }

    // @param courseList is the list of all courses
    // @param fromRange is a double value of min GPA
    // @param toRange is a double value of max GPA
    // @return ArrayList of courses that correspond within the range of the GPA value
    public static List<Course> getAmountOfStudentsGPAWithinRange(List<Course> courseList,
                                                                 double fromRange, double toRange) {
        List<Course> coursesGPAInRange = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getAverage() >= fromRange
                    && courseList.get(i).getAverage() <= toRange) {
                coursesGPAInRange.add(courseList.get(i));
            }
        }
        return coursesGPAInRange;
    }

    // STEP 4: Aggregation Methods *************************************************************************************

    // @param collectionOfCourses is a list of type Course
    // @return int which is the value of the amount of students on the courseList
    public static int getAmountStudentsOfCourse(List<Course> collectionOfCourses) {
        int totalStudentsInCourseList = 0;
        for (int i = 0; i < collectionOfCourses.size(); i++) {
            totalStudentsInCourseList += collectionOfCourses.get(i).getNumStudents();
        }
        return totalStudentsInCourseList;
    }

    // Helper method for getAmountOfStudentsWithinGradeRange
    // @param gradeArray is an array of the how many students got each grade
    // @param fromRange is an int which is the index of the starting range
    // @param toRange is an int which the index of the ending range
    // @return int which is the value of the amount of students on a given range of the gradeArray
    public static int getStudentsInGradeArrWithinRange(int[] gradeArray, int fromRange, int toRange) {
        int getNumStudents = 0;
        for (int i = fromRange; i < toRange + 1; i++) {
            getNumStudents += gradeArray[i];
        }
        return getNumStudents;
    }

    // @param collectionOfCourses is a list of type Course
    // @param fromRange is a String of the grade mark which is turned into the index of the starting range
    // @param toRange is a String of the grade mark which is turned into the index of the ending range
    // @return int which is the value of the amount of students on a given range of the courseList
    public static int getAmountOfStudentsWithinGradeRange(List<Course> collectionOfCourses,
                                                          String fromRange, String toRange) {
        String[] gradeMarkArray =
                {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F", "W"};
        int indexOfFromRange = 0;
        int indexOfToRange = 0;
        int numStudents = 0;
        for (int i = 0; i < gradeMarkArray.length; i++) {
            if (gradeMarkArray[i].equals(fromRange)) {
                indexOfFromRange = i;
            }
            if (gradeMarkArray[i].equals(toRange)) {
                indexOfToRange = i;
                break;
            }
        }
        for (int i = 0; i < collectionOfCourses.size(); i++) {
            numStudents += getStudentsInGradeArrWithinRange(collectionOfCourses.get(i).getGrades(),
                    indexOfFromRange, indexOfToRange);
        }
        return numStudents;
    }

    // @param collectionOfCourses is the list of the courses of type Course
    // @return the average GPA (double) of the courses on the list
    public static double getAverageGPA(List<Course> collectionOfCourses) {
        double sumGPA = 0.0;
        int listLength = 0;
        for (int i = 0; i < collectionOfCourses.size(); i++) {
            sumGPA += collectionOfCourses.get(i).getAverage();
            listLength++;
        }
        double averageGPA = sumGPA / listLength;
        return averageGPA;
    }

}

