package RelativeGame;
import java.awt.geom.Rectangle2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player {
	
	final static float MOVE_ACC = 60;
	final static float MAX_MOVE_ACC = 5.5f;
	final static float JUMP_ACC = 10;
	public final static float WIDTH = 50;
	public final static float HEIGHT = 50;
	final static float MASS = 10;
	final static float GROUND_FRICTION_FROCE = 80;
	final static float BOUNDING_TOP_SIZE_FACETOR = 0.1f;
	final static float BOUNDING_SIDE_SIZE_FACETOR = 0.1f;
	
	Image image;
	public float pos_x, pos_y;
	public float vel_x, vel_y;
	public boolean isCollide_Top = false;
	public boolean isCollide_Bottom = false;
	public boolean isCollide_Right = false;
	public boolean isCollide_Left = false;
	boolean triggerJump = false;
	
	public Rectangle2D Bounding_Top, Bounding_Bottom, Bounding_Right, Bounding_Left;
	
	
	public Player(float start_pos_x, float start_pos_y, String start_image_url) throws SlickException
	{
		pos_x = start_pos_x;
		pos_y = start_pos_y;
		
		UpdateColliderPosition();
		
		
	}
	public void Update(GameContainer gameContainer, int delta)
	{
		/*
		 * Because the collision detection in the main game class
		 * always be called after this player update.
		 */
		CollidingCheck();
		
		Input();
		
		UpdatePosition();

		FrictionCheck();
		GravityCheck();
		
		ResetPerFrame();
	}

	void CollidingCheck()
	{
		if(isCollide_Bottom)
		{
			vel_y = 0;
		}
		if(isCollide_Top)
		{
			vel_y = 0;
		}
		if(isCollide_Left)
		{
			vel_x = 0;
		}
		if(isCollide_Right)
		{
			vel_x = 0;
		}
	}
	
	void Input()
	{
		if(isCollide_Bottom)
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
	}
	void Jump()
	{
		vel_y = JUMP_ACC;
	}
	
	void UpdatePosition()
	{
		pos_x += vel_x ;
		pos_y -= vel_y ;
		UpdateColliderPosition();
	}
	public void UpdateColliderPosition()
	{
		Bounding_Top = new Rectangle2D.Float(pos_x + WIDTH * BOUNDING_SIDE_SIZE_FACETOR, pos_y
				, WIDTH - 2*WIDTH*BOUNDING_SIDE_SIZE_FACETOR, HEIGHT * BOUNDING_TOP_SIZE_FACETOR);
		Bounding_Bottom = new Rectangle2D.Float(pos_x + WIDTH * BOUNDING_SIDE_SIZE_FACETOR, pos_y + HEIGHT - HEIGHT * BOUNDING_TOP_SIZE_FACETOR
				, WIDTH - 2*WIDTH*BOUNDING_SIDE_SIZE_FACETOR, HEIGHT * BOUNDING_TOP_SIZE_FACETOR);
		
		Bounding_Left = new Rectangle2D.Float(pos_x, pos_y + HEIGHT * BOUNDING_TOP_SIZE_FACETOR
				, WIDTH * BOUNDING_SIDE_SIZE_FACETOR, HEIGHT - 2*HEIGHT*BOUNDING_TOP_SIZE_FACETOR);
		Bounding_Right = new Rectangle2D.Float(pos_x + WIDTH - WIDTH * BOUNDING_SIDE_SIZE_FACETOR, pos_y + HEIGHT * BOUNDING_TOP_SIZE_FACETOR
				, WIDTH * BOUNDING_SIDE_SIZE_FACETOR, HEIGHT - 2*HEIGHT*BOUNDING_TOP_SIZE_FACETOR);
	}
	
	void GravityCheck()
	{
		vel_y += RelativeGame.GRAVITY * RelativeGame.TIME_DELTA_FACTOR;
	}
	
	void FrictionCheck()
	{
		if(isCollide_Bottom && !InputController.holding_moveRight && !InputController.holding_moveLeft)
		{
			if(vel_x > 0)
			{
				vel_x -= GROUND_FRICTION_FROCE * RelativeGame.TIME_DELTA_FACTOR ;
				if(vel_x <0)
					vel_x =0;
			}
			if(vel_x < 0)
			{
				vel_x += GROUND_FRICTION_FROCE * RelativeGame.TIME_DELTA_FACTOR;
				if(vel_x >0)
					vel_x =0;
			}
			
		}
	}
	
	void ResetPerFrame()
	{
		isCollide_Bottom = isCollide_Left = isCollide_Right = isCollide_Top = false;
	}
	
	public void Render(GameContainer gameContainer, Graphics graphics)
	{
		graphics.setColor(Color.white);
		graphics.fillRect(pos_x - Camera.pos_x, pos_y - Camera.pos_y, WIDTH, HEIGHT);
		
		graphics.setColor(Color.black);
		
		/*
		//top
		graphics.fillRect(pos_x + WIDTH * BOUNDING_SIDE_SIZE_FACETOR - Camera.pos_x,  pos_y - Camera.pos_y, WIDTH - 2*WIDTH*BOUNDING_SIDE_SIZE_FACETOR, HEIGHT * BOUNDING_TOP_SIZE_FACETOR);
		//Down
		graphics.fillRect(pos_x + WIDTH * BOUNDING_SIDE_SIZE_FACETOR - Camera.pos_x,  pos_y + HEIGHT - HEIGHT * BOUNDING_TOP_SIZE_FACETOR - Camera.pos_y ,WIDTH - 2*WIDTH*BOUNDING_SIDE_SIZE_FACETOR, HEIGHT * BOUNDING_TOP_SIZE_FACETOR);
		//left
		graphics.fillRect(pos_x - Camera.pos_x, pos_y + HEIGHT * BOUNDING_TOP_SIZE_FACETOR - Camera.pos_y, WIDTH * BOUNDING_SIDE_SIZE_FACETOR, HEIGHT - 2*HEIGHT*BOUNDING_TOP_SIZE_FACETOR);
		//right
		graphics.fillRect(pos_x + WIDTH - WIDTH * BOUNDING_SIDE_SIZE_FACETOR - Camera.pos_x,  pos_y + HEIGHT * BOUNDING_TOP_SIZE_FACETOR - Camera.pos_y, WIDTH * BOUNDING_SIDE_SIZE_FACETOR,  HEIGHT - 2*HEIGHT*BOUNDING_TOP_SIZE_FACETOR);
		*/
		graphics.setColor(Color.white);
	}
	
	
	void Walk_Right()
	{
		if(vel_x < MAX_MOVE_ACC)
		{
			vel_x += MOVE_ACC * RelativeGame.TIME_DELTA_FACTOR;
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
			vel_x -= MOVE_ACC * RelativeGame.TIME_DELTA_FACTOR;
		}
		else
		{
			vel_x = -MAX_MOVE_ACC;
		}
	}
	

}
