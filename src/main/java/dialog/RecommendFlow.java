package dialog;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import soap.ws.skiresortitem.ISkiResortItemService;
import soap.ws.skiresortitem.SkiResortItem;

public class RecommendFlow extends AbstractFlow {

	private static Logger logger = Logger.getLogger("RecommendFlow");
	
	// TODO: add REST service to send POST requests to get recommendations
	
	private SkiResortItem resortItem;
	
	private ISkiResortItemService skiResortService;
	
	private WebTarget service;
	
     
     private static URI getBaseURI() {
         return UriBuilder.fromUri(
                 "http://localhost:8092/recommend/"
//         		"http://assignment2-chernukha.herokuapp.com/"
         		).build();
     }
//	        Response resp = service.path(path).request().accept(MediaType.APPLICATION_JSON)
//	        		.header("Content-type","application/json").get();
//	        String responseStr = resp.readEntity(String.class);
	
	public RecommendFlow(long chatId) {
		super(chatId);
		
		 ClientConfig clientConfig = new ClientConfig();
	     Client client = ClientBuilder.newClient(clientConfig);
	     service = client.target(getBaseURI());
	     
	     logger.info("Recommendation server address: " + getBaseURI() ); 
	}

	/**
	 * Gives basic 3 recommendations.
	 */
	@Override
	public SendMessage initFlow() {
		this.currentStep += 1;
		return new SendMessage().setChatId(this.chatId)
						.setText("Welcome to our service! What is your name?");
	}

	/**
	 * Evaluate the given recommendations.
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		return null;
	}

}
