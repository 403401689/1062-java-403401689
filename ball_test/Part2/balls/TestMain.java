package balls;

import javax.swing.JFrame;
public class TestMain {

	public static void main(String[] args) {
		  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		            JFrame frame = new JFrame("A World of Balls");
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            frame.setContentPane(new BallBord(640, 480)); // BallWorld is a JPanel
		            frame.pack();            // Preferred size of BallWorld
		            frame.setVisible(true);  // Show it
		         }
		      });
	}

}
