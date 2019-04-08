import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MancalaBoardGame { 
	JFrame f;  
	public MancalaBoardGame()
	{
		 f=new JFrame();  
	      
		    JButton b1=new JButton("1");  
		    JButton b2=new JButton("2");  
		    JButton b3=new JButton("3");  
		    JButton b4=new JButton("4");  
		    JButton b5=new JButton("5");  
		    JButton b6=new JButton("6");  
		    JButton b7=new JButton("7");  
		    JButton b8=new JButton("8");  
		    JButton b9=new JButton("9");  
		    JButton b10=new JButton("6");  
		    JButton b11=new JButton("7");  
		    JButton b12=new JButton("8");  
		    //b1.setIcon();
		    //JButton b19=new JButton("9");        
		    f.add(b1);
		    f.add(b2);
		    f.add(b3);
		    f.add(b4);
		    f.add(b5);  
		    f.add(b6);
		    f.add(b7);
		    f.add(b8);
		    f.add(b9);  
		    f.add(b10);
		    f.add(b11);
		    f.add(b12); 
		    f.setLayout(new GridLayout(2,6)); 
		    f.setSize(600,600); 
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    f.setVisible(true);  
	}
	public static void main(String[] args) {
		
//		MancalaBoardView theView = new MancalaBoardView();
//		
//		MancalaBoardModel theModel = new MancalaBoardModel(3); //the stone count is to be received from view
//		
//		//MancalaBoardController theController = new MancalaBoardController(theView, theModel);
//		
//		theView.setVisible(true);
//		
		new MancalaBoardGame();
	}
}