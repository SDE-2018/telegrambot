package dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import dialog.connection.ServiceProvider;
import soap.ws.skiresortitem.SkiResortItem;
import util.SkiItemUtil;
import soap.ws.skiresortitem.ISkiResortItemService;

/**
 * Recommends to the user 3 items (ski resorts).
 * After display waits for user evaluation of all items.
 * 
 * @author ivan
 *
 */
public class RecommendFlow extends AbstractFlow {

	private static Logger logger = Logger.getLogger(RecommendFlow.class.getName());
	
	private WebTarget recService;
	
	private ISkiResortItemService skiResortService;
    
	/**
	 * Default number of recommendations to propose for a user.
	 */
    private int nRecommendations = 3;
	
    /**
     * Number of the processed recommendations at the moment.
     */
    private int nEvaluated = 0;
    
    /**
     * List containing evaluations and ids of items already have been evaluated by user.
     * This to prevent a behavior, when a user evaluates the same item
     * multiple times. Also it helps to control
     * if user call /stop after he evaluated some items, it will not
     * send the evaluation of those items.
     */    
    private List<List<String>> evaluations = new ArrayList<>();
	        
	
	public RecommendFlow(long chatId) {
		super(chatId);
		recService = ServiceProvider.getOrCreateRecommendationService();
	    skiResortService = ServiceProvider.getOrCreateISkiResortItemService();
        logger.info("Recommend flow initialized");
	}

	/**
	 * Gives basic 3 recommendations.
	 */
	@Override
	public List<SendMessage> initFlow() {
		try {
		List<SendMessage> result = new ArrayList<>();
		// send rest request to get recommendation by user id
		// --------------------------
		String path = "recommend/user/" + Long.toString(this.chatId)
									+ "/" + Integer.toString(nRecommendations);
		Response resp = recService.path(path).request().accept(MediaType.APPLICATION_JSON)
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
        	item = skiResortService.getSkiResortItem(resortId);
        	if (item != null) {
				msg.setText(SkiItemUtil.getSkiResortItemAsString(item, true));
				nValidRecommendations += 1; 
	        	// add evaluation 5 stars reply markup
	        	msg.setReplyMarkup(getEvaluationReplyMarkup(resortId,
	        											Long.toString(chatId)));
	        	result.add(msg);
        	} 
        }
        
        if (nValidRecommendations == 0) {
        	SendMessage msg = new SendMessage().setChatId(chatId)
        						.setText("I'm sorry, I have a bad and cannot"
        								+ " suggest anything to you...( ");
        	result.add(msg);
        } else {
        	/**
        	 *  The logic here is that in case we retrieved less then default
        	 *  number should be retrieved, then we check this counter in order to 
        	 *  properly process responses for these recommendations.
        	 */
        	
        	nRecommendations = nValidRecommendations;
        	
        	// add reminder to evaluate all of them
            result.add(new SendMessage().setChatId(chatId).setText(
            					"Check out all resorts, please!"));
        }
        return result;
		} catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return null;
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
	
	public static <T> boolean hasDuplicate(Iterable<T> all) {
	    Set<T> set = new HashSet<T>();
	    // Set#add returns false if the set does not change, which
	    // indicates that a duplicate element has been added.
	    for (T each: all) if (!set.add(each)) return true;
	    return false;
	}

	/**
	 * Evaluates the given recommendations.
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		this.currentStep += 1;
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		msg.setText(DialogManager.SKIP);
		
		// get callback data 
		// 0 - resortId
		// 1 - chatId (don't need, but in case)
		// 2 - rating in stars from 1 to 5
		List<String> evaluation = Arrays.asList(
						updates.getCallbackQuery().getData().split(","));
		
		// check we get from the right chat :)
		assert(evaluation.get(1).equals(Long.toString(this.chatId)));
		evaluations.add(evaluation);
		logger.info(evaluation.toString());
		for (String s: evaluation) {
			logger.info(s);
		}

		// check how many response callbacks we received
		if (this.currentStep == nRecommendations) { // if all
			String res = "Thank you for your responses!\n";
			this.isFinished = true;
			
			// if user evaluated twice - discard
			if (hasDuplicate(evaluations)) {
				res = "Hmm, I got confused... Could you, please, start over?";
				this.isFinished = true;
				msg.setText(res);
				return msg;
			}
			// update via soap the ratings
			for (List<String> e: evaluations) {
				if (skiResortService.addSkiItemRating(
						e.get(1), e.get(0),
						Integer.parseInt(e.get(2)))){
					nEvaluated += 1;
				}
			}
			// check if we processed all evaluations
			if (nEvaluated == nRecommendations) {
				res += "I put down everything to my notes ;)\n";		
			} else {
				res += "Ouch, I lost my pen, couldn't record your responses..:(((";
			}
			msg.setText(res);
		}
		return msg;
	}

    public int getnRecommendations() {
		return nRecommendations;
	}


	public void setnRecommendations(int nRecommendations) {
		this.nRecommendations = nRecommendations;
	}
	
}
