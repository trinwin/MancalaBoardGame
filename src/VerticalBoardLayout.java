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
				g2.setColor(new Color(102, 51, 0));

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
				return 720;
			}
		};

		return board;
	}

	/**
	 * Create a Vertical Mancala Board
	 * @param pits - list of all JButtons of pits
	 * @param label - main Mancala Board
	 */
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label)
	{
		//Set up Mancala A and B
		mancalaA.setSize(new Dimension(100, 60));
		mancalaA.setBackground(new Color(207, 185, 154));
		mancalaA.setOpaque(true);
		mancalaA.setBorderPainted(false);

		mancalaB.setPreferredSize(new Dimension(100, 60));
		mancalaB.setBackground(new Color(207, 185, 154));
		mancalaB.setOpaque(true);
		mancalaB.setBorderPainted(false);

		//Set board layout and add 2 Mancala
		label.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 0;
		gbc.gridheight = 3;

//		gbc.weightx = 0.0;
//		gbc.ipady = 20;
//        gbc.gridx = 1;
		gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
        label.add(mancalaB, gbc);

		//Create A and B pits
		JPanel centerPits = new JPanel();
		centerPits.setLayout(new GridLayout(6, 4, 10, 10));
		centerPits.setOpaque(false);

		for (int i = 1, j = 6, k = 11; i <= 6; i++, j--, k--){
			//Add Pit A Labels
			JButton pitALabel = new JButton("A" + i);
			pitALabel.setFont(new Font("Dotum", Font.PLAIN, 30));
			pitALabel.setBackground(new Color(102, 51, 0));
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
			pitBLabel.setBackground(new Color(102, 51, 0));
			pitBLabel.setOpaque(true);
			pitBLabel.setBorderPainted(false);
			centerPits.add(pitBLabel);
		}


		gbc.gridy = GridBagConstraints.RELATIVE;
		label.add(centerPits, gbc);

		gbc.gridy = GridBagConstraints.PAGE_END;
        label.add(mancalaA, gbc);

	}

	/**
	 * Create Stone objects associated with each pits and mancala
	 * @param pits - list of all JButtons of pits
	 * @param mancalaData - number of stones in each pit
	 */
	@Override
	public void addStones(ArrayList<JButton> pits, int [] mancalaData) {
		for(int i = 0; i < mancalaData.length; i++) {
			Stone stones  = new Stone(mancalaData[i]);
			stones.setColorOfStone(Color.BLACK);
			System.out.println(mancalaData[i]);

			if(i == 6) { // Mancala A
				stones.setIconHeight(100);
				stones.setIconWidth(30);
				mancalaA.setIcon(stones);

			}else if(i == 13) { // Mancala B
				stones.setIconHeight(100);
				stones.setIconWidth(30);
				mancalaB.setIcon(stones);
			} else {
				if(i > 6) {
					pits.get(i - 1).setIcon(stones);
				} else{
					pits.get(i).setIcon(stones);
				}
			}
		}
	}

	
}