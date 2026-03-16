package SITS;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import SITS.Actions.PrisonerAction;
import SITS.Game.GameHistory;
import SITS.Game.MoveEvent;
import SITS.Game.RoundResult;

import org.junit.jupiter.api.BeforeEach;


class GameHistoryTest {
	GameHistory history;
	MoveEvent event;

	@BeforeEach
	void setUp() throws Exception {
		history = new GameHistory("Mitch", "Adam");
	}

	@Test
	void Names_rounds_Test() {
		
		assertTrue(history.getRounds().isEmpty());
		assertNull(history.getLastRound());
		
		assertEquals("Mitch",history.getP1Name());
		assertEquals("Adam",history.getP2Name());
	}
	
	@Test
	void lastRoundTest() {
		RoundResult round1 = new RoundResult(PrisonerAction.COOPERATE, PrisonerAction.COOPERATE, 5, 5, 1);
		history.getRounds().add(round1);
		RoundResult round2 = new RoundResult(PrisonerAction.DEFECT, PrisonerAction.COOPERATE, 10, 0, 2);
		history.getRounds().add(round2);
		RoundResult round3 = new RoundResult(PrisonerAction.DEFECT, PrisonerAction.DEFECT, 1, 1, 3);
		history.getRounds().add(round3);
		
		assertEquals(round3, history.getLastRound());
		
		
		//I'm adding in the moveEvent testing here just for code coverage purposes
		event = new MoveEvent(history, round1);
		assertEquals(history, event.getHistory());
		assertEquals(round1, event.getRound());
		
	}
	
    

}
