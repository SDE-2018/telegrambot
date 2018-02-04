package dialog;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.fasterxml.jackson.databind.ObjectMapper;

import soap.ws.skiresortitem.SkiResortItem;
import soap.ws.skiresortitem.SkiResortItemService;
import soap.ws.botuser.IBotUserService;
import soap.ws.skiresortitem.ApiException_Exception;
import soap.ws.skiresortitem.ISkiResortItemService;

/**
 * 
 * @author ivan
 *
 */
public class RecommendFlow extends AbstractFlow {

	private static Logger logger = Logger.getLogger(RecommendFlow.class.getName());
	
	// TODO: add REST service to send POST requests to get recommendations
	// TODO: create util package and 
//	private SkiResortItem resortItem;
	
//	private ISkiResortItemService skiResortService;
	
	private WebTarget service;
	
	/**
	 * Default number of recommendations to propose for a user.
	 */
    private int nRecommendations = 3;
	
    private ISkiResortItemService skiResortService;
    /**
     * Number of the processed recommendations at the moment.
     */
    private int nEvaluated = 0;
    
    private static URI getRecommendationServiceURI() {
         return UriBuilder.fromUri(
                 "http://localhost:5901/"
//         		"http://assignment2-chernukha.herokuapp.com/"
         		).build();
     }
	        
	
	public RecommendFlow(long chatId) {
		super(chatId);
		// recommendation service
		 ClientConfig clientConfig = new ClientConfig();
	     Client client = ClientBuilder.newClient(clientConfig);
	     service = client.target(getRecommendationServiceURI());
	     
	     logger.info("Recommendation server address: " + 
	    		 				getRecommendationServiceURI().toString() ); 
	     // ski resort item storage service
	     URL url = null;
			try {
//				URL url = new URL("https://assignment3-chernukha.herokuapp.com/people?wsdl");
				url = new URL("http://localhost:9093/skiresort?wsdl");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
        QName qname = new QName("http://skiresortitem.ws.soap/", "SkiResortItemService"); 
        Service service = Service.create(url, qname);
        skiResortService = service.getPort(ISkiResortItemService.class);
        logger.info("Recommend flow initialized");
	}

	/**
	 * Gives basic 3 recommendations.
	 */
	@Override
	public List<SendMessage> initFlow() {
		List<SendMessage> result = new ArrayList<>();
		// send rest request to get recommendation by user id
		
		// TODO: move this to logic layer!
		// --------------------------
		String path = "recommend/user/" + Long.toString(this.chatId)
									+ "/" + Integer.toString(nRecommendations);
		Response resp = service.path(path).request().accept(MediaType.APPLICATION_JSON)
        		.header("Content-type","application/json").get();
        String responseStr = resp.readEntity(String.class);
        logger.info(responseStr);
        // --------------------------
        // parse json
        List<Object> resortIds = new JSONArray(responseStr).toList();
        int nValidRecommendations = 0;
        for (Object o: resortIds) {
        	SendMessage msg = new SendMessage().setChatId(chatId)
					.enableHtml(true);
        	String resortId = o.toString();
        	logger.info("Resort id: " + resortId);
        	SkiResortItem item = null;
        	// get skiresortitem entities by id
	        try {
				item = skiResortService.getSkiResortItem(resortId);
				msg.setText(item.toString());
				nValidRecommendations += 1;
			} catch (ApiException_Exception e) {
				e.printStackTrace();
				logger.info("error retrieving ski item with id " + resortId);
				// skip this failed recommendation
				continue;
			} 
        	// add evaluation 5 stars reply markup
        	msg.setReplyMarkup(getEvaluationReplyMarkup(resortId,
        											Long.toString(chatId)));
        	result.add(msg);
        }
        if (nValidRecommendations == 0) {
        	SendMessage msg = new SendMessage().setChatId(chatId)
        						.setText("I'm sorry, I have a bad and cannot"
        								+ " suggest anything to you...( ");
        	result.add(msg);
        } else {
        	/**
        	 *  The logic here is that in case we retrieved less then default
        	 *  number should be retrieved, then we set this counter in order to 
        	 *  properly process responses for these recommendations.
        	 */
        	
        	nRecommendations = nValidRecommendations;
        }
        return result;
	}

	
	private static InlineKeyboardMarkup getEvaluationReplyMarkup(
										String resortId, String chatId) {
		
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		rowsInline.add(new ArrayList<InlineKeyboardButton>(
			 Arrays.asList(new InlineKeyboardButton().setText("Excellent, I like it! 5 stars1 <3")
 							.setCallbackData(resortId + "," + chatId + ",5"))));
		rowsInline.add(new ArrayList<InlineKeyboardButton>(
				 Arrays.asList(new InlineKeyboardButton().setText("Cool, seems good! 4 stars :)")
 							.setCallbackData(resortId + "," + chatId + ",4"))));
		rowsInline.add(new ArrayList<InlineKeyboardButton>(
				 Arrays.asList(new InlineKeyboardButton().setText("Just OK! 3 stars.")
 							.setCallbackData(resortId + "," + chatId + ",3"))));
		rowsInline.add(new ArrayList<InlineKeyboardButton>(
				 Arrays.asList(new InlineKeyboardButton().setText("Bad! 2 stars..")
 							.setCallbackData(resortId + "," + chatId + ",2"))));
		rowsInline.add(new ArrayList<InlineKeyboardButton>(
				 Arrays.asList(new InlineKeyboardButton().setText("Dislike! 1 star.")
 							.setCallbackData(resortId + "," + chatId + ",1"))));
		markupInline.setKeyboard(rowsInline);
		 
		return markupInline;
	}


	/**
	 * Evaluates the given recommendations.
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		msg.setText(DialogManager.SKIP);
		
		// get callback data 
		// 0 - resortId
		// 1 - chatId (don't need, but in case)
		// 2 - rating in stars from 1 to 5
		List<String> evaluation = Arrays.asList(updates.getCallbackQuery().getData().split(","));
		
		// check we get from the right chat :)
		assert(evaluation.get(1).equals(Long.toString(this.chatId)));
		
		logger.info(evaluation.toString());
		for (String s: evaluation) {
			logger.info(s);
		}
		// update via soap the rating
		
		nEvaluated += 1;
		
		// check if we processed all evaluations
		if (nEvaluated == nRecommendations) {
			this.isFinished = true;
			msg.setText("Thank you for your responses!");
		}
		return msg;
	}

}
