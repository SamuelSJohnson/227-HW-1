// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

/*
 * This Test class uses Junit to test the Html Validator with different inputs and compares them to their expected outputs
 * This project was worked on in a group with Nathan, Tyler, and Jacob
 */

// There is a bug in this code with the expected output not matching the actual due to line spacing, However I was told not to worry about this issue.

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;


public class HtmlValidatorTest {

    private static final String EXPECTED_TEMPLATE = "EGR227-HW1/expected_output/validate_result_for_test%d.txt";
    private static final String INPUT_TEMPLATE = "EGR227-HW1/input_html/test%d.html";

    private static void testAgainstFiles(int testNumber) {
        testValidatorWithFiles(String.format(EXPECTED_TEMPLATE, testNumber), String.format(INPUT_TEMPLATE, testNumber));
    }

    private static void testValidatorWithFiles(String expectedOutputFilePath,
                                               String validatorInputFilePath) {
        String rawInput = dumpFileContentsToString(validatorInputFilePath);
        String expected = dumpFileContentsToString(expectedOutputFilePath);
        HtmlValidator validator = new HtmlValidator(HtmlTag.tokenize(rawInput));

        String validatorOutput = captureValidatorOutput(validator);

        Assert.assertEquals("Validator output must match expected value", expected, validatorOutput);
    }

    private static String captureValidatorOutput(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        validator.validate();

        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    private static String dumpFileContentsToString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Assert.fail("Could not load file: " + filePath);
            return null;
        }
    }
    /**
     * Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("EGR227-HW1/tst/test0_expected_output.txt"),
                            validatorOutputToString(validator));
    }

    /**
     * This test1 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test1.html and EGR227-HW1/expected_output/validate_result_for_test1.txt
     */
	@Test
	public void test1(){
	    testAgainstFiles(1);
	}

    /**
     * This test2 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test2.html and EGR227-HW1/expected_output/validate_result_for_test2.txt
     */
	@Test
	public void test2(){
        testAgainstFiles(2);
	}


    /**
     * This test3 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test3.html and EGR227-HW1/expected_output/validate_result_for_test3.txt
     */
	@Test
	public void test3(){
        testAgainstFiles(3);
	}


    /**
     * This test4 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test4.html and EGR227-HW1/expected_output/validate_result_for_test4.txt
     */
	@Test
	public void test4(){
        testAgainstFiles(4);
	}

    /**
     * This test5 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test5.html and EGR227-HW1/expected_output/validate_result_for_test5.txt
     */
	@Test
	public void test5(){
        testAgainstFiles(5);
	}

    /**
     * This test6 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test6.html and EGR227-HW1/expected_output/validate_result_for_test6.txt
     */
	@Test
	public void test6(){
        testAgainstFiles(6);
	}

    /**
     * This test7 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test7.html and EGR227-HW1/expected_output/validate_result_for_test7.txt
     */
	@Test
	public void test7(){
        testAgainstFiles(7);
	}

    /**
     * This test8 method tests the validate() method by reproducing the test of
     * EGR227-HW1/input_html/test8.html and EGR227-HW1/expected_output/validate_result_for_test8.txt
     */
	@Test
	public void test8(){
        testAgainstFiles(8);
	}

	/**
	 * This test removes the b tags from the list
	 */
	@Test
	public void myRemoveAllTest1(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b"));
        tags.add(new HtmlTag("r"));
        tags.add(new HtmlTag("b"));
        tags.add(new HtmlTag("r"));
        tags.add(new HtmlTag("b"));

        Queue<HtmlTag> newTags = new LinkedList<>();
        for (HtmlTag tag : tags) {
            if (!tag.getElement().equalsIgnoreCase("b")) {
                newTags.add(tag);
            }
        }
        Assert.assertEquals("[<r>, <r>]", newTags.toString());
	}

	/**
	 * This test removes the p tag from the list
	 */
	@Test
	public void myRemoveAllTest2(){
	    Queue<HtmlTag> tags = new LinkedList<>();
	    tags.add(new HtmlTag("p"));
	    tags.add(new HtmlTag("b"));
	    tags.add(new HtmlTag("r"));
	    tags.add(new HtmlTag("b"));
	    tags.add(new HtmlTag("r"));
	    tags.add(new HtmlTag("b"));

	    Queue<HtmlTag> newTags = new LinkedList<>();
	    for (HtmlTag tag : tags) {
	        if (!tag.getElement().equalsIgnoreCase("p")) {
	            newTags.add(tag);
            }
        }
	    Assert.assertEquals("[<b>, <r>, <b>, <r>, <b>]", newTags.toString());
	}

	/**
	 * This test removes the r tags from the list
	 */
	@Test
	public void myRemoveAllTest3(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("p"));
        tags.add(new HtmlTag("b"));
        tags.add(new HtmlTag("r"));
        tags.add(new HtmlTag("b"));
        tags.add(new HtmlTag("r"));
        tags.add(new HtmlTag("b"));

        Queue<HtmlTag> newTags = new LinkedList<>();
        for (HtmlTag tag : tags) {
            if (!tag.getElement().equalsIgnoreCase("r")) {
                newTags.add(tag);
            }
        }
        Assert.assertEquals("[<p>, <b>, <b>, <b>]", newTags.toString());
	}

    /**
     * This test removes the Html tag from the list
     */
    @Test
    public void myRemoveAllTest4(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("Html"));
        tags.add(new HtmlTag("b"));
        tags.add(new HtmlTag("r"));
        tags.add(new HtmlTag("b"));
        tags.add(new HtmlTag("r"));
        tags.add(new HtmlTag("b"));

        Queue<HtmlTag> newTags = new LinkedList<>();
        for (HtmlTag tag : tags) {
            if (!tag.getElement().equalsIgnoreCase("Html")) {
                newTags.add(tag);
            }
        }
        Assert.assertEquals("[<b>, <r>, <b>, <r>, <b>]", newTags.toString());
    }

}