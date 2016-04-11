package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import main.java.Util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UtilTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testValidateInteger() throws Exception{
		assertTrue(Util.validateInteger("123"));
		assertFalse(Util.validateInteger("abc"));
		assertFalse(Util.validateInteger("123a"));
	}
	
	@Test
	public void testValidateIntegerWithEmptyValue() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty integer to validate.");
		assertTrue(Util.validateInteger(null));
	}
	
	@Test
	public void testValidateString() throws Exception{
		assertTrue(Util.validateString("abcd"));
		assertTrue(Util.validateString("abc d"));
		assertFalse(Util.validateString("123"));
		assertFalse(Util.validateString("abc1"));
		assertFalse(Util.validateString("abc 1"));
	}
	
	@Test
	public void testValidateStringWithEmptyValue() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty string to validate.");
		assertTrue(Util.validateString(null));
	}
	
	@Test
	public void testConstructString2() throws Exception{
		List<String> str = new ArrayList<String>();
		str.add("a");
		str.add("b");
		String s = "a, b";
		assertTrue(s.equals(Util.constructString(str)));
	}
	
	@Test
	public void testConstructString2WithEmptyString() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Error Constructing String. Empty string.");
		String s = "a, b";
		assertTrue(s.equals(Util.constructString(null)));
	}
	
	@Test
	public void testConstructString1() throws Exception{
		List<String> str = new ArrayList<String>();
		List<Integer> intgr = new ArrayList<Integer>();
		str.add("a");
		str.add("b");
		str.add("c");
		intgr.add(2);
		intgr.add(0);
		intgr.add(1);
		String expectedString = "c, a, b";
		assertTrue(expectedString.equals(Util.constructString(str, intgr)));
	}
	
	@Test
	public void testConstructString1WithEmptyStingList() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Error Constructing String. Empty list of value");
		List<Integer> intgr = new ArrayList<Integer>();
		intgr.add(2);
		intgr.add(0);
		intgr.add(1);
		String expectedString = "c, a, b";
		assertTrue(expectedString.equals(Util.constructString(null, intgr)));
	}
	
	@Test
	public void testConstructStringWithEmptyIndexList() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Error Constructing String. Empty list of attributes");
		List<String> str = new ArrayList<String>();
		str.add("a");
		str.add("b");
		str.add("c");
		String expectedString = "c, a, b";
		assertTrue(expectedString.equals(Util.constructString(str, null)));
	}
	
	@Test
	public void testGetAttrList() throws Exception{
		List<String> str = new ArrayList<String>();
		str.add("a");
		str.add("b");
		str.add("c");
		String[] strArray = {"c", "a"};
		
		List<Integer> expIntList = new ArrayList<Integer>();
		expIntList.add(2);
		expIntList.add(0);
		
		assertTrue(expIntList.equals(Util.getAttrList(str, strArray)));
	}
	
	@Test
	public void testGetAttrListWithEmptyStingList() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty list of attributes (full set)");
		
		String[] strArray = {"c", "a"};
		
		List<Integer> expIntList = new ArrayList<Integer>();
		expIntList.add(2);
		expIntList.add(0);
		
		assertTrue(expIntList.equals(Util.getAttrList(null, strArray)));
	}
	
	@Test
	public void testGetAttrListWithEmptyStringArray() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("Empty list of attributes (subset)");
		
		List<String> str = new ArrayList<String>();
		str.add("a");
		str.add("b");
		str.add("c");
		
		List<Integer> expIntList = new ArrayList<Integer>();
		expIntList.add(2);
		expIntList.add(0);
		
		assertTrue(expIntList.equals(Util.getAttrList(str, null)));
	}
	
	@Test
	public void testGetMap() throws Exception{
		List<String> attributes = new ArrayList<String>();
		attributes.add("attr_1");
		attributes.add("attr_2");
		attributes.add("attr_3");
		
		String[] keyAttributes = {"attr_1"};
		String[] valueAttributes = {"attr_3"};
		
		List<String> dataSet = new ArrayList<String>();
		dataSet.add("a,1,a1");
		dataSet.add("b,1,b1");
		dataSet.add("a,2,a2");
		
		Map<String, List<String>> expMap = new LinkedHashMap<String, List<String>>();
		List<String> expAList = new ArrayList<String>();
		expAList.add("a1");
		expAList.add("a2");
		List<String> expBList = new ArrayList<String>();
		expBList.add("b1");
		expMap.put("a", expAList);
		expMap.put("b", expBList);
		
		assertTrue(expMap.equals(Util.getMap(attributes, keyAttributes, valueAttributes, dataSet, ",")));
	}
	
	@Test
	public void testGetMapWithEmptyAttributeList() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Error constructing map. Empty List of Attributes.");
		
		String[] keyAttributes = {"attr_1"};
		String[] valueAttributes = {"attr_3"};
		
		List<String> dataSet = new ArrayList<String>();
		dataSet.add("a,1,a1");
		dataSet.add("b,1,b1");
		dataSet.add("a,2,a2");
		
		Map<String, List<String>> expMap = new LinkedHashMap<String, List<String>>();
		List<String> expAList = new ArrayList<String>();
		expAList.add("a1");
		expAList.add("a2");
		List<String> expBList = new ArrayList<String>();
		expBList.add("b1");
		expMap.put("a", expAList);
		expMap.put("b", expBList);
		
		assertTrue(expMap.equals(Util.getMap(null, keyAttributes, valueAttributes, dataSet, ",")));
	}
	
	@Test
	public void testGetMapWithEmptyKeyAttributes() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Error constructing map. Empty KeySet Attributes.");
		
		List<String> attributes = new ArrayList<String>();
		attributes.add("attr_1");
		attributes.add("attr_2");
		attributes.add("attr_3");
		
		String[] valueAttributes = {"attr_3"};
		
		List<String> dataSet = new ArrayList<String>();
		dataSet.add("a,1,a1");
		dataSet.add("b,1,b1");
		dataSet.add("a,2,a2");
		
		Map<String, List<String>> expMap = new LinkedHashMap<String, List<String>>();
		List<String> expAList = new ArrayList<String>();
		expAList.add("a1");
		expAList.add("a2");
		List<String> expBList = new ArrayList<String>();
		expBList.add("b1");
		expMap.put("a", expAList);
		expMap.put("b", expBList);
		
		assertTrue(expMap.equals(Util.getMap(attributes, null, valueAttributes, dataSet, ",")));
	}
	
	@Test
	public void testGetMapWithEmptyValueAttributes() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Error constructing map. Empty ValueSet Attributes.");
		
		List<String> attributes = new ArrayList<String>();
		attributes.add("attr_1");
		attributes.add("attr_2");
		attributes.add("attr_3");
		
		String[] keyAttributes = {"attr_1"};
		
		List<String> dataSet = new ArrayList<String>();
		dataSet.add("a,1,a1");
		dataSet.add("b,1,b1");
		dataSet.add("a,2,a2");
		
		Map<String, List<String>> expMap = new LinkedHashMap<String, List<String>>();
		List<String> expAList = new ArrayList<String>();
		expAList.add("a1");
		expAList.add("a2");
		List<String> expBList = new ArrayList<String>();
		expBList.add("b1");
		expMap.put("a", expAList);
		expMap.put("b", expBList);
		
		assertTrue(expMap.equals(Util.getMap(attributes, keyAttributes, null, dataSet, ",")));
	}
	
	@Test
	public void testGetMapWithEmptyDataSet() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Error constructing map. Empty Data Set.");
		
		List<String> attributes = new ArrayList<String>();
		attributes.add("attr_1");
		attributes.add("attr_2");
		attributes.add("attr_3");
		
		String[] keyAttributes = {"attr_1"};
		String[] valueAttributes = {"attr_3"};
		
		Map<String, List<String>> expMap = new LinkedHashMap<String, List<String>>();
		List<String> expAList = new ArrayList<String>();
		expAList.add("a1");
		expAList.add("a2");
		List<String> expBList = new ArrayList<String>();
		expBList.add("b1");
		expMap.put("a", expAList);
		expMap.put("b", expBList);
		
		assertTrue(expMap.equals(Util.getMap(attributes, keyAttributes, valueAttributes, null, ",")));
	}
	
	@Test
	public void testGetMapWithEmptyDelimiter() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Missing delimiter.");
		
		List<String> attributes = new ArrayList<String>();
		attributes.add("attr_1");
		attributes.add("attr_2");
		attributes.add("attr_3");
		
		String[] keyAttributes = {"attr_1"};
		String[] valueAttributes = {"attr_3"};
		
		List<String> dataSet = new ArrayList<String>();
		dataSet.add("a,1,a1");
		dataSet.add("b,1,b1");
		dataSet.add("a,2,a2");
		
		Map<String, List<String>> expMap = new LinkedHashMap<String, List<String>>();
		List<String> expAList = new ArrayList<String>();
		expAList.add("a1");
		expAList.add("a2");
		List<String> expBList = new ArrayList<String>();
		expBList.add("b1");
		expMap.put("a", expAList);
		expMap.put("b", expBList);
		
		assertTrue(expMap.equals(Util.getMap(attributes, keyAttributes, valueAttributes, dataSet, null)));
	}
	
}