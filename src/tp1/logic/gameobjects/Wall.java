package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;

public class Wall {
	
	Position pos; 
	
	public Wall(Position p) {
		pos = p;
	}
	
	public void update() {
		
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public String toString() {
		return Messages.WALL;
	}
}
