import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A concrete strategy used to display Mancala board horizontally
 * when plugged into the context (MancalaBoardView)
 * @author Angel Nguyen, Trinh Nguyen, Diana Sok
 * @version 1.0
 */
public class HorizontalBoardLayout implements BoardLayoutStrategy{

	JButton mancalaB = new JButton();	// JButton representing Mancala B
	JButton mancalaA = new JButton();	// JButton representing Mancala A

	/**
	 * Create and return a horizontal board as an Icon
	 * @return vertical board as an Icon
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
				return 1100;
			}

			@Override
			public int getIconHeight() {
				return 350;
			}
		};

		return board;
	}

	/**
	 * Set up a Horizontal Mancala Board with pits and two mancalas
	 * @param pits - list of all JButtons of pits
	 * @param label - contains main Mancala Board
	 */
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label)
	{
		//Set Mancala A and Mancala B label
		JButton mancalaBLabel = new JButton("B");
		mancalaBLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		mancalaBLabel.setBackground(new Color(102, 51, 0));
		mancalaBLabel.setForeground(Color.WHITE);
		mancalaBLabel.setOpaque(true);
		mancalaBLabel.setBorderPainted(false);

		JButton mancalaALabel = new JButton("A");
		mancalaALabel.setFont(new Font("Arial", Font.PLAIN, 30));
		mancalaALabel.setBackground(new Color(102, 51, 0));
		mancalaALabel.setForeground(Color.WHITE);
		mancalaALabel.setOpaque(true);
		mancalaALabel.setBorderPainted(false);

		//Set Mancala A and Mancala B JButton
		mancalaA.setBackground(new Color(207, 185, 154));
		mancalaA.setPreferredSize(new Dimension(100,70));
		mancalaA.setOpaque(true);
		mancalaA.setBorderPainted(false);

		mancalaB.setBackground(new Color(207, 185, 154));
		mancalaB.setPreferredSize(new Dimension(100,70));
		mancalaB.setOpaque(true);
		mancalaB.setBorderPainted(false);

		label.setLayout(new FlowLayout());
		label.add(mancalaBLabel);
		label.add(mancalaB);

		// JPanel to add pit JButtons onto
		JPanel centerPits = new JPanel();
		centerPits.setOpaque(false);
		centerPits.setLayout(new GridLayout(4, 6, 10, 10));

		// Add pit labels for B to JPanel 
		for(int i = 6; i > 0; i --) {
			JButton pitLabel = new JButton("B" + i);
			pitLabel.setFont(new Font("Arial", Font.PLAIN, 30));
			pitLabel.setBackground(new Color(102, 51, 0));
			pitLabel.setForeground(Color.WHITE);
			pitLabel.setOpaque(true);
			pitLabel.setBorderPainted(false);
			centerPits.add(pitLabel);
		}
		
		// Add pit B JButtons to JPanel
		for (int i = pits.size() - 1; i >= 6; i--) {
			centerPits.add(pits.get(i));
		}
		
		// Add pit A JButtons to JPanel
		for (int i = 0; i < 6; i++) {
			centerPits.add(pits.get(i));
		}

		// Add pit labels for A to JPanel 
		for (int i = 1; i <= 6; i++) {
			JButton pitLabel = new JButton("A" + i);
			pitLabel.setFont(new Font("Arial", Font.PLAIN, 30));
			pitLabel.setBackground(new Color(102, 51, 0));
			pitLabel.setForeground(Color.WHITE);
			pitLabel.setOpaque(true);
			pitLabel.setBorderPainted(false);
			centerPits.add(pitLabel);
		}

		label.add(centerPits);
		label.add(mancalaA);
		label.add(mancalaALabel);
	}

	/**
	 * Create Stone objects associated with each pits and mancala
	 * @param pits - list of all JButtons of pits
	 * @param mancalaData - number of stones in each pit
	 */
	@Override
	public void addStones(ArrayList<JButton> pits, int [] mancalaData) {
		// TODO, consider:
		for(int i = 0; i < mancalaData.length; i++) {
			Stone stones = new Stone(mancalaData[i]);
			stones.setIconHeight(60);
			stones.setIconWidth(100);

			if(i == 6) { 					// Mancala A
				mancalaA.setIcon(stones);
			} else if(i == 13) { 			// Mancala B
				mancalaB.setIcon(stones);
			} else {						// Pits

				if(i > 6) {
					pits.get(i - 1).setIcon(stones);
				} else {
					pits.get(i).setIcon(stones);
				}
			}
		}
	}
}