import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class serves as the model of the MVC for the Mancala game.  This model houses the the data for the number of stones currently 
 * in a particular pit, which is held in 2 arrays.  One array houses the current state of the board while the other houses the previous 
 * state of the board to allow a user a limited number of undos per turn.  An ArrayList is used to hold ChangeListeners that perform an 
 * action once the model notifies them of changes.  MancalaBoardModel also contains accessors and mutators to the data and states of the game.
 * 
 * 
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 * @version 1.0
 *
 */
public class MancalaBoardModel {
	
	private static final int NUMBER_OF_PITS = 14;

	//Position of Mancala A and B in the Array
	private static final int A_MANCALA = 6;
	private static final int B_MANCALA = 13;
	
	private int[] currBoard; 
	private int[] prevBoard;
	private ArrayList<ChangeListener> listeners; //will only contain View Listener
	private boolean lastStoneInMancala;
	private int undos;
	
	/**
	 * Constructs a MancalaBoardModel with specified number of stones in each pit, and 0 stones in Mancalas
	 * @param stonesPerPit the number of stones each pit initially contains
	 */
	public MancalaBoardModel(int stonesPerPit) {
		
		//index 0 - 6 belong to player A, index 7 - 13 belong to player B
		//index 6 and 13 correspond to Mancalas

		//Layout of the currBoard array corresponding to the board labels
        // A1 - A2 - A3 - A4 - A5 - A6 - Mancala A
        // B1 - B2 - B3 - B4 - B5 - B6 - Mancala B

		currBoard = new int[] { stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, 0,
							    stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, stonesPerPit, 0 };
			
		prevBoard = currBoard.clone();
		lastStoneInMancala = false;
		listeners = new ArrayList<>();
		undos = 0;
	}
	
	/**
	 * Constructs an empty MancalaBoardModel.
	 */
	public MancalaBoardModel() {
		
		currBoard = new int[NUMBER_OF_PITS];
		prevBoard = currBoard.clone(); // or null?
		lastStoneInMancala = false;
		listeners = new ArrayList<>(); 
		undos = 0;
		
	}
	
