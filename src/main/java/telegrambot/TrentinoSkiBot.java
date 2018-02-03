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

/**
 * Class utilizing Telegram API to handle inputs and generate responses.
 * 
 * @author ivan
 *
 */
public class TrentinoSkiBot extends TelegramLongPollingBot {
	
	private static Logger logger = Logger.getLogger("logger");
	
	protected static String BOT_TOKEN = "";
	
	private static DialogManager dm = new DialogManager();
	
	/**
	 * Cache supported commands.
	 */
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
	
	/**
	 * Pass the decision for response to dialog manager.
	 * @param update
	 * @throws TelegramApiException
	 */
	private void handleInputMessage(Update update) throws TelegramApiException {
		long chatId = update.getMessage().getChatId();
		SendMessage message;
		String text = update.getMessage().getText();
		logger.info(text);
//		logger.info(supportedCommands.toString()); // need to convert array to stream
		
		// check if a user intends to start a new flow or continue existing one
		for (String c: supportedCommands) {
			if (c.equals(text)) {
				message = dm.startCommandFlow(chatId, c);
			    execute(message);
			    return;
			}
		}
		// other messages
    	message = dm.continueDialog(chatId, update);
    	execute(message);
	}
	
	/**
	 * Pass the decision for response to dialog manager.
	 * The difference is that a callback is usually an input from a user
	 * directly to the message sent by the dialog manager, for instance,
	 * a pressed button in inline keyboard.
	 * @param update
	 * @throws TelegramApiException
	 */
	private void handleCallback(Update update) throws TelegramApiException {
		long chatId = update.getCallbackQuery().getMessage().getChatId();
		SendMessage message = dm.continueDialog(chatId, update);
    	execute(message);
	}
	
	/**
	 * Entry method for all incoming responses from a user.
	 */
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
        return "trentinoskibot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather\
    	return BOT_TOKEN;
    }
}