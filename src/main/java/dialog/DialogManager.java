package dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Manages inputs from users and passes the control to dialog flows.
 * Should take care of the current global state (switching topics etc).
 * List of supported flows:
 * 1. /start
 * ----------
 * 2. /preferences
 * 3. /recommend
 * 4. /help
 * 5. /forget
 * 5. /stop
 * 7. /search_resort
 * 8. /add_resort
 * 9. /update_resort
 * 10./remove_resort
 * @author ivan
 *
 */
public class DialogManager {
	
	private static Logger logger = Logger.getLogger("dialogManagerLogger");
	
	/**
	 * 
	 */
	private static Map<Long, AbstractFlow> currentDialogFlow = new HashMap<>();
	
	/**
	 * 
	 */
	private static String[] supportedCommands = {
			"/start"
	};
	
	public DialogManager() {};
	
	/**
	 * 
	 * @param chatId
	 * @param update
	 * @return
	 */
	public SendMessage continueDialog(long chatId, Update update) {
		DialogFlow dialogFlow = currentDialogFlow.get(chatId);
		SendMessage result = dialogFlow.continueFlow(update);
		if (dialogFlow.isFinished()) {
			finishDialogFlow(chatId);
		}
		return result;
	}
	
	/**
	 * Properly finish the current dialog flow.
	 * @param chatId
	 */
	public boolean finishDialogFlow(long chatId) {
		try{
			currentDialogFlow.remove(chatId);
			logger.info("dialogflow finished..." + Long.toString(chatId));
			return true;
		} catch(Exception e) {
			logger.info(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Initiate StartFlow for command '/start'.
	 * @param chatId
	 */
	private SendMessage start(long chatId) {
		StartFlow f = new StartFlow(chatId);
		currentDialogFlow.put(chatId, f);
		logger.info("starting new start dialogflow.." + Long.toString(chatId));
		return f.initFlow();
	};
	
	
	
	private SendMessage preferences(long chatId) {
		PreferencesFlow f = new PreferencesFlow(chatId);
		currentDialogFlow.put(chatId, f);
		logger.info("starting new preferences dialogflow.." + Long.toString(chatId));
		return f.initFlow();
	}

	public static String[] getSupportedCommands() {
		return supportedCommands;
	}

	/**
	 * 
	 * @param chatId
	 * @param command
	 * @return
	 */
	public SendMessage startCommandFlow(long chatId, String command) {
		SendMessage result = new SendMessage().setChatId(chatId);
		if (currentDialogFlow.containsKey(chatId)) {
			result.setText("Hmm, seems you've changed the topic,"
								+ " are you sure you wanna stop?");
			return result;
		} 
		logger.info(Long.toString(chatId) + " "  + command);
		switch (command) {
			case "/start":
				logger.info("start...");
				result = start(chatId);
				break;
			case "/preferences":
				result = preferences(chatId);
				break;
			case "/recommend":
				result = recommend(chatId);
				break;
			case "/search_resort":
				result = searchResort(chatId);
				break;
			default:
				result.setText("I'm sorry, didn't get you. Could you repeat, please?");
		}
		logger.info(result.getText());
		return result;
	}

	
	
	private SendMessage searchResort(long chatId) {
		SearchResortFlow f = new SearchResortFlow(chatId);
		currentDialogFlow.put(chatId, f);
		logger.info("starting new search resort flow.." + Long.toString(chatId));
		return f.initFlow();
	}

	private SendMessage recommend(long chatId) {
		RecommendFlow f = new RecommendFlow(chatId);
		currentDialogFlow.put(chatId, f);
		logger.info("starting new recommendation flow.." + Long.toString(chatId));
		return f.initFlow();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
