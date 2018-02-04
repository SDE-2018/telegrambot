package dialog;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import soap.ws.skiresortitem.SkiResortItem;
import soap.ws.skiresortitem.SkiResortItemService;

//import soap.ws.skiresortitem.ISkiResortItemService;
//import soap.ws.skiresortitem.SkiResortItem;

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
	
     
     private static URI getRecommendationServiceURI() {
         return UriBuilder.fromUri(
                 "http://localhost:5901/"
//         		"http://assignment2-chernukha.herokuapp.com/"
         		).build();
     }
	        
	
	public RecommendFlow(long chatId) {
		super(chatId);
		
		 ClientConfig clientConfig = new ClientConfig();
	     Client client = ClientBuilder.newClient(clientConfig);
	     service = client.target(getRecommendationServiceURI());
	     
	     logger.info("Recommendation server address: " + 
	    		 				getRecommendationServiceURI().toString() ); 
	}

	/**
	 * Gives basic 3 recommendations.
	 */
	@Override
	public List<SendMessage> initFlow() {
		// this.currentStep += 1;
		List<SendMessage> result = new ArrayList<>();
		// send rest request to get recommendation by user id
		
		// TODO: move this to logic layer!
		String path = "recommend/user/" + Long.toString(this.chatId) + "/3";
		Response resp = service.path(path).request().accept(MediaType.APPLICATION_JSON)
        		.header("Content-type","application/json").get();
        String responseStr = resp.readEntity(String.class);
        logger.info(responseStr);
        
        // parse json
        List<Object> resortIds = new JSONArray(responseStr).toList();
        for (Object o: resortIds) {
        	String resortId = o.toString();
        	logger.info("Resort id: " + resortId);
        	String text = "text";
        	
		// get skiresortitem entities by id
//        SkiResortItem item = 
        // add evaluation 5 stars reply markup
		
		// call .toString and setText
        	
        	SendMessage msg = new SendMessage().setChatId(chatId)
        												.enableHtml(true);
        	msg.setReplyMarkup(getEvaluationReplyMarkup(resortId, Long.toString(chatId)));
        	msg.setText(text);
        	result.add(msg);
        }
        String res = "";
		res += "<b>" + "Monte Bondone" + "</b>\n";
		res += "<a href=\"" + "https://skimap.org/data/12873/2205/1505481965.jpg" + "\">Map</a>";

		
		
		
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
	 * Evaluate the given recommendations.
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		// get callback data - resort id, rating
		List<String> evaluation = Arrays.asList(updates.getCallbackQuery().getData().split(","));

		logger.info(evaluation.toString());
		for (String s: evaluation) {
			logger.info(s);
		}
		// update via soap the rating
		// ask if the user want more
		
		// check if no then finish the flow 
		// if yes - ???
		return null;
	}

}
