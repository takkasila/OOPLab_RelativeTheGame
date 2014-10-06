import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class CollisionMap {
	
	public ArrayList<Block> CollisionList = new ArrayList<Block>();
	public ArrayList<Color> CollisionDebugColorList = new ArrayList<Color>();
	Random rand;
	public CollisionMap()
	{		
		rand = new Random();
		//Map generating section
		CollisionList.add(new Block(0, 0, 1000, 100, 0, 0));
		CollisionList.add(new Block(500, 0, 200, 100, 2, 0));
		CollisionList.add(new Block(-300, -300, 100, 400, 0, 3));
		CollisionList.add(new Block(-600, 0, 200, 100, 2, 2));
		
		SetStartColor();
	}
	void SetStartColor()
	{
		for (int i = 0; i < CollisionList.size(); i++) {
			CollisionDebugColorList.add(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			
		}
	}
	
	public void Update(float player_x, float player_y)
	{
		for (int i = 0; i < CollisionList.size(); i++) {
			CollisionList.get(i).Update(player_x, player_y);
		}
	}
	
	public void Render(GameContainer gameContainer, Graphics graphics)
	{
		for (int i = 0; i < CollisionList.size(); i++) {
			graphics.setColor(CollisionDebugColorList.get(i));
			CollisionList.get(i).Render(graphics);
			graphics.setColor(Color.black);
			graphics.drawString(""+(i+1), (float)CollisionList.get(i).getX() - Camera.pos_x, (float)CollisionList.get(i).getY() - Camera.pos_y);
			
		}
		
	}
	
	

}
