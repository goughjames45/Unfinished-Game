package James.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import James.Game.framework.GameObject;
import James.Game.framework.ObjectId;

public class Player extends GameObject{
	
	private float width = 64, height = 32;
	private float gravity =0;

	public Player(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		//if(falling || jumping){
		//	velY+= gravity;
		//}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsUp());
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)((int)x + (width/2) - ((width/2)/2)), ((int)y+(int)(height/2)), (int)width/2, (int)height/2);
		
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
		
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
		
	}
	public Rectangle getBoundsUp() {
		return new Rectangle((int)((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
		
	}
	
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
		}

}
