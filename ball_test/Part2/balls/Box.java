package balls;

import java.awt.Color;
import java.awt.Graphics;

public class Box {
	 int minX, maxX, minY, maxY;  // Box's bounds (package access)
	   private Color colorFilled;   // Box's filled color (background)
	   private Color colorBorder;   // Box's border color
	   public Box(int x, int y, int width, int height, Color colorFilled) {
		      minX = x;
		      minY = y;
		      maxX = x + width - 1;
		      maxY = y + height - 1;
		      this.colorFilled = colorFilled;
		   }
		   
		   /** Set or reset the boundaries of the box. */
		   public void set(int x, int y, int width, int height) {
		      minX = x;
		      minY = y;
		      maxX = x + width - 1;
		      maxY = y + height - 1;
		   }

		   /** Draw itself using the given graphic context. */
		   public void draw(Graphics g) {
		      g.setColor(colorFilled);
		      g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
		      g.setColor(colorBorder);
		      g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
		   }
}
