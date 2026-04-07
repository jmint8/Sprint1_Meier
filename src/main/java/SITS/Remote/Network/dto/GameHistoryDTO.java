package SITS.Remote.Network.dto;

import java.util.ArrayList;
import java.util.List;

import SITS.Game.GameHistory;
import SITS.Game.RoundResult;
import SITS.Remote.Network.StringAction;

public class GameHistoryDTO {
	
	public String nameP1;
	public String nameP2;
	public List<RoundResultDTO> rounds;
	
	public GameHistoryDTO(String nameP1, String nameP2, List<RoundResultDTO> rounds) {
		this.nameP1 = nameP1;
		this.nameP2 = nameP2;
		this.rounds = rounds;
	}
	
	public GameHistory toGameHistory() {
		GameHistory history = new GameHistory(nameP1, nameP2);
		int numRounds = 1;
		for(RoundResultDTO round: rounds) {
			history.getRounds().add(new RoundResult(
					new StringAction(round.actionP1),
					new StringAction(round.actionP2),
					round.payoffP1,
					round.payoffP2,
					numRounds++
							));
		}
		
		return history;
	}
	
	
	public static GameHistoryDTO fromGameHistory(GameHistory history) {
		List<RoundResultDTO> dtos = new ArrayList<>();

		for (RoundResult round : history.getRounds()) { 
			 
			dtos.add(new RoundResultDTO(
					round.getActionP1().getLabel(), 
					round.getActionP2().getLabel(), 
					round.getScoreP1(),
					round.getScoreP2())); 
			} 
		return new GameHistoryDTO(history.getP1Name(), history.getP2Name(), dtos);
	}
	

}
