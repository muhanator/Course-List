
import java.io.*;
import java.util.Scanner;

/** 
 * @author Muhammad Saad Mujtaba
 * This class is used to make Course Objects.
 * It contains the attributes known as the Course's student id, course Name, the number of credits it has,
 * the prerequisites and the the corequisites reqired for that class.
 */
public class Course implements DirectlyRelatable 
{	
	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;		//REMEMBER: course name is always a single word
	
	/**
	 * parametrized constructor which takes all the attributes as parameters and creates an Object of type Course
	 * @param courseID: the ID of the course being created (eg. COMP 201, COMP 248 etc)
	 * @param courseName: The actual name of the Course being created (eg. System Hardware)
	 * @param credit: The number of credits the course being created contains
	 * @param preReqID: The pre-requisite course(s) of the Course being created 
	 * @param coReqID: The co-requisite course(s) of the Course being created
	 */
	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID)
	{
		this.courseID=courseID;
		this.courseName=courseName;
		this.credit=credit;
		this.preReqID=preReqID;
		this.coReqID=coReqID;
	}
	/**
	 * Copy constructor that takes in a Course Object and a String value(used for the CurseID).
	 * The new created copied object will have all of the same attributes except that courseID.
	 * Remember: The course ID will be the passed string
	 * @param c: The course object that is being copied, 
	 * @param s: The coureID attribute that will be used to create a copy of the passed Course object with that course ID
	 */
	public Course(Course c, String s)
	{
		this.courseID= s;
		this.courseName= c.courseName;
		this.credit= c.credit;
		this.preReqID= c.preReqID;
		this.coReqID= c.coReqID;
	}
	
	// setters and getters:
	
	/**
	 * Getter method for courseId of the calling Course Object:
	 * @return: The String representing the courseID attribute of the calling Course object. 
	 */
	public String getCourseID()
	{
		return courseID;
	}
	
	public void setCourseID(String courseID)
	{
		this.courseID=courseID;
	}
	
	/**
	 * Getter method for the Course Name of the calling Course Object.
	 * @return: The String representing the courseName attribute of the calling Course object.
	 */
	public String getCourseName()
	{
		return courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName= courseName;
	}
	
	/**
	 * Getter method for the number of credits the Course Object being called contains.
	 * @return: The double representing the credit attribute of the calling Course object.
	 */
	public double getCredit()
	{
		return credit;
	}
	public void setCredit(double credit)
	{
		this.credit=credit;
	}
	
	/**
	 * Getter method for the pre-requisite ID of the calling Course Object.
	 * @return: The String representing the pre-requisite attribute of the calling Course object.
	 */
	public String getPreReqID()
	{
		return preReqID;
	}
	
	/**
	 * Getter method for the co-requisite ID of the calling Course Object.
	 * @return: The String representing the co-requisite attribute of the calling Course object.
	 */
	public String getCoReqID()
	{
		return coReqID;
	}
	
	/**
	 * Clone() method that prompts the user to enter a new courseID and returns a clone of the calling object 
	 * with the exception of the CourseID. The CourseID will be that assigned by the user when asked. 
	 */
	public Course clone()
	{
		Scanner key= new Scanner(System.in);
		System.out.println("Please enter a new course ID");
		String ID= key.next();
		
		return new Course(this, ID);
	}

	/**
	 * toString() method which returns all the attributes of the calling course object
	 */
	public String toString()
	{
		return ("CourseId= " + this.courseID + ", course Name= " + this.courseName + ", credits= " + this.credit
				+ ", preRecquisite ID= " + this.preReqID + ", coRecquisite ID= " + this.coReqID);
	}
	
	/**
	 * 
	 * @param c: The Course object that is being passed to see if it is the same as the caling Course object
	 * @return: true or false depending whether or not the courses are equal to one another..
	 */
	public boolean equals(Course c)
	{
		return ( this.courseName== c.courseName && this.credit==c.credit
				&& this.preReqID==c.preReqID && this.coReqID==c.coReqID);
	}
	
	/**
	 * This method implements the interface DirectlyRelatabe with the method isDirectlyRelated.
	 * It takes as an object of Course C as a parameter and will return true if C is is a
	 * pre requisite or a co requisite of the current passed object or vice versa(if they are related).
	 * Otherwise it will return false; 
	 */
	public boolean isDirectlyRelated(Course c)
	{
		if (c.getCoReqID().equals(courseID) || c.getPreReqID().equals(courseID) || c.getCourseID().equals(coReqID)
				|| c.getCourseID().equals(preReqID) || c.equals(this))
			return true;
		else
			return false;
	}
}
