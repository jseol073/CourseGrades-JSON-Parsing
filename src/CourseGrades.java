/**
 * Created by johnseol on 1/26/18.
 */
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

public class CourseGrades {
    public String fall2013 = Data.getFileContentsAsString(Data.JSON_FILES[0]);
    private String fall2014 = Data.getFileContentsAsString(Data.JSON_FILES[1]);
    private String spring2013 = Data.getFileContentsAsString(Data.JSON_FILES[2]);
    private String spring2014 = Data.getFileContentsAsString(Data.JSON_FILES[3]);
    private String summer2013 = Data.getFileContentsAsString(Data.JSON_FILES[4]);
    private String summer2014 = Data.getFileContentsAsString(Data.JSON_FILES[5]);
    private Course course;

//    public ArrayList<Course> filesToList(String file1, String file2, String file3, String file4, String file5, String file6) {
//        ArrayList<String> fileList = new ArrayList<String>();
//
//    }

}

