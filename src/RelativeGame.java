import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//todo : collision debug
public class RelativeGame extends BasicGame {
	
	final static int FRAME_PER_SECOND = 60;
	final static int SCREEN_WIDTH = 800;
	final static int SCREEN_HEIGHT = 600;
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
		player = new Player(SCREEN_WIDTH/2 - player.WIDTH/2, 100, "SAMPLE");
		map1 = new CollisionMap();
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		player.Render(gameContainer, graphics);
	}


	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
		player.Update(gameContainer, delta);
		PlayerCollisionDetection();
	}
	
	void PlayerCollisionDetection()
	{
		for (int i = 0; i < map1.CollisionList.size(); i++) {

			if(player.Bounding_Top.intersects(map1.CollisionList.get(i)))
			{
				player.isCollide_Top = true;
			}
			if(player.Bounding_Bottom.intersects(map1.CollisionList.get(i)))
			{
				player.isCollide_Bottom = true;
			}
			if(player.Bounding_Left.intersects(map1.CollisionList.get(i)))
			{
				player.isCollide_Left = true;
			}
			if(player.Bounding_Right.intersects(map1.CollisionList.get(i)))
			{
				player.isCollide_Right = true;
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
