import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HorizontalBoardLayout implements BoardLayoutStrategy{

	JButton mancalaB = new JButton("b"); //to change??????/
	JButton mancalaA = new JButton("a");

//	public void organizePits(ArrayList<JButton> pits, JPanel panel)
//	{
//
//		panel.setLayout(new GridLayout(2, 6, 30, 30));
//		//add txt for label
//		for(JButton pit: pits)
//		{
//			panel.add(pit);
//		}
//	}
	
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label)
	{
		mancalaA.setBackground(new Color(207, 185, 154));
		mancalaA.setOpaque(true);
		mancalaA.setBorderPainted(false);

		mancalaB.setBackground(new Color(207, 185, 154));
		mancalaB.setOpaque(true);
		mancalaB.setBorderPainted(false);

		label.setLayout(new FlowLayout());

		//JButton mancalaB = new JButton("b");
		//JButton mancalaA = new JButton("a");
		
		label.add(mancalaB);
		
		JPanel centerPits = new JPanel();
		centerPits.setOpaque(false);
		centerPits.setLayout(new GridLayout(4, 6, 10, 10));

//		for(int i = 1; i <= 6; i++) {
//			centerPits.add(new JTextField(5));
//		}
//		for(JButton pit: pits) {//must  alter for proper order
//			centerPits.add(pit);
//		}

		for(int i = 6; i > 0; i --) {
			JButton pitLabel = new JButton("B" + i);
			pitLabel.setFont(new Font("Arial", Font.PLAIN, 40));
			pitLabel.setBackground(new Color(102, 51, 0));
			pitLabel.setOpaque(true);
			pitLabel.setBorderPainted(false);
			centerPits.add(pitLabel);
		}
		
		for (int i = pits.size() - 1; i >= 6; i--) {
			centerPits.add(pits.get(i));
		}
		for (int i = 0; i < 6; i++) {
			centerPits.add(pits.get(i));
		}

		for (int i = 1; i <= 6; i++) {
			JButton pitLabel = new JButton("A" + i);
			pitLabel.setFont(new Font("Arial", Font.PLAIN, 40));
			pitLabel.setBackground(new Color(102, 51, 0));
			pitLabel.setOpaque(true);
			pitLabel.setBorderPainted(false);
			centerPits.add(pitLabel);
		}

		label.add(centerPits);
		label.add(mancalaA);
	}
	
//	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel frame)
//	{
//		frame.setLayout(new GridLayout(2, 6, 30, 30));
//		///frame.setVgap(20);
//		//preferredLayoutSize(frame);
//		for(JButton pit: pits)
//		{
//			frame.add(pit);
//		}
//	}
	
	
	public Icon getBoard() {
		
		Icon board = new Icon() {

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(102, 51, 0));

				Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, getIconWidth(), getIconHeight());
				g2.fill(rectangle);
			}

			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return 800;
			}

			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return 400;
			}
		};

		return board;
	}

	@Override
	public void addStones(ArrayList<JButton> pits, int [] mancalaData) {
		// TODO, consider:
		for(int i = 0; i < mancalaData.length; i++) {
	
			if(i == 6) { // Mancala A
				System.out.println(mancalaData[i]);
				Stone stones = new Stone(mancalaData[i]);
				stones.setIconHeight(100);
				stones.setIconWidth(50);
				mancalaA.setIcon(stones);
				
			}else if(i == 13) { // Mancala B
				System.out.println(mancalaData[i]);
				Stone stones = new Stone(mancalaData[i]);
				stones.setIconHeight(100);
				stones.setIconWidth(50);
				mancalaB.setIcon(stones);
				
			} else {
				System.out.println(mancalaData[i]);
				if(i > 6) {
					pits.get(i - 1).setIcon(new Stone(mancalaData[i]));
				} else{
					pits.get(i).setIcon(new Stone(mancalaData[i]));
				}
			}
		}
	}
}