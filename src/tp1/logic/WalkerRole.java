package tp1.logic;
import tp1.logic.gameobjects.*;

public class WalkerRole {
	
	String role;
	
	public WalkerRole(String type) {
		role = type;
	}
	
	public void advance(Lemming lem) {
		
		//needs to call basic `move` method of lemming
		lem.move();
	}
	
	//@Override
	public String getIcon() {
		return role;
	}

}
