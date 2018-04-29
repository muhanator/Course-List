package assignment4;
import java.io.*;
import java.util.*;

/**
 * @author Muhammad Saad Mujtaba.
 * This class will be used to create a linkedList of Course Objects which will each be stored in nodes. 
 */
public class CourseList 
{
	/**
	 * Inner class CourseNode: has 2 private attributes which are a Course object named "course" and a pointer
	 * to the next node in the linked list called "next".
	 * This inner class will take Course objects and store them inside a Node and add them to a linkedList. 
	 * 
	 */
	public class CourseNode
	{
		private Course course;
		private CourseNode next;
		
		/**
		 * default constructor: assigns all the attributes to null.
		 */
		public CourseNode()
		{
			course=null;
			next=null;
		}
		/**
		 * parametrized constructor which takes as parameter all the attributes and creates a node using them. 
		 * @param course: the course being added in the node. 
		 * @param next: the pointer to type node pointing to the next node.
		 */
		public CourseNode(Course course, CourseNode next)
		{
			this.course=course;
			this.next=next;
		}
		/**
		 * copy constructor: Takes a courseNode as parameter and creates clone of it using the clone() method.
		 * @param c: The course being cloned/copied.
		 */
		public CourseNode(CourseNode c)
		{
			this.course=c.course.clone();
			this.next= c.next.clone();
		}
		
	//setters and getters:
		/**
		 * Getter method which returns the Course
		 * @return: The course of the calling CourseNode;
		 */
		public Course getCourse()
		{
			return course;
		}
		
		/**
		 * Getter method which returns the CourseNode that points to the next node in the linked list
		 * @return: The pointer next of the calling CourseNode.
		 */
		public CourseNode getCourseNode()
		{
			return next;
		}
		
		/**
		 * setter method that sets the Course of the calling CourseNode.
		 * @param course: parameter that is used to set the Course of the calling CourseNode
		 */
		public void setCourse(Course course)
		{
			this.course=course;
		}
		/**
		 * setter method that sets the CourseNode pointer that points to the next CourseNode
		 * @param next: parameter used to set the next pointer of the calling CourseNode
		 */
		public void setNext(CourseNode next)
		{
			this.next=next;
		}
		
		/**
		 * clone method: clones the passed CourseNode.
		 */
		public CourseNode clone()
		{
			return (new CourseNode(this));
		}
		
		/**
		 * toString() method that prints the attributes of the CourseNode
		 */
		public String toString()
		{
			return (course + " \n=>" + next);
		}
	}
	
//attributes:
	/**private attribute called head which points to the first node in this list object
	 */
	private CourseNode head;
//attribute that indicates the current size of the list(how many nodes are in the list):
	
	/**
	 * default constructor: creates an empty list
	 */
	public CourseList()
	{
		head=null; //if the head is pointing to null, that means that the list is empty
	}
	/**
	 * copy constructor:
	 * @param c
	 */
	public CourseList(CourseList c)
	{
	//if the head of head of c is pointing to null, then the head of the new object will point to null:
		if (c.head==null)
			this.head=null;
		else
		{
			head=null;
			CourseNode t1, t2;
			
			t1=c.head;
			t2=null;
			
			while (t1!=null)
			{
				if (head==null)
				{
					t2=new CourseNode(t1.course.clone(), null);
					head=t2;
				}
				else
				{
					t2.next= new CourseNode(t1.course.clone(), null);
					t2=t2.next;
				}
				t1=t1.next;
			}
			t2=null;
		}
	}
	public CourseList clone()
	{
		return new CourseList(this);
	}
	
	/**
	 * method that adds a course to the start of the linked list:
	 * @param crse: the course that will be added to the start of the calling CourseList
	 */
	public void addToStart(Course crse)
	{
		head= new CourseNode(crse, head);
	}
	
	/**
	 * Method that  returns the size of the linked list:
	 * @return: an integer value representing the size of the CourseList(the number of Course Nodes it contains) 
	 */
	public int size()
	{
		int ctr=0;
		CourseNode t=head;
		while (t!=null)
		{
			ctr++;
			t=t.next;
		}
		return ctr;
	}
	
	/**
	 * method that takes an index in the linked list of courses and adds a course at that index,
	 * while pushing all the other contents down
	 * @param i: represents the index the user wants to insert the course at
	 * @param crse: represents the course that will be inserted at index i
	 */
	public void insertAtIndex(int i, Course crse)
	{
		int index=1;
		CourseNode cn=this.head;
		
	//if the linked list is empty:
		if (cn==null)
			System.out.println("The Linked List is empty and therefore...");
	//if the user wants to put the node at index 0:
		else if (i==0)
		{
			head= new CourseNode(crse,cn);
			cn=null;
		}
		else
		{
		//while node is not pointing to null, point to the next node and increment the size
			while(cn.next!=null && (index!=(i)) && (index!=0))
			{
				cn=cn.next;
				index++;
			}
		}
		
	//if the index exists:
		try
		{
			if ((index)==i)
			{
				cn.next=new CourseNode(crse,cn.next);
				cn=null;
			}
		//if the index does not exist, then throw a NoSuchElementException:
			else 
				throw new NoSuchElementException("No such index exists!");
		}
		catch (NoSuchElementException e)
		{
			System.out.println(e.getMessage());
			System.out.println("System will now exit");
			System.exit(0);
		}
	}
	
	/**
	 * This method takes an index i as parameter and deletes whatever Course node is at that object
	 * in the linked list
	 * @param i: represents the index where a CourseNode is located which wil be deleted
	 */
	public void deleteFromIndex(int i)
	{
		int index=1;
		CourseNode cn= head;
	//if the index is the first one on the list:
		if (cn==null)
			System.out.println("The linked list is empty and therefore...");
		else if (i==0)
		{
			head= cn.next;
			cn=null;
		}
		else 
		{
		//while the next node is not null and while we are not at the index we want to be at:
			while (cn.next!=null && index!=i)
			{
				index++;
				cn=cn.next;
			}
		}
		try
		{
			if (index==i)
			{
				cn.next= cn.next.next;
			}
			else 
				throw new NoSuchElementException("No such index exists");
		}
		catch (NoSuchElementException e)
		{
			System.out.println(e.getMessage());
			System.out.println("System will now exit");
			System.exit(0);
		}
	}
	
	/**
	 * This method deletes the first node in the Course list:
	 */
	public void deleteFromStart()
	{
		if (head==null)
			System.out.println("List is empty and therefore nothing can be deleted from the start");
		else
			head= head.next;
	}
	
	/**
	 * method that is given a course and an index and puts that course at the given index
	 * @param crse: 
	 * @param i
	 * @return
	 */
	public boolean replaceAtIndex(Course crse, int i)
	{
		int ctr=0;
	//if the list is empty:
		if (head==null)
		{
			System.out.println("List is empty and therefore nothing could be replaced at index " + i);
			return false;
		}
	//if the index does not exist in the list:
		else if (this.size()<(i+1))
		{
			System.out.println("Index does not exist in this list");
			return false;
		}
		else
		{
			CourseNode cn=head;
		//while we are not at the index we want to be at:
			while (ctr!=i-1)
			{
				ctr++;
				cn=cn.next;
			}
			
			cn.next=new CourseNode(crse, cn.next);
			return true;
		}
	}
	
	/**
	 * find method that finds the pointer pointing to the passed course Id and returns
	 * that pointer
	 * @param crseID
	 * @return
	 */
	
	/**
	 * PRIVACY LEAK:
	 * @param crseID: finds course ID in the calling Course List
	 * 
	 * Because this method is public, the user has access to it and with the node that this method returns, 
	 * he/she can decide to completely destroy the linked list as they please. If they make this pointer
	 * point to null, it will delete everything that was originally after it. If they make the head
	 * node pointer point to it, it will delete all the course nodes that originally came before this one   
	 * 
	 * @return: returns a pointer to a Course Node that contains the Course id that the
	 * user wants
	 */
	public CourseNode find(String crseID)
	{
	//variable that stores the number of iterations it took to find the course
		int tries=1;
	//if the list is empty:
		if (head==null)
		{
			System.out.println("The CourseList is empty so it does not contain that course");
			return null;
		}
		
		CourseNode cn=head;
		while (cn!=null)
		{
			if (cn.course.getCourseID().equals(crseID))
			{
				System.out.println("The course List does contain that course");
				System.out.println("Number of iterations it took to find it: " + tries);
				return cn;
			}
			cn=cn.next;
			tries++;
		}
		
		System.out.println("That course is not in the Course List");
		System.out.println("It took " + (tries) + " iterations to figure that out!");
		return null;
	}
	
	public CourseNode find2(String crseID)
	{
	//variable that stores the number of iterations it took to find the course
		int tries=1;
	//if the list is empty:
		if (head==null)
		{
			return null;
		}
		
		CourseNode cn=head;
		while (cn!=null)
		{
			if (cn.course.getCourseID().equals(crseID))
			{
				return cn;
			}
			cn=cn.next;
			tries++;
		}
		
		return null;
	}
	
	/**
	 * method that tells user if a course ID exists in the linked list or not:
	 * @param crseID: the Course ID the user wants to know whether or not it is contained in the Course List or not.
	 * @return
	 */
	public boolean contains(String crseID)
	{
		if (this.find(crseID)==null)
			return false;
		else
			return true;
	}
	
	/**
	 * equals method that returns true if 2 CourseLists are equivalent. 
	 * @param cl: the passed CourseList which will be compared to the calling CourseList to see if both are equal
	 */
	public boolean equals(CourseList cl)
	{
		return (this.head.course.getCourseName()== cl.head.course.getCourseName()
				&& this.head.course.getCredit()==cl.head.course.getCredit()
				&& this.head.course.getPreReqID()==cl.head.course.getPreReqID()
				&& this.head.course.getCoReqID()==cl.head.course.getCoReqID());
	}
	
	/**
	 * toString() method that returns the attributes of the CourseList.
	 */
	public String toString()
	{
		
		CourseNode p=head;
//		for (int i=0; i<this.size(); i++)
//		{
//			System.out.println("Course at index ==> " + i + "= " + p);
//			p=p.next;
//		}
		System.out.println("HEAD \n=> " + p);
		return (" ");
	}
	
}
