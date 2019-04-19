import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface BoardLayoutStrategy{

	public Icon getBoard();
	
	//public void organizePits(ArrayList<JButton> pits, JPanel b); //take out later?
	
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label);
	
	public void addStones(ArrayList<JButton> pits, int[] mancalaData);
}