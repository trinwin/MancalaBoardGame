import java.awt.Component;
import java.awt.Graphics;

public class RedAndBlackStone implements StoneStrategy {

	private int stoneCount; 
	
	public RedAndBlackStone() {
		
		stoneCount = 0;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		// TODO
		//paint stoneCount amount of Stones
	}

	@Override
	public int getIconWidth() {
		
		// TODO 
		
		return 0;
	}

	@Override
	public int getIconHeight() {
		
		// TODO 
		return 0;
	}

	public void setStoneCount(int stoneCount) {

		this.stoneCount = stoneCount;
	}
	
}