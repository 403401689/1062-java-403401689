package p3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	final int MAX = 100;
	private Shape shapes[];
	private int shapeCount;
	private int x1, y1, x2, y2;
	private Color color=Color.black;

	private boolean filled;
	
	private int shapeType=1;
	
	final int RANDOM_SHAPES = 2;
	final int SQUARE = 1;

	
	private Shape currentShape;

	
	Random rand;
	
	public DrawPanel(){
		super();
		shapes = new Shape[MAX];
		rand = new Random();
		addMouseListener(this);
		addMouseMotionListener(this);
		//randgen(10);
		//randgen(10);
	}

	public void randgen(int n) {
		
		for(int i=0;i<n;i++){
			//System.out.println(n+" "+shapeCount+" "+x1+" "+y1+" "+x2+" "+y2);
			x1 = rand.nextInt(600);
			y1 = rand.nextInt(600);
			x2 = rand.nextInt(600);
			y2 = rand.nextInt(600);
			//color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			//color2 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			filled = rand.nextBoolean();
			shapeType = rand.nextInt(RANDOM_SHAPES)+1;
			
		}		
	   repaint();
	}

	public void setShape(int shapeType) {
		this.shapeType = shapeType;
	}

	public void setColor(Color color){
		this.color = color;
		System.out.println("setColor: "+color);
	}

	
	public void clear(){
		System.out.println("DrawPanel: clear()");
		shapeCount=0;
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0;i<shapeCount;i++){
			shapes[i].draw(g);
			shapes[i].toString();
		}
		if(currentShape != null){
			currentShape.draw(g);
		}
	}

	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = x1;
		y2 = y1;
		System.out.println("mousePressed:  "+x1+" "+y1+" "+shapeType);
	    switch(shapeType){
	    
	    case SQUARE:
	    	currentShape = new Line(x1,y1,x2,y2,color,filled);
	    
	    }
	}

	void adjustCurrentY2(){
		/*int w = Math.abs(currentShape.x1-currentShape.x2);
		int h = Math.abs(currentShape.y1-currentShape.y2);
		int d = w-h;
		if(currentShape.y1 < currentShape.y2)
			currentShape.y2 += d;
		else
			currentShape.y2 -= d;*/
	}
	

	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if(shapeType == SQUARE ){
			adjustCurrentY2();
			y2 = currentShape.y2;
		}
		System.out.println("mouseReleased: "+x2+" "+y2);
		switch(shapeType){
		
		case SQUARE:
			shapes[shapeCount++] = new Line(x1,y1,x2,y2,color,filled);
			break;			
	
		}
		currentShape = null;
		repaint();
	}

	
	public void mouseClicked(MouseEvent e) {		
		
			}
			
	
	
	public void mouseDragged(MouseEvent e) {
		currentShape.x2 = e.getX();
		currentShape.y2 = e.getY();
		if(shapeType == SQUARE )
		   adjustCurrentY2();
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
