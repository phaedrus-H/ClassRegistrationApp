package main.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @author: Harshad Hussain
 * Filename    : Enrollment.java
 * Description : Class for querying the data set according to the requirement.
 * Members     : mSplitByClassSection - Map holding data set split based on class section
 *               mSplitByStudent      - Map holding data set split based on student
 *               mSplitByClass        - Map holding data set split based on class
 *               mSplitByProfessor    - Map holding data set split based on professor
 */
public class Enrollment {
	
	private Map<String,List<String>> mSplitByClassSection;
	private Map<String,List<String>> mSplitByStudent;
	private Map<String,List<String>> mSplitByClass;
	private Map<String,List<String>> mSplitByProfessor;
	
	public Enrollment() {
		mSplitByClassSection = new LinkedHashMap<String, List<String>>();
		mSplitByStudent = new LinkedHashMap<String, List<String>>();
		mSplitByClass = new LinkedHashMap<String, List<String>>();
		mSplitByProfessor = new LinkedHashMap<String, List<String>>();
	}
	
	/*
	 * Getter method
	 */
	public Map<String,List<String>> getmSplitByClassSection(){
		return mSplitByClassSection;
	}
	
	/*
	 * Getter method
	 */
	public Map<String,List<String>> getmSplitByStudent(){
		return mSplitByStudent;
	}
	
	/*
	 * Getter method
	 */
	public Map<String,List<String>> getmSplitByClass(){
		return mSplitByClass;
	}
	
	/*
	 * Getter method
	 */
	public Map<String,List<String>> getmSplitByProfessor(){
		return mSplitByProfessor;
	}
	
	/*
	 * @param fileName : file that is used as the database
	 * 	      delimiter: delimiter used in the file
	 * @description : Fetch data from the source file and execute the queries.
	 */
	public static void executeEnrollmentOperations (String fileName, String delimiter){
		try{
			if (null == fileName || 0 == fileName.length()){
				throw new Exception("Missing file name");
			}
			if (null == delimiter || 0 == delimiter.length()){
				throw new Exception("Missing delimiter");
			}
			
			Enrollment enrollment = new Enrollment();
			
			//Read the data from the source file into enrollmentDataSet object
			EnrollmentDataSet enrollmentDataSet = new EnrollmentDataSet();
			enrollmentDataSet.readDataSet(fileName, delimiter);
			
			//Get the attribute types of data set
			List<String> enrollAttr = enrollmentDataSet.getAttributes();
			
			//Get the data set
			List<String> enrollDS = enrollmentDataSet.getDataSet();
			
			System.out.println("Class Sections Taught:");
			enrollment.listClassesTaught(enrollAttr, enrollDS, delimiter);
			
			System.out.println("\nClasses Taken By Each Student:");
			enrollment.listClassesEnrolledByStudent(enrollAttr, enrollDS, delimiter);
			
			System.out.println("\nHow many students are registered for each class?");
			enrollment.listNumStudentsInClass(enrollAttr, enrollDS, delimiter);
			
			System.out.println("\nHow many students take more than one class?");
			enrollment.listMultiEnrollStudents();
			
			System.out.println("\nHow many professors teach more than one class?");
			enrollment.listMultiTeachProf(enrollAttr, enrollDS, delimiter);
			
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	 * @param enrollAttr : List of enrollment data set attribute types
	 *        enrollDS   : List of enrollment data
	 *  
	 * @description : List the different class sections taught
	 */
	public void listClassesTaught(List<String> enrollAttr, List<String> enrollDS, String delimiter) throws Exception{
		if (null == enrollAttr || 0 == enrollAttr.size())
			throw new Exception("Empty Enrollment Attribute Types.");
		if (null == enrollDS || 0 == enrollDS.size())
			throw new Exception("Empty Enrollment Data Set.");
		if (null == delimiter || 0 == delimiter.length())
			throw new Exception("Empty Delimiter.");
		
		String[] keyAttr = {"Class", "Professor"};
		String[] valAttr = {"Student ID"};
		
		//Create a map with each class section taught as the key and 
		//list of students enrolled for each class section as value.
		mSplitByClassSection = Util.getMap(enrollAttr, keyAttr, valAttr, enrollDS, delimiter);
		
		if (null == mSplitByClassSection){
			throw new Exception("Error splitting by class section.");
		}
		
		//Print out each class section
		Set<String> classByProf = mSplitByClassSection.keySet();
		for (String sClassBySection: classByProf)
			System.out.println(sClassBySection);
	}
	
	/*
	 * @param enrollAttr : List of enrollment data set attribute types
	 *        enrollDS   : List of enrollment data
	 *  
	 * @description : List the different classes taken by each student
	 */
	public void listClassesEnrolledByStudent(List<String> enrollAttr, List<String> enrollDS, String delimiter) throws Exception{
		if (null == enrollAttr || 0 == enrollAttr.size())
			throw new Exception("Empty Enrollment Attribute Types.");
		if (null == enrollDS || 0 == enrollDS.size())
			throw new Exception("Empty Enrollment Data Set.");
		if (null == delimiter || 0 == delimiter.length())
			throw new Exception("Empty Delimiter.");
		
		String[] keyAttr = {"Student ID"};
		String[] valAttr = {"Class"};
		
		//Create a map with each student as the key and 
		//list of classes enrolled by the student as value.
		mSplitByStudent = Util.getMap(enrollAttr, keyAttr, valAttr, enrollDS, delimiter);
		
		if (null == mSplitByStudent){
			throw new Exception("Error splitting by student.");
		}
		
		//List out the each student and the corresponding classes enrolled for
		for (String student: mSplitByStudent.keySet()){
			String sClassesEnrolled = Util.constructString(mSplitByStudent.get(student));
			System.out.println(student + " : " + sClassesEnrolled);
		}
	}
	
	/*
	 * @param enrollAttr : List of enrollment data set attribute types
	 *        enrollDS   : List of enrollment data
	 *  
	 * @description : List each class and number of students registered
	 */
	public void listNumStudentsInClass(List<String> enrollAttr, List<String> enrollDS, String delimiter) throws Exception{
		if (null == enrollAttr || 0 == enrollAttr.size())
			throw new Exception("Empty Enrollment Attribute Types.");
		if (null == enrollDS || 0 == enrollDS.size())
			throw new Exception("Empty Enrollment Data Set.");
		if (null == delimiter || 0 == delimiter.length())
			throw new Exception("Empty Delimiter.");
		
		String[] keyAttr = {"Class"};
		String[] valAttr = {"Student ID"};
		
		//Create a map with each class as the key and 
		//list of students enrolled for the class as value.
		mSplitByClass = Util.getMap(enrollAttr, keyAttr, valAttr, enrollDS, delimiter);
		
		if (null == mSplitByClass){
			throw new Exception("Error splitting by class.");
		}
		
		//List out each class and the number of students enrolled in each class
		for (String sClass: mSplitByClass.keySet()){
			System.out.println(sClass + " : " + mSplitByClass.get(sClass).size());
		}
	}
	
	/*
	 * @param enrollAttr : List of enrollment data set attribute types
	 *        enrollDS   : List of enrollment data
	 *  
	 * @description : Get the number of students who have taken more than one class and list
	 */
	public void listMultiEnrollStudents() throws Exception{
		if (null == mSplitByStudent){
			throw new Exception("Map split by student is empty.");
		}
		
		//Using the splitByStudent map list the students enrolled for more than one class
		List<String> students = new ArrayList<String>();
		int count = 0;
		for (String student: mSplitByStudent.keySet()){
			if (mSplitByStudent.get(student).size() > 1){
				count++;
				students.add(student);
			}
		}
		
		//Construct a comma separated string from the list of students
		String sStudents = Util.constructString(students);
		if (null == sStudents)
			throw new Exception("Error constructing list of students.");
		
		System.out.println(count + " : " + sStudents);
	}
	
	/*
	 * @param enrollAttr : List of enrollment data set attribute types
	 *        enrollDS   : List of enrollment data
	 *  
	 * @description : Get the number of professors teaching more than one class and list
	 */
	public void listMultiTeachProf(List<String> enrollAttr, List<String> enrollDS, String delimiter) throws Exception{
		if (null == enrollAttr || 0 == enrollAttr.size())
			throw new Exception("Empty Enrollment Attribute Types.");
		if (null == enrollDS || 0 == enrollDS.size())
			throw new Exception("Empty Enrollment Data Set.");
		if (null == delimiter || 0 == delimiter.length())
			throw new Exception("Empty Delimiter.");
		
		String[] keyAttr = {"Professor"};
		String[] valAttr = {"Class"};
		
		//Create a map with each professor as the key and 
		//list of classes taught by the professor as value.
		mSplitByProfessor = Util.getMap(enrollAttr, keyAttr, valAttr, enrollDS, delimiter);
		
		if (null == mSplitByProfessor){
			throw new Exception("Error splitting by Professor.");
		}
		
		//Using the splitByProfessor map list the number of professors teaching more than one class
		List<String> profNames = new ArrayList<String>();
		int count = 0;
		for (String sProf: mSplitByProfessor.keySet()){
			if(mSplitByProfessor.get(sProf).size() > 1){
				count++;
				profNames.add(sProf);
			}
		}
		
		//Construct a comma separated string from the list of professors
		String sProfessors = Util.constructString(profNames);
		if (null == sProfessors)
			throw new Exception("Error constructing list of professors.");
		
		System.out.println(count + " : " + sProfessors);
	}
	
	
	/*
	 * Main method
	 */
	public static void main(String[] args) {
		String fileName = "dataset.csv";
		String delimiter = ",";
		executeEnrollmentOperations(fileName, delimiter);
	}
}