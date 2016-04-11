package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * @author: Harshad Hussain
 * Filename		: Util.java
 * Description	: Utility class to perform operations like creating maps, strings, lists, and validating strings, integers
 * Members		: null
 */
public class Util {
	
	/*
	 * @param listOfAttributes 	: List of attribute types of the data set
	 * 		  keyAttr			: List of attribute types to be considered for constructing the key
	 * 		  valAttr			: List of attribute types to be considered for constructing the value
	 * 		  dataSet			: List of tuples to be mapped
	 */
	public static Map<String, List<String>> getMap (List<String> listOfAttributes, 
			String[] keyAttr, String[] valAttr, List<String> dataSet, String delimiter) throws Exception{
		if (null == listOfAttributes || 0 == listOfAttributes.size())
			throw new Exception("Error constructing map. Empty List of Attributes.");
		if (null == keyAttr || 0 == keyAttr.length)
			throw new Exception("Error constructing map. Empty KeySet Attributes.");
		if (null == valAttr || 0 == valAttr.length)
			throw new Exception("Error constructing map. Empty ValueSet Attributes.");
		if (null == dataSet || 0 == dataSet.size())
			throw new Exception("Error constructing map. Empty Data Set.");
		if (null == delimiter || 0 == delimiter.length())
			throw new Exception("Missing delimiter.");
		
		Map<String, List<String>> retMap = new LinkedHashMap<String, List<String>>();
		
		//Get the list of attribute types identified as key
		List<Integer> keyAttrList = getAttrList(listOfAttributes, keyAttr);
		
		//Get the list of attribute types identified as value
		List<Integer> valAttrList = getAttrList(listOfAttributes, valAttr);
		
		if (null == keyAttrList || 0 == keyAttrList.size())
			throw new Exception("Error constructing map. Unable to get list of KeySet attributes.");
		if (null == valAttrList || 0 == valAttrList.size())
			throw new Exception("Error constructing map. Unable to get list of ValueSet attributes.");
		
		
		//Construct a map for the dataSet with keyAttrList as key and valAttrList as value
		for (String sEnrollmentData: dataSet){
			List<String> data = Arrays.asList(sEnrollmentData.split(delimiter));
			List<String> listOfVals = new ArrayList<String>();
			
			//Construct a comma separated string of key attributes data
			String sKey = constructString(data, keyAttrList);
			
			//Construct a comma separated string of value attributes data
			String sVal = constructString(data, valAttrList);
			
			if (null == sKey || 0 == sKey.length())
				throw new Exception("Error constructing map. Unable to construct key.");
			if (null == sVal || 0 == sVal.length())
				throw new Exception("Error constructing map. Unable to construct value.");
			
			
			//Constructing the map
			if (retMap.containsKey(sKey)){
				listOfVals = retMap.get(sKey);
				listOfVals.add(sVal);
				retMap.put(sKey, listOfVals);
			}
			else {
				listOfVals.add(sVal);
				retMap.put(sKey, listOfVals);
			}
		}
		return retMap;
	}
	
	/*
	 * @param listOfAttributes	: list of all possible attribute types
	 * 		  attr				: array of required attribute types in order
	 * @description : Construct a list of attribute type indexes from listOfAttributes consisting attribute types given 
	 *                in attr while also maintaining the order they appear in attr
	 */
	public static List<Integer> getAttrList(List<String> listOfAttributes, String[] attr) throws Exception{
		if (null == listOfAttributes || 0 == listOfAttributes.size())
			throw new Exception("Empty list of attributes (full set)");
		if (null == attr || 0 == attr.length)
			throw new Exception("Empty list of attributes (subset)");
		
		List<Integer> attrList = new ArrayList<Integer>();
		for (int i = 0; i < attr.length; i++){
			attrList.add(listOfAttributes.indexOf(attr[i]));
		}
		return attrList;
	}
	
	
	/*
	 * @param listOfValues : values to be separated by comma
	 *        attrList     : list of attribute types to be considered
	 * @description : Construct a comma separated string of values for attribute types in attrList
	 */
	public static String constructString(List<String> listOfValues, List<Integer> attrList) throws Exception{
		if (null == listOfValues || 0 == listOfValues.size())
			throw new Exception("Error Constructing String. Empty list of value");
		if (null == attrList || 0 == attrList.size())
			throw new Exception("Error Constructing String. Empty list of attributes");
		
		String str = new String();
		int idx = 0;
		while (idx < attrList.size()){
			str += listOfValues.get(attrList.get(idx));
			idx++;
			if (idx < attrList.size())
				str += ", ";
		}
		return str;
	}
	
	/*
	 * @param stringList : list of strings to be comma separated
	 * @description : Construct a comma separated string from the list of strings stringList
	 */
	public static String constructString(List<String> stringList) throws Exception{
		if (null == stringList || 0 == stringList.size())
			throw new Exception("Error Constructing String. Empty string.");
		
		String str = new String();
		int idx = 0;
		while (idx < stringList.size()){
			str += stringList.get(idx);
			idx++;
			if (idx < stringList.size())
				str += ", ";
		}
		return str;
	}
	
	/*
	 * @param val: String value to be validated
	 * @description : Validate whether val has only letters from 'a' to 'z', 'A' to 'Z' and spaces
	 */
	public static boolean validateString(String val) throws Exception{
		if (null == val || 0 == val.length())
			throw new Exception("Empty string to validate.");
		
		boolean bValid = false;
		if (true == val.matches("[a-zA-Z ]+"))
			bValid = true;
		return bValid;
	}
	
	/*
	 * @param val : String value to be validated
	 * @description: Validate whether val has only numbers 0 to9
	 */
	public static boolean validateInteger(String val) throws Exception{
		if (null == val || 0 == val.length())
			throw new Exception("Empty integer to validate.");
		
		boolean bValid = false;
		if (true == val.matches("[0-9]+"))
			bValid = true;
		return bValid;
	}
}