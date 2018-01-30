import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CourseGradesTest {

    private static final String firstCourse = "{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": " +
            "\"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": " +
            "\"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }";

    private final String firstThreeCourses = "[\n" +
            "    { \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 },\n" +
            "    { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 11, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
            "    { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [2, 24, 1, 2, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.75 },\n" +
            "    ]";
    private final String moreCourses = "[\n" +
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
    private final String onlyAAS = "[{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 },\n" +
            "  { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Arai, Sayuri\", \"Grades\": [6, 11, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.64 },\n" +
            "  { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [2, 24, 1, 2, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.75 },\n" +
            "  { \"CRN\": 51248, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Davis, Thomas E\", \"Grades\": [7, 16, 4, 4, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.71 },\n" +
            "  { \"CRN\": 51249, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [4, 12, 6, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.7 },\n" +
            "  { \"CRN\": 51932, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120138, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [12, 14, 2, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.83 },\n" +
            "  { \"CRN\": 58092, \"Subject\": \"AAS\", \"Number\": 287, \"Title\": \"Food and Asian Americans\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Manalansan, Martin F\", \"Grades\": [0, 23, 2, 4, 2, 3, 2, 0, 0, 0, 0, 0, 0, 1], \"Average\": 3.65 }]";

    private final String onlySinghVijayInstructor = "[{ \"CRN\": 57478, \"Subject\": \"ABE\", \"Number\": 488, \"Title\": \"Bioprocessing Biomass for Fuel\", \"Section\": \"VS\", \"Type\": \"LEC\", \"Term\": 120138, \"Instructor\": \"Singh, Vijay\", \"Grades\": [0, 5, 9, 6, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0], \"Average\": 3.47 }]";

    // Courses with number of students from 50 to 130
    private final String numStudentsInRange = "[ " +
            "{ \"CRN\": 31263, \"Subject\": \"ABE\", \"Number\": 100, \"Title\": \"Intro Agric & Biological Engrg\", \"Section\": \"B\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Green, Angela R\", \"Grades\": [0, 23, 0, 0, 22, 0, 0, 5, 0, 0, 3, 0, 2, 0], \"Average\": 3.11 }," +
            "{ \"CRN\": 58927, \"Subject\": \"ABE\", \"Number\": 223, \"Title\": \"ABE Principles: Machine Syst\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Grift, Tony E\", \"Grades\": [8, 36, 3, 2, 3, 0, 0, 0, 0, 1, 1, 0, 0, 0], \"Average\": 3.8 }," +
            "{ \"CRN\": 58931, \"Subject\": \"ABE\", \"Number\": 224, \"Title\": \"ABE Principles: Soil & Water\", \"Section\": \"AL1\", \"Type\": \"LEC\", \"Term\": 120148, \"Instructor\": \"Kalita, Prasanta K\", \"Grades\": [5, 32, 5, 1, 7, 0, 2, 3, 2, 0, 3, 0, 0, 0], \"Average\": 3.46 }," +
            "{ \"CRN\": 36669, \"Subject\": \"ABE\", \"Number\": 430, \"Title\": \"Project Management\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120148, \"Instructor\": \"Schideman, Lance C\", \"Grades\": [1, 24, 11, 29, 48, 5, 4, 7, 1, 0, 0, 0, 0, 0], \"Average\": 3.23 }]";
    // Using "Number : 386" as an example
    private final String coursesInRangeEx = "[{ \"CRN\": 50021, \"Subject\": \"PS\", \"Number\": 386, \"Title\": \"International Law\", \"Section\": \"A\", \"Type\": \"LCD\", \"Term\": 120138, \"Instructor\": \"Diehl, Paul F\", \"Grades\": [0, 4, 5, 5, 20, 5, 6, 5, 3, 1, 1, 0, 0, 0], \"Average\": 2.83 }]";

    private final String coursesOfSameTitleInStr = "[{ \"CRN\": 41758, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD1\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [2, 7, 4, 4, 5, 1, 2, 2, 0, 0, 0, 1, 2, 0], \"Average\": 3.03 }, //30 students\n" +
            "  { \"CRN\": 47100, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD2\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Thomas, Merin A\", \"Grades\": [6, 19, 2, 2, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0], \"Average\": 3.69 }, //33 students\n" +
            "  { \"CRN\": 47102, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD3\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Peralta, Christine N\", \"Grades\": [2, 15, 6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0], \"Average\": 3.72 }, //29 students\n" +
            "  { \"CRN\": 51248, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD4\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Peralta, Christine N\", \"Grades\": [1, 14, 7, 5, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0], \"Average\": 3.58 }, //31 students\n" +
            "  { \"CRN\": 51249, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD5\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Lee, Sang S\", \"Grades\": [3, 11, 4, 3, 6, 2, 0, 1, 1, 0, 0, 1, 0, 0], \"Average\": 3.39 }, //32 students\n" +
            "  { \"CRN\": 51932, \"Subject\": \"AAS\", \"Number\": 100, \"Title\": \"Intro Asian American Studies\", \"Section\": \"AD6\", \"Type\": \"DIS\", \"Term\": 120148, \"Instructor\": \"Lee, Sang S\", \"Grades\": [2, 9, 4, 5, 4, 2, 0, 4, 0, 0, 0, 0, 1, 0], \"Average\": 3.25 }]";
    // courses with GPA (3.20-3.72)
    private final String coursesOfGPARange =
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
    private static Course firstJSONLine;
    private static Course[] smallCourseArray;
    private static Course[] biggerCourseArray;
    private static Course[] onlyAASArray;
    private static Course[] onlyVijaySinghArray;
    private static Course[] courseInRangeArrayEx;
    private static Course[] numStudentsInRangeArray;
    private static Course[] coursesOfSameTitle;
    private static Course[] coursesOfGPARangeArray;
    public String fall2013 = Data.getFileContentsAsString(Data.JSON_FILES[0]);
    public String fall2014 = Data.getFileContentsAsString(Data.JSON_FILES[1]);
    public String spring2013 = Data.getFileContentsAsString(Data.JSON_FILES[2]);
    public String spring2014 = Data.getFileContentsAsString(Data.JSON_FILES[3]);
    public String summer2013 = Data.getFileContentsAsString(Data.JSON_FILES[4]);
    public String summer2014 = Data.getFileContentsAsString(Data.JSON_FILES[5]);

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        firstJSONLine = gson.fromJson(firstCourse, Course.class);
        smallCourseArray = gson.fromJson(firstThreeCourses, Course[].class);
        biggerCourseArray = gson.fromJson(moreCourses, Course[].class);
    }

    // STEP 1: Parsing the JSON ****************************************************************************************

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
        assertEquals(3.72, firstJSONLine.getAverage(), 0.001);
    }

    @Test
    public void getGradesOfOneLine() {
        int[] grades = {6, 16, 5, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0};
        assertEquals(grades, firstJSONLine.getGrades());
    }

    @Test
    public void getCRNOfArray() {
        assertEquals(47102, smallCourseArray[2].getCRN());
    }

    @Test
    public void getSubjectOfArray() {
        assertEquals("AAS", smallCourseArray[2].getSubject());
    }

    // STEP 2: Loading JSON from Files *********************************************************************************

    @Test
    public void turnFilesToArrayListFirstCourseTest() {
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(firstJSONLine, CourseGrades.filesToList(fileList).get(0));
    }

    @Test
    public void turnFilesToArrayListSmallRandomCourseTest() {
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(smallCourseArray[2], CourseGrades.filesToList(fileList).get(2));
    }

    @Test
    public void turnFilesToArrayListBiggerRandomCourseTest() {
        List<String> fileList = Data.getJsonFilesAsList();
        assertEquals(biggerCourseArray[40], CourseGrades.filesToList(fileList).get(40));
    }

    // Step 3: Filtering Methods ***************************************************************************************

    // Testing if subject is "AAS" and see if output is all "AAS" courses
    @Test
    public void getSubjectCoursesTest() {
        Gson localgson = new Gson();
        onlyAASArray = localgson.fromJson(onlyAAS, Course[].class);
        List<String> fall13CoursesInStr = new ArrayList<>();
        fall13CoursesInStr.add("Fall2013.json");
        List<Course> fall13Courses = CourseGrades.filesToList(fall13CoursesInStr);
        String subject = "AAS";
        List<Course> onlySubjectClasses = new ArrayList<>(Arrays.asList(onlyAASArray));
        assertEquals(onlySubjectClasses, CourseGrades.getSubjectCourses(fall13Courses, subject));
    }

    @Test
    public void getInstructorCoursesTest() {
        Gson localgson = new Gson();
        onlyVijaySinghArray = localgson.fromJson(onlySinghVijayInstructor, Course[].class);
        List<String> fall13CoursesInStr = new ArrayList<>();
        fall13CoursesInStr.add("Fall2013.json");
        List<Course> fall13Courses = CourseGrades.filesToList(fall13CoursesInStr);
        String instructorName = "Singh, Vijay";
        List<Course> onlyInstructorClasses = new ArrayList<>(Arrays.asList(onlyVijaySinghArray));
        assertEquals(onlyInstructorClasses,
                CourseGrades.getInstructorCourses(fall13Courses, instructorName));
    }

    @Test
    public void getNumCoursesWithinRangeTest() {
        Gson localgson = new Gson();
        courseInRangeArrayEx = localgson.fromJson(coursesInRangeEx, Course[].class);
        List<String> fall13CoursesInStr = new ArrayList<>();
        fall13CoursesInStr.add("Fall2013.json");
        List<Course> fall13Courses = CourseGrades.filesToList(fall13CoursesInStr);
        int fromRange = 386;
        int toRange = 386;
        List<Course> coursesWithinRangeList = new ArrayList<>(Arrays.asList(courseInRangeArrayEx));
        assertEquals(coursesWithinRangeList,
                CourseGrades.getNumCoursesInRange(fall13Courses, fromRange, toRange));
    }

    @Test
    public void getNumStudentCoursesWithinRangeTest() {
        Gson localgson = new Gson();
        numStudentsInRangeArray = localgson.fromJson(numStudentsInRange, Course[].class);
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        List<Course> coursesNumStudentRange = new ArrayList<>(Arrays.asList(numStudentsInRangeArray));
        int fromRange = 50;
        int toRange = 130;
        assertEquals(coursesNumStudentRange,
                CourseGrades.getNumStudentsWithinRange(testJSONFile, fromRange, toRange));
    }

    // Test to make sure all courses have the title : "Intro Asian American Studies"
    @Test
    public void getTitleCourses() {
        Gson localgson = new Gson();
        coursesOfSameTitle = localgson.fromJson(coursesOfSameTitleInStr, Course[].class);
        List<String> testCoursesInStr = new ArrayList<>();
        testCoursesInStr.add("TestCourses.json");
        List<Course> testCourses = CourseGrades.filesToList(testCoursesInStr);
        List<Course> testCourseOfSameTitle = new ArrayList<>(Arrays.asList(coursesOfSameTitle));
        String title = "Intro Asian American Studies";
        assertEquals(testCourseOfSameTitle, CourseGrades.getTitleCourses(testCourses, title));
    }

    @Test
    public void getNumGPAWithinRangeTest() {
        Gson localgson = new Gson();
        coursesOfGPARangeArray = localgson.fromJson(coursesOfGPARange, Course[].class);
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        List<Course> coursesGPARange = new ArrayList<>(Arrays.asList(coursesOfGPARangeArray));
        double fromRange = 3.20;
        double toRange = 3.72;
        assertEquals(coursesGPARange,
                CourseGrades.getNumGPAWithinRange(testJSONFile, fromRange, toRange));
    }

    // STEP 4: Aggregation Methods *************************************************************************************

    // Test to find total number of students in TestCourses.json
    @Test
    public void getNumStudentsOfCourseTest() {
        List<String> testJSONFileInStr = new ArrayList<>();
        testJSONFileInStr.add("TestCourses.json");
        List<Course> testJSONFile = CourseGrades.filesToList(testJSONFileInStr);
        int totalNumStudents = 606;
        assertEquals(totalNumStudents, CourseGrades.getNumStudentsOfCourse(testJSONFile));
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
        assertEquals(numStudentsWithinGradeRange, CourseGrades.getNumStudentGradeRange(testJSONFile, dMinus, w));
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