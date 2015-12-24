package uk.co.nozzer.states;

import java.util.ArrayList;
import java.util.List;

import uk.co.nozzer.gfx.Bitmap;

public class StateManager {

	private List<State> gameStates;
	private int currentState;
	
	public StateManager() {
		this.gameStates = new ArrayList<State>();
		this.currentState = 0;
	}
	
	public void update(double delta) {
		gameStates.get(currentState).update(delta);
	}
	
	public void render(Bitmap screen) {
		gameStates.get(currentState).render(screen);
	}
	
	public void addState(State gameState) {
		gameStates.add(gameState);
	}
	
	// --- getters and setters
	public void setCurrentState(int index) {
		System.out.println(index + "," + gameStates.size());
		if (index < 0 || index >= gameStates.size()) System.err.println("No state found with that ID. Did you add it to the StateManager?");
		else this.currentState = index;
	}
}
