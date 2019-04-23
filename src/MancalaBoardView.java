//testing push
//testintint

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaBoardView extends JFrame implements ChangeListener { //
	
//	private static final int PIT_A1 = 1;
//	private static final int PIT_A2 = 2;
//	private static final int PIT_A3 = 3;
//	private static final int PIT_A4 = 4;
//	private static final int PIT_A5 = 5;
//	private static final int PIT_A6 = 6;
//	
//	private static final int PIT_B1 = 8;
//	private static final int PIT_B2 = 9;
//	private static final int PIT_B3 = 10;
//	private static final int PIT_B4 = 11;
//	private static final int PIT_B5 = 12;
//	private static final int PIT_B6 = 13;
	
	private static final int MANCALA_A = 6;
	private static final int MANCALA_B = 13;
	
	private MancalaBoardModel theModel;
	
	private BoardLayoutStrategy boardLayoutStrategy;

	private ArrayList<JButton> pits = new ArrayList<>();
	
	private int undoCount;
	private int turn; //0 - A; 1 - B
	
	/**
	 * Constructs a MancalaBoardView with specified model and
	 * default HorizontalBoardLayout strategy
	 * @param theModel the model
	 */
	public MancalaBoardView(MancalaBoardModel theModel) {
		
		this.theModel = theModel;
<<<<<<< HEAD
		boardLayoutStrategy = new HorizontalBoardLayout();
		undoCount = 0;
		turn = 0;
		
=======
		boardLayoutStrategy = new HorizontalBoardLayout();
		undoCount = 0;
		turn = 0;
>>>>>>> 26065d5a38234d40ef74f326aa7682869b9a3493
		setUpPreferences();
	}
	
	/**
	 * Constructs a MancalaBoardView with no model and
	 * default HorizontalBoardLayout strategy
	 */
	public MancalaBoardView() {
		
		this.theModel = null;
<<<<<<< HEAD
		boardLayoutStrategy = new HorizontalBoardLayout();
		undoCount = 0;
		turn = 0;
		
=======
		boardLayoutStrategy = new HorizontalBoardLayout();
		undoCount = 0;
		turn = 0;
>>>>>>> 26065d5a38234d40ef74f326aa7682869b9a3493
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
				
				// Display the board after updating the strategy
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
	    JButton a1 = new JButton();//"A1"); 
	    JButton a2 = new JButton();//"A2");  
	    JButton a3 = new JButton();//"A3");
	    JButton a4 = new JButton();//"A4"); 
	    JButton a5 = new JButton();//"A5");  
	    JButton a6 = new JButton();//"A6"); 
	    
	    // Buttons representing pits belonging to player B
	    JButton b1 = new JButton();//"B1");  
	    JButton b2 = new JButton();//"B2");  
	    JButton b3 = new JButton();//"B3");  
	    JButton b4 = new JButton();//"B4");  
	    JButton b5 = new JButton();//"B5");  
	    JButton b6 = new JButton();//"B6"); 	    
	    
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
	    
	    for(int i = 0; i < pits.size(); i ++) {
	    	
	    	// Set the background of each pit JButton to light brown
	    	pits.get(i).setBackground(new Color(207, 185, 154));
			pits.get(i).setOpaque(true);
			pits.get(i).setBorderPainted(false);
	    	
			// Add a listener to pits and update model if button is pressed
    		pits.get(i).addMouseListener(new PitMouseListener(i) {
    			public void mousePressed(MouseEvent e) {
    				int mouseID = this.getMouseListenerID();
    				System.out.println(mouseID);
    				theModel.move(mouseID);
    			}
    		});
	    }
	    
<<<<<<< HEAD
	    int [] mancalaData = theModel.getCurrBoard();
=======
	    int [] mancalaData = theModel.getCurrBoard();
>>>>>>> 26065d5a38234d40ef74f326aa7682869b9a3493
	    
	    // Center
	    JLabel center = new JLabel(boardLayoutStrategy.getBoard());
	    boardLayoutStrategy.addStones(pits, mancalaData);
	    boardLayoutStrategy.organizePitsJLabel(pits, center);
	    add(center, BorderLayout.CENTER);
	    
	    // North
	    // practice:
		JButton test1 = new JButton("A test 1");
		JButton test = new JButton("A test");
		
		test.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//call model's move
				//get the data model 
				test.setText("pressed");
				test1.setText("test was pressed");
				//repaint();
			}
			
		});
		
		test1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//model's move
				test1.setText("pressed");
				test.setText("test1 was pressed");
				//test1.setIcon(new GreenStone());
				//repaint();
			}
			
		});

	    JPanel north = new JPanel();
		north.add(test);
		north.add(test1);
		add(north, BorderLayout.NORTH);
		
		//south panel
		JPanel south = new JPanel();
		
		JTextField undoCountText = new JTextField(20);
		undoCountText.setText("Number of undos:");
		
		JTextField player = new JTextField(20);
		player.setText("Player A's Turn");
		
		JButton undoButton = new JButton("undo");
		undoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				undoCount++;
				undoCountText.setText("Number of undos: " + undoCount);
				theModel.undo();
			}
			
		});
		
		south.add(player);
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
		//aView.displayBoard();
		aModel.attach(aView);
	}
	
	public void stateChanged(ChangeEvent e) { //Refer to Cay Hortsmann BarFrame
		int[] mancalaData= theModel.getCurrBoard();
		boardLayoutStrategy.addStones(pits, mancalaData);
		//repaint();
		//if theModel.isGameOver find the winner and display
	}
	
}