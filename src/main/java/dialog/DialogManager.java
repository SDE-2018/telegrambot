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
 * Usually there is a class that's responsible for a certain flow,
 * however for some simple short cases we have exceptions (/stop or /help).
 * 
 * Should take care of the current global state (switching topics etc).
 * 
 * List of supported flows:
 *  /start 		  | DONE
 *  /preferences  | TODO: add the first recommendation in the end of the flow
 *  /help		  | IN PROGRESS
 *  /stop		  | 
 * ----------
 * 3. /recommend
 * 
 * 
 * 
 * 
 * 5. /forget
 * 7. /search_resort
 * 8. /add_resort
 * 9. /update_resort
 * 10./remove_resort
 * 11. /info
 * 
 * @author ivan
 *
 */
public class DialogManager {
	
	private static Logger logger = Logger.getLogger("dialogManagerLogger");
	
	/**
	 * Store the global state of users dialogs, meaning key-value pair of flow that a 
	 * user is now involved in and chatId of the user.
	 * The user can be involved only in one flow at a time. 
	 */
	private static Map<Long, AbstractFlow> currentDialogFlow = new HashMap<>();
	
	
	/**
	 * This explanation is taken from the output of the '/help' command.
	 * 
	 * 1. /start Initial command to start from. I will ask you a few general
	 *  questions. If you call /start after you did once, it will overwrite
	 *  your previous information and remove your preferences as well.
	 * 2. /preferences I recommend you to call this command after the 1st one.
	 *  I will ask you questions that helps me to make a better ski resort 
	 *  recommendation.The questions are about your expertise level, budget 
	 *  you want to spend, etc.
	 * 3. /recommend I will give you a few ski resort recommendations and give
	 *  me a feedback how do you like them.
	 * 4. /search_resort In progress
	 * 5. /add_resort In progress
	 * 6. /update_resort In progress
	 * 7. /remove_resort In progress
	 * 8. /forget I will forget everything you typed here (like Men in Black you know :)
	 * 9. /stop  Stop the interaction with the current command. 
	 * 	Be careful - I will not ask you for a confirmation!
	 * 10. /help List all commands.
	 * 11. /info List info about the user.
	 */
	private static String[] supportedCommands = {
			"/start",
			"/preferences",
			"/help",
			"/stop"
	};
	
	
	public DialogManager() {};
	
	/**
	 * Manages the user interaction after any command has started.
	 * @param chatId
	 * @param update
	 * @return response for user input in the middle of the flow
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
			// usually it's ok if we try to remove a non-existing flow
			// (meaning user maybe typed /stop several times
			// but for some cases it may be useful
			return false;
		}
	}
	
	/**
	 * Initiate StartFlow responsible for the command '/start'.
	 */
	private SendMessage start(long chatId) {
		StartFlow f = new StartFlow(chatId);
		currentDialogFlow.put(chatId, f);
		logger.info("starting new start dialogflow.." + Long.toString(chatId));
		return f.initFlow();
	};
	
	
	/**
	 * Initiate PreferencesFlow responsible for the command '/preferences'.
	 */
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
	 * Entry point for initialization of any command flow.
	 * Invokes a certain dialog flow, manages interruptions and topic switch.
	 * @param command one of the available commands user just typed.
	 * @return list of messages to be sent. Only in the beginning of the flow
	 * (for methods initFlow()) it is allowed to send multiple messages.
	 */
	public List<SendMessage> startCommandFlow(long chatId, String command) {
		List<SendMessage> msgList = new ArrayList<>(); 
		SendMessage msg = new SendMessage().setChatId(chatId);
		logger.info(Long.toString(chatId) + " "  + command);
		
		if (command.equals("/help")) {
			msg = help(chatId);
			msgList.add(msg);
			return msgList;
		}
		
		if (command.equals("/forget")) {
			// TODO:
		}
		
		if (command.equals("/stop")) {
			// terminate current dialog flow
			finishDialogFlow(chatId);
			msg.setText("Alright, I got you!");
			msgList.add(msg);
			return msgList;
		}
		
		// deal with topic switch
		if (currentDialogFlow.containsKey(chatId)) {
			msg.setText("Hmm, seems you've changed the topic,"
								+ " are you sure you wanna stop? "
								+ "If yes, please, type /stop");
			msgList.add(msg);
			return msgList;
		} 
		
		switch (command) {
			case "/start":
				logger.info("start...");
				msg = start(chatId);
				break;
			case "/preferences":
				msg = preferences(chatId);
				break;
			case "/recommend":
				msg = recommend(chatId);
				break;
			case "/search_resort":
				msg = searchResort(chatId);
				break;
			
			default:
				msg.setText("I'm sorry, didn't get you. Could you repeat, please?");
		}
		logger.info(msg.getText());
		
		msgList.add(msg);
		for (SendMessage m: msgList) {
			logger.info(m.getText());
		}
		return msgList;
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
			+ "9. /stop  Stop the interaction with the current command.Be careful -"
			+ " I will not ask you for a confirmation!\n"
			+ "10. /help List all commands.\n"
			+ "11. /info In progress \n\n"
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
