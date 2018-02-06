package dialog.connection;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;

import soap.ws.botuser.IBotUserService;
import soap.ws.skiresortitem.ISkiResortItemService;

/**
 * Provides access to external web services.
 * 
 * @author ivan
 *
 */
public class ServiceProvider {

	private static Logger logger = Logger.getLogger(ServiceProvider.class.getName());
	
	private static IBotUserService botUserService = null;
	
	private static ISkiResortItemService skiResortService = null;
	
	private static WebTarget recommendationService = null;
	
	private static String botUserServiceURL = 
				"https://buss-chernukha.herokuapp.com/botuser?wsdl";
	
	private static String skiResortServiceURL = 
			"https://iss-chernukha.herokuapp.com/skiresort?wsdl";
	
	private static String recommendationServiceURL = 
			"https://rfs-chernukha.herokuapp.com/";
	
	/**
	 * Get BotUserStorageService access.
	 * 
	 * @return
	 */
	public static IBotUserService getOrCreateIBotUserService() {
		if (botUserService != null) {
			return botUserService;
		}
		URL url = null;
		try {
			url = new URL(botUserServiceURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.info("error accessing " + botUserServiceURL);
		}
		
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://botuser.ws.soap/", "BotUserService"); 
        Service s = Service.create(url, qname);
        botUserService = s.getPort(IBotUserService.class);
        return botUserService;
	}

	/**
	 * Get ItemStorageService access.
	 * 
	 * @return
	 */
	public static ISkiResortItemService getOrCreateISkiResortItemService() {
		if (skiResortService != null) {
			return skiResortService;
		}
	     URL url = null;
			try {
				url = new URL(skiResortServiceURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.info("error accessing " + skiResortServiceURL );
			}
       QName qname = new QName("http://skiresortitem.ws.soap/", "SkiResortItemService"); 
       Service resortService = Service.create(url, qname);
       skiResortService = resortService.getPort(ISkiResortItemService.class);
       return skiResortService;
	}
	
	/**
	 * Get RecommendationService access.
	 * 
	 * @return
	 */
	public static WebTarget getOrCreateRecommendationService() {
		if( recommendationService != null) {
			return recommendationService;
		}
		ClientConfig clientConfig = new ClientConfig();
	    Client client = ClientBuilder.newClient(clientConfig);
	    recommendationService = client.target(recommendationServiceURL);
	    logger.info("Recommendation server address: " + 
	    		 			recommendationServiceURL ); 
	    return recommendationService;
	}
	
	
	
	
	
	
	
}
