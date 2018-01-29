/**
 * Created by johnseol on 1/26/18.
 */
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseGrades {
    private Course course;

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

//    public List<Course> getSubjectCourses(List<Course> courseList, String subjectName) {
//
//    }

    public static void main(String[] args) {
        List<String> fileList = Data.getJsonFilesAsList();
        System.out.println(filesToList(fileList));
    }

}

