import java.awt.event.MouseAdapter;


/**
 * PitMouseListener inherits from MouseAdapter in order to use its mousePressed method.  
 * The main addition is the field mouseID, which identifies the mouseListener belonging to a particular pit,
 * which is used to keep track of which pit was clicked on by the user.
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 * @version 1.0
 *
 */
public class PitMouseListener extends MouseAdapter{

	private int mouseID;		//ID of mouse corresponding to a certain button/pit
	
	/**
	 * Constructs a PitMouseListener with a particular identifier.
	 * @param mouseID is the identifier of a particular pit on the board.
	 */
	public PitMouseListener(int mouseID) {
		super();
		this.mouseID = mouseID;
	}
	
	/**
	 * Accessor to the mouseID
	 * @return the integer representing the mouseID
	 */
	public int getMouseListenerID() {
		return mouseID;
	}

	/**
	 * toString method used for testing for bugs in the game.
	 */
	public String toString() {
		return "PitMouseListener [mouseID=" + mouseID + "]";
	}
	

}
