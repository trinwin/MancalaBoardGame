import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaBoardView extends JFrame implements ChangeListener {
	
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
	private StoneStrategy theStrategy; //

	public MancalaBoardView() {
		 
		//TODO
		
		theModel = null;
		theStrategy = null;
		
		setUpPreferences();
	}
	
	public void setUpPreferences() {
		
		//TODO 
		//should have buttons to select number of stones and then initialize the model
		//should have buttons to select style
		//should call models fillInitialBoard
		//should  update theStrategy
		//should call diplayBoard
		
		
//		JFrame preferenceMenu = new JFrame();
//		preferenceMenu.setLayout(new BoxLayout(preferenceMenu, BoxLayout.X_AXIS));
//		
//		JTextField promptForStones = new JTextField();
//		promptForStones.setText("How many stones do you want per pit? Please select:");
//		
//		JButton stones3 = new JButton("3");
//		JButton stones4 = new JButton("4");
//		
//		preferenceMenu.add(promptForStones);
//		preferenceMenu.add(stones3);
//		preferenceMenu.add(stones4);
//		
//		preferenceMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		preferenceMenu.pack();
//		preferenceMenu.setVisible(true);
	}
	
	public void displayBoard()
	{
		setLayout(new BorderLayout(10, 10));
	    
	    JButton a1 = new JButton("A1");  
	    JButton a2 = new JButton("A2");  
	    JButton a3 = new JButton("A3");  
	    JButton a4 = new JButton("A4");  
	    JButton a5 = new JButton("A5");  
	    JButton a6 = new JButton("A6"); 
	    
	    JButton b1 = new JButton("B1");  
	    JButton b2 = new JButton("B2");  
	    JButton b3 = new JButton("B3");  
	    JButton b4 = new JButton("B4");  
	    JButton b5 = new JButton("B5");  
	    JButton b6 = new JButton("B6"); 
	    
	    JButton m1 = new JButton("m1");
	    JButton m2 = new JButton("m2");
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(2, 6, 10, 10));
	    
//	    a2.addMouseListener(new PitMouseListener("mouse #1") {
//	    	public void mousePressed(MouseEvent event) {
//	    		String mouseID = this.getMouseListenerID();
//	    		System.out.println(mouseID);
//	    	}
//	    });
	    
	    JButton[] pitButtons = new JButton[] {b6, b5, b4, b3, b2, b1, m2,
	    		 a6, a5, a4, a3, a2, a1, m1};
	    
//	    for(int i = pitButtons.length - 1; i <= 0; i--) { 
//	    	
//	    	if(i != MANCALA_B && i != MANCALA_A) {
//	    		
//	    		pitButtons[i].addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						pitButtons[3].setText("pressed");
//						// TODO notify model of changes to data (amount of stones in each pit) using move()
//					}
//	    			
//	    		});
//	    		
//	    		//add(pitButtons[i]);
//	    	}
//	    }

	    int counter = 1;		
	    for(int i = pitButtons.length - 1; i >= 0; i--) { 
	    	
	    	if(i != MANCALA_B && i != MANCALA_A) {
	    		
	    		pitButtons[i].addMouseListener(new PitMouseListener( counter ) {

					public void mousePressed(MouseEvent event) {
						int mouseID = this.getMouseListenerID();
						System.out.println(mouseID);
						// TODO notify model of changes to data (amount of stones in each pit) using move()
					}
	    			
	    		});
	    		counter++;
	    		//add(pitButtons[i]);
	    	}
	    }
	    
	    
	    
	    panel.add(b6);
	    panel.add(b5);
	    panel.add(b4);
	    panel.add(b3);
	    panel.add(b2);
	    panel.add(b1);
	    
	    panel.add(a1);
	    panel.add(a2);
	    panel.add(a3);
	    panel.add(a4);
	    panel.add(a5);
	    panel.add(a6);
	    
	    add(panel);
	    add(m1, BorderLayout.EAST);
	    add(m2, BorderLayout.WEST);
	    
//	  //  TODO
//		JButton test1 = new JButton("A test 1");
//		JButton test = new JButton("A test");
//		
//		test.setIcon(new GreenStone());
//		
//		test.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				//call model's move
//				//get the data model 
//				test.setText("pressed");
//				test1.setText("test was pressed");
//				//repaint();
//			}
//			
//		});
//		add(test);
//		
//
//		//test1.setIcon(new GreenStone());
//		test1.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				//model's move
//				test1.setText("pressed");
//				test.setText("test1 was pressed");
//				test1.setIcon(new GreenStone());
//				//repaint();
//			}
//			
//		});
//		add(test);
//		add(test1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		MancalaBoardView a = new MancalaBoardView();
		a.displayBoard();
	}
	
	/**
	 * Gets the number of the pit corresponding to mouse click.
	 * @return the number of the pit corresponding to mouse click
	 */
	public int getPitNumber() {
		
		//TODO
		
		return 0;
	}
	
	public void stateChanged(ChangeEvent e) { //Refer to Cay Hortsmann BarFrame
		
		repaint();
		//if isGameOver find the winner and display as popup
	}
	
}