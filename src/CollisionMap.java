import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class CollisionMap {
	
	public ArrayList<Rectangle2D> CollisionList = new ArrayList<Rectangle2D>();
	public CollisionMap()
	{		
		CollisionList.add(new Rectangle2D.Float(0, 400, 800, 100));// Ground
		CollisionList.add(new Rectangle2D.Float(150, 200, 40, 200));// Wall Left
		CollisionList.add(new Rectangle2D.Float(700, 100, 40, 200));// Wall Right
		//CollisionList.add(new Rectangle2D.Float(100, 150, 600, 100));// Ceiling 
	}
	
	public void VisualDebug(GameContainer gameContainer, Graphics graphics)
	{
		for (int i = 0; i < CollisionList.size(); i++) {
			graphics.fillRect((float)CollisionList.get(i).getX()
					, (float)CollisionList.get(i).getY()
					, (float)CollisionList.get(i).getWidth()
					, (float)CollisionList.get(i).getHeight());
		}
		
	}
	
	

}
