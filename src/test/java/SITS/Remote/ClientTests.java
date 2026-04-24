package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import SITS.Remote.Client.TournamentServerClient;

import org.springframework.boot.test.context.SpringBootTest;



class ClientTests {

	/*
	 requirements for this test file include hitting the GET/name, POST/action, 
	 POST/reset endpoints.
	 
	 note to self: create an actual tournament instance. not with mockito like how you started ServerTests.
	 - Test the endpoint communications. 
	 - All methods and classes in this package should be tested. 
	*/
	

	@Test
	void tournamentServerClientTests() {
		TournamentServerClient TSC = new TournamentServerClient("http://testing");
		
		assertEquals("http://testing",TSC.getServer_url());
		assertNotNull(TSC.getRestTemplate());
		
		//ListTournaments test
		//needs more setup from rest template
		
		
	}

}
