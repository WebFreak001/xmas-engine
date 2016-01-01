module engine.states.statemanager;

import engine.states.state;
import engine.gfx.bitmap;

class StateManager
{
public:
	void update(float delta) {
		_gameStates[_currentState].update(delta);
	}
	
	void render(Bitmap screen) {
		_gameStates[_currentState].render(screen);
	}
	
	void addState(State gameState) {
		_gameStates ~= gameState;
	}
private:
	int _currentState;
	State[] _gameStates;
}
