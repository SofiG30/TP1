package tp1.logic;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class Game {
	
	GameObjectContainer container;
	int cycleNum;
	int numLemmingsBoard;
	int numLemmingsDead;
	int numLemmingsExit;
	int numLemmingsWin;
	int levelNum;
	boolean gameWon;
	

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	
	
	
	public Game(int nlevel) {
		//different levels of the game, specifies which board version they want to play
		levelNum = nlevel;
		if(nlevel ==0) {
			initGame0();
		}
		else if(nlevel == 1) {
			initGame1();
		}
		else if(nlevel ==2) {
			initGame2();
		}
	}
	
	public void initGame0() {
		//initialize exit door at pos(9,9)
		Position exitPos = new Position (9,9);
		ExitDoor exit = new ExitDoor(exitPos);
		
		//initialize container
		container = new GameObjectContainer(exit, 10,10);
		
		//add walls
		container.add(new Wall(new Position (4,4)));
		container.add(new Wall(new Position (5,6)));
		container.add(new Wall(new Position (8,7)));

		//add lemmings
		container.add(new Lemming(new Position(0,0), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
		container.add(new Lemming(new Position(1,7), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
		container.add(new Lemming(new Position(5,5), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));

		//num of lemmings on board
		numLemmingsBoard = container.getLemmingCount();
		//num lems dead at start
		numLemmingsDead = 0;
		//num lems exited at start
		numLemmingsExit = 0;
		//min num of lems that exit needed to win
		numLemmingsWin = 2;
	}
	public void initGame1() {
		
		//initialize exit door at pos(9,9)
		Position exitPos = new Position (9,0);
		ExitDoor exit = new ExitDoor(exitPos);
				
		//initialize container
		container = new GameObjectContainer(exit, 10,10);
				
		//add walls
		container.add(new Wall(new Position (3,5)));
		container.add(new Wall(new Position (5,6)));
		container.add(new Wall(new Position (8,7)));
		container.add(new Wall(new Position (1,3)));
		container.add(new Wall(new Position (9,9)));


		//add lemmings
		container.add(new Lemming(new Position(0,0), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
		container.add(new Lemming(new Position(1,7), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
		container.add(new Lemming(new Position(5,5), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
	    container.add(new Lemming(new Position(3, 3), true, Direction.LEFT, Direction.LEFT, 0, new WalkerRole("ᗺ"), this));
	    container.add(new Lemming(new Position(8, 9), true, Direction.LEFT, Direction.LEFT, 0, new WalkerRole("ᗺ"), this));

		//num of lemmings on board
		numLemmingsBoard = container.getLemmingCount();
		//num lems dead at start
		numLemmingsDead = 0;
		//num lems exited at start
		numLemmingsExit = 0;
		//min num of lems that exit needed to win
		numLemmingsWin = 3;
	}
	public void initGame2() {
		//initialize exit door at pos(9,9)
		Position exitPos = new Position (7,4);
		ExitDoor exit = new ExitDoor(exitPos);
						
		//initialize container
		container = new GameObjectContainer(exit, 10,10);
						
		//add walls
		container.add(new Wall(new Position (3,5)));
		container.add(new Wall(new Position (5,6)));
		container.add(new Wall(new Position (8,7)));
		container.add(new Wall(new Position (1,3)));
		container.add(new Wall(new Position (9,9)));
		container.add(new Wall(new Position (2,2)));
		container.add(new Wall(new Position (4,9)));

		//add lemmings
		container.add(new Lemming(new Position(0,0), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
		container.add(new Lemming(new Position(1,7), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
		container.add(new Lemming(new Position(5,5), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));
	    container.add(new Lemming(new Position(3, 3), true, Direction.LEFT, Direction.LEFT, 0, new WalkerRole("ᗺ"), this));
	    container.add(new Lemming(new Position(8, 9), true, Direction.LEFT, Direction.LEFT, 0, new WalkerRole("ᗺ"), this));
		container.add(new Lemming(new Position(2,7), true, Direction.RIGHT, Direction.RIGHT, 0, new WalkerRole("B"), this));

		
		//num of lemmings on board
		numLemmingsBoard = container.getLemmingCount();
		//num lems dead at start
		numLemmingsDead = 0;
		//num lems exited at start
		numLemmingsExit = 0;
		//min num of lems that exit needed to win
		numLemmingsWin = 5;
	}
	
	public void update() {
		//updates state of elements in game
		//increments cycle num, calls container update which then calls
		//update method of each of the game updates
		
		//update cycle number
		cycleNum++;
		
		//update game objects
		container.update();
		
		//check number of lems alive, dead, exited
		numLemmingsBoard = container.getLemmingCount();
		numLemmingsDead = container.getDeadLemmingsCount();
		numLemmingsExit = container.getExitedLemmingsCount();
		
		//check if game is over,
		if(playerWins()) {
			System.out.println(Messages.PLAYER_WINS);
		}
		else if(playerLooses()) {
			System.out.println(Messages.PLAYER_LOOSES);
		}
	}

	public int getCycle() {
		// TODO Auto-generated method stub
		return cycleNum;
	}

	public int numLemmingsInBoard() {
		// TODO Auto-generated method stub
		return numLemmingsBoard;
	}

	public int numLemmingsDead() {
		// TODO Auto-generated method stub
		return numLemmingsDead;
	}

	public int numLemmingsExit() {
		// TODO Auto-generated method stub
		return numLemmingsExit;
	}

	public int numLemmingsToWin() {
		// TODO Auto-generated method stub
		return numLemmingsWin;
	}

	public String positionToString(int col, int row) {
		// TODO Auto-generated method stub
		return container.positionToString(col,row);
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return numLemmingsExit >= numLemmingsWin;
	}

	public boolean playerLooses() {
		// TODO Auto-generated method stub
		return numLemmingsInBoard() == 0 && numLemmingsExit < numLemmingsWin;
	}

	public String help() {
		// TODO Auto-generated method stub
		return Messages.HELP;
	}
	
	public void reset() {
		//reset board to current level
		if(levelNum ==0) {
			initGame0();
		}
		else if(levelNum == 1) {
			initGame1();
		}
		else if(levelNum ==2) {
			initGame2();
		}
		cycleNum = 0;
	}
	
	public Position getExitDoorPosition() {
		return container.getExitDoorPosition();
	}
	
	public GameObjectContainer getContainer() {
		return container;
	}
	
	//tracks number of exits
	public void incrementLemmingsExited() {
		numLemmingsExit++;
	}
	
	//checks if enough lemmys exited to win
	public boolean checkWinCondition() {
		if(numLemmingsExit >= numLemmingsWin) {
			gameWon = true;
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		//game is over bc enough lemmys left or died
		return numLemmingsExit >= numLemmingsWin || numLemmingsInBoard() == 0;
	}

}
