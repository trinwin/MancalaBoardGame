import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
/**
 * A stone icon paints multiple stones in rows.
 *
 */
public class Stone implements Icon {
		
	private static final int MAX_NUM_STONES_PER_ROW = 6;	// number of stones to draw per row
	private static final int DIAMETER = 10;					// diameter of the stones to draw
	private int stoneCount; 								// number of the stones to draw
	private Color colorOfStone;								// color of the stones to draw
	private int width;										// width of this icon
	private int height;										// height of this icon
	
	/**
	 * Draws 
	 * @param count the number of stones this Stone Icon will draw
	 */
	public Stone(int count) {
		stoneCount = count;
		colorOfStone = Color.BLUE;
		width = MAX_NUM_STONES_PER_ROW * DIAMETER;
		height = MAX_NUM_STONES_PER_ROW * DIAMETER;
	}
	

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		Graphics2D g2 = (Graphics2D) g;
		int dx = x;	//how much x changes for each stone
		int dy = 0;	//how much y changes for each stone
		
		for(int i = 1; i <= stoneCount; i++) {
			
			// if the maximum number of stones per row has been drawn
			// go down one row and draw again
			if(i % MAX_NUM_STONES_PER_ROW == 0)
			{
				dy++;
			}
			
			dx = (x/DIAMETER) + (i % MAX_NUM_STONES_PER_ROW)*DIAMETER; ///10
			
			Ellipse2D.Double stone = new Ellipse2D.Double(dx, y*dy + 10, DIAMETER, DIAMETER);
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