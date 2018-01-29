import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import static org.junit.Assert.*;

public class CourseGradesTest {

    private static final String firstCourse = "{\"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", " +
            "\"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, " + "\"Instructor\": \"Arai, Sayuri\", " +
            "\"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }";

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        Course firstJSONLine = gson.fromJson(firstCourse, Course.class);
    }


    @Test
    public void getSubject() {
//        Gson gson = new Gson();
//        Course courseAAS = gson.fromJson(firstCourse, Course.class);
        assertEquals("AAS", Course.getSubject());
    }

    @Test
    public void getInstructorName() {
//        Gson gson = new Gson();
//        Course courseAAS = gson.fromJson(firstCourse, Course.class);
        assertEquals("Arai, Sayuri", Course.getInstructor());
    }

}