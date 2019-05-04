/**
 *  Dr. Kim - Object Oriented Design
 *  @project Mancala Board Game
 *  @team Like A Boss
 *  @author Angel Nguyen, Trinh Nguyen, Diana Sok
 *  @date 05/04/2019
 *  @version 1.0
 */

/**
 * Main class to run Mancala Game
 */
public class MancalaBoardGameTester {
	
	public static void main(String[] args) {
		MancalaBoardModel theModel = new MancalaBoardModel();
		MancalaBoardView theView = new MancalaBoardView(theModel);
		theModel.attach(theView);
	}
}