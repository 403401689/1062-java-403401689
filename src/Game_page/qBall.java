package Game_page;
import java.awt.Graphics;
import java.awt.Color;

public class qBall extends Billiard
{
    public qBall(double x, double y,String number,Color color)
    {
        super(x,y,number,Color.white);
    }

    public void paint(Graphics g)
    {
       g.setColor(Color.white);
       g.fillOval((int)super.getX(),(int)super.getY(),20,20);
    }
}
