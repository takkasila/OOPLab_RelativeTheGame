import org.newdawn.slick.Graphics;


public class Block {
	public float pos_x, pos_y;
	float lock_x,lock_y;
	float width, height;
	float factor_x, factor_y;
	
	public Block(float lock_x, float lock_y, float width, float height, float factor_x, float factor_y)
	{
		this.lock_x = lock_x;
		this.lock_y = lock_y;
		this.width = width;
		this.height = height;
		this.factor_x = factor_x;
		this.factor_y = factor_y;
	}
	
	public void Update(float player_x, float player_y)
	{
		pos_x = factor_x * player_x - lock_x;
		pos_y = factor_x * player_y - lock_y;
	}
	
	public void Render(Graphics graphics)
	{
		graphics.drawRect(pos_x - Camera.pos_x, pos_y - Camera.pos_y, width, height);
	}
}
