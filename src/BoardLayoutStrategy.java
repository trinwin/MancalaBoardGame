import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Interface for the strategy used to draw the Mancala board.
 *
 * @team Like A Boss
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 * @version 1.0
 */

public interface BoardLayoutStrategy{

	/**
	 * Create and return the main board as an Icon
	 * @return the main mancala board
	 */
	Icon getBoard();

	/**
	 * Create main board with pits, mancala and their labels
	 * @param pits - list of all JButtons of pits
	 * @param label - contains main Mancala Board
	 */
	void organizePitsJLabel(ArrayList<JButton> pits, JLabel label);

	/**
	 * Create Stone objects associated with each pits and mancala
	 * @param pits - list of all JButtons of pitss
	 * @param mancalaData - number of stones in each pit
	 */
	void addStones(ArrayList<JButton> pits, int[] mancalaData);
}



