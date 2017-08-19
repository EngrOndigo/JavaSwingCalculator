import javax.swing.JFrame;
public class CalcDriver {

	public static void main(String[] args) {
		
	       Calculator calc = new Calculator();
	       calc.setSize(calc.getWindowWidth(), calc.getWindowHeight() );
	       calc.setVisible(true);
	       calc.setResizable(false);
	       calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       calc.setTitle("Calculator");

	}

}
