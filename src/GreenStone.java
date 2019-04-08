import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class GreenStone implements StoneStrategy {
	
	private static final int DIAMETER = 10;
	private int stoneCount; 
	
	public GreenStone() {
		stoneCount = 3;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		// TODO
		//paint stoneCount amount of Stones
		
		//trial:
		Graphics2D g2 = (Graphics2D) g;
		int dx = x;
		for(int i = 1; i <= stoneCount; i++) {
			dx = x * i;
			Ellipse2D.Double stone = new Ellipse2D.Double(dx, y, DIAMETER, DIAMETER);
			g2.setColor(Color.GREEN);
			g2.fill(stone);
		}
		
		
		
	}

	@Override
	public int getIconWidth() {
		
		return stoneCount * DIAMETER;
	}

	@Override
	public int getIconHeight() {

		return DIAMETER;
	}

	public void setStoneCount(int stoneCount) {
		
		this.stoneCount = stoneCount;
	}
	
}