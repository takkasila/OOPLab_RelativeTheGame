package RelativeGame;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class RelativeGame extends BasicGame {
	
	final static int FRAME_PER_SECOND = 60;
	final static int SCREEN_WIDTH = 1366;
	final static int SCREEN_HEIGHT = 768;
	public static float GRAVITY = -23f;
	final static float TIME_DELTA_FACTOR = 1/(float)FRAME_PER_SECOND;
	final static float GRID_SIZE = 100;
	private boolean antiAlias = true;
	TrueTypeFont myFont;
	
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
		player = new Player(0, -150, "SAMPLE");
		map1 = new CollisionMap();
		
		
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("arial.ttf");
			Font arialFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			arialFont = arialFont.deriveFont(24f);
			myFont = new TrueTypeFont(arialFont, antiAlias);
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

		//DrawGrid(graphics);
		map1.Render(gameContainer, graphics);
		player.Render(gameContainer, graphics);
		//Debug(graphics);
		RenderQuotes();
	}
	
	void RenderQuotes()
	{
		//1
		myFont.drawString(-1700 - Camera.pos_x, -340 - Camera.pos_y, "Life can only be understood backward;");
		myFont.drawString(-1330 - Camera.pos_x, -310 - Camera.pos_y, "but it must be lived forward");
		
		//2
		myFont.drawString(-126 - Camera.pos_x, -310 - Camera.pos_y, "Taking the first step can be harder");
		myFont.drawString(-80 - Camera.pos_x, -280 - Camera.pos_y, "than rest of the challenge");
		
		//6
		myFont.drawString(8800 - Camera.pos_x, -350 - Camera.pos_y, "Some hurdles are");
		myFont.drawString(8788 - Camera.pos_x, -320 - Camera.pos_y, "too high to jump over");
		myFont.drawString(9400 - Camera.pos_x, -850 - Camera.pos_y, "Leap of faith?");
		
		//7
		myFont.drawString(10180 - Camera.pos_x, -300 - Camera.pos_y, "Life is full of");
		myFont.drawString(10160 - Camera.pos_x, -270 - Camera.pos_y, "ups and downs");
		
		
	}
	
	void DrawGrid(Graphics graphics)
	{
		graphics.setColor(Color.white);
		DrawHorizontalLine(graphics);
		DrawVerticalLine(graphics);
		DrawAxis(graphics);
	}
	
	void DrawHorizontalLine(Graphics graphics)
	{
		int MaxLines = ((int) (Camera.pos_x + SCREEN_WIDTH))/(int)GRID_SIZE;
		int MinLines = ((int) Camera.pos_x)/(int)GRID_SIZE;
		int LinesCount = MaxLines - MinLines + 1;
		float StartPosX = (MinLines) * GRID_SIZE - Camera.pos_x;
		
		for (int i = 0; i < LinesCount; i++) {
			graphics.drawString(""+(int)GRID_SIZE * (i + MinLines), StartPosX + GRID_SIZE * i, 0);
			graphics.drawLine(StartPosX + GRID_SIZE * i
					, 0
					, StartPosX + GRID_SIZE * i
					, SCREEN_HEIGHT);
		}
	}
	
	void DrawVerticalLine(Graphics graphics)
	{
		int MaxLines = ((int) (Camera.pos_y + SCREEN_HEIGHT))/(int)GRID_SIZE;
		int MinLines = ((int) Camera.pos_y)/(int)GRID_SIZE;
		int LinesCount = MaxLines - MinLines + 1;
		float StartPosY = (MinLines) * GRID_SIZE - Camera.pos_y;
		
		for (int i = 0; i < LinesCount; i++) {
			graphics.drawString(""+(int)GRID_SIZE * (i + MinLines), 0, StartPosY + GRID_SIZE * i);
			graphics.drawLine(0
					, StartPosY + GRID_SIZE * i
					, SCREEN_WIDTH
					, StartPosY + GRID_SIZE * i);
		}
		
	}
	
	void DrawAxis(Graphics graphics)
	{
		graphics.setColor(Color.pink);
		graphics.drawLine(-10000 - Camera.pos_x, 0 - Camera.pos_y, 10000 - Camera.pos_x, 0 - Camera.pos_y);
		graphics.drawLine(0 - Camera.pos_x, -10000 - Camera.pos_y, 0 - Camera.pos_x, 10000 - Camera.pos_y);
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
		map1.Update(player.pos_x, player.pos_y);
		PlayerCollisionDetection();
		Camera.UpdatePosition(player.pos_x, player.pos_y);
		
		if(player.pos_y > 2000)
		{
			player.pos_x = 0;
			player.pos_y = -200;
		}
		
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
