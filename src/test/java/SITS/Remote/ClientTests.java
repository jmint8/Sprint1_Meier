package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import SITS.Remote.Client.ClientApp;
import SITS.Remote.Client.TournamentServerClient;
import SITS.Remote.Network.dto.GameHistoryDTO;
import SITS.Remote.Network.dto.RegistrationRequest;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {ClientApp.class, ClientTests.TestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientTests {

	/*
	 requirements for this test file include hitting the GET/name, POST/action, 
	 POST/reset endpoints.
	 
	 note to self: create an actual tournament instance. not with mockito like how you started ServerTests.
	 - Test the endpoint communications. 
	 - All methods and classes in this package should be tested. 
	*/
	
	@LocalServerPort
	private int port;
	
	private RestTemplate restTemp = new RestTemplate();
	
	@TestConfiguration
	static class TestConfig{
		@Bean
		@Primary
		public TournamentServerClient testStartupClient() {
			return new TournamentServerClient("http://localhost:8080") 
			{
				@Override
				public void register(String tournamentId, String name, String ip, int port) 
				{ //nothing	
				}
			};
		}
		// a dummy test controller hosted on clientApp for
		//TounramentServerClient testing
		@RestController
		static class testServerController
		{
			//this is for testing the registration. verrifying that the regirster function works 
			public List<String> regiPlayers = new ArrayList<>();
			
			@GetMapping("/tournaments")
			public List<String> testGetTournaments(){
				List<String> list = new ArrayList<>();
				list.add("ipd-1");
				return list;
			}
			@PostMapping("/register/{id}")
			public ResponseEntity<String> dummyRegister(@PathVariable String id, @RequestBody RegistrationRequest req) {
				regiPlayers.add(req.name);// this adds the name to the array
				return ResponseEntity.ok("registered " + req.name);
			}
			//this is literally only for verifying the register method. 
			@GetMapping("/registered")
			public List<String> getRegistered(){
				 
				return regiPlayers;
			}
			
		}
	}
	
	//name endpoint
	@Test 
	void nametest(){
		String url = "http://localhost:"+port +"/name";
		ResponseEntity<String> response = restTemp.getForEntity(url, String.class);
		assertEquals("Tit For Tat", response.getBody());
	}

	//action endpoint
	@Test
	void testAction() {
		String url = "http://localhost:" + port + "/action";
		GameHistoryDTO dto = new GameHistoryDTO( "TitForTat", "Opponent",new ArrayList<>()); 
		ResponseEntity<String> response = restTemp.postForEntity(url, dto, String.class);
		assertEquals("COOPERATE", response.getBody());
	}

	//reset endpoint
	@Test
	void resetTest() {
		String url = "http://localhost:"+port+"/reset";
		ResponseEntity<Void> response = restTemp.postForEntity(url, null, Void.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	//we need to test the tournamentServerContoller here too. 
	
	//testing getServerUrl()
	@Test
	void testTournamentgeturl() {
		String url = "http://localhost:" + port;
		TournamentServerClient client = new TournamentServerClient(url);
		assertEquals(url,client.getServer_url());
		assertNotNull(client.getRestTemplate());	
	}
	
	
	@Test
	void listTournametsTest() {
		TournamentServerClient client = new TournamentServerClient("http://localhost:" + port);
		
		List<?> tourna = client.listTournaments();
		assertNotNull(tourna);
		assertEquals("ipd-1", tourna.get(0));
	}
	
	@Test
	void registerTest() {
		TournamentServerClient client = new TournamentServerClient("http://localhost:" + port);
		
		client.register("ipd-1","testPlayer","127.0.0.1",9090);
		//register test player and check if the player is in there. /registered only exists in testing
		//to make sure registration works
		List<?> registered =  restTemp.getForObject("http://localhost:"+port+"/registered", List.class);
		assertNotNull(registered);
		assertTrue(registered.contains("testPlayer"));
	}

}