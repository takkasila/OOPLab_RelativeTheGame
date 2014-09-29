import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player {
	
	final static float MOVE_ACC = 1;
	final static float MAX_MOVE_ACC = 5.5f;
	final static float JUMP_ACC = 1;
	final static float WIDTH = 50;
	final static float HEIGHT = 50;
	final static float MASS = 10;
	final static float GROUND_FRICTION_FROCE = 0.5f;
	
	Image image;
	public float pos_x, pos_y;
	public float vel_x, vel_y;
	public boolean isOnGround = false;
	
	public Player(float start_pos_x, float start_pos_y, String start_image_url) throws SlickException
	{
		pos_x = start_pos_x;
		pos_y = start_pos_y;
		
	}
	
	public void Update(GameContainer gameContainer, int delta)
	{
		Input();
		UpdatePosition();
		GravityCheck();
		FrictionCheck();
		
	}
	
	void Input()
	{
		if(InputController.holding_moveRight)
		{
			Walk_Right();
		}
		if(InputController.holding_moveLeft)
		{
			Walk_Left();
		}
		if(InputController.holding_Jump)
		{
			Jump();
		}
	}
	void UpdatePosition()
	{
		pos_x += vel_x;
		pos_y += vel_y;
		
	}
	void GravityCheck()
	{
		vel_y -= RelativeGame.GRAVITY * RelativeGame.TIME_DELTA_FACTOR;
		if(isOnGround)
		{
			vel_y = 0;
		}
	}
	void FrictionCheck()
	{
		if(isOnGround)
		{
			if(vel_x > 0)
			{
				vel_x -= GROUND_FRICTION_FROCE;
				if(vel_x <0)
					vel_x =0;
			}
			if(vel_x < 0)
			{
				vel_x += GROUND_FRICTION_FROCE;
				if(vel_x >0)
					vel_x =0;
			}
			
		}
	}
	
	public void Render(GameContainer gameContainer, Graphics graphics)
	{
		graphics.fillRect(pos_x, pos_y, WIDTH, HEIGHT);
	}
	
	void Jump()
	{
		vel_y = JUMP_ACC;
	}
	void Walk_Right()
	{
		if(vel_x < MAX_MOVE_ACC)
		{
			vel_x += MOVE_ACC;
		}
		else
		{
			vel_x = MAX_MOVE_ACC;
		}
	}
	void Walk_Left()
	{
		if(vel_x > -MAX_MOVE_ACC)
		{
			vel_x -= MOVE_ACC;
		}
		else
		{
			vel_x = -MAX_MOVE_ACC;
		}
	}
	

}
