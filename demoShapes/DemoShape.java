package demoShapes;

import javax.swing.JPanel;
import java.awt.*;

@SuppressWarnings("serial")
public class DemoShape extends JPanel {
	
	//private data
	private int height;
	private int width;
	private int diameter;
	private Color color;
	
	public DemoShape(int height, int width, Color color) {
		this.height=height;
		this.width=width;
		this.color=color;
	}
	
	public DemoShape (int diameter, Color color) {
		this.diameter=diameter;		
		this.color=color;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		int w=getWidth();
		int h=getHeight();
		//if diameter is zero, a rectangle will be generated, otherwise a circle
		//centers rectangle in the center of the screen
		if (diameter==0) {
			g.fillRect((w-width)/2, (h-height)/2, width, height);
			g.setColor(Color.BLACK);
			g.drawRect((w-width)/2, (h-height)/2, width, height);
		} else {
			g.fillOval((w-diameter)/2, (h-diameter)/2, diameter, diameter);
			g.setColor(Color.BLACK);
			g.drawOval((w-diameter)/2, (h-diameter)/2, diameter, diameter);
		}
	}

}
