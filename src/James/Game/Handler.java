package James.Game;

import java.awt.Graphics;
import java.util.LinkedList;

import James.Game.framework.GameObject;
import James.Game.framework.ObjectId;
import James.Objects.Block;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	public void tick(){
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}

	public void render(Graphics g){
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}

	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void createLevel(){
		for (int i = 0; i < Game.WIDTH; i+=25) {
			addObject(new Block(i, Game.HEIGHT, ObjectId.Block));
		}
	
	}

}
