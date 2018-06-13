package balls;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


/**
 * The control logic and main display panel for game.
 */
public class BallBord extends JPanel implements ActionListener{
   private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   private Ball motherball;        

   private Ball ball1;
   private Ball ball2;
   private Ball ball3;
   private Ball ball4;
   private Ball ball5;
   
   private Box gametable;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
  
   private JButton suspend;
   private JButton resume;
   private JPanel bottomPanel;
   
   Thread gameThread;
   private volatile boolean running = true;
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    * 
    * @param width : screen width
    * @param height : screen height
    */
   public BallBord(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      
      // Init the ball at a random location (inside the box) and moveAngle
      motherball = new Ball(320, 400, 15, 5, 90, Color.WHITE); //x=320,y=400,radius=15,speed=0,angle=90,color=white
      ball1 = new Ball(320, 200, 15, 0, 60, Color.RED);
      
      gametable = new Box(0, 0, canvasWidth, canvasHeight, Color.BLACK);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      bottomPanel = new JPanel();
      suspend = new JButton("Suspend");
      suspend.addActionListener(this);
      bottomPanel.add(suspend);
      resume = new JButton("Resume");
      resume.addActionListener(this);
      bottomPanel.add(resume);      
      this.add(bottomPanel, BorderLayout.SOUTH);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height;
            // Adjust the bounds of the container to fill the window
            gametable.set(160, 120, 320, 240);
         }
      });
    
      // Start the ball bouncing
      gameStart();
      

   }
   
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
  
	   gameThread = new Thread() {
         public void run() {
            while (true) {
               // Execute one time-step for the game 
               
            	gameUpdate();
               // Refresh the display
               repaint();
               // Delay and give other thread a chance
               
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) {}
            }
         }
      };
      
      gameThread.start();  // Invoke GaemThread.run()
   }

   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
      motherball.ballmovewithcollisiondetection(gametable , ball1);
      ball1.ballmovewithcollisiondetection(gametable , motherball);

   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the ball
         gametable.draw(g);
         motherball.draw(g);
         ball1.draw(g);

         // Display ball's information
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
      }
  
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==suspend){
		gameThread.suspend();
	}else if(e.getSource()==resume){
		gameThread.resume();
		//motherball.setspeed(30);

	}
	
}
}