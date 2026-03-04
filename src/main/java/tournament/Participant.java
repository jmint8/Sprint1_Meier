package tournament;

public interface Participant {
	Action chooseAction(GameHistory history);

    void reset();
}

