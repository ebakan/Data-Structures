/** Eric Bakan + Ryan Barekat
 *  Student.java
 *  
 *  Makes a student that stores the id, name, and age of a student
 */

package org.bcp.bakan;

public class Student
{
	private int id;
	private String name;
	private int age;
	static int currentID;
	
	public Student() {
		this(2010,"Eric Chen",17);
	}
	
	public Student (int year, String name, int age) {
		this.id = year/1000*100000+year%100*1000+currentID++;
		this.name=name;
		this.age=age;
	}
	
	public String toString() {
		return String.format("Name:%s\tID:%s",name,id);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public boolean equals(Student s) {
		return id==s.getId();
	}
	
}