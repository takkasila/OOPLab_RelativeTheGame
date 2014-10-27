package RelativeGame;
import java.awt.geom.Rectangle2D;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Block extends Rectangle2D.Float{
	public float pos_x, pos_y;
	float lock_x,lock_y;
	float width, height;
	float factor_x, factor_y;
	
	public enum Direction
	{
		Still
		, Left
		, Right
		, Up
		, Down
	};
	Direction direction;
	
	public Block(float lock_x, float lock_y, float width, float height, float factor_x, float factor_y, Direction direction)
	{
		super(0, 0, width, height);
		this.lock_x = lock_x;
		this.lock_y = lock_y;
		this.width = width;
		this.height = height;
		this.factor_x = factor_x;
		this.factor_y = factor_y;
		this.direction = direction;
	}
	
	public void Update(float player_x, float player_y)
	{
		
		if(direction == Direction.Still)
		{
			pos_x = lock_x - width/2f;
			pos_y = lock_y;
			
		}
		else if(direction == Direction.Right)
		{
			pos_x = factor_x * (player_x + Player.WIDTH/2f) - lock_x - width/2;
			pos_y = lock_y;
		}
		else if(direction == Direction.Left)
		{
			pos_x = factor_x * player_x + 3*lock_x;
			pos_y = lock_y;
		}
		else if(direction == Direction.Up)
		{
			pos_x = lock_x - width/2f;
			pos_y = factor_y * (player_y + Player.HEIGHT) - lock_y ;
			pos_y *= -1;
		}
		else if(direction == Direction.Down)
		{
			pos_x = lock_x - width/2f;
			pos_y = factor_y * (player_y + Player.HEIGHT) - lock_y ;
		}
		
		
		
		
		setRect(pos_x, pos_y, width, height);
		
		
	}
	
	public void Render(Graphics graphics)
	{
		graphics.fillRect(pos_x - Camera.pos_x, pos_y - Camera.pos_y, width, height);
	}

}
