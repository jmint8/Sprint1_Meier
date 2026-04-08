package SITS.Remote.Client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import SITS.Remote.Network.dto.RegistrationRequest;
import SITS.Remote.Server.NetworkedTournament;

public class TournamentServerClient {
	
	private String server_url;
	private RestTemplate restTemplate;
	
	public TournamentServerClient(String server_url) 
	{
		this.server_url = server_url;
		this.restTemplate = new RestTemplate();
	}
	
	//methods for this. 
	//why are we using rest template and not rest client again? 
	//anyways I digress. 
	/*diego designed this on a Saturday alone without us and didn't tell us until
	 * after. then proceeded to ghost us until after the presentation of the design */
	//and since the Remote PArticipant is using Remote Template i guess I'll keep the same style

	
	//should only be read only here so I guess this <?> is fine 
	public List<?> listTournaments()
	{
		return restTemplate.getForObject(server_url+ "/tournaments",List.class); 
	} 
	
	public void register(String tournamentId, String name, String ip, int port) 
	{
		RegistrationRequest request = new RegistrationRequest(name,ip,port); 
		restTemplate.postForObject(
				server_url +"/register/"+ tournamentId,request, String.class); 
	
	}

}
