import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;

/**
 * A stone icon paints multiple stones in rows.
 *
 * @team Like A Boss
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 * @version 1.0
 */

public class Stone implements Icon {
		
	private static final int MAX_NUM_STONES_PER_ROW = 7;	// number of stones to draw per row
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
		colorOfStone = Color.WHITE;
		width = MAX_NUM_STONES_PER_ROW * DIAMETER;
		height = MAX_NUM_STONES_PER_ROW * DIAMETER;
	}
	
	/**
	 * Draws the stones.
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		Graphics2D g2 = (Graphics2D) g;

		for(int i = 1; i <= stoneCount; i++) {
			if (i == 1) x = DIAMETER;
			else if ((i-1) % MAX_NUM_STONES_PER_ROW == 0 ) {
				x = DIAMETER;
				y += (DIAMETER + 1);

			} else x += (DIAMETER + 1);

			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, DIAMETER, DIAMETER);
			g2.setColor(colorOfStone);
			g2.fill(stone);
		}	
	}
	
	/**
	 * Get width of the stones.
	 * @return width
	 */
	@Override
	public int getIconWidth() {
		return width;
	}

	/**
	 * Get height of the stones.
	 * @return height
	 */
	@Override
	public int getIconHeight() {
		return height;
	}

	/**
	 * Sets the number of stones in each pit.
	 * @param stoneCount number of stones per pit
	 */
	public void setStoneCount(int stoneCount) {
		this.stoneCount = stoneCount;
	}
	
	/**
	 * Sets the color for the stones.
	 * @param theColor color of the stones
	 */
	public void setColorOfStone(Color theColor){
		colorOfStone = theColor;
	}
	
	/**
	 * Sets the width of the stone.
	 * @param width of stone
	 */
	public void setIconWidth(int width){
		this.width = width;
	}
	
	/**
	 * sets the height of the stone.
	 * @param height of stone
	 */
	public void setIconHeight(int height){
		this.height = height;
	}
}