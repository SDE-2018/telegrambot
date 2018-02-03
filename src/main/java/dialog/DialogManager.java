package dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

/**
 * TODO: forbid /preferences to happen before /start
 * 
 * Manages inputs from users and passes the control to dialog flows.
 * Flow is a sequence of user interactions, that starts with a command
 * from the list <code>supportedCommands</code>.
 * 
 * Should take care of the current global state (switching topics etc).
 * 
 * List of supported flows:
 * 1. /start 		| DONE
 * 2. /preferences  | TODO: add the first recommendation in the end of the flow
 * 
 * ----------
 * 3. /recommend
 * 
 * 4. /help
 * 5. /forget
 * 5. /stop
 * 
 * 7. /search_resort
 * 8. /add_resort
 * 9. /update_resort
 * 10./remove_resort
 * 
 * @author ivan
 *
 */
public class DialogManager {
	
	private static Logger logger = Logger.getLogger("dialogManagerLogger");
	
	/**
	 * Store the global state of users dialogs - which flow any user is now involved in. 
	 */
	private static Map<Long, AbstractFlow> currentDialogFlow = new HashMap<>();
	
	
	private static String[] supportedCommands = {
			"/start",
			"/preferences",
			"/help"
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
	 * Initiate StartFlow responsible for command '/start'.
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
		logger.info(Long.toString(chatId) + " "  + command);
		
		if (command.equals("/help")) {
			result = help(chatId);
			return result;
		}
		
		if (command.equals("/forget")) {
			
		}
		
		// TODO: check if the user have changed the topic
		// deal with topic switch
		if (currentDialogFlow.containsKey(chatId)) {
			// init forget flow ?
			result.setText("Hmm, seems you've changed the topic,"
								+ " are you sure you wanna stop?");
			return result;
		} 
		
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

	/**
	 * Now we simply create a list of available commands and explain them.
	 * I the future it's recommended to implement HelpFlow in case we have 
	 * FAQ as well.
	 * @param chatId
	 * @return
	 */
	private SendMessage help(long chatId) {
		SendMessage msg = new SendMessage().setChatId(chatId);
		String resultText = "I provide the following services:\n"
			+ "1. /start Initial command to start from. I will ask you a few "
			+ "general questions. If you call /start after you did once, it will overwrite "
			+ "your previous information and remove your preferences as well.\n" 
			+ "2. /preferences I recommend you to call this command after the 1st one. "
			+ "I will ask you questions that will help me to make a better"
			+ " ski resort recommendation. "
			+ "The questions are about your expertise level, budget you'd like to spend, etc.\n"  
			+ "3. /recommend I will suggest a few ski resorts and you give me "
			+ "a feedback how do you like them.\n" 
			+ "4. /search_resort In progress\n"
			+ "5. /add_resort In progress\n"
			+ "6. /update_resort In progress\n"
			+ "7. /remove_resort In progress\n"
			+ "8. /forget I will forget everything you typed here (like Men in Black you know :)\n"
			+ "9. /stop  Stop the interaction with the current command.\n"
			+ "10. /help List all commands.\n\n"
			+ "I hope this helped you!)"; 
		
		msg.setText(resultText);
		return msg;
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
