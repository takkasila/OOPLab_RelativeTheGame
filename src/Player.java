import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player {
	
	final static float MOVE_ACC = 1;
	final static float JUMP_ACC = 1;
	final static float WIDTH = 50;
	final static float HEIGHT = 50;
	final static float MASS = 10;
	
	Image image;
	public float pos_x, pos_y;
	public float vel_x, vel_y; 
	
	public Player(float start_pos_x, float start_pos_y, String start_image_url) throws SlickException
	{
		pos_x = start_pos_x;
		pos_y = start_pos_y;
		
	}
	
	public void Update(GameContainer gameContainer, int delta)
	{
		UpdatePosition();
		GravityCheck();
		
	}
	void UpdatePosition()
	{
		pos_x += vel_x;
		pos_y += vel_y;
		
	}
	void GravityCheck()
	{
		if(vel_y > 0)
		{
			vel_y -= RelativeGame.GRAVITY;
			
			//pretends it hit the ground ground
			if(vel_y < 0)
			{
				vel_y = 0;
			}
		}
	}
	
	public void Render(GameContainer gameContainer, Graphics graphics)
	{
		graphics.fillRect(pos_x, pos_y, WIDTH, HEIGHT);
	}
	
	public void Jump()
	{
		vel_y = JUMP_ACC;
	}
	public void Walk_Right()
	{
		vel_x = MOVE_ACC;
	}
	public void Walk_Left()
	{
		vel_x = -MOVE_ACC;
	}
	

}
