import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VerticalBoardLayout implements BoardLayoutStrategy
{

	
//	public void organizePits(ArrayList<JButton> pits, JPanel panel)
//	{
//		// TODO : something like below!
//		// whats below right now is not working properly though
//		panel.setLayout(new GridLayout(6, 2));
//		
//		for(int i = 0; i < pits.size()/2; i ++)
//		{
//			panel.add(pits.get(pits.size() - 1 - i));
//			panel.add(pits.get(1));
//		}
//	}

	public Icon getBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label)
	{
		//return null;
	}

	@Override
	public void addStones(ArrayList<JButton> pits, int[] mancalaData) {
		// TODO Auto-generated method stub
		
	}

	
}