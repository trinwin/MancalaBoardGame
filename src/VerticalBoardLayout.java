import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VerticalBoardLayout implements BoardLayoutStrategy
{

	JButton mancalaA = new JButton("A");
	JButton mancalaB = new JButton("B");

	/**
	 *
	 * @return
	 */
	public Icon getBoard() {
		Icon board = new Icon() {

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {

				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.WHITE);

				Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, getIconWidth(), getIconHeight());
				g2.fill(rectangle);
			}

			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return 700;
			}

			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return 1000;
			}
		};

		return board;
	}

	/**
	 * Create a Vertical Mancala Board
	 * @param pits
	 * @param label
	 */
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label)
	{
		//Set up Mancala A and B
		mancalaA.setBackground(new Color(207, 185, 154));
		mancalaA.setOpaque(true);
		mancalaA.setBorderPainted(false);

		mancalaB.setBackground(new Color(207, 185, 154));
		mancalaB.setOpaque(true);
		mancalaB.setBorderPainted(false);

		//Set board layout and add 2 Mancala
		label.setLayout(new BorderLayout());
		label.add(mancalaB, BorderLayout.NORTH);
		label.add(mancalaA, BorderLayout.SOUTH);

		//Create A and B pits
		JPanel centerPits = new JPanel();
		centerPits.setLayout(new GridLayout(6, 4, 10, 10));
		centerPits.setOpaque(false);

		for (int i = 1, j = 6, k = 11; i <= 6; i++, j--, k--){
			//Add Pit A Labels
			JButton pitALabel = new JButton("A" + i);
			pitALabel.setFont(new Font("Dotum", Font.PLAIN, 30));
			pitALabel.setBackground(Color.WHITE);
			pitALabel.setOpaque(true);
			pitALabel.setBorderPainted(false);
			centerPits.add(pitALabel);

			//Add JButton A
			centerPits.add(pits.get(i-1));

			//Add JButton B
			centerPits.add(pits.get(k));

			//Add Pit B Labels
			JButton pitBLabel = new JButton("B" + j);
			pitBLabel.setFont(new Font("Dotum", Font.PLAIN, 30));
			pitBLabel.setBackground(Color.WHITE);
			pitBLabel.setOpaque(true);
			pitBLabel.setBorderPainted(false);
			centerPits.add(pitBLabel);
		}

		label.add(centerPits, BorderLayout.CENTER);

	}

	/**
	 * Create Stone objects associated with each pits and mancala
	 * @param pits -
	 * @param mancalaData
	 */
	@Override
	public void addStones(ArrayList<JButton> pits, int[] mancalaData) {
		for(int i = 0; i < mancalaData.length; i++) {

			if(i == 6) { // Mancala A
				System.out.println(mancalaData[i]);
				Stone stones = new Stone(mancalaData[i]);
				stones.setIconHeight(100);
				stones.setIconWidth(20);
				stones.setColorOfStone(Color.BLACK);
				mancalaA.setIcon(stones);

			}else if(i == 13) { // Mancala B
				System.out.println(mancalaData[i]);
				Stone stones = new Stone(mancalaData[i]);
				stones.setIconHeight(100);
				stones.setIconWidth(20);
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