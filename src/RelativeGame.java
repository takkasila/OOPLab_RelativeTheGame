import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class RelativeGame extends BasicGame {
	
	final static int FRAME_PER_SECOND = 60;
	final static int SCREEN_WIDTH = 800;
	final static int SCREEN_HEIGHT = 600;
	
	static Player player;

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
		player = new Player(100, 100, "SAMPLE");
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		player.Render(gameContainer, graphics);
	}


	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		player.Update(gameContainer, delta);
	}
	
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(key == Input.KEY_SPACE)
		{
			player.Jump();
		}
		if(key == Input.KEY_RIGHT)
		{
			player.Walk_Right();
		}
		if(key == Input.KEY_LEFT)
		{
			player.Walk_Left();
		}
	}

}
