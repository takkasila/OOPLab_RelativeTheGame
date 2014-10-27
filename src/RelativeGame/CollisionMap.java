package RelativeGame;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
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

		//test
		
		//1 Left end
		CollisionList.add(new Block(-2300, -400, 700, 400, 0, 0, Block.Direction.Still));//Wall
		
		
		//2 Start point
		CollisionList.add(new Block(-650, 0, 4300, 500, 0, 0, Block.Direction.Still));//floor
		
		//3 First moving platform
		CollisionList.add(new Block(1700, 0, 400, 100, 2, 0, Block.Direction.Right));
		CollisionList.add(new Block(2950, 0, 2100, 500, 0, 0, Block.Direction.Still));//floor
		
		//4 First floating up platform
		CollisionList.add(new Block(5400, 0, 1200, 500, 0, 0, Block.Direction.Still));//floor
		CollisionList.add(new Block(4400, -50, 600, 100, 0, -0.5f, Block.Direction.Up));
		
		//5 First hurdle
		CollisionList.add(new Block(6500, -20, 50, 200, 0, -0.45f, Block.Direction.Up));
		CollisionList.add(new Block(6500, 0, 1000, 500, 0, 0, Block.Direction.Still));//floor
		
		//6 Second hurdle
		CollisionList.add(new Block(8100, 1300, 200, 100, 0, -5, Block.Direction.Down));
		CollisionList.add(new Block(7900, 500, 200, 100, 0, -3, Block.Direction.Down));
		CollisionList.add(new Block(7700, 1500, 200, 100, 0, -5.5f, Block.Direction.Down));

		CollisionList.add(new Block(7500, -300, 200, 100, 0, 0, Block.Direction.Still));
		CollisionList.add(new Block(7300, -400, 200, 100, 0, 0, Block.Direction.Still));
		CollisionList.add(new Block(7675, -500, 250, 10, 0, 0, Block.Direction.Still));
		CollisionList.add(new Block(7900, -600, 200, 10, 0, 0, Block.Direction.Still));
		CollisionList.add(new Block(8100, -700, 200, 10, 0, 0, Block.Direction.Still));
		CollisionList.add(new Block(8100, -700, 200, 10, 0, 0, Block.Direction.Still));
		CollisionList.add(new Block(8700, -700, 1000, 10, 0, 0, Block.Direction.Still));
		
		
		CollisionList.add(new Block(9050, -20, 100, 300, 0, -2f, Block.Direction.Up));//hurdle
		CollisionList.add(new Block(8050, 0, 2100, 500, 0, 0, Block.Direction.Still));//floor
		
		//7 Ups and Downs
		
		CollisionList.add(new Block(10050, 0, 700, 500, 0, 0, Block.Direction.Still));//floor
		CollisionList.add(new Block(10500, -100, 300, 50, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(10900, 500, 200, 100, 0, -3, Block.Direction.Down));
		CollisionList.add(new Block(11300, 200, 400, 100, 2, 0, Block.Direction.Right));
		CollisionList.add(new Block(11600, 100, 300, 50, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(11800, 0, 300, 50, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(12050, -100, 300, 50, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(11850, 800, 200, 100, 0, -3, Block.Direction.Down));
		CollisionList.add(new Block(12100, 1600, 200, 50, 0, -5, Block.Direction.Down));
		CollisionList.add(new Block(12400, -350, 200, 50, 0, 0, Block.Direction.Still));

		CollisionList.add(new Block(12600, -300, 200, 80, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(12800, -200, 200, 80, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(13000, -100, 200, 80, -2, 0, Block.Direction.Left));
		CollisionList.add(new Block(13200, 0, 200, 80, -2, 0, Block.Direction.Left));
		
		CollisionList.add(new Block(14350, 0, 2000, 500, 0, 0, Block.Direction.Still));
		
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
		
		RenderString(gameContainer, graphics);
		
	}
	
	void RenderString(GameContainer gameContainer, Graphics graphics)
	{
		//graphics.drawString("Life can only be understood back ward; but it must be lived forward", x, y);
		
	}
	
	

}
