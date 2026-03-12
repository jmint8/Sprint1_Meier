package SITS.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SITS.Actions.Participant;

public class RoundRobin implements TournamentFormat {

	@Override
	public TournamentResult run(List<Participant> participants, Game game) {
		Map<String, Integer> cumulative = new HashMap<>();
		
		for (int i = 0; i < participants.size(); i++) {
		    Participant player = participants.get(i);
		    cumulative.put(player.getName(), 0);
		}
		int totalPlayers = participants.size();
	    
	    for (int i = 0; i < totalPlayers; i++) {
	        for (int j = i + 1; j < totalPlayers; j++) {
	        	Participant p1= participants.get(i);
                Participant p2 =participants.get(j);
                

                p1.reset();
                p2.reset();
                
                GameResult result = game.play(p1, p2);
                
                cumulative.put(p1.getName(), cumulative.get(p1.getName()) + result.getTotalScoreP1());
                cumulative.put(p2.getName(), cumulative.get(p2.getName()) + result.getTotalScoreP2());
	        }
	    }
		
		return new TournamentResult(cumulative);
		
		
	}

}
