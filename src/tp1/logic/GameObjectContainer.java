package tp1.logic;
import tp1.logic.gameobjects.*;
import tp1.view.*;

public class GameObjectContainer {
	//TODO fill your code
	
	ExitDoor exit;
	Lemming[] lemmings;
	Wall[] walls;
	int lemmingCount;
	int wallCount;
	
	//default for max vals is the board size
	
	public GameObjectContainer(ExitDoor e, int maxValLem, int maxValWall) {
		exit = e;
		lemmings = new Lemming[maxValLem];
		walls = new Wall[maxValWall];
		
	}
	
	public void update() {
		/*
		 * gets called in Game class, 
		 * invokes update method of each game object
		 */
		//update lemmings
		for(int i = 0; i < lemmingCount; i++) {
			if(lemmings[i] != null) {
				lemmings[i].update();
			}
		}
		//no updates for walls and exit doors needed right now
	}
	
	public void add(Lemming lemming) {
		//something to add lemming to list
		lemmings[lemmingCount] = lemming;
		lemmingCount++;
	}
	
	public void add(Wall wall) {
		//something to add wall to list
		walls[wallCount] = wall;
		wallCount++;
	}
	
	public void add(ExitDoor exitDoor) {
		
	}
	
	public String positionToString(int row, int col) {
		Position pos = new Position(row, col);

        // Check if the exit door is at this position first
        if (exit != null && exit.getPosition().equals(pos)) {
            return Messages.EXIT_DOOR;
        }

        // Then check if there is a lemming at this position
        for (int i = 0; i < lemmingCount; i++) {
            if (lemmings[i] != null && lemmings[i].getPosition().equals(pos) && lemmings[i].isAlive()) {
                return lemmings[i].toString();  // Only display if lemming is alive
            }
        }

        // Check if there is a wall at this position
        for (int i = 0; i < wallCount; i++) {
            if (walls[i] != null && walls[i].getPosition().equals(pos)) {
                return walls[i].toString();
            }
        }

        // Otherwise, return an empty space
        return Messages.EMPTY;
		
	}
	
	//helper method, get lemming count
	public int getLemmingCount() {
		return lemmingCount;
	}
	
	public int getDeadLemmingsCount() {
		int count = 0;
		for(int i = 0; i < lemmingCount; i++) {
			if(!lemmings[i].isAlive() && !lemmings[i].getPosition().equals(exit.getPosition())) {
				count++;
			}
		}
		return count;
	}
	
	public int getExitedLemmingsCount() {
		int count = 0;
		for(int i = 0; i < lemmingCount; i++) {
			if(!lemmings[i].isAlive() && lemmings[i].getPosition().equals(exit.getPosition())) {
                count++;
            }
		}
		return count;
	}
	
	public Position getExitDoorPosition() {
		return exit.getPosition();
	}
	
	//check if there's a solid object below
	public boolean hasSolidObjectBelow(Position pos) {
		Position below = new Position(pos.getRow()+1, pos.getCol());
		
		//check if wall at below pos
		for(int i = 0; i < wallCount; i++) {
			if (walls[i] != null && walls[i].getPosition().equals(below)) {
				//there is a solid object below
				return true;
			}
		}
		
		//no solid object below
		return false;
	}
	
	//checks if wall at next pos
	public boolean hasWallAtPosition(Position pos) {
		for(int i = 0; i < wallCount; i++) {
			if(walls[i] != null && walls[i].getPosition().equals(pos)) {
				//there are walls at pos
				return true;
			}
		}
		//no walls at pos
		return false;
		
	}

}
