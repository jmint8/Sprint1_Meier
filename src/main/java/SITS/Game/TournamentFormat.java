package SITS.Game;

import java.util.List;

import SITS.Actions.Participant;

public interface TournamentFormat {
	TournamentResult run(List<Participant> participants, Game game);

}
