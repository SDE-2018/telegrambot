package telegrambot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import dialog.DialogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Math.toIntExact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TrentinoSkiBot extends TelegramLongPollingBot {
	
	private static Logger logger = Logger.getLogger("logger");
	
	protected static String BOT_TOKEN = "";
	
	private static DialogManager dm = new DialogManager();
	
	private static String[] supportedCommands = DialogManager.getSupportedCommands();
	
	static {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("local.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        BOT_TOKEN = properties.getProperty("BOT_TOKEN");
	}
	
	static {
	    FileHandler fh;  
	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("logs.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        // the following statement is used to log any messages  
	        logger.info("My first log");  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	private void handleInputMessage(Update update) throws TelegramApiException {
//		String message_text = update.getMessage().getText();
		long chatId = update.getMessage().getChatId();
		SendMessage message;
		String text = update.getMessage().getText();
		logger.info(supportedCommands.toString());
		for (String c: supportedCommands) {
			logger.info(text);
			if (c.equals(text)) {
				message = dm.startCommandFlow(chatId, c);
			    execute(message);
			    return;
			}
		}
		// other message:
    	message = dm.continueDialog(chatId, update);
    	execute(message);
	}
	
	private void handleCallback(Update update) throws TelegramApiException {
    	// Set variables
        String call_data = update.getCallbackQuery().getData();
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        
        if (call_data.startsWith("START")) {
        	String answer = new String(call_data.getBytes());
        	logger.info("answer: " + answer);
            EditMessageText new_message = new EditMessageText()
                    .setChatId(chatId)
                    .setMessageId(toIntExact(message_id))
                    .setText(answer);
            execute(new_message);
        } else if (call_data.startsWith("HELP")) {
        	
        } else if (call_data.startsWith("FINISH")){
        	dm.finishDialogFlow(chatId);
        }
	}
	
    @Override
    public void onUpdateReceived(Update update) {  	
        if (update.hasMessage() && update.getMessage().hasText()) {
        	 try {
        		 handleInputMessage(update);
        	 } catch (TelegramApiException e) {
                 e.printStackTrace();
             }
        }
        if (update.hasCallbackQuery()) {
        	try {
        		handleCallback(update);
        	} catch (TelegramApiException e) {
        		e.printStackTrace();
        	}
        }
    }
    
    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "trentinoskibot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather\
    	return BOT_TOKEN;
    }
}