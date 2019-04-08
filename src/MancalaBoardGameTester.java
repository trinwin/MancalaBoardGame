public class MancalaBoardGameTester {
	
	public static void main(String[] args) {
		
		MancalaBoardView theView = new MancalaBoardView();
		MancalaBoardModel theModel = new MancalaBoardModel();
		
		theModel.attach(theView);
	}
	
}