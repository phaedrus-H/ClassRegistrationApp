# ClassRegistrationApp
Steps to execute
1. Import the project into an IDE
2. Run the file Enrollment.java - Check the console for answers to the 5 questions asked
3. Run each fo the following test classes to execute all the unit test cases 
	a. EnrollmentTest.java - Test class for Enrollment.java
	b. EnrollmentDataSetTest.java - Test class for EnrollmentDataSet.java
	c. UtilTest.java - Test class for Util.java

Notes
1. The FILE which acts as the database is stored in the src folder of the project directory structure, dataset.csv. If the file is altered, please ensure the changes are saved to this file.
2. If another file is being used to run the code, please copy it to the src folder of the project directory structure and set the variable 'fileName' (in the main function of Enrollment.java) to the name of the file being used.
3. If the file has another delimiter other than ",", it can be assigned to the variable 'delimiter' (in the main function of Enrollment.java).
4. The csv files starting with test* are used for different unit tests.
5. Application is written in Java 8.
6. Unit test cases are written in JUnit 4.
