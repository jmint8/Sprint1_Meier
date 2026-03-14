package SITS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Actions.AlwaysCooperate;
import SITS.Actions.AlwaysDefect;
import SITS.Actions.PrisonerAction;
import SITS.Actions.TitForTat;
import SITS.Game.GameHistory;
import SITS.Game.RoundResult;

public class ActionsTest {
	
	AlwaysCooperate alwaysCoop;
	AlwaysDefect alwaysDef;
	TitForTat tft;
	
	@BeforeEach
	void setUp() {
		alwaysCoop = new AlwaysCooperate();
        alwaysDef = new AlwaysDefect();
        tft = new TitForTat();
	}
	
	//always cooperate 
	
	@Test
	void alwaysCoopTest() {
		assertEquals("Always Cooperate", alwaysCoop.getName());
		
		GameHistory history = new GameHistory("Always Cooperate", "Opponent");
        assertEquals(PrisonerAction.COOPERATE, alwaysCoop.chooseAction(history));
	}
	

}
