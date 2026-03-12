package SITS.Observers;

import SITS.Game.GameResult;
import SITS.Game.MoveEvent;
import SITS.Game.TournamentResult;

public interface GameObserver 
{
	void onMoveMade(MoveEvent event);
	void onTournamentEnd(TournamentResult result);
	void onGameEnd(GameResult result);
}
