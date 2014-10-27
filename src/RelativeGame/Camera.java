package RelativeGame;

public class Camera {

	public static float pos_x, pos_y;
	public static float offset_x, offset_y;
	
	public static void UpdatePosition(float Player_Pos_x, float Player_Pos_y)
	{
		offset_x = RelativeGame.SCREEN_WIDTH/2 - Player.WIDTH/2f;
		offset_y = RelativeGame.SCREEN_HEIGHT/2 - Player.HEIGHT/4f;
		//todo smoother the following
		pos_x  = Player_Pos_x - offset_x;
		pos_y = Player_Pos_y - offset_y;
	}
}
