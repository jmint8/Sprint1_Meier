package tournament;

public interface GameObserver 
{
	void onMove(MoveEvent event);
	
	void onGameEnd(GameResult result);
	
}
