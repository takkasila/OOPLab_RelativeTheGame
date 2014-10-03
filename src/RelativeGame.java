import java.awt.geom.Rectangle2D;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class RelativeGame extends BasicGame {
	
	final static int FRAME_PER_SECOND = 60;
	final static int SCREEN_WIDTH = 1000;
	final static int SCREEN_HEIGHT = 800;
	final static float GRAVITY = -9.8f;
	final static float TIME_DELTA_FACTOR = 1/60f;
	
	Player player;
	CollisionMap map1; 
	public RelativeGame(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {
		RelativeGame game = new RelativeGame("Relative Game");
		AppGameContainer appgc = new AppGameContainer(game, 0, 0, false);
		appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		appgc.setTargetFrameRate(FRAME_PER_SECOND);
		appgc.start();

	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		player = new Player(SCREEN_WIDTH/2 - player.WIDTH/2, 300, "SAMPLE");
		map1 = new CollisionMap();
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		map1.VisualDebug(gameContainer, graphics);
		player.Render(gameContainer, graphics);
		Debug(graphics);
	}
	void Debug(Graphics graphics)
	{
		graphics.drawString("X: "+player.pos_x+" Y: "+player.pos_y, 10, 25);
		graphics.drawString("Vel_X: "+player.vel_x+" Vel_Y: "+player.vel_y, 10, 45);
		graphics.drawString("Hit Right: "+player.isCollide_Left+" Hit Left: "+player.isCollide_Right, 10, 65);
		graphics.drawString("Hit Top: "+player.isCollide_Top+" Hit Bottom: "+player.isCollide_Bottom, 10, 85);
	}


	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
		player.Update(gameContainer, delta);
		PlayerCollisionDetection();
	}
	
	void PlayerCollisionDetection()
	{
		for (int i = 0; i < map1.CollisionList.size(); i++) {
			
			Rectangle2D current = map1.CollisionList.get(i);

			if(player.Bounding_Top.intersects(current))
			{
				player.isCollide_Top = true;
				player.pos_y = (float) (current.getY() + current.getHeight());
				player.UpdateColliderPosition();
			}
			if(player.Bounding_Bottom.intersects(current))
			{
				player.isCollide_Bottom = true;
				player.pos_y = (float) (current.getY() - player.WIDTH);
				player.UpdateColliderPosition();
			}
			if(player.Bounding_Left.intersects(current))
			{
				player.isCollide_Left = true;
				player.pos_x = (float) (current.getX() + current.getWidth());
				player.UpdateColliderPosition();
			}
			if(player.Bounding_Right.intersects(current))
			{
				player.isCollide_Right = true;
				player.pos_x = (float) (current.getX() - player.WIDTH);
				player.UpdateColliderPosition();
			}
			
		}
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(key == InputController.Jump)
		{
			InputController.holding_Jump = true;
		}
		if(key == InputController.MoveRight)
		{
			InputController.holding_moveRight = true;
		}
		if(key == InputController.MoveLeft)
		{
			InputController.holding_moveLeft = true;
		}
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		if(key == InputController.Jump)
		{
			InputController.holding_Jump = false;
		}
		if(key == InputController.MoveRight)
		{
			InputController.holding_moveRight = false;
		}
		if(key == InputController.MoveLeft)
		{
			InputController.holding_moveLeft = false;
		}
	}

}
