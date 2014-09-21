import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class RelativeGame extends BasicGame {
	
	final static int FRAME_PER_SECOND = 60;
	final static int SCREEN_WIDTH = 800;
	final static int SCREEN_HEIGHT = 600;

	public RelativeGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		RelativeGame game = new RelativeGame("Relative Game");
		AppGameContainer appgc = new AppGameContainer(game, 0, 0, false);
		appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		appgc.setTargetFrameRate(FRAME_PER_SECOND);
		appgc.start();

	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
