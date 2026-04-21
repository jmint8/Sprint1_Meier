package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import SITS.Actions.Action;
import SITS.Remote.Network.RemoteParticipant;
import SITS.Remote.Network.StringAction;

class NetworkTests {
	
	

	@BeforeEach
	void setUp() throws Exception {
		
	}

	//I know human participant is in this package but I'm going to do it in a separate test file
	
	
	//String action test, i still have it in there. 
	@Test
	void StringActionTest() {
		StringAction action = new StringAction("DEFECT");
		assertEquals("DEFECT",action.getLabel());
	}
	
	//remote participant tests
	
	@Test
	void RemoteParticipantGetters()
	{
		RemoteParticipant rm = new RemoteParticipant("NetRunner", "http://localhost:8080", StringAction::new);
		assertEquals("NetRunner", rm.getName());
	}
	
	@Test
	void RMParticipantTest()
	{
		// this is the test setup for testing RestTemplate, the other things are 
		// for the remoteParticipant constructor
		RestTemplate mockRestTemp = mock(RestTemplate.class);
		Function<String, Action> actionFactory = StringAction::new;
		String client_url = "htp://localhost:808";
		
		//test the getname 
		RemoteParticipant rm = new RemoteParticipant("NetRunner", client_url, actionFactory);
		assertEquals("NetRunner", rm.getName());
		
	}
	
	
	

}
