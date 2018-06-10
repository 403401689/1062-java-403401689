package p3;

import javax.swing.JFrame;

public class ShapeTest {

	public static void main(String[] args) {
		DrawFrame app = new DrawFrame();
		app.setSize(800, 600);
		app.setVisible(true);

		  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		            JFrame frame = new JFrame("A World of Balls");
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            frame.setContentPane(new BallWorld(640, 480)); // BallWorld is a JPanel
		            frame.pack();            // Preferred size of BallWorld
		            frame.setVisible(true);  // Show it
		         }
		      });
	}

}
