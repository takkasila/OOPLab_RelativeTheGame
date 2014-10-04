import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class CollisionMap {
	
	public ArrayList<Rectangle2D> CollisionList = new ArrayList<Rectangle2D>();
	public ArrayList<Color> CollisionDebugColorList = new ArrayList<Color>();
	Random rand;
	public CollisionMap()
	{		
		rand = new Random();
		CollisionList.add(new Rectangle2D.Float(-1000, 400, 2000, 100));// Ground
		CollisionList.add(new Rectangle2D.Float(150, 300, 40, 200));// Wall Left
		CollisionList.add(new Rectangle2D.Float(700, 100, 40, 200));// Wall Right
		CollisionList.add(new Rectangle2D.Float(400, 150, 200, 100));// Ceiling
		
		SetStartColor();
	}
	void SetStartColor()
	{
		for (int i = 0; i < CollisionList.size(); i++) {
			CollisionDebugColorList.add(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		}
	}
	
	public void VisualDebug(GameContainer gameContainer, Graphics graphics)
	{
		for (int i = 0; i < CollisionList.size(); i++) {
			graphics.setColor(CollisionDebugColorList.get(i));
			graphics.fillRect((float)CollisionList.get(i).getX() - Camera.pos_x
					, (float)CollisionList.get(i).getY() - Camera.pos_y
					, (float)CollisionList.get(i).getWidth()
					, (float)CollisionList.get(i).getHeight());
			graphics.setColor(Color.black);
			graphics.drawString(""+(i+1), (float)CollisionList.get(i).getX() - Camera.pos_x, (float)CollisionList.get(i).getY() - Camera.pos_y);
			
		}
		
	}
	
	

}
