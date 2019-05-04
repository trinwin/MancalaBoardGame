import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Apply Strategy Pattern that provide 2 styles of Mancala board
 * (Horizontal and Vertical board)
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
	 *
	 * @param pits - list of all JButtons of pitss
	 * @param mancalaData - number of stones in each pit
	 */
	void addStones(ArrayList<JButton> pits, int[] mancalaData);
}



