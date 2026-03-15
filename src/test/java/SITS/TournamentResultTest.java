package SITS;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Game.TournamentResult;

class TournamentResultTest {
	TournamentResult result;
	Map<String, Integer> initialScores;
	
	@BeforeEach
	void setUp() throws Exception {
		initialScores = new HashMap<>();
		initialScores.put("Mitch", 50);
        initialScores.put("Adam", 120);
        initialScores.put("Wes", 90);
        result = new TournamentResult(initialScores);
	}

	@Test
	void getsTests() {
		assertEquals(50, result.getScores("Mitch"));
        assertEquals(120, result.getScores("Adam"));
        assertEquals(90, result.getScores("Wes"));
        
        assertEquals(0, result.getScores("David"));
	}
	
	@Test
	void RankingTests() {
        List<String> rankings = result.getRankings();
        assertEquals(3, rankings.size());
        assertEquals("Adam", rankings.get(0));
        assertEquals("Wes", rankings.get(1));
        assertEquals("Mitch", rankings.get(2));
        
        initialScores.put("Mitch", 999);
        assertEquals(3, rankings.size());//cant modify og scores
        assertEquals(50, result.getScores("Mitch"));
	}

	
	
	
	
	
	
	
	
}
