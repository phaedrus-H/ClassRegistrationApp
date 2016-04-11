package main.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * @author: Harshad Hussain
 * Filename		: EnrollmentDataSet.java
 * Description	: Modeling specific properties of enrollment data set and performing functions specific to enrollmenr data set
 * Members		: enrolData			: List of each data row fetched from database file
 *                enrolAttributes	: List of each attribute type of the database file 
 *                possibleClasses	: Types of classes
 *                errMessage		: Error message to be propagated up
 */
public class EnrollmentDataSet {
	
	private List<String> enrolData;
	private List<String> enrolAttributes;
	private List<String> possibleClasses;
	private String errMessage;
	
	public EnrollmentDataSet(){
		enrolData = new ArrayList<String>();
		enrolAttributes = new ArrayList<String>();
		possibleClasses = new ArrayList<String>();
		errMessage = new String();
	}
	
	/*
	 * Getter method for data set
	 */
	public List<String> getDataSet (){
		return enrolData;
	}
	
	/*
	 * Getter method for attribute types
	 */
	public List<String> getAttributes(){
		return enrolAttributes;
	}
	
	/*
	 * Set different attribute types
	 */
	private List<String> setEnrollmentAttributes(){
		List<String> attrList = new ArrayList<String>();
		attrList.add("Class");
		attrList.add("Professor");
		attrList.add("Student ID");
		return attrList;
	}
	
	/*
	 * Set allowed classes
	 */
	private List<String> setPossibleClasses(){
		List<String> classList = new ArrayList<String>();
		classList.add("Chemistry");
		classList.add("History");
		classList.add("Mathematics");
		classList.add("Physics");
		return classList;
	}
	
	/*
	 * @param fileName : file that is used as the database
	 * 	      delimiter: delimiter used in the file 
	 * @description : Read the data set from fileName and construct the enrollment data set object with attribute types for 
	 *                data set read and allowed values for Class
	 */
	public void readDataSet(String fileName, String delimiter) throws Exception{
		if (null == fileName || 0 == fileName.length()){
			errMessage = "Missing file name";
			throw new Exception(errMessage);
		}
		
		if (null == delimiter || 0 == delimiter.length()){
			errMessage = "Missing delimiter";
			throw new Exception(errMessage);
		}
		
		enrolAttributes = setEnrollmentAttributes();
		possibleClasses = setPossibleClasses();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				getClass().getClassLoader().getResourceAsStream(fileName)));
		String line;
		boolean bValid = true;
		boolean bIsEmpty = true;
		while (null != (line = reader.readLine())) {
			bIsEmpty = false;
			bValid = validateLine(line, delimiter);
			if (true == bValid)
				enrolData.add(line);
			else
				throw new Exception(errMessage);
		}
		
		if (true == bIsEmpty){
			errMessage = "File is empty.";
			throw new Exception(errMessage);
		}
	}
	
	/*
	 * @param line : each row/tuple read from the data set
	 *        delimiter : delimiter used to tokenize the data
	 * @description: validate a row read from the data set
	 */
	public boolean validateLine(String line, String delimiter) throws Exception{
		if (null == line || 0 == line.length()){
			errMessage = "Missing line to validate";
			throw new Exception(errMessage);
		}
		
		if (null == delimiter || 0 == delimiter.length()){
			errMessage = "Missing delimiter";
			throw new Exception(errMessage);
		}
		
		String[] splitLine = line.split(delimiter);
		
		//validate each value in the row
		boolean bValid = true;
		for (int idx = 0; idx < splitLine.length; idx++){
			bValid = validate(enrolAttributes.get(idx), splitLine[idx]);
			if (bValid == false)
				break;
		}
		return bValid;	
	}
	
	/*
	 * @param attributeType : Type of the attribute to be validated 
	 *        attributeValue: Value of the attribute
	 * @description : Validate attribute value based on its type
	 */
	public boolean validate(String attributeType, String attributeValue) throws Exception{
		if (null == attributeType || 0 == attributeType.length()){
			errMessage = "Missing attribute name.";
			throw new Exception(errMessage);
		}
		if (null == attributeValue || 0 == attributeValue.length()){
			errMessage = "Missing attribute value.";
			throw new Exception(errMessage);
		}
		
		boolean bVal = true;
		
		//validate attribute value if type is Class
		if ("Class".equals(attributeType))
			bVal = validateClass(attributeValue);
		
		//validate attribute value if type is Professor
		else if ("Professor".equals(attributeType)){
			bVal = Util.validateString(attributeValue);
			if (false == bVal)
				errMessage = "Invalid Professor : " + attributeValue;
		}
		
		//validate attribute value if type is Student ID
		else if ("Student ID".equals(attributeType)){
			bVal = Util.validateInteger(attributeValue);
			if (false == bVal)
				errMessage = "Invalid Student ID : " + attributeValue;
		}
		return bVal;
	}
	
	/*
	 * @param val : Value to be validated
	 * @description : Validate if val is in the list of possible values for Class attribute
	 */
	public boolean validateClass(String val) throws Exception{
		if (null == val || 0 == val.length()){
			errMessage = "Empty class";
			throw new Exception(errMessage);
		}
		
		boolean bValid = false;
		for (int idx = 0; idx < possibleClasses.size(); idx++)
			if(val.equals(possibleClasses.get(idx)))
				bValid = true;
		
		if(bValid == false)
			errMessage = "Invalid Class : " + val;
		return bValid;
	}
}