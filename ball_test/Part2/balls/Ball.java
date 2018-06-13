package balls;

import java.awt.Color;
import java.awt.Graphics;


public class Ball {
	float x , y , radius;
	float lastx , lasty;
	float speedx , speedy;
	float angle , speed;
	private Color color;
	

	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x = x;
		this.y = y;
		      // Convert (speed, angle) to (x, y), with y-axis inverted
		this.speedx = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedy = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
		this.speed = speed;
		this.angle = angleInDegree;
		}
	
	public void ballmovewithcollisiondetection(Box table ,Ball ball) {
	      // Get the ball's bounds, offset by the radius of the ball
	      float ballMinX = table.minX + radius;
	      float ballMinY = table.minY + radius;
	      float ballMaxX = table.maxX - radius;
	      float ballMaxY = table.maxY - radius;
	   
	      
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
	      
	      
	      //two ball distance
	      float xdistance = x - ball.x;
	      float ydistance = y - ball.y;
	      float distance = (float) Math.sqrt (xdistance * xdistance + ydistance * ydistance );
	      
	      
	      //detection collision with other balls
	      if (distance <= 2*radius) {
	    	  
	    	  
	    	  float temp = speed;
	    	  speed = ball.speed;
	    	  ball.speed = temp;
	    	  
	    	  boolean samedegree = 0 < angle && angle <=90 && 0 < ball.angle && ball.angle <= 90
	    			  ||	90 < angle && angle <=180 && 90 < ball.angle && ball.angle <= 180
	    			  ||	180 < angle && angle <=270 && 180 < ball.angle && ball.angle <= 270
	    			  ||	270 < angle && angle <=360 && 270 < ball.angle && ball.angle <= 360; 
	    	  
	    	  if ( samedegree == true  ) {
	    		  if(angle > ball.angle) {
	    		  angle -= 45;
	    		  ball.angle += 45;
	    		  }else
	    		  {
	    			  angle +=45;
	    			  ball.angle -=45;
	    		  }
	    	  } else if(Math.abs(angle - ball.angle) == 0 || Math.abs(angle - ball.angle) == 180){
	    		  angle += 180;
	    		  ball.angle += 180;
	    	  }
	    	  else {
	    		  if (angle > ball.angle) {
	    			  angle +=45;
	    			  ball.angle -=45;
	    		  }
	    		  else {
	    			  angle -= 45;
	    			  ball.angle += 45;
	    		  }
	    	  }

	    	  speedx = (float)(speed * Math.cos(Math.toRadians(angle)));
	    	  x = lastx;
	    	  speedy = (float)(-speed * (float)Math.sin(Math.toRadians(angle)));
	    	  y = lasty;
	    	  
	    	  ball.speedx = (float)(ball.speed * Math.cos(Math.toRadians(ball.angle)));
	    	  ball.x = ball.lastx;
	    	  ball.speedy = (float)(-ball.speed * (float)Math.sin(Math.toRadians(ball.angle)));
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