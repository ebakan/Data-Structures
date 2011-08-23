/*DemoShapes by Eric Bakan
 * 9/2/10 P3
 */
package demoShapes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;

public class DemoShapes {

	public static void main(String[] args) {
		//get shape value
		String shape = JOptionPane.showInputDialog("Would you like to draw a circle or a rectangle?").toLowerCase();
		//error checking
		while (!shape.equals("circle") && !shape.equals("rectangle")) {
			shape=JOptionPane.showInputDialog(null, "Please answer either \"circle\" or \"rectangle\"");
		}
		
		//get color in rgb values
		Color color=getColor();
		
		DemoShape drawing;
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//get diameter and make circle
		if (shape.equals("circle")) {
			int diameter=getInt("diameter",0,1050);
			drawing = new DemoShape(diameter,color);
			app.setSize(diameter+100,diameter+100);
		}
		//get height and width and make rectangle
		else {
			int height=getInt("height",0,1050);
			int width=getInt("width",0,1680);
			drawing = new DemoShape(height,width,color);
			app.setSize(width+100,height+100);
		}
		
		//make 800x800 window and display
		app.add(drawing);
		app.setVisible(true);

	}
	
	//get a valid value between min and max inclusive, uses valueName for input prompt
	private static int getInt(String valueName,int min,int max) {
		//run while loop at least once
		//i chose this method over a do loop that way if the first input cannot be parsed, the loop will run again
		int value=min-1;
		String input = JOptionPane.showInputDialog(String.format("What would you like the %s to be?",valueName));
		
		//loop until a valid number is inputted
		while (value<min || value>max) {
			try {
				value = Integer.parseInt(input);
			}
			catch (Exception e) {
			}
			if (value<min || value>max) input=JOptionPane.showInputDialog(String.format("Please enter a valid integer between %d and %d.",min,max));
		}
		return value;
	}
	//use getInt to get 3 values and return a color object
	private static Color getColor() {
		return new Color(getInt("red value",0,255),getInt("green value",0,255),getInt("blue value",0,255));
	}
}