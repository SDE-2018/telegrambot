package dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class DialogManager {
	
	private static Map<Long, String> currentChatState = new HashMap<>();
	
	public DialogManager() {};
	
	public SendMessage continueDialog(long chatId, Update update) {
		return null;
	}
	
	public SendMessage start(long chatId) {
		 
		 SendMessage message = new SendMessage() // Create a message object object
                 .setChatId(chatId)
                 .setText("You send /start");
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
	};
	
	
}
