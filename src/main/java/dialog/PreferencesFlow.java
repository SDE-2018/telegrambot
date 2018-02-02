package dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class PreferencesFlow extends AbstractFlow {

	private static Logger logger = Logger.getLogger("PreferencesFlow");
	
	// TODO: add User SOAP REST service to send POST requests to create user preferences
	
	public PreferencesFlow(long chatId) {
		super(chatId);
	}

	@Override
	public SendMessage initFlow() {
		this.currentStep += 1;
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

	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		return null;
	}
}
