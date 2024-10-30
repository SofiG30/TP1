package tp1.control;

import tp1.logic.Game;
import tp1.view.*;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		view.showWelcome();
		//TODO fill your code: The main loop that displays the game, 
		//asks the user for input, and executes the action.
		boolean gameRunning = true;
		
		while(gameRunning) {
			view.showGame();
			
			//check if game has been won
			if(game.isGameOver()) {
				gameRunning = false;
				//exit loop
				break;
			}
			
			//user command
			String[] command = view.getPrompt();
			
			//handle user's input
			switch(command[0].toLowerCase()) {
				case "none":
				case "n":
				case "":
					game.update();
					break;
				case "reset":
				case "r":
					game.reset();
					break;
				case "exit":
				case "e":
					view.showMessage("Player leaves game");
					gameRunning = false;
					break;
				case "help":
				case "h":
					view.showMessage(game.help());
					break;
				default:
					view.showError(Messages.UNKNOWN_COMMAND);
			}
		}
		view.showGame();
		//if gameis won, display end game message
		if(game.checkWinCondition()) {
			view.showMessage(Messages.PLAYER_WINS);
		}
		//else
		view.showEndMessage();
	}

}
