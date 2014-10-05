import java.awt.geom.Rectangle2D;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Block extends Rectangle2D.Float{
	public float pos_x, pos_y;
	float lock_x,lock_y;
	float width, height;
	float factor_x, factor_y;
	
	public Block(float lock_x, float lock_y, float width, float height, float factor_x, float factor_y)
	{
		super(0, 0, width, height);
		this.lock_x = lock_x;
		this.lock_y = lock_y;
		this.width = width;
		this.height = height;
		this.factor_x = factor_x;
		this.factor_y = factor_y;
	}
	
	public void Update(float player_x, float player_y)
	{
		pos_x = factor_x * player_x - lock_x - width/2 + Player.WIDTH/2;
		pos_y = factor_y * (player_y + Player.HEIGHT) - lock_y;
		setRect(pos_x, pos_y, width, height);
		
		
	}
	
	public void Render(Graphics graphics)
	{
		graphics.fillRect(pos_x - Camera.pos_x, pos_y - Camera.pos_y, width, height);
	}

}
