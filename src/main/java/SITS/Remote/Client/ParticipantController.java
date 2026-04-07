package SITS.Remote.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import SITS.Actions.Participant;
import SITS.Game.GameHistory;
import SITS.Remote.Network.dto.GameHistoryDTO;


public class ParticipantController {
	
	private Participant participant;
	
	public ParticipantController(Participant participant)
	{
		this.participant = participant;
	}
	
	@GetMapping("/name")
	public String getName() { 
		return participant.getName();
	}
	
	@GetMapping("/action")
	public String getAction(@RequestBody GameHistoryDTO dto) {
		return participant.chooseAction(dto.toGameHistory()).getLabel();
	}
		
	@PostMapping("/reset")
	public void reset() {
		participant.reset();
	}		

}
