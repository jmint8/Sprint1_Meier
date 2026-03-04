package tournament;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
	private List<RoundResult> rounds = new ArrayList<>();
	private String p1Name;
	private String p2Name;
	
	public List<RoundResult> getRounds(){
		return rounds;
	}
	
	public String getP1Name(){
		return p1Name;
		
	}
	
	
	public String getP2Name(){
		return p2Name;
		
	}
	

}
