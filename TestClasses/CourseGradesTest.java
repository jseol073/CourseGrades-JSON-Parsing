import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import static org.junit.Assert.*;

public class CourseGradesTest {

    private static final String firstCourse = "{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": " +
            "\"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": " +
            "\"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }";

    private final String array = "[\n" +
            "    { \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 },\n" +
            "    { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 11, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
            "    { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [2, 24, 1, 2, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.75 },\n" +
            "    ]";
    private static Course firstJSONLine;
    private static Course[] courseArray;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        firstJSONLine = gson.fromJson(firstCourse, Course.class);
        courseArray = gson.fromJson(array, Course[].class);
    }

    @Test
    public void getSubjectOfOneLine() {
        assertEquals("AAS", firstJSONLine.getSubject());
    }

    @Test
    public void getInstructorNameOfOneLine() {
        assertEquals("Arai, Sayuri", firstJSONLine.getInstructor());
    }

    @Test
    public void getCRNOfOneLine() {
        assertEquals(41758, firstJSONLine.getCRN());
    }

    @Test
    public void getNumberOfOneLine() {
        assertEquals(100, firstJSONLine.getNumber());
    }

    @Test
    public void getAverageOfOneLine() {
        assertEquals(100, firstJSONLine.getNumber());
    }

    @Test
    public void getGradesOfOneLine() {
        int[] grades = {6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0};
        assertEquals(grades, firstJSONLine.getGrades());
    }

    @Test
    public void getCRNOfArray() {
        assertEquals(47102, courseArray[2].getCRN());
    }

    @Test
    public void getSubjectOfArray() {
        assertEquals("AAS", courseArray[2].getSubject());
    }

}