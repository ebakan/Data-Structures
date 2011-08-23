/** Eric Bakan + Ryan Barekat
 *  Student.java
 *  
 *  Makes a student that stores the id, name, and age of a student
 *  This class cannot have child classes
 */

package org.bcp.bakan;

public final class ChildlessStudent extends Student{
	public ChildlessStudent() {
		super();
	}
	public ChildlessStudent (int year, String name, int age) {
		super(year,name,age);
	}

}
