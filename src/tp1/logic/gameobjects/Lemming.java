package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.*;

public class Lemming {

	//TODO fill your code
	
	Position position;
	boolean healthStatus;
	Direction movementDir;
	Direction orientation;
	int fallForce;
	WalkerRole role;
	Game game;
	
	public Lemming(Position p, boolean b, Direction d, Direction o, int i, WalkerRole r, Game g) {
		position = p;
		healthStatus = b;
		movementDir = d;
		orientation = o;
		fallForce = i;
		role = r;
		game = g;
	}
	
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//TODO fill your code
		/*
		 * need to:
		 * •check if lemming is alive
		 * •call WalkerRole method advance, will call Lemming move method
		 */
		
		if(!healthStatus) {
			//add one to the dead lemming count, subtract one from number of alive lemmings
			return;
		}
		if(position.equals(game.getExitDoorPosition())) {
			//mark lemming as exit
			exit();
			return;
		}
		
		//if the other two if statements don't run, do this
		role.advance(this);
		
	}
	
	public void move() {
		/*
		 * If falling, manage the fall; in particular, 
		 * die if a floor has been reached after a fall that is too big.
		 * If in the air but not falling, fall. If neither falling nor in the air, 
		 * move normally. A normal move is either advancing one cell or changing direction.
		 */
		
		//if lemmy is at same pos as exit door
		if(position.equals(game.getExitDoorPosition())) {
			exit();
			return;
		}
		
		//what the next position of lemmy would be
		Position nextPosition = new Position(position.getRow(), position.getCol()+movementDir.getX());
		
		//check if there is a wall in the next position
		if(game.getContainer().hasWallAtPosition(nextPosition)) {
			//reverse direction if there's a wall
			movementDir = (movementDir == Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
			return;//nextPosition = new Position(position.getRow(), position.getCol()+movementDir.getX()); 
		}
		
		//check if lemmy is at bottom of board & falling
		if(position.getRow() == Game.DIM_Y-1 && !game.getContainer().hasSolidObjectBelow(position))
		{
			//lemmy falls of board and dies
			dead();
			return;
		}
		
		//if lemming at edge of board
		/*if(movementDir == Direction.RIGHT && position.getCol() == Game.DIM_X -1) {
			//reverse when at right edge
			movementDir = Direction.LEFT;
		}
		else if(movementDir == Direction.LEFT && position.getCol()==0) {
			//reverse when at left edge
			movementDir = Direction.RIGHT;
		}*/
		
		//check if lemmy is standing on solid object
		if(!game.getContainer().hasSolidObjectBelow(position)) {
			//fall one row if no solid object
			fallForce++;
			position = new Position(position.getRow()+1, position.getCol());
		}
		else {
			//if lemmy lands after fall, check distance of fall
			if(fallForce >= 3) {
				//if more than three rows, lemmy == dead :(
				dead();
			}
			else {
				//reset fall force and move a step
				fallForce = 0;
				//move left/right based on direction
				position = new Position(position.getRow(), position.getCol()+movementDir.getX());
			
			}
		}	
	}
	
	public void exit() {
		//lemming exited, no longer in game
		healthStatus = false;
		game.incrementLemmingsExited();
		
		//check if enough lemmys left
		if(game.checkWinCondition()) {
			System.out.println(Messages.PLAYER_WINS);
		}
	}
	
	public void dead() {
		//lemmy died :(
		healthStatus = false;
	}
	public Position getPosition() {
		return position;
	}
	
	public boolean equals(Position pos2) {
		if (position.getRow() == pos2.getRow() && position.getCol() == pos2.getCol()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isAlive() {
		return healthStatus;
	}
	
	
	
	@Override
	public String toString() {
		if(movementDir == Direction.LEFT) {
			return Messages.LEMMING_LEFT;
		}
		else {
			return Messages.LEMMING_RIGHT;
		}
	}
}
