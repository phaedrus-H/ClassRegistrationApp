package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.java.EnrollmentDataSet;

public class EnrollmentDataSetTest {

	@Test
	public void testReadDataSet() throws Exception {
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",",");
		
		List<String> enrollDS = eDS.getDataSet();
		List<String> enrollAttributes = eDS.getAttributes();
		
		List<String> expEnrollDS = new ArrayList<String>();
		expEnrollDS.add("Chemistry,Joseph,1234");
		expEnrollDS.add("Chemistry,Jane,3455");
		expEnrollDS.add("History,Jane,3455");
		expEnrollDS.add("Mathematics,Doe,56767");
		
		List<String> expAttributes = new ArrayList<String>();
		expAttributes.add("Class");
		expAttributes.add("Professor");
		expAttributes.add("Student ID");
		
		assertTrue(expEnrollDS.equals(enrollDS));
		assertTrue(expAttributes.equals(enrollAttributes));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testReadDataSetWithInvalidClass() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Invalid Class : Biology");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdatasetInvalidClass.csv",",");
	}
	
	@Test
	public void testReadDataSetWithInvalidProfessor() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Invalid Professor : Joseph3");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdatasetInvalidProfessor.csv",",");
	}
	
	@Test
	public void testReadDataSetWithInvalidStudentID() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Invalid Student ID : ABCD");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdatasetInvalidStudentID.csv",",");
	}
	
	@Test
	public void testReadDataSetWithMissingFile() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Missing file name");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet(null,",");
	}
	
	@Test
	public void testReadDataSetWithMissingDelimiter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Missing delimiter");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",null);
	}
	
	@Test
	public void testReadDataSetWithEmptyFile() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("File is empty.");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdatasetEmpty.csv",",");
	}
	
	@Test
	public void testValidateLine() throws Exception {
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",",");
		assertTrue(eDS.validateLine("Chemistry,Joseph,1234", ","));
	}
	
	@Test
	public void testValidateLineWithMissingLine() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Missing line to validate");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",",");
		assertTrue(eDS.validateLine(null, ","));
	}
	
	@Test
	public void testValidateLineWithMissingDelimter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Missing delimiter");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",",");
		assertTrue(eDS.validateLine("Chemistry,Joseph,1234", null));
	}
	
	@Test
	public void testValidate() throws Exception{
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",",");
		assertTrue(eDS.validate("Class","Chemistry"));
		assertTrue(eDS.validate("Professor","Joseph"));
		assertTrue(eDS.validate("Student ID","1234"));
	}
	
	@Test
	public void testValidateClass() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty class");
		EnrollmentDataSet eDS = new EnrollmentDataSet();
		eDS.readDataSet("testdataset.csv",",");
		assertTrue(eDS.validateClass(null));
	}
	
}
