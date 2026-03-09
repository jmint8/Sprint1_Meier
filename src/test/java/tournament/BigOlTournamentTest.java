package tournament;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BigOlTournamentTest {
	
	AlwaysCooperate alwaysCoop;
	AlwaysDefect alwaysDef;
	TitForTat tft;
	
	ItteratedPrisonersDilemma game1Round;
	ItteratedPrisonersDilemma game5Rounds;

	@BeforeEach
	void setUp() throws Exception 
	{
		alwaysCoop = new AlwaysCooperate();
		alwaysDef = new AlwaysDefect();
		tft = new TitForTat();
		
		
		game1Round = new ItteratedPrisonersDilemma(1);
		game5Rounds = new ItteratedPrisonersDilemma(5);
	}

	@Test
	void payoffTest() 
	{
		//test mutual cooperation
		GameResult resultCC = game1Round.play(alwaysCoop, alwaysCoop);
		assertEquals(5, resultCC.getTotalScoreP1());
		assertEquals(5, resultCC.getTotalScoreP2());
		assertEquals("it's a tie", resultCC.getWinner());
		
		//test mutual defection
		GameResult resultDD = game1Round.play(alwaysDef, alwaysDef);
		assertEquals(1, resultDD.getTotalScoreP1());
		assertEquals(1, resultDD.getTotalScoreP2());
		assertEquals("it's a tie", resultDD.getWinner());
		
		//test exploitation (Defect vs Cooperate)
		GameResult resultDC = game1Round.play(alwaysDef, alwaysCoop);
		assertEquals(10, resultDC.getTotalScoreP1());
		assertEquals(0, resultDC.getTotalScoreP2());
		assertEquals("Always Defect", resultDC.getWinner());
		
		//test exploitation (Cooperate vs Defect)
		GameResult resultCD = game1Round.play(alwaysCoop, alwaysDef);
		assertEquals(0, resultCD.getTotalScoreP1());
		assertEquals(10, resultCD.getTotalScoreP2());
		assertEquals("Always Defect", resultCD.getWinner());
	}
	
	@Test
	void gameplayTest()
	{
		//test TitForTat vs AlwaysCooperate over 5 rounds
		// Both will cooperate 5 times -> 5 * 5 = 25 points each
		GameResult resultTFTvsCoop = game5Rounds.play(tft, alwaysCoop);
		
		assertEquals(25, resultTFTvsCoop.getTotalScoreP1());
		assertEquals(25, resultTFTvsCoop.getTotalScoreP2());
		assertEquals("it's a tie", resultTFTvsCoop.getWinner());
		
		//test TitForTat vs AlwaysDefect over 5 rounds
		// Round 1: TFT(C) vs AD(D) -> TFT gets 0, AD gets 10
		// Round 2: TFT(D) vs AD(D) -> TFT gets 1, AD gets 1
		// Round 3: TFT(D) vs AD(D) -> TFT gets 1, AD gets 1
		// Round 4: TFT(D) vs AD(D) -> TFT gets 1, AD gets 1
		// Round 5: TFT(D) vs AD(D) -> TFT gets 1, AD gets 1
		// Total: TFT = 4, AD = 14
		GameResult resultTFTvsDefect = game5Rounds.play(tft, alwaysDef);
		
		assertEquals(4, resultTFTvsDefect.getTotalScoreP1());
		assertEquals(14, resultTFTvsDefect.getTotalScoreP2());
		assertEquals("Always Defect", resultTFTvsDefect.getWinner());
	}
}