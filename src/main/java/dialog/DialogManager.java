package dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class DialogManager {
	
	private static Logger logger = Logger.getLogger("dialogManagerLogger");
	
	private static Map<Long, AbstractFlow> currentDialogFlow = new HashMap<>();
	
	public DialogManager() {};
	
	public SendMessage continueDialog(long chatId, Update update) {
		DialogFlow dialogFlow = currentDialogFlow.get(chatId);
		
		return dialogFlow.continueFlow(update);
	}
	
	public void finishDialogFlow(long chatId) {
		try{
			currentDialogFlow.remove(chatId);
			logger.info("dialogflow finished..." + Long.toString(chatId));
		} catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public SendMessage start(long chatId) {
		SendMessage msg = new SendMessage().setChatId(chatId);
		if (currentDialogFlow.containsKey(chatId)) {
			msg.setText("Hmm, seems you've changed the topic, are you sure you wanna stop?");
			return msg;
		} 
		StartFlow f = new StartFlow(chatId);
		currentDialogFlow.put(chatId, f);
		logger.info("starting new start dialogflow.." + Long.toString(chatId));
		return f.initFlow();
	};
	
	public SendMessage preferences(long chatId) {
		SendMessage message = new SendMessage() // Create a message object object
		         .setChatId(chatId)
		         .setText("Please, state your preferences.");
		 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		 List<InlineKeyboardButton> rowInline = new ArrayList<>();
		 rowInline.add(new InlineKeyboardButton().setText("Monte Bondone")
		 		.setCallbackData("START_PREF_update_msg_text"));
		 rowInline.add(new InlineKeyboardButton().setText("Update message text1")
		  		.setCallbackData("update_msg_text"));
		 rowInline.add(new InlineKeyboardButton().setText("Update message text2")
		  		.setCallbackData("update_msg_text2"));
		 // Set the keyboard to the markup
		 rowsInline.add(rowInline);
		 // Add it to the message
		 markupInline.setKeyboard(rowsInline);
		 message.setReplyMarkup(markupInline);
		 return message;
	}
}
