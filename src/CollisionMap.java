import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;


public class CollisionMap {
	
	public ArrayList<Rectangle2D> CollisionList = new ArrayList<Rectangle2D>();
	public CollisionMap()
	{		
		CollisionList.add(new Rectangle2D.Float(100, 600, 400, 100));
	}
	
	

}
