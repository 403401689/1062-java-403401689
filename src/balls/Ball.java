package balls;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Formatter;

public class Ball {
	float x , y , radius;
	float lastx , lasty;
	float speedx , speedy;
	private Color color;
	float vx, vy;
	

	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x = x;
		this.y = y;
		      // Convert (speed, angle) to (x, y), with y-axis inverted
		this.speedx = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedy = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
		}
	
	public void ballmovewithcollisiondetection(Box table ,Ball ball) {
	      // Get the ball's bounds, offset by the radius of the ball
	      float ballMinX = table.minX + radius;
	      float ballMinY = table.minY + radius;
	      float ballMaxX = table.maxX - radius;
	      float ballMaxY = table.maxY - radius;
	   
	      //two ball distance
	      float xdistance = x - ball.x;
	      float ydistance = y - ball.y;
	      float distance = (float) Math.sqrt (xdistance * xdistance + ydistance * ydistance );
	      
	      // Calculate the ball's new position
	      lastx = x;
	      lasty = y;
	      x += speedx;
	      y += speedy;
	      // Check if the ball moves over the bounds. If so, adjust the position and speed.
	      if (x < ballMinX) {
	         speedx = -speedx; // Reflect along normal
	         x = ballMinX;     // Re-position the ball at the edge
	      } else if (x > ballMaxX) {
	         speedx = -speedx;
	         x = ballMaxX;
	      }
	      // May cross both x and y bounds
	      if (y < ballMinY) {
	         speedy = -speedy;
	         y = ballMinY;
	      } else if (y > ballMaxY) {
	         speedy = -speedy;
	         y = ballMaxY;
	      }
	      
	      //detection collision with other balls
	      if (distance < 2*radius) {
	    	  speedx = -speedx;
	    	  x = lastx;
	    	  speedy = -speedy;
	    	  y = lasty;
	    	  ball.speedx = -ball.speedx;
	    	  ball.x = ball.lastx;
	    	  ball.speedy = -ball.speedy;
	    	  ball.y = ball.lasty;
	    	
	    		  
	    	  
	      }
	   }


	
	public float getSpeed() {
	      return (float)Math.sqrt(speedx * speedx + speedy * speedy);
	   }
	public float getx() {
		return x;
	}
	public float gety() {
		return y;
	}
	public float getradius() {
		return radius;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}
	
	   
}