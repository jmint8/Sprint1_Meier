package SITS.Actions;

import SITS.Game.GameHistory;

public interface Participant {
	String getName();
	Action chooseAction(GameHistory history);
    void reset();
}

