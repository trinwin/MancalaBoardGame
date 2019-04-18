import java.awt.event.MouseAdapter;

public class PitMouseListener extends MouseAdapter{

	private int mouseID;		//ID of mouse corresponding to a certain button/pit
	
	public PitMouseListener(int mouseID) {
		super();
		this.mouseID = mouseID;
	}
	
	public int getMouseListenerID() {
		return mouseID;
	}

	@Override
	public String toString() {
		return "PitMouseListener [mouseID=" + mouseID + "]";
	}
	

}
