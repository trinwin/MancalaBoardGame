import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaBoardModel {
	
	private static final int NUMBER_OF_PITS = 14;
	private static final int A_MANCALA = 6;
	private static final int B_MANCALA = 13;
	
	private int[] currBoard; 
	private int[] prevBoard;
	private ArrayList<ChangeListener> listeners; //will only contain View Listener
	private boolean lastStoneInMancala; //ungo, turn
	
	/**
	 * Constructs a MancalaBoardModel with specified number of stones in each pit, and 0 stones in Mancalas
	 * @param stonesPerPit the number of stones each pit initially contains
	 */
	public MancalaBoardModel(int stonesPerPit) {
		
		//index 0 - 6 belong to player A, index 7 - 13 belong to player B
		//index 0 and 7 correspond to Mancalas
		currBoard = new int[] { 0, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit,
							    0, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit };
			
		prevBoard = currBoard.clone();
		lastStoneInMancala = false;
		listeners = new ArrayList<>();
	}
	
	public MancalaBoardModel() {
		
		currBoard = new int[NUMBER_OF_PITS];
		prevBoard = currBoard.clone(); // or null?
		lastStoneInMancala = false;
		listeners = new ArrayList<>(); 
		
	}
	
	public void fillInitialBoard(int stonesPerPit) {
		
		for(int i = 0; i < currBoard.length; i++) {
			
			if(i == A_MANCALA || i == B_MANCALA) {
				
				currBoard[i] = 0;
			} else {
				
				currBoard[i] = stonesPerPit;
			}
		}
		
		prevBoard = currBoard.clone();
	}
	
	/**
	 * Gets the current board.
	 * @return the current board
	 */
	public int[] getCurrBoard() {
		
		return currBoard.clone();
	}
	
	/**
	 * Gets the previous board.
	 * @return the previous board
	 */
	public int[] getPrevBoard() {
		return prevBoard.clone();
	}
	
	/**
	 * Gets the number of stones in specified pit number.
	 * @param pitNumber the pit number 
	 * @return the number of stones in the specified pit number
	 */
	public int getAmountInPit(int pitNumber) {
		
		return currBoard[pitNumber];
	}
	
	/**
	 * Checks if last stone was dropped in a Mancala
	 * @return the value oflastStoneInMancala
	 */
	public boolean isLastStoneInMancala() //we will use this in Control to prompt player to go again
	{
		return lastStoneInMancala;
	}
	
	/**
	 * Checks if the game is over when pits belonging to either player are all empty.
	 * @return true if the pits belonging to any player are all empty
	 */
	public boolean isGameOver() {
		
		// to be implemented
		//just check if row of pits belonging to either player is all 0
		//remember to not include value in Mancala while checking!
		
		return false;
	}
	
	/**
	 * Updates board according to the number of stones in pit chosen 
	 * @param pitNumber the pit number 
	 */
	public void move(int pitNumber) { //pit number calculated in view, provided by Control
		
		// to be implemented 
		
		prevBoard = currBoard.clone(); //save board prior to move to allow undo option
		lastStoneInMancala = false;
		
		//put implementation below:
		
		//pseudo code to consider:
		//save the number of stones in the pit number in variable stoneCount
		
		//set the number of stones in specified pit number to 0
		
		//all of the following changes are done in currBoard:
		//increment each element in currBoard by one from right to left starting with index: pitNumber - 1 
		//until there are no more stones
		
		//remember: a player can only drop a stone into her Mancala (either index 0 or 7)
		//if pitNumber > 6, a stone can be dropped in B's Mancala, index 7, but cannot be dropped in A's Mancala
		//if pitNumber < 7, a stone can be dropped in A's Mancala, index 0, but cannot be dropped in B's Mancala
		//remember: if the player's last stone lands on an empty pit on her side, she captures the stones opposite hers
		//on her opponent's side
		
		//remember: if the player's last stone lands in her Mancala, she gets another turn
		//in this case, just set lastStoneInMancala to True
		
		//to alert listeners of change
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}	
	}
	
	/**
	 * Makes the current board equivalent to the previous board.
	 */
	public void undo() {
		
		currBoard = prevBoard.clone();
	}
	
	public void attach(ChangeListener l) {
		
		listeners.add(l);
	}
}