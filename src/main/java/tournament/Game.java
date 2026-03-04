package tournament;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
	private final List<GameObserver> observers = new ArrayList<>();
	
	public void attach(GameObserver observer)
	{
		observers.add(observer);
	}
	

}
