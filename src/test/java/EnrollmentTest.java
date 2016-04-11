package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.java.Enrollment;
import main.java.EnrollmentDataSet;

public class EnrollmentTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void TestListClassesTaught() throws Exception {
		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listClassesTaught(enrollAttr, enrollDS, ",");
		
		Map<String, List<String>> expectedMapSplitByClassSection = new LinkedHashMap<String, List<String>>();
		
		List<String> expectedList1 = new ArrayList<String>();
		expectedList1.add("1234");
		expectedMapSplitByClassSection.put("Chemistry, Joseph", expectedList1);
		List<String> expectedList2 = new ArrayList<String>();
		expectedList2.add("3455");
		expectedMapSplitByClassSection.put("Chemistry, Jane", expectedList2);
		List<String> expectedList3 = new ArrayList<String>();
		expectedList3.add("3455");
		expectedMapSplitByClassSection.put("History, Jane", expectedList3);
		List<String> expectedList4 = new ArrayList<String>();
		expectedList4.add("56767");
		expectedMapSplitByClassSection.put("Mathematics, Doe", expectedList4);
		
		assertTrue(expectedMapSplitByClassSection.equals(enrollment.getmSplitByClassSection()));
	}
	
	@Test
	public void TestListClassesTaughtWithEmptyAttributeList() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Attribute Types.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listClassesTaught(null, enrollDS, ",");
	}
	
	@Test
	public void TestListClassesTaughtWithEmptyDataSet() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Data Set.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		
		enrollment.listClassesTaught(enrollAttr, null, ",");
	}
	
	@Test
	public void TestListClassesTaughtWithEmptyDelimiter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Delimiter.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listClassesTaught(enrollAttr, enrollDS, null);
	}
	
	@Test
	public void TestListClassesEnrolledByStudent() throws Exception {
		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listClassesEnrolledByStudent(enrollAttr, enrollDS, ",");
		
		Map<String, List<String>> expectedMapSplitByStudent = new LinkedHashMap<String, List<String>>();
		
		List<String> expectedList1 = new ArrayList<String>();
		expectedList1.add("Chemistry");
		expectedMapSplitByStudent.put("1234", expectedList1);
		List<String> expectedList2 = new ArrayList<String>();
		expectedList2.add("Chemistry");
		expectedList2.add("History");
		expectedMapSplitByStudent.put("3455", expectedList2);
		List<String> expectedList3 = new ArrayList<String>();
		expectedList3.add("Mathematics");
		expectedMapSplitByStudent.put("56767", expectedList3);
	
		assertTrue(expectedMapSplitByStudent.equals(enrollment.getmSplitByStudent()));
	}
	
	@Test
	public void TestListClassesEnrolledByStudentWithEmptyAttributeList() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Attribute Types.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listClassesEnrolledByStudent(null, enrollDS, ",");
	}
	
	@Test
	public void TestListClassesEnrolledByStudentWithEmptyDataSet() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Data Set.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		
		enrollment.listClassesEnrolledByStudent(enrollAttr, null, ",");
	}
	
	@Test
	public void TestListClassesEnrolledByStudentWithEmptyDelimiter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Delimiter.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listClassesEnrolledByStudent(enrollAttr, enrollDS, null);
	}
	
	@Test
	public void TestListNumStudentsInClass() throws Exception {
		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listNumStudentsInClass(enrollAttr, enrollDS, ",");
		
		Map<String, List<String>> expectedMapSplitByClass = new LinkedHashMap<String, List<String>>();
		
		List<String> expectedList1 = new ArrayList<String>();
		expectedList1.add("1234");
		expectedList1.add("3455");
		expectedMapSplitByClass.put("Chemistry", expectedList1);
		List<String> expectedList2 = new ArrayList<String>();
		expectedList2.add("3455");
		expectedMapSplitByClass.put("History", expectedList2);
		List<String> expectedList3 = new ArrayList<String>();
		expectedList3.add("56767");
		expectedMapSplitByClass.put("Mathematics", expectedList3);
		
		assertTrue(expectedMapSplitByClass.equals(enrollment.getmSplitByClass()));
	}
	
	@Test
	public void TestListNumStudentsInClassWithEmptyAttributeList() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Attribute Types.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listNumStudentsInClass(null, enrollDS, ",");
	}
	
	@Test
	public void TestListNumStudentsInClassWithEmptyDataSet() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Data Set.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		
		enrollment.listNumStudentsInClass(enrollAttr, null, ",");
	}
	
	@Test
	public void TestListNumStudentsInClassWithEmptyDelimiter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Delimiter.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listNumStudentsInClass(enrollAttr, enrollDS, null);
	}
	
	@Test
	public void TestListMultiTeachProf() throws Exception {
		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listMultiTeachProf(enrollAttr, enrollDS, ",");
		
		Map<String, List<String>> expectedMapSplitByProf = new LinkedHashMap<String, List<String>>();
		
		List<String> expectedList1 = new ArrayList<String>();
		expectedList1.add("Chemistry");
		expectedMapSplitByProf.put("Joseph", expectedList1);
		List<String> expectedList2 = new ArrayList<String>();
		expectedList2.add("Chemistry");
		expectedList2.add("History");
		expectedMapSplitByProf.put("Jane", expectedList2);
		List<String> expectedList3 = new ArrayList<String>();
		expectedList3.add("Mathematics");
		expectedMapSplitByProf.put("Doe", expectedList3);
		
		assertTrue(expectedMapSplitByProf.equals(enrollment.getmSplitByProfessor()));
	}
	
	@Test
	public void TestListMultiTeachProfWithEmptyAttributeList() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Attribute Types.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listMultiTeachProf(null, enrollDS, ",");
	}
	
	@Test
	public void TestListMultiTeachProfWithEmptyDataSet() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Enrollment Data Set.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		
		enrollment.listMultiTeachProf(enrollAttr, null, ",");
	}
	
	@Test
	public void TestListMultiTeachProfWithEmptyDelimiter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty Delimiter.");

		Enrollment enrollment = new Enrollment();
		EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
		enrollmentDataSet.readDataSet("testdataset.csv", ",");
		List<String> enrollAttr = enrollmentDataSet.getAttributes();
		List<String> enrollDS = enrollmentDataSet.getDataSet();
		
		enrollment.listMultiTeachProf(enrollAttr, enrollDS, null);
	}

}