	/**
	 * Sets the number of stones in each pit in the mancala board, excluding the mancalas.
	 * @param stonesPerPit the number of stones in each pit
	 */
	public void fillInitialBoard(int stonesPerPit) {
		
		for (int i = 0; i < currBoard.length; i++) {
			if (i == A_MANCALA || i == B_MANCALA) {
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
	 * Checks if the last stone was dropped in a Mancala.
	 * @return the boolean value of lastStoneInMancala; true allows player another turn
	 */
	public boolean isLastStoneInMancala() //we will use this in Control to prompt player to go again
	{
		return lastStoneInMancala;
	}
	
	/**
	 * Checks if the game is over when pits belonging to either player are all empty.
	 * @return  0 if game is not over
     *          1 if game is over and only A pits are empty
     *          2 if game is over and only B pits are empty
	 */
	public int isGameOver() {

        boolean A_empty = false, B_empty = false;

        //Check if all A's pits are empty
        for (int pitA = 0; pitA < A_MANCALA; pitA++){
            if (currBoard[pitA] != 0){
                A_empty = false;
                break;
            } else A_empty = true;
        }

        //Check if all B's pits are empty
        for (int pitB = 7; pitB < B_MANCALA; pitB++){
            if (currBoard[pitB] != 0){
                B_empty = false;
                break;

            } else B_empty = true;
        }

		if (A_empty) return 1;
		else if (B_empty) return 2;

        return 0;
	}
	
    /**
     * Determines the winner of the game by comparing the number of stones accumulated in each players mancala after 
     * all remaining stones in the board are properly distributed.
     * Winner has the greater number of stones.
     * @param emptyPitFlag value passed in that dictates the player that receives the remaining stones on the board
     * @return 1 if winner is A or
     *         2 if the winner is B or
     *         3 if there is a tie
     */
	public int winner(int emptyPitFlag){

	    if (emptyPitFlag == 1){ //only all A pits are empty
            moveStonesToMancala(7, B_MANCALA); //move leftover stones to Mancala B
        }
	    else if (emptyPitFlag == 2){ //only all B pits are empty
            moveStonesToMancala(0, A_MANCALA); //move leftover stones to Mancala A
        }

	    //Compare number of stones in two mancalas
        if (currBoard[A_MANCALA] > currBoard[B_MANCALA])
            return 1;
        else if (currBoard[A_MANCALA] < currBoard[B_MANCALA])
            return 2;
        else return 3;
	}

	/**
     * Sums up the remaining stones on the board and adds that to the mancala specified by the mancalaPos, 
     * then notifies the listeners of the change in the model's state.
     * @param pitPos the starting position of the summation.
     * @param mancalaPos the position of the mancala that will receive the rest of the stones.
     */
	public void moveStonesToMancala(int pitPos, int mancalaPos){
        for (int i = pitPos; i < mancalaPos; i++){
            currBoard[mancalaPos] += currBoard[i];
            currBoard[i] = 0;
        }
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }
	
	/**
	 * Given the pit number and the number of stones currently in the pit, the function redistributes the stones across
	 * the board according to the rules of the mancala game. 
	 * First exception is when a player has their last stone dropped in their own Mancala, resulting in a free turn.
	 * Second exception is when a player's last stone dropped is in an empty pit on their side of the board.  
	 * This results in the player getting to add the stones in the last pit and the stones in the pit across from that on 
	 * the opponent's side into their own Mancala.
	 * @param pitNumber the pit number chosen by the user
	 */
	public void move(int pitNumber) { //pit that is pressed by user 
		
		boolean turnA = true;
		int ownPitNumber = pitNumber;
		if(pitNumber > 5) {
			turnA = false;
			pitNumber = pitNumber +1;
			ownPitNumber = pitNumber - 7;
		}
		if(currBoard[pitNumber] != 0) {
			prevBoard = currBoard.clone(); //save board prior to move to allow undo option
			lastStoneInMancala = false;
			//save the number of stones in the pit number in variable stoneCount
			int stoneCount = currBoard[pitNumber];
			int oPitNumber = pitNumber; 
			int endingPit = pitNumber + stoneCount;
			
			boolean opponMancReached = false;
			if(ownPitNumber + stoneCount >= 13) {
				opponMancReached = true;
			}
			
			//If a player's last stone lands in their own Mancala, that player gets another turn, so set lastStoneInMancala = true
			if(ownPitNumber + stoneCount == 6) {
				lastStoneInMancala = true;
				System.out.println("You get another turn!");
			}
			
			//remove stones from chosen pit and redistribute them
			for(int i = 1; i <= stoneCount; i++) {
				if ((pitNumber + i) == 14) {
					pitNumber = -1*i;//Looping around the board once b6 pit has been reached
				}
				currBoard[pitNumber + i] = currBoard[pitNumber + i] + 1;
			}
			//set the number of stones in specified pit number to 0
			currBoard[oPitNumber] = 0;
		
			/*A's last stone dropped lands in an empty pit on A's side, so A gets to add the stones in that last pit and the stones 
			on the opponent's side that is across from that last pit, into A's Mancala.*/
			if(turnA && endingPit <= 5 && prevBoard[endingPit] == 0 && !opponMancReached) {
				currBoard[endingPit] = 0;
				int opponStones = currBoard[endingPit + (2*(6-endingPit))];
				currBoard[A_MANCALA] = currBoard[A_MANCALA] + opponStones + 1;
				currBoard[endingPit + (2*(6-endingPit))] = 0;
			}
			/*This time, B's last stone dropped lands in an empty pit on B's side, the same rule applies here.*/
			if(!turnA && endingPit > 6 && endingPit < 13 && prevBoard[endingPit] == 0 && !opponMancReached) {
				currBoard[endingPit] = 0;
				int opponStones = currBoard[endingPit - (2*(endingPit - 6))];
				currBoard[B_MANCALA] = currBoard[B_MANCALA] + opponStones + 1;
				currBoard[endingPit - (2*(endingPit - 6))] = 0;
			}
			
			//When A passes through opponents side and reaches A's side with the last stone, will require at least 8 stones in the starting pit.
			if(turnA && opponMancReached) {
				currBoard[13] = prevBoard[13];
				int nextPitToGetStone = ownPitNumber + stoneCount + 1;
				if(nextPitToGetStone > 13) {
					nextPitToGetStone = nextPitToGetStone - 14;
				}
				currBoard[nextPitToGetStone] = currBoard[nextPitToGetStone] + 1;
				//When A passes through opponent's side and also lands in an empty pit
				if(prevBoard[nextPitToGetStone] == 0) {	
					currBoard[nextPitToGetStone] = 0;
					int opponStones = currBoard[nextPitToGetStone + (2*(6-nextPitToGetStone))];
					currBoard[A_MANCALA] = currBoard[A_MANCALA] + opponStones + 1;
					currBoard[nextPitToGetStone + (2*(6-nextPitToGetStone))] = 0;
				}
			}
			//When B passes through opponents side and reaches B's side with the last stone
			if(!turnA && opponMancReached) {
				currBoard[6] = prevBoard[6];
				int nextPitToGetStone = ownPitNumber + stoneCount + 1;
				if(nextPitToGetStone > 13) {
					nextPitToGetStone = nextPitToGetStone - 14;
				}
				currBoard[nextPitToGetStone + 7] = currBoard[nextPitToGetStone + 7] + 1;
				//B passes through the opponent's side and also lands in an empty pit
				if(prevBoard[nextPitToGetStone + 7] == 0) {
					currBoard[nextPitToGetStone + 7] = 0;
					int opponStones = currBoard[nextPitToGetStone + 7 + (2*(6 - (nextPitToGetStone + 7)))];
					currBoard[B_MANCALA] = currBoard[B_MANCALA] + opponStones + 1;
					currBoard[nextPitToGetStone + 7 + (2*(6 - (nextPitToGetStone + 7)))] = 0;
				}
			}
	
			//to alert listeners of change 
			for (ChangeListener l : listeners) {
				l.stateChanged(new ChangeEvent(this));
			}
		}
	}
	
	/**
	 * Makes the current board equivalent to the previous board.
	 */
	public void undo() {
		
		currBoard = prevBoard.clone();
		//to alert listeners of change 
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}	
	}

	/*
	 * Adds a listener to ArrayList of listeners in the model.
	 */
	public void attach(ChangeListener l) {
		
		listeners.add(l);
	}

}