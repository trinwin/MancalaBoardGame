import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The View and Controller.
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 *
 */
public class MancalaBoardView extends JFrame implements ChangeListener {

	private static final int LAST_PIT_OF_B = 11;
	private static final int FIRST_PIT_OF_B = 6;

	private static final int LAST_PIT_OF_A = 5;

	private static final int TURN_OF_A = 0;
	private static final int TURN_OF_B = 1;

	private static final int MAX_NUM_OF_UNDOS = 3;

	private MancalaBoardModel theModel;
	private BoardLayoutStrategy boardLayoutStrategy;	// general strategy
	private ArrayList<JButton> pits = new ArrayList<>();// JButtons Representing Pits

	private boolean mancalaReached = false;
	private int undoCountDownA;
	private int undoCountDownB;
	private int turn; //0 - A; 1 - B

	/**
	 * Constructs a MancalaBoardView with specified model and
	 * default HorizontalBoardLayout strategy
	 * @param theModel the model
	 */
	public MancalaBoardView(MancalaBoardModel theModel) {

		this.theModel = theModel;
		boardLayoutStrategy = new HorizontalBoardLayout();
		undoCountDownA = MAX_NUM_OF_UNDOS;
		undoCountDownB = MAX_NUM_OF_UNDOS;
		turn = TURN_OF_A;
		setUpPreferences();
	}

	/**
	 * Constructs a MancalaBoardView with no model and
	 * default HorizontalBoardLayout strategy
	 */
	public MancalaBoardView() {

		this.theModel = null;
		boardLayoutStrategy = new HorizontalBoardLayout();
		undoCountDownA = MAX_NUM_OF_UNDOS;
		undoCountDownB = MAX_NUM_OF_UNDOS;
		turn = TURN_OF_A;
		setUpPreferences();
	}

	/**
	 * JFrame that has buttons to select number of stones and style of board.
	 * After choices are clicked, the Mancala board is visualized.
	 */
	public void setUpPreferences() {

		// JFrame for selecting number of stones and style of board
		JFrame preferenceMenu = new JFrame();
		preferenceMenu.setLayout(new FlowLayout());

		// Prompt for getting number of stones
		JTextField promptForStones = new JTextField(20);
		promptForStones.setText("How many stones do you want per pit?");

		// Combo box for selecting number of stones
		JComboBox<String> stonesCombo = new JComboBox<>();
		stonesCombo.addItem("3");
		stonesCombo.addItem("4");

		// JButton for selecting number of stones
		// Changes the state of model according to combo box selection
		JButton selectStones = new JButton("select");
		selectStones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(stonesCombo.getSelectedItem() == "3") {
					theModel.fillInitialBoard(3);

				} else if(stonesCombo.getSelectedItem() == "4") {
					theModel.fillInitialBoard(4);
				}
			}

		});

		// Prompt for getting the style of board
		JTextField promptForStyle = new JTextField(10);
		promptForStyle.setText("Which style?");

		// Combo box for selecting the style of the board
		JComboBox<String> styleCombo = new JComboBox<>();
		styleCombo.addItem("vertical");
		styleCombo.addItem("horizontal");

		// JButton for selecting style of board
		// Updates the strategy to plug in according to combo box selection
		JButton selectStyle = new JButton("select");
		selectStyle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(styleCombo.getSelectedItem() == "vertical") {
					boardLayoutStrategy = new VerticalBoardLayout();

				} else if(styleCombo.getSelectedItem() == "horizontal") {
					boardLayoutStrategy = new HorizontalBoardLayout();
				}

				displayBoard();
			}

		});

		// Add stone choice options to JFrame
		preferenceMenu.add(promptForStones);
		preferenceMenu.add(stonesCombo);
		preferenceMenu.add(selectStones);

		// Add style choice options to JFrame
		preferenceMenu.add(promptForStyle);
		preferenceMenu.add(styleCombo);
		preferenceMenu.add(selectStyle);

		preferenceMenu.pack();
		preferenceMenu.setVisible(true);

	}

	/**
	 * Visualizes the Mancala board.
	 */
	public void displayBoard()
	{
		setLayout(new BorderLayout(20, 20));

		// Buttons representing pits belonging to player A
		JButton a1 = new JButton();
		JButton a2 = new JButton();
		JButton a3 = new JButton();
		JButton a4 = new JButton();
		JButton a5 = new JButton();
		JButton a6 = new JButton();

		// Buttons representing pits belonging to player B
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		JButton b3 = new JButton();
		JButton b4 = new JButton();
		JButton b5 = new JButton();
		JButton b6 = new JButton();

		// Add JButtons belonging to player A to ArrayList of pits
		pits.add(a1);
		pits.add(a2);
		pits.add(a3);
		pits.add(a4);
		pits.add(a5);
		pits.add(a6);

		// Add JButtons belonging to player B to ArrayList of pits
		pits.add(b1);
		pits.add(b2);
		pits.add(b3);
		pits.add(b4);
		pits.add(b5);
		pits.add(b6);

		//north
		JTextField announcements = new JTextField(40);
		announcements.setText("Play game!");

		for(int i = 0; i < pits.size(); i ++) {

			// Set the background of each pit JButton to light brown
			pits.get(i).setBackground(new Color(207, 185, 154));
			pits.get(i).setPreferredSize(new Dimension(100,70));
			pits.get(i).setOpaque(true);
			pits.get(i).setBorderPainted(false);

			// Add a listener to pits and update model if button is pressed
			pits.get(i).addMouseListener(new PitMouseListener(i) {
				public void mousePressed(MouseEvent e) {
					int mouseID = this.getMouseListenerID();

					//prevents player A from going on player B's turn
					if(turn == TURN_OF_B && mouseID <= LAST_PIT_OF_A) {
						announcements.setText("It's not your turn A! It's B's turn.");
					}
					//prevents player B from going on player A's turn
					else if(turn == TURN_OF_A && mouseID <= LAST_PIT_OF_B && mouseID >= FIRST_PIT_OF_B) {
						announcements.setText("It's not your turn B! It's A's turn.");
					}
					else if (mouseID >= 6 && theModel.getAmountInPit(mouseID + 1) == 0) {
						announcements.setText("oops, no stones in this pit!");
					}
					else if (mouseID < 6 && theModel.getAmountInPit(mouseID) == 0) {
						announcements.setText("oops, no stones in this pit!");
					}
					else if(turn == TURN_OF_A && mouseID <= LAST_PIT_OF_A) {
						theModel.move(mouseID);
						if(theModel.isLastStoneInMancala()) {
							turn = TURN_OF_A;
							announcements.setText("go again!");
							mancalaReached = true;
						} else {
							turn = TURN_OF_B;
							mancalaReached = false;
						}
						undoCountDownB = MAX_NUM_OF_UNDOS;
					}
					else if(turn == TURN_OF_B && mouseID <= LAST_PIT_OF_B && mouseID >= FIRST_PIT_OF_B) {
						theModel.move(mouseID);
						if(theModel.isLastStoneInMancala()) {
							turn = TURN_OF_B;
							announcements.setText("go again!");
							mancalaReached = true;
						} else {
							turn = TURN_OF_A;
							mancalaReached = false;
						}
						undoCountDownA = MAX_NUM_OF_UNDOS;
					}


					//check for game over
					int gameOverFlag = theModel.isGameOver();

					if (gameOverFlag > 1) {
						int winner = theModel.winner(gameOverFlag);
						if (winner == 1){
							announcements.setText("Congratulation! A is the winner");
						} else if (winner == 2){
							announcements.setText("Congratulation! B is the winner");
						}
					}
				}
			});
		}


		int [] mancalaData = theModel.getCurrBoard();

		// Center
		JLabel center = new JLabel(boardLayoutStrategy.getBoard());
		boardLayoutStrategy.addStones(pits, mancalaData);
		boardLayoutStrategy.organizePitsJLabel(pits, center);
		add(center, BorderLayout.CENTER);

		// North
		JPanel north = new JPanel();
		north.add(announcements);
		add(north, BorderLayout.NORTH);

		//south panel
		JPanel south = new JPanel();

		JTextField undoCountText = new JTextField(20);
		undoCountText.setText("Number of undos: 3");

		JButton undoButton = new JButton("undo");
		undoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				if(Arrays.equals(theModel.getCurrBoard(), theModel.getPrevBoard())) {
					undoCountText.setText("Oops! No move to undo");
				}

				else if (turn == TURN_OF_B && undoCountDownA <= 0) {
					undoCountText.setText("Oops! Undo max has been reached.");
				}
				else if (turn == TURN_OF_A && undoCountDownB <= 0) {
					undoCountText.setText("Oops! Undo max has been reached.");
				}
				else if (mancalaReached && turn == TURN_OF_B) {
					undoCountDownB--;
					undoCountText.setText("Number of undos: " + undoCountDownB);
					theModel.undo();
					if(Arrays.equals(theModel.getCurrBoard(), theModel.getPrevBoard())) {
						turn = TURN_OF_A;
					}
					mancalaReached = false;
				}
				else if (mancalaReached && turn == TURN_OF_A) {
					undoCountDownA--;
					undoCountText.setText("Number of undos: " + undoCountDownA);
					theModel.undo();
					if(Arrays.equals(theModel.getCurrBoard(), theModel.getPrevBoard())) {
						turn = TURN_OF_B;
					}
					mancalaReached = false;
				}
				else if (turn == TURN_OF_A) {
					undoCountDownB--;
					undoCountText.setText("Number of undos: " + undoCountDownB);
					theModel.undo();
					turn = TURN_OF_B;
				}
				else if (turn == TURN_OF_B) {
					undoCountDownA--;
					undoCountText.setText("Number of undos: " + undoCountDownA);
					theModel.undo();
					turn = TURN_OF_A;
				}

			}

		});


		south.add(undoCountText);
		south.add(undoButton);

		add(south, BorderLayout.SOUTH);

		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

	public static void main(String[] args) {
		MancalaBoardModel aModel = new MancalaBoardModel();
		MancalaBoardView aView = new MancalaBoardView(aModel);
		aModel.attach(aView);
	}

	/**
	 * Called when the data in the model is changed.
	 *
	 * @param e the event representing the change
	 */
	public void stateChanged(ChangeEvent e) {
		int[] mancalaData= theModel.getCurrBoard();
		boardLayoutStrategy.addStones(pits, mancalaData);
		repaint();
	}

}