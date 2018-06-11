import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CourseGradesTest {

    private static final String FIRST_COURSE =
            "{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": " +
                    "\"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": " +
                    "\"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }";

    private final String FIRST_THREE_COURSES = "[\n" +
            "    { \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 },\n" +
            "    { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 11, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
            "    { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [2, 24, 1, 2, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.75 },\n" +
            "    ]";
    private final String MORE_COURSES = "[\n" +
            "  { \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 },\n" +
            "  { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 11, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
            "  { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [2, 24, 1, 2, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.75 },\n" +
            "  { \"CRN\": 51248, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [7, 16, 4, 4, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.71 },\n" +
            "  { \"CRN\": 51249, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [4, 12, 6, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.7 },\n" +
            "  { \"CRN\": 51932, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [12, 14, 2, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.83 },\n" +
            "  { \"CRN\": 58092, \"Subject\": \"AAS\", \"Number\": 287, \"Title\": \"Food and Asian Americans\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Manalansan, Martin F\", \"Grades\": [0, 23, 2, 4, 2, 3, 2, 0, 0, 0, 0, 0, 0, 1], \"Average\": 3.65 },\n" +
            "  { \"CRN\": 58927, \"Subject\": \"ABE\", \"Number\": 223, \"Title\": \"ABE Principles: Machine Syst\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Grift, Tony E\", \"Grades\": [3, 24, 8, 2, 6, 0, 0, 3, 0, 0, 2, 0, 0, 0], \"Average\": 3.54 },\n" +
            "  { \"CRN\": 58931, \"Subject\": \"ABE\", \"Number\": 224, \"Title\": \"ABE Principles: Soil & Water\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Kalita, Prasanta K\", \"Grades\": [0, 32, 0, 10, 6, 0, 0, 0, 0, 0, 2, 0, 0, 1], \"Average\": 3.63 },\n" +
            "  { \"CRN\": 57598, \"Subject\": \"ABE\", \"Number\": 341, \"Title\": \"Transport Processes in ABE\", \"Section\": \"MGD\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Danao, Mary-Grace\", \"Grades\": [1, 2, 3, 3, 5, 7, 2, 3, 2, 0, 1, 0, 0, 0], \"Average\": 2.82 },\n" +
            "  { \"CRN\": 36669, \"Subject\": \"ABE\", \"Number\": 430, \"Title\": \"Project Management\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Schideman, Lance C\", \"Grades\": [0, 15, 12, 15, 53, 15, 5, 7, 4, 0, 0, 0, 0, 0], \"Average\": 3.06 },\n" +
            "  { \"CRN\": 31280, \"Subject\": \"ABE\", \"Number\": 466, \"Title\": \"Engineering Off-Road Vehicles\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Hansen, Alan C\", \"Grades\": [0, 6, 13, 6, 4, 2, 2, 3, 0, 0, 1, 0, 0, 0], \"Average\": 3.26 },\n" +
            "  { \"CRN\": 57478, \"Subject\": \"ABE\", \"Number\": 488, \"Title\": \"Bioprocessing Biomass for Fuel\", \"Section\": \"VS\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Singh, Vijay\", \"Grades\": [0, 5, 9, 6, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0], \"Average\": 3.47 },\n" +
            "  { \"CRN\": 29670, \"Subject\": \"ACCY\", \"Number\": 200, \"Title\": \"Fundamentals of Accounting\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Silhan, Peter A\", \"Grades\": [2, 29, 20, 20, 16, 15, 15, 9, 4, 2, 1, 2, 3, 0], \"Average\": 3.02 },\n" +
            "  { \"CRN\": 39539, \"Subject\": \"ACCY\", \"Number\": 200, \"Title\": \"Fundamentals of Accounting\", \"Section\": \"B\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Silhan, Peter A\", \"Grades\": [0, 23, 25, 27, 23, 20, 9, 3, 4, 1, 6, 1, 0, 0], \"Average\": 3.08 },\n" +
            "  { \"CRN\": 36478, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Hennessy, James E\", \"Grades\": [1, 3, 7, 9, 4, 5, 2, 2, 3, 0, 0, 0, 0, 0], \"Average\": 3.07 },\n" +
            "  { \"CRN\": 36482, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Hennessy, James E\", \"Grades\": [0, 5, 9, 8, 2, 6, 3, 2, 0, 0, 3, 0, 0, 0], \"Average\": 3.04 },\n" +
            "  { \"CRN\": 36484, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Huang, John\", \"Grades\": [2, 6, 7, 5, 6, 4, 2, 1, 3, 0, 1, 0, 0, 0], \"Average\": 3.13 },\n" +
            "  { \"CRN\": 36486, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Ledvora, Joseph A\", \"Grades\": [2, 4, 5, 5, 5, 7, 5, 1, 1, 0, 1, 0, 1, 0], \"Average\": 2.95 },\n" +
            "  { \"CRN\": 36487, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"AD7\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Lin, Jonathan T\", \"Grades\": [0, 4, 6, 9, 1, 3, 5, 2, 1, 0, 6, 0, 0, 0], \"Average\": 2.77 },\n" +
            "  { \"CRN\": 36489, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"AD8\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Huang, John\", \"Grades\": [1, 1, 10, 8, 1, 6, 3, 2, 2, 0, 0, 0, 1, 0], \"Average\": 2.99 },\n" +
            "  { \"CRN\": 36494, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADA\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Ledvora, Joseph A\", \"Grades\": [1, 7, 4, 6, 4, 3, 2, 4, 2, 0, 4, 0, 0, 0], \"Average\": 2.88 },\n" +
            "  { \"CRN\": 36496, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADB\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Lin, Jonathan T\", \"Grades\": [1, 4, 5, 10, 3, 7, 5, 2, 1, 0, 1, 0, 0, 0], \"Average\": 3.02 },\n" +
            "  { \"CRN\": 36499, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADC\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"McNamara, Sean M\", \"Grades\": [0, 6, 4, 4, 7, 4, 4, 2, 3, 0, 0, 0, 0, 1], \"Average\": 3 },\n" +
            "  { \"CRN\": 36502, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADD\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Stefani, Marisa A\", \"Grades\": [0, 2, 3, 1, 6, 6, 2, 4, 5, 0, 5, 0, 1, 0], \"Average\": 2.35 },\n" +
            "  { \"CRN\": 36506, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADE\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Moradi, Maseeh\", \"Grades\": [0, 2, 5, 9, 8, 7, 2, 1, 2, 0, 2, 0, 0, 0], \"Average\": 2.92 },\n" +
            "  { \"CRN\": 36509, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADF\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Smigielski, Michael A\", \"Grades\": [0, 1, 3, 8, 5, 14, 3, 1, 3, 0, 1, 0, 1, 0], \"Average\": 2.73 },\n" +
            "  { \"CRN\": 36516, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADH\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"McNamara, Sean M\", \"Grades\": [1, 5, 7, 10, 6, 3, 2, 0, 2, 0, 2, 0, 1, 0], \"Average\": 3.05 },\n" +
            "  { \"CRN\": 36522, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADI\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Moradi, Maseeh\", \"Grades\": [1, 4, 8, 11, 4, 5, 2, 1, 2, 0, 0, 0, 0, 0], \"Average\": 3.19 },\n" +
            "  { \"CRN\": 36526, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADJ\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Zhao, Weiran\", \"Grades\": [0, 2, 5, 6, 6, 9, 6, 2, 1, 0, 2, 0, 0, 0], \"Average\": 2.82 },\n" +
            "  { \"CRN\": 36536, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADL\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Stefani, Marisa A\", \"Grades\": [1, 4, 9, 8, 6, 3, 2, 0, 3, 0, 2, 0, 0, 0], \"Average\": 3.09 },\n" +
            "  { \"CRN\": 36538, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADM\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Smigielski, Michael A\", \"Grades\": [0, 4, 9, 4, 3, 7, 4, 1, 1, 0, 1, 0, 0, 1], \"Average\": 3.06 },\n" +
            "  { \"CRN\": 36543, \"Subject\": \"ACCY\", \"Number\": 201, \"Title\": \"Accounting and Accountancy I\", \"Section\": \"ADN\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Zhao, Weiran\", \"Grades\": [1, 3, 8, 14, 3, 2, 2, 2, 2, 0, 1, 0, 1, 0], \"Average\": 3.06 },\n" +
            "  { \"CRN\": 36552, \"Subject\": \"ACCY\", \"Number\": 202, \"Title\": \"Accounting and Accountancy II\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Le, Nga H\", \"Grades\": [1, 9, 6, 4, 10, 1, 3, 3, 3, 0, 1, 0, 1, 0], \"Average\": 3.02 },\n" +
            "  { \"CRN\": 36556, \"Subject\": \"ACCY\", \"Number\": 202, \"Title\": \"Accounting and Accountancy II\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Le, Nga H\", \"Grades\": [3, 13, 6, 11, 5, 1, 1, 2, 3, 0, 1, 0, 0, 0], \"Average\": 3.32 },\n" +
            "  { \"CRN\": 36560, \"Subject\": \"ACCY\", \"Number\": 202, \"Title\": \"Accounting and Accountancy II\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Mulvihill, Brian J\", \"Grades\": [3, 20, 6, 8, 3, 3, 1, 3, 0, 0, 0, 0, 0, 0], \"Average\": 3.53 },\n" +
            "  { \"CRN\": 36565, \"Subject\": \"ACCY\", \"Number\": 202, \"Title\": \"Accounting and Accountancy II\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Mulvihill, Brian J\", \"Grades\": [2, 18, 11, 6, 5, 2, 1, 1, 1, 0, 0, 0, 0, 0], \"Average\": 3.55 },\n" +
            "  { \"CRN\": 36568, \"Subject\": \"ACCY\", \"Number\": 202, \"Title\": \"Accounting and Accountancy II\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Ye, Xinxin\", \"Grades\": [2, 12, 7, 10, 5, 4, 4, 2, 0, 0, 1, 0, 0, 0], \"Average\": 3.3 },\n" +
            "  { \"CRN\": 36574, \"Subject\": \"ACCY\", \"Number\": 202, \"Title\": \"Accounting and Accountancy II\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Ye, Xinxin\", \"Grades\": [1, 8, 7, 11, 10, 4, 3, 2, 1, 0, 0, 0, 0, 1], \"Average\": 3.23 },\n" +
            "  { \"CRN\": 36577, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE1\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Asante Appiah, Bright\", \"Grades\": [2, 4, 11, 9, 2, 6, 4, 1, 0, 0, 0, 0, 0, 0], \"Average\": 3.27 },\n" +
            "  { \"CRN\": 36581, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE2\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Asante Appiah, Bright\", \"Grades\": [2, 10, 14, 2, 5, 5, 1, 0, 0, 0, 1, 0, 0, 0], \"Average\": 3.44 },\n" +
            "  { \"CRN\": 36584, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE3\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Zhou, Hailan\", \"Grades\": [1, 3, 11, 10, 7, 2, 2, 0, 0, 0, 1, 0, 1, 0], \"Average\": 3.2 },\n" +
            "  { \"CRN\": 36587, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE4\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Jackson, Kevin\", \"Grades\": [0, 7, 9, 8, 10, 2, 0, 1, 1, 0, 1, 0, 0, 0], \"Average\": 3.27 },\n" +
            "  { \"CRN\": 36590, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE5\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Finnegan, Thomas\", \"Grades\": [1, 4, 14, 11, 4, 1, 4, 0, 0, 0, 0, 0, 0, 1], \"Average\": 3.38 },\n" +
            "  { \"CRN\": 36592, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE6\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Zhou, Hailan\", \"Grades\": [3, 5, 12, 5, 9, 2, 2, 1, 0, 0, 0, 0, 1, 0], \"Average\": 3.29 },\n" +
            "  { \"CRN\": 36598, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AE8\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Jackson, Kevin\", \"Grades\": [0, 5, 13, 5, 8, 5, 0, 1, 0, 0, 0, 0, 2, 0], \"Average\": 3.17 },\n" +
            "  { \"CRN\": 49255, \"Subject\": \"ACCY\", \"Number\": 301, \"Title\": \"Atg Measurement & Disclosure\", \"Section\": \"AEB\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Asante Appiah, Bright\", \"Grades\": [1, 4, 13, 9, 5, 6, 0, 0, 1, 0, 0, 0, 1, 0], \"Average\": 3.26 },\n" +
            "  ]";
    private final String ONLY_AAS =
            " [{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 },\n" +
                    "  { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 11, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
                    "  { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [2, 24, 1, 2, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.75 },\n" +
                    "  { \"CRN\": 51248, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [7, 16, 4, 4, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.71 },\n" +
                    "  { \"CRN\": 51249, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [4, 12, 6, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.7 },\n" +
                    "  { \"CRN\": 51932, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [12, 14, 2, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.83 },\n" +
                    "  { \"CRN\": 58092, \"Subject\": \"AAS\", \"Number\": 287, \"Title\": \"Food and Asian Americans\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Manalansan, Martin F\", \"Grades\": [0, 23, 2, 4, 2, 3, 2, 0, 0, 0, 0, 0, 0, 1], \"Average\": 3.65 }]";

    private final String ONLY_SINGH_VIJAY_INSTRUCTOR =
            "[{ \"CRN\": 57478, \"Subject\": \"ABE\", \"Number\": 488, \"Title\": \"Bioprocessing Biomass for Fuel\", \"Section\": \"VS\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Singh, Vijay\", \"Grades\": [0, 5, 9, 6, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0], \"Average\": 3.47 }]";
    private final String COURSES_BY_SAME_INSTRUCTOR_STR =
            "[{ \"CRN\": 30460, \"Subject\": \"STAT\", \"Number\": 400, \"Title\": \"Statistics and Probability I\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Stepanov, Alexey G\", \"Grades\": [23, 9, 9, 4, 8, 3, 1, 2, 3, 0, 3, 0, 3, 1], \"Average\": 3.25 },\n" +
                    " { \"CRN\": 33498, \"Subject\": \"STAT\", \"Number\": 410, \"Title\": \"Statistics and Probability II\", \"Section\": \"G1G\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Stepanov, Alexey G\", \"Grades\": [12, 6, 4, 6, 11, 4, 0, 2, 2, 0, 2, 2, 2, 2], \"Average\": 3.11 }" +
                    "]";
    // Courses with number of students from 50 to 130
    private final String COURSES_OF_STUDENTS_IN_RANGE = "[ " +
            "{ \"CRN\": 31263, \"Subject\": \"ABE\", \"Number\": 100, \"Title\": \"Intro Agric & Biological Engrg\", \"Section\": \"B\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Green, Angela R\", \"Grades\": [0, 23, 0, 0, 22, 0, 0, 5, 0, 0, 3, 0, 2, 0], \"Average\": 3.11 }," +
            "{ \"CRN\": 58927, \"Subject\": \"ABE\", \"Number\": 223, \"Title\": \"ABE Principles: Machine Syst\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Grift, Tony E\", \"Grades\": [8, 36, 3, 2, 3, 0, 0, 0, 0, 1, 1, 0, 0, 0], \"Average\": 3.8 }," +
            "{ \"CRN\": 58931, \"Subject\": \"ABE\", \"Number\": 224, \"Title\": \"ABE Principles: Soil & Water\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Kalita, Prasanta K\", \"Grades\": [5, 32, 5, 1, 7, 0, 2, 3, 2, 0, 3, 0, 0, 0], \"Average\": 3.46 }," +
            "{ \"CRN\": 36669, \"Subject\": \"ABE\", \"Number\": 430, \"Title\": \"Project Management\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120148, \"Instructor\": \"Schideman, Lance C\", \"Grades\": [1, 24, 11, 29, 48, 5, 4, 7, 1, 0, 0, 0, 0, 0], \"Average\": 3.23 }]";

    private final String COURSE_OF_SAME_TITLE_STR =
            " [{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [2, 7, 4, 4, 5, 1, 2, 2, 0, 0, 0, 1, 2, 0], \"Average\": 3.03 }, //30 students\n" +
                    "  { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [6, 19, 2, 2, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0], \"Average\": 3.69 }, //33 students\n" +
                    "  { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Peralta, Christine N\", \"Grades\": [2, 15, 6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }, //29 students\n" +
                    "  { \"CRN\": 51248, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Peralta, Christine N\", \"Grades\": [1, 14, 7, 5, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0], \"Average\": 3.58 }, //31 students\n" +
                    "  { \"CRN\": 51249, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Lee, Sang S\", \"Grades\": [3, 11, 4, 3, 6, 2, 0, 1, 1, 0, 0, 1, 0, 0], \"Average\": 3.39 }, //32 students\n" +
                    "  { \"CRN\": 51932, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Lee, Sang S\", \"Grades\": [2, 9, 4, 5, 4, 2, 0, 4, 0, 0, 0, 0, 1, 0], \"Average\": 3.25 }]";
    // courses with GPA (3.20-3.72)
    private final String COURSES_GPA_RANGE_EX =
            "[ { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [6, 19, 2, 2, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0], \"Average\": 3.69 }, \n" +
                    "  { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Peralta, Christine N\", \"Grades\": [2, 15, 6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }, \n" +
                    "  { \"CRN\": 51248, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Peralta, Christine N\", \"Grades\": [1, 14, 7, 5, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0], \"Average\": 3.58 }, \n" +
                    "  { \"CRN\": 51249, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Lee, Sang S\", \"Grades\": [3, 11, 4, 3, 6, 2, 0, 1, 1, 0, 0, 1, 0, 0], \"Average\": 3.39 }, \n" +
                    "  { \"CRN\": 51932, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Lee, Sang S\", \"Grades\": [2, 9, 4, 5, 4, 2, 0, 4, 0, 0, 0, 0, 1, 0], \"Average\": 3.25 }, \n" +
                    "  { \"CRN\": 58092, \"Subject\": \"AAS\", \"Number\": 287, \"Title\": \"Food and Asian Americans\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120148, \"Instructor\": \"Manalansan, Martin F\", \"Grades\": [7, 15, 2, 3, 3, 1, 1, 0, 0, 0, 0, 0, 1, 0], \"Average\": 3.62 }, \n" +
                    "  { \"CRN\": 58931, \"Subject\": \"ABE\", \"Number\": 224, \"Title\": \"ABE Principles: Soil & Water\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Kalita, Prasanta K\", \"Grades\": [5, 32, 5, 1, 7, 0, 2, 3, 2, 0, 3, 0, 0, 0], \"Average\": 3.46 }," +
                    "  { \"CRN\": 36669, \"Subject\": \"ABE\", \"Number\": 430, \"Title\": \"Project Management\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120148, \"Instructor\": \"Schideman, Lance C\", \"Grades\": [1, 24, 11, 29, 48, 5, 4, 7, 1, 0, 0, 0, 0, 0], \"Average\": 3.23 }, \n" +
                    "  { \"CRN\": 31280, \"Subject\": \"ABE\", \"Number\": 466, \"Title\": \"Engineering Off-Road Vehicles\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Hansen, Alan C\", \"Grades\": [0, 4, 11, 8, 6, 2, 1, 2, 0, 0, 0, 0, 1, 0], \"Average\": 3.22 }, \n" +
                    "  { \"CRN\": 57478, \"Subject\": \"ABE\", \"Number\": 488, \"Title\": \"Bioprocessing Biomass for Fuel\", \"Section\": \"VS\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Singh, Vijay\", \"Grades\": [3, 4, 6, 7, 3, 4, 0, 1, 1, 0, 0, 0, 0, 1], \"Average\": 3.33 }]";
    private final String FIRST_LINE_OF_FALL_2014 =
            "{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [2, 7, 4, 4, 5, 1, 2, 2, 0, 0, 0, 1, 2, 0], \"Average\": 3.03 }";
    private final String ONLY_STAT_STR = "[{ \"CRN\": 30452, \"Subject\": \"STAT\", \"Number\": 100, \"Title\": \"Statistics\", \"Section\": \"K1\", \"Type\": \"LCD\", \"Term\": 120145, \"Instructor\": \"Eisinger, Robert D\", \"Grades\": [5, 7, 3, 1, 2, 2, 2, 1, 5, 1, 2, 0, 0, 0], \"Average\": 2.97 },\n" +
            "  { \"CRN\": 36918, \"Subject\": \"STAT\", \"Number\": 100, \"Title\": \"Statistics\", \"Section\": \"ONL\", \"Type\": \"ONL\", \"Term\": 120145, \"Instructor\": \"Ye, Sangbeak\", \"Grades\": [7, 35, 7, 8, 4, 7, 2, 4, 2, 0, 2, 2, 11, 2], \"Average\": 2.97 },\n" +
            "  { \"CRN\": 30460, \"Subject\": \"STAT\", \"Number\": 400, \"Title\": \"Statistics and Probability I\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Stepanov, Alexey G\", \"Grades\": [23, 9, 9, 4, 8, 3, 1, 2, 3, 0, 3, 0, 3, 1], \"Average\": 3.25 },\n" +
            "  { \"CRN\": 33498, \"Subject\": \"STAT\", \"Number\": 410, \"Title\": \"Statistics and Probability II\", \"Section\": \"G1G\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Stepanov, Alexey G\", \"Grades\": [12, 6, 4, 6, 11, 4, 0, 2, 2, 0, 2, 2, 2, 2], \"Average\": 3.11 },\n" +
            "  { \"CRN\": 34304, \"Subject\": \"STAT\", \"Number\": 440, \"Title\": \"Statistical Data Management\", \"Section\": \"N1G\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Unger, David\", \"Grades\": [2, 11, 5, 3, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0], \"Average\": 3.59 },\n" +
            "  { \"CRN\": 37298, \"Subject\": \"STAT\", \"Number\": 448, \"Title\": \"Advanced Data Analysis\", \"Section\": \"S1\", \"Type\": \"LCD\", \"Term\": 120145, \"Instructor\": \"Glosemeyer, Darren W\", \"Grades\": [0, 11, 7, 4, 2, 4, 0, 0, 0, 0, 0, 0, 2, 0], \"Average\": 3.32 }]";
    // Using "Number : 386" as an example
    private final String COURSES_IN_NUM_RANGE_STR = "[{ \"CRN\": 50021, \"Subject\": \"PS\", \"Number\": 386, \"Title\": \"International Law\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Diehl, Paul F\", \"Grades\": [0, 4, 5, 5, 20, 5, 6, 5, 3, 1, 1, 0, 0, 0], \"Average\": 2.83 }]";
    // Second example to the one above
    private final String COURSE_OF_SAME_TITLE_STR_2 =
            " [{ \"CRN\": 31389, \"Subject\": \"SPED\", \"Number\": 117, \"Title\": \"The Culture of Disability\", \"Section\": \"A\", \"Type\": \"ONL\", \"Term\": 120145, \"Instructor\": \"Bentz, Johnell L\", \"Grades\": [0, 22, 0, 0, 5, 0, 0, 2, 0, 0, 1, 0, 2, 0], \"Average\": 3.38 },\n" +
                    "  { \"CRN\": 36271, \"Subject\": \"SPED\", \"Number\": 322, \"Title\": \"Intro Intellectual Disability\", \"Section\": \"A\", \"Type\": \"ONL\", \"Term\": 120145, \"Instructor\": \"Wolowiec-Fisher, Kimberly\", \"Grades\": [3, 13, 0, 0, 8, 0, 0, 5, 0, 0, 1, 0, 2, 0], \"Average\": 3.09 },\n" +
                    "  { \"CRN\": 30452, \"Subject\": \"STAT\", \"Number\": 100, \"Title\": \"Statistics\", \"Section\": \"K1\", \"Type\": \"LCD\", \"Term\": 120145, \"Instructor\": \"Eisinger, Robert D\", \"Grades\": [5, 7, 3, 1, 2, 2, 2, 1, 5, 1, 2, 0, 0, 0], \"Average\": 2.97 },\n" +
                    "  { \"CRN\": 36918, \"Subject\": \"STAT\", \"Number\": 100, \"Title\": \"Statistics\", \"Section\": \"ONL\", \"Type\": \"ONL\", \"Term\": 120145, \"Instructor\": \"Ye, Sangbeak\", \"Grades\": [7, 35, 7, 8, 4, 7, 2, 4, 2, 0, 2, 2, 11, 2], \"Average\": 2.97 },\n" +
                    "  { \"CRN\": 30460, \"Subject\": \"STAT\", \"Number\": 400, \"Title\": \"Statistics and Probability I\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Stepanov, Alexey G\", \"Grades\": [23, 9, 9, 4, 8, 3, 1, 2, 3, 0, 3, 0, 3, 1], \"Average\": 3.25 },\n" +
                    "  { \"CRN\": 33498, \"Subject\": \"STAT\", \"Number\": 410, \"Title\": \"Statistics and Probability II\", \"Section\": \"G1G\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Stepanov, Alexey G\", \"Grades\": [12, 6, 4, 6, 11, 4, 0, 2, 2, 0, 2, 2, 2, 2], \"Average\": 3.11 },\n" +
                    "  { \"CRN\": 34304, \"Subject\": \"STAT\", \"Number\": 440, \"Title\": \"Statistical Data Management\", \"Section\": \"N1G\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Unger, David\", \"Grades\": [2, 11, 5, 3, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0], \"Average\": 3.59 },\n" +
                    "  { \"CRN\": 37298, \"Subject\": \"STAT\", \"Number\": 448, \"Title\": \"Advanced Data Analysis\", \"Section\": \"S1\", \"Type\": \"LCD\", \"Term\": 120145, \"Instructor\": \"Glosemeyer, Darren W\", \"Grades\": [0, 11, 7, 4, 2, 4, 0, 0, 0, 0, 0, 0, 2, 0], \"Average\": 3.32 },\n" +
                    "  { \"CRN\": 30463, \"Subject\": \"TAM\", \"Number\": 212, \"Title\": \"Introductory Dynamics\", \"Section\": \"C\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Sanders, John W\", \"Grades\": [9, 7, 2, 10, 5, 3, 1, 2, 2, 0, 0, 0, 0, 0], \"Average\": 3.35 },\n" +
                    "  { \"CRN\": 30464, \"Subject\": \"TAM\", \"Number\": 251, \"Title\": \"Introductory Solid Mechanics\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120145, \"Instructor\": \"Rajarathinam, Robin Jepht\", \"Grades\": [2, 9, 5, 6, 3, 2, 1, 1, 1, 0, 1, 0, 1, 0], \"Average\": 3.24 },\n" +
                    "  { \"CRN\": 30468, \"Subject\": \"TAM\", \"Number\": 335, \"Title\": \"Introductory Fluid Mechanics\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120145, \"Instructor\": \"Johnson, Blake E\", \"Grades\": [2, 2, 1, 2, 10, 4, 4, 7, 4, 0, 2, 1, 5, 0], \"Average\": 2.27 },\n" +
                    "  { \"CRN\": 30486, \"Subject\": \"THEA\", \"Number\": 101, \"Title\": \"Introduction to Theatre Arts\", \"Section\": \"A\", \"Type\": \"ONL\", \"Term\": 120145, \"Instructor\": \"Morrissette, Jason W\", \"Grades\": [9, 17, 7, 0, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
                    "  { \"CRN\": 37856, \"Subject\": \"THEA\", \"Number\": 101, \"Title\": \"Introduction to Theatre Arts\", \"Section\": \"S1\", \"Type\": \"ONL\", \"Term\": 120145, \"Instructor\": \"Morrissette, Jason W\", \"Grades\": [12, 12, 3, 4, 3, 3, 1, 1, 1, 0, 0, 0, 2, 1], \"Average\": 3.41 }" +
                    "]";
    private static Course firstF13Course;
    private static Course firstF14Course;
    private static Course[] smallCourseArr;
    private static Course[] biggerCourseArr;
    private static Course[] subjectAASArr;
    private static Course[] instructorSinghArr;
    private static Course[] coursesInNumRangeArr;
    private static Course[] numStudentsInRangeArray;
    private static Course[] coursesOfSameTitle;
    private static Course[] coursesOfGPARangeArray;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        firstF13Course = gson.fromJson(FIRST_COURSE, Course.class);
        smallCourseArr = gson.fromJson(FIRST_THREE_COURSES, Course[].class);
        biggerCourseArr = gson.fromJson(MORE_COURSES, Course[].class);
    }

    // STEP 1: Parsing the JSON ****************************************************************************************

    @Test
    public void getSubjectOfOneLine() {
        assertEquals("AAS", firstF13Course.getSubject());
    }

    @Test
    public void getInstructorNameOfOneLine() {
        assertEquals("Arai, Sayuri", firstF13Course.getInstructor());
    }

    @Test
    public void getCRNOfOneLine() {
        assertEquals(41758, firstF13Course.getCRN());
    }

    @Test
    public void getNumberOfOneLine() {
        assertEquals(100, firstF13Course.getNumber());
    }

    @Test
    public void getAverageOfOneLine() {
        assertEquals(3.72, firstF13Course.getAverage(), 0.001);
    }

    @Test
    public void getGradesOfOneLine() {
        assertEquals(6, firstF13Course.getGrades()[0]);
    }

    @Test
    public void getCRNOfArray() {
        assertEquals(47102, smallCourseArr[2].getCRN());
    }

    @Test
    public void getSubjectOfArray() {
        assertEquals("AAS", smallCourseArr[2].getSubject());
    }

    // STEP 2: Loading JSON from Files *********************************************************************************

    @Test
    public void turnFilesToArrayListFirstCourseTest() {
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(firstF13Course, CourseGrades.filesToList(fileList).get(0));
    }

    @Test
    public void turnFilesToArrayListSmallRandomCourseTest() {
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(smallCourseArr[2], CourseGrades.filesToList(fileList).get(2));
    }

    @Test
    public void turnFilesToArrayListBiggerRandomCourseTest() {
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(biggerCourseArr[40], CourseGrades.filesToList(fileList).get(40));
    }

    // Testing first line of the second file
    @Test
    public void turnFilesToArrayListNextFileCourseTest() {
        Gson localGson = new Gson();
        firstF14Course = localGson.fromJson(FIRST_LINE_OF_FALL_2014, Course.class);
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(firstF14Course, CourseGrades.filesToList(fileList).get(2586));
    }

    // Step 3: Filtering Methods ***************************************************************************************

    // Testing if subject is "AAS" and see if output is all "AAS" courses
    @Test
    public void getSubjectCoursesTest() {
        Gson localGson = new Gson();
        subjectAASArr = localGson.fromJson(ONLY_AAS, Course[].class);
        List<String> fall13CoursesInStr = new ArrayList<>();
        fall13CoursesInStr.add("Fall2013.json");
        List<Course> fall13Courses = CourseGrades.filesToList(fall13CoursesInStr);
        String subject = "AAS";
        List<Course> onlySubjectClasses = new ArrayList<>(Arrays.asList(subjectAASArr));
        assertEquals(onlySubjectClasses, CourseGrades.getSubjectCourses(fall13Courses, subject));
    }

    // Test for STAT subject which should take all of TestStat.json
    @Test
    public void getSubjectCoursesAllListTest() {
        Gson localGson = new Gson();
        Course[] onlyStatArray = localGson.fromJson(ONLY_STAT_STR, Course[].class);
        List<String> testStatStr = new ArrayList<>();
        testStatStr.add("TestStat.json");
        List<Course> testStatList = CourseGrades.filesToList(testStatStr);
        String subject = "STAT";
        List<Course> onlySubjectClasses = new ArrayList<>(Arrays.asList(onlyStatArray));
        assertEquals(onlySubjectClasses, CourseGrades.getSubjectCourses(testStatList, subject));
    }

    @Test
    public void getInstructorCoursesOneTest() {
        Gson localGson = new Gson();
        instructorSinghArr = localGson.fromJson(ONLY_SINGH_VIJAY_INSTRUCTOR, Course[].class);
        List<String> fall13CoursesInStr = new ArrayList<>();
        fall13CoursesInStr.add("Fall2013.json");
        List<Course> fall13Courses = CourseGrades.filesToList(fall13CoursesInStr);
        String instructorName = "Singh, Vijay";
        List<Course> onlyInstructorClasses = new ArrayList<>(Arrays.asList(instructorSinghArr));
        assertEquals(onlyInstructorClasses,
                CourseGrades.getInstructorCourses(fall13Courses, instructorName));
    }

    @Test
    public void getInstructorCoursesManyTest() {
        Gson localGson = new Gson();
        Course[] onlyStepanovArray = localGson.fromJson(COURSES_BY_SAME_INSTRUCTOR_STR, Course[].class);
        List<String> testCourses2Str = new ArrayList<>();
        testCourses2Str.add("TestCourses2.json");
        List<Course> testCourses2 = CourseGrades.filesToList(testCourses2Str);
        String instructorName = "Stepanov, Alexey G";
        List<Course> onlyInstructorClasses = new ArrayList<>(Arrays.asList(onlyStepanovArray));
        assertEquals(onlyInstructorClasses,
                CourseGrades.getInstructorCourses(testCourses2, instructorName));
    }

    @Test
    public void getInstructorCoursesEmptyTest() {
        List<String> testCourses2Str = new ArrayList<>();
        testCourses2Str.add("TestCourses2.json");
        List<Course> testCourses2 = CourseGrades.filesToList(testCourses2Str);
        String instructorName = "";
        List<Course> onlyInstructorClasses = new ArrayList<>();
        assertEquals(onlyInstructorClasses,
                CourseGrades.getInstructorCourses(testCourses2, instructorName));
    }


    @Test
    public void getNumCoursesInRangeSingleTest() {
        Gson localGson = new Gson();
        coursesInNumRangeArr = localGson.fromJson(COURSES_IN_NUM_RANGE_STR, Course[].class);
        List<String> fall13CoursesInStr = new ArrayList<>();
        fall13CoursesInStr.add("Fall2013.json");
        List<Course> fall13Courses = CourseGrades.filesToList(fall13CoursesInStr);
        int fromRange = 386;
        int toRange = 386;
        List<Course> coursesWithinRangeList = new ArrayList<>(Arrays.asList(coursesInNumRangeArr));
        assertEquals(coursesWithinRangeList,
                CourseGrades.getNumberCoursesInRange(fall13Courses, fromRange, toRange));
    }

    @Test
    public void getNumCoursesInRangeManyTest() {
        Gson localGson = new Gson();
        Course[] courseInRangeArrayEx = localGson.fromJson(COURSE_OF_SAME_TITLE_STR_2, Course[].class);
        List<String> testCoursesStr = new ArrayList<>();
        testCoursesStr.add("TestCourses2.json");
        List<Course> fall13Courses = CourseGrades.filesToList(testCoursesStr);
        int fromRange = 100;
        int toRange = 500;
        List<Course> coursesWithinRangeList = new ArrayList<>(Arrays.asList(courseInRangeArrayEx));
        assertEquals(coursesWithinRangeList,
                CourseGrades.getNumberCoursesInRange(fall13Courses, fromRange, toRange));
    }

    @Test
    public void getNumCoursesInRangeEmptyTest() {
        List<String> testCourses2Str = new ArrayList<>();
        testCourses2Str.add("TestCourses2.json");
        List<Course> testCourses2 = CourseGrades.filesToList(testCourses2Str);
        int fromRange = 0;
        int toRange = 2;
        List<Course> coursesWithinRangeList = new ArrayList<>();
        assertEquals(coursesWithinRangeList,
                CourseGrades.getNumberCoursesInRange(testCourses2, fromRange, toRange));
    }

    @Test
    public void getNumStudentsWithinRangeMaxTest() {
        Gson localGson = new Gson();
        numStudentsInRangeArray = localGson.fromJson(COURSES_OF_STUDENTS_IN_RANGE, Course[].class);
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        List<Course> coursesNumStudentRange = new ArrayList<>(Arrays.asList(numStudentsInRangeArray));
        int fromRange = 50;
        int toRange = 130;
        assertEquals(coursesNumStudentRange,
                CourseGrades.getAmountStudentsWithinRange(testJSONFile, fromRange, toRange));
    }

    @Test
    public void getNumStudentsWithinRangeEmptyTest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        List<Course> coursesNumStudentRange = new ArrayList<>();
        int fromRange = 500;
        int toRange = 600;
        assertEquals(coursesNumStudentRange,
                CourseGrades.getAmountStudentsWithinRange(testJSONFile, fromRange, toRange));
    }

    // Test to make sure all courses have the title : "Intro Asian American Studies"
    @Test
    public void getTitleCoursesManyTest() {
        Gson localGson = new Gson();
        coursesOfSameTitle = localGson.fromJson(COURSE_OF_SAME_TITLE_STR, Course[].class);
        List<String> testCoursesInStr = new ArrayList<>();
        testCoursesInStr.add("TestCourses.json");
        List<Course> testCourses = CourseGrades.filesToList(testCoursesInStr);
        List<Course> testCourseOfSameTitle = new ArrayList<>(Arrays.asList(coursesOfSameTitle));
        String title = "Intro Asian American Studies";
        assertEquals(testCourseOfSameTitle, CourseGrades.getTitleCourses(testCourses, title));
    }

    @Test
    public void getTitleCoursesEmptyTest() {
        List<String> testCoursesInStr = new ArrayList<>();
        testCoursesInStr.add("TestCourses.json");
        List<Course> testCourses = CourseGrades.filesToList(testCoursesInStr);
        List<Course> emptyList = new ArrayList<>();
        String title = "false subject";
        assertEquals(emptyList, CourseGrades.getTitleCourses(testCourses, title));
    }

    @Test
    public void getNumGPAWithinRangeMaxTest() {
        Gson localGson = new Gson();
        coursesOfGPARangeArray = localGson.fromJson(COURSES_GPA_RANGE_EX, Course[].class);
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        List<Course> coursesGPARange = new ArrayList<>(Arrays.asList(coursesOfGPARangeArray));
        double fromRange = 3.20;
        double toRange = 3.72;
        assertEquals(coursesGPARange,
                CourseGrades.getAmountOfStudentsGPAWithinRange(testJSONFile, fromRange, toRange));
    }

    @Test
    public void getNumGPAWithinRangeEmptyTest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        List<Course> emptyList = new ArrayList<>();
        double fromRange = 5.00;
        double toRange = 6.00;
        assertEquals(emptyList,
                CourseGrades.getAmountOfStudentsGPAWithinRange(testJSONFile, fromRange, toRange));
    }

    // STEP 4: Aggregation Methods *************************************************************************************

    // Test to find total number of students in TestCourses.json
    @Test
    public void getNumStudentsOfCourseTest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        int totalNumStudents = 606;
        assertEquals(totalNumStudents, CourseGrades.getAmountStudentsOfCourse(testJSONFile));
    }

    // Test to find total number of students that have a D- to W in TestCourse.json
    @Test
    public void getNumStudentGradeRangeTest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        int numStudentsWithinGradeRange = 11;
        String dMinus = "D-";
        String w = "W";
        assertEquals(numStudentsWithinGradeRange,
                CourseGrades.getAmountOfStudentsWithinGradeRange(testJSONFile, dMinus, w));
    }

    // Test to find total number of students that have a A+ to W in TestCourse.json
    @Test
    public void getNumStudentGradeRangeAllTest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        int numStudentsWithinGradeRange = 606;
        String dMinus = "A+";
        String w = "W";
        assertEquals(numStudentsWithinGradeRange,
                CourseGrades.getAmountOfStudentsWithinGradeRange(testJSONFile, dMinus, w));
    }

    // Test to find the average GPA in TestCourses.json
    @Test
    public void getAverageGPATest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        double avgGPA = 3.38;
        assertEquals(avgGPA, CourseGrades.getAverageGPA(testJSONFile), 0.01);
    }

}