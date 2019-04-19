import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

public class Stone implements Icon {
	
	private static final int DIAMETER = 10;
	private int stoneCount; 
	private Color colorOfStone;
	private int width;
	private int height;
	
	public Stone(int count) {
		stoneCount = count;
		colorOfStone = Color.BLUE;
		width = 5 * DIAMETER;
		height = 5 * DIAMETER;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		// TODO
		//paint stoneCount amount of Stones
		
		//trial:
		Graphics2D g2 = (Graphics2D) g;
		int dx = x;
		int dy = y;
		for(int i = 1; i <= stoneCount; i++) {
			
			//int t = i/7;
			//dy = y;
			dx = x + i*DIAMETER;

//			if(t > 0)
//			{
//				dx = x;
//			}
			Ellipse2D.Double stone = new Ellipse2D.Double(dx, y, DIAMETER, DIAMETER);
			g2.setColor(colorOfStone);
			g2.fill(stone);
		}	
	}
	
	@Override
	public int getIconWidth() {
		
		return width;
	}

	@Override
	public int getIconHeight() {

		return height;
	}

	public void setStoneCount(int stoneCount) {
		
		this.stoneCount = stoneCount;
	}
	
	public void setColorOfStone(Color theColor){
		colorOfStone = theColor;
	}
	
	public void setIconWidth(int width){
		this.width = width;
	}
	
	public void setIconHeight(int height){
		this.height = height;
	}
}