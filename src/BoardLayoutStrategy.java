import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 * @version 1.0
 * Interface for the strategy used to draw the Mancala board.
 */
public interface BoardLayoutStrategy{

	Icon getBoard();
	
	void organizePitsJLabel(ArrayList<JButton> pits, JLabel label);
	
	void addStones(ArrayList<JButton> pits, int[] mancalaData);
}