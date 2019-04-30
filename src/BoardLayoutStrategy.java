import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

public interface BoardLayoutStrategy{

	Icon getBoard();
	
	void organizePitsJLabel(ArrayList<JButton> pits, JLabel label);
	
	void addStones(ArrayList<JButton> pits, int[] mancalaData);
}