package assignment4;
import java.io.*;
import java.util.*;


public class EnrolmentResults
{
	/**
	 * This is the main method where all the methods from the Course class and the CourseList class
	 * will be tested along with the DirectlyRelatable interface. 
	 */
	public static void main(String [] args)
	{
	/**
	 * making 2 empty Course Lists:
	 */
		CourseList cl1= new CourseList();
		CourseList cl2= new CourseList();
		
	/**
	 * trying to open the file "Syllabus.txt" in order to read from it
	 */
		Scanner sc=null;
		String line="";
		String courseID="";
		String courseName="";
		double credit=0;
		String preReqID="";
		String coReqID="";
		
		String line2="";
		boolean first=true;
		
		String lineArray[];
		String allTheCourses="";
		
		/**
		 * create a course from the syllabus file and add it to the linked list
		 */
		try
		{
		sc= new Scanner(new FileInputStream("Syllabus.txt"));
	
	/**
	 * while the file has next line
	 */
		while (sc.hasNextLine())
		{
			line=sc.nextLine();
			lineArray= line.split("\t");
			
			courseID="";
			courseName="";
			credit=0;
			preReqID="";
			coReqID="";
		//1st line always has 3 
			if (lineArray.length<2)
				continue;
			else
			{
				
				courseID= lineArray[0];
				
				courseName= lineArray[1];
				if (lineArray[2].contains("3") && !lineArray[2].contains("3.5"))
					credit= 3;
				else if (lineArray[2].contains("3.5"))
					credit= 3.5;
				else credit=4;
				
				line=sc.nextLine();
				lineArray=line.split("\t");
				if (line.contains("P"))
					if (lineArray.length>1)
						preReqID= lineArray[1];
				line=sc.nextLine();
				lineArray=line.split("\t");
				if (line.contains("C"))
					if (lineArray.length>1 && !lineArray[1].contains(" ") && !lineArray[1].equals(""))
						coReqID= lineArray[1];
			}
		/**If the "allTheCourses" string does not contain the course Id already, then you make the course:*/
			if (!allTheCourses.contains(courseID))
			{
			/**
			 * creating the course object:
			 */
				Course c= new Course(courseID, courseName, credit, preReqID, coReqID);
				allTheCourses+= courseID + " ";
				
			//adding the course to the start of the Course List 1
				System.out.println(c);
			/**Add the course to the start of the first Course List:*/
				cl1.addToStart(c);
			}
		}
		System.out.println();
//		System.out.println(cl1);
		sc.close();
		}
	/**Catching the the fileNotFoundException if the file does not exist*/
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		Scanner key= new Scanner(System.in);
	
		System.out.println("Please enter the name of the Request file");
		String id= key.next();
		
		try
		{
			sc= new Scanner(new FileInputStream(id));
		/**Creating 2 arraylists of type strings for courses requested and completed*/
			ArrayList<String> finishedCourses= new ArrayList<String>(5);
			ArrayList<String> requestedCourses= new ArrayList<String>(5);
			ArrayList<String> enrolledCourses= new ArrayList<String>(5);
			
		/**While the opened file has lines to read: */
			while (sc.hasNextLine())
			{
				if (first==true)
				{
					line2=sc.nextLine();
					first=false;
				}
				
			/**if the line says finished: */
				if (line2.contains("Finished"))
				{
					line2=sc.nextLine();
				/**while the line does not equal requested keep reading the lines: */
					while (!line2.contains("Requested"))
					{
					/**add the course to the finished course list: */
						finishedCourses.add(line2);
						line2=sc.nextLine();
					}
				}
			/**if the line says requested: */
				else if (line2.contains("Requested"))
				{
					while (sc.hasNextLine())
					{
						line2=sc.nextLine();
						requestedCourses.add(line2);
					}
				}
			}
			
			System.out.println("Displaying finished courses:");
			for (String s: finishedCourses)
			{
				System.out.println(s);
			}
			System.out.println("Displaying requested courses:");
			for (String s: requestedCourses)
			{
				System.out.println(s);
			}
			
			//use the fine method to find an address to the node
			//find has privacy since it is public 
			
			//if you do not find the prerequisite see if it is the corecquiste
			//2 arraylists one for corecquisite and 1 for prerecquisite use the contains to see if the course is there
			
			//go to the course object from the linked list by looping through it to find the requested course
			//and grab the corecquisites and prerecquisites
			
			//you ask for comp 228, you get the prerecquites and co recquisites from the linked list
			//check if the prereq and coreqs are in the array list
			
			//you go through the requested courses array list one at a time
			//you find the coreqs and the prereqs of that class
			//you check if those coreqs and prereqs are present in completed courses array list
			System.out.println();
			
			for (String s: requestedCourses)
			{
			//find the requested course in the course list:
				CourseList.CourseNode cn=cl1.find2(s);
			//getting the preReqID and coReqID:
				String preReq= cn.getCourse().getPreReqID();
				String coReq= cn.getCourse().getCoReqID();
				
			//if the PreReqID is one of the classes that has already been finished by the student:
				if (finishedCourses.contains(preReq) && finishedCourses.contains(coReq))
				{
					System.out.println("Student can enroll in " + s + " as he/she has completed the "
							+ "pre-requisite " + preReq + " and the co-requisite " + coReq);
					enrolledCourses.add(s);
				}
				else if (finishedCourses.contains(preReq))
				{
					System.out.println("Student can enroll in " + s + " as he/she has completed the "
							+ "pre-requisite " + preReq);
					enrolledCourses.add(s);
				}
				else if (finishedCourses.contains(coReq) || enrolledCourses.contains(coReq))
				{
					System.out.println("Student can enroll in " + s + " as he/she  is enrolling for "
							+ "co-requisite " + coReq);
					enrolledCourses.add(s);
				}
				else if(!finishedCourses.contains(preReq) && !enrolledCourses.contains(coReq))
				{
					System.out.println("Student can't enrol in " + s + " course as he/she does not have sufficient "
										+ "background needed");
				}
			}
		//if the student has not requested to take any courses then display no enrollement courses found:
			if (requestedCourses.size()==0)
				System.out.println("No enrollment courses found");
			sc.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File could not be opened or may not exist");
			System.out.println("System will now exit ");
			System.exit(0);
		}
		
		/**
		 * asking the user a few times to enter a few course ID's and searching through course list 1
		 * to see if you can find it and display the number of iterations perfomed to find it 
		 */
		System.out.println("\n******************Testing the find() method***********************************");
		System.out.println("\nEnter a course ID and i will see if it is contained in the Course List");
		String c= key.next();
		cl1.find(c);
		System.out.println("\nEnter another course ID and i will see if it is contained in the Course List");
		String c2=key.next();
		cl1.find(c2);
		System.out.println("\nEnter one last course ID and i will see if it is contained in the Course List");
		String c3=key.next();
		cl1.find(c3);
		
		/**
		 * Testing the isDirectly() method:
		 */
		
		Course course1= new Course("ENGR301", "Engineering management", 3, "ENGR201", "ENGR202");
		Course course2= new Course("ENGR391", "Numerical method", 4, "COMP248", "ENGR233");
		Course course3= new Course("ENGR233", "Advanced Calculus", 3.5, "ENGR201", "ENGR213");
		
		System.out.println();
		System.out.println("*****************************Testing the isDirectlyRelated() method *****************************************************");
		System.out.println(course1);
		System.out.println(course2);
		System.out.println(course3);
		System.out.println("Course " + course1.getCourseID() + " and Course " +  course2.getCourseID() + 
							" are directly related is: " + course1.isDirectlyRelated(course2));
		System.out.println("Course " + course2.getCourseID() + " and Course " +  course3.getCourseID() + 
							" are directly related is: " + course2.isDirectlyRelated(course3));
		
	/**
	 * ********************TESTING THE COURSE METHODS:*****************************************
	 */
		System.out.println("\n*********************************************************************************************");
		System.out.println("Testing the Course class methods: ");
	//Testing the equals() method from the Course class:
		System.out.println("********************Testing the equals() method ****************************************");
		System.out.println("Course " + course1.getCourseID() + " and Course " +  course2.getCourseID() + " are equal is: "
							+ course1.equals(course2));
		
	//Testing the clone() method from the Course class:
		System.out.println("I am now cloning the Course " + course1.getCourseID());
	//Course 4 is the cloned() class of Course 1
		Course course4= course1.clone();
		System.out.println("Course " + course1.getCourseID() + " and the its clone are equal is " + course1.equals(course4));
		System.out.println("Course " + course4.getCourseID() + ": \n" + course4);
		System.out.println("Now i am setting the course name of the Course " + course4.getCourseID() 
							+ " to \"Engineering class\""); 
		course4.setCourseName("Engineering class");
		System.out.println("After the change the two courses are equal is " + course4.equals(course1));
		
		/**
		 * ***********************************TESTING THE COURSE LIST METHODS ******************************************
		 */
		System.out.println("\n**********************Testing the Course List methods***************************");
		System.out.println("Adding course 1 to the start of Course Node 2");
		cl2.addToStart(course1);
		System.out.println("Size of Course List 2= " + cl2.size());
		System.out.println("Adding course 2 to the start of Course Node 2");
		cl2.addToStart(course2);
		System.out.println("Size of the Course List 2= " + cl2.size());
		System.out.println("Adding course 3 to the start of Course Node 2");
		cl2.addToStart(course3);
		System.out.println("Size of the Course List 2= " + cl2.size());
		System.out.println("NOW WE ARE CHECKING TO SEE IF COURSE LIST 1 AND COURSE LIST 2 ARE EQUAL");
		System.out.println("cl1 and cl2 are equal is= " + cl1.equals(cl2));
		System.out.println("PRINTING THE CONTENTS OF CL1");
		System.out.println(cl1);
		System.out.println("PRINTING THE CONTENTS OF CL2");
		System.out.println(cl2);
		System.out.println("NOW I AM DELETING THE FIRST NODE FROM THE COURSE LIST 2");
		cl2.deleteFromStart();
		System.out.println("Printing cl2:");
		System.out.println(cl2);
		System.out.println("Now i am deleting whatever was at index 1 of course node 2");
		cl2.deleteFromIndex(1);
		System.out.println("Printing cl2: ");
		System.out.println(cl2);
		System.out.println("Now i am inserting course 3 at index 1 of cl2");
		cl2.insertAtIndex(1, course3);
		System.out.println("Printing cl2:");
		System.out.println(cl2);
		System.out.println("Now I am deleting whatever was at the start(index=0) of cl2");
		cl2.deleteFromStart();
		System.out.println("Displaying cl2: ");
		System.out.println(cl2);
		System.out.println("Now I am making a copy of Course List 1 and calling it cl3");
		CourseList cl3= new CourseList(cl1);
		System.out.println("Displaying cl1:");
		System.out.println(cl1);
		System.out.println("displaying cl3:");
		System.out.println(cl3);
		System.out.println("Now I am deleting what ever was at the start of cl3");
		cl3.deleteFromStart();
		System.out.println("Displaying cl1: ");
		System.out.println(cl1);
		System.out.println("Displaying cl3: ");
		System.out.println(cl3);
		System.out.println("Now i am inserting course 1 at index 4 of cl2");
		cl2.insertAtIndex(4, course1);
	}
}
