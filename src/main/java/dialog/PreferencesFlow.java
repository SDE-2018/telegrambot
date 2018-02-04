package dialog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import soap.ws.botuser.ApiException_Exception;
import soap.ws.botuser.BotUser;
import soap.ws.botuser.IBotUserService;


/**
 * TODO: send the first recommendation (pass control to recommendation flow?)
 * 
 * PreferencesFlow manages the dialog flow of the command '/preferences'.
 * The flow looks like this:
 * Init: 
 * 		0. Ask for preferred ski type. Will display keyboard with 4 options:
 * 	    - Ski
 *  	- Snowboard
 *  	- Free-ride
 *  	- All
 * Continue:
 * 		1. Ask how good is the expert level of the option the user selected in the
 * 			previous step. Display a keyboard with 10 buttons meaning range from 1 to 10.
 * 		2. Ask for budget limit a user would like to spend in one day.
 * 			Expects integer number in EUR.
 * 		3. Ask if the resort should be close to Trento city or it's not important.
 * 			Will display a keyboard with two options:
 * 			- Yes
 * 			- Not important
 * 
 * After the last question - sends the first recommendation to the user.
 * 
 * @author ivan
 *
 */
public class PreferencesFlow extends AbstractFlow {

	private static Logger logger = Logger.getLogger(PreferencesFlow.class.getName());
	
	private IBotUserService  userService;
	
	private BotUser user;
	
	public PreferencesFlow(long chatId) {
		super(chatId);
		
		URL url = null;
		try {
//			URL url = new URL("https://assignment3-chernukha.herokuapp.com/people?wsdl");
			url = new URL("http://localhost:9090/botuser?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://botuser.ws.soap/", "BotUserService"); 
        Service service = Service.create(url, qname);
        userService = service.getPort(IBotUserService.class);
        
        user = new BotUser();
        user.setChatId((int)chatId);
	}

	@Override
	public List<SendMessage> initFlow() {
		List<SendMessage> res = new ArrayList<>();
	
		SendMessage message = new SendMessage() 
		         .setChatId(chatId)
		         .setText("Alright, let me know what do you prefer the most - skiing,"
		         		+ "snowboarding or something else?");
		
		 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		 List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
		 List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
		 rowInline1.add(new InlineKeyboardButton().setText("Skiing")
		 		.setCallbackData("skiing"));
		 rowInline1.add(new InlineKeyboardButton().setText("Snowboarding")
		  		.setCallbackData("snowboarding"));
		 rowsInline.add(rowInline1);
		 
		 rowInline2.add(new InlineKeyboardButton().setText("Free-ride")
		  		.setCallbackData("free-ride"));
		 rowInline2.add(new InlineKeyboardButton().setText("All :)")
			  		.setCallbackData("all"));
		 rowsInline.add(rowInline2);
		 
		 // Add keyboard to the message
		 markupInline.setKeyboard(rowsInline);
		 message.setReplyMarkup(markupInline);
		 
		 this.currentStep += 1;
		 res.add(message);
		 return res;
	}
	/**
	* Continue:
 * 		1. Ask how good is the expert level of the option the user selected in the
 * 			previous step. Display a keyboard with 10 buttons meaning range from 1 to 10.
 * 		2. Ask for budget limit a user would like to spend in one day.
 * 			Expects integer number in EUR.
 * 		3. Ask if the resort should be close to Trento city or it's not important.
 * 			Will display a keyboard with two options:
 * 			- Yes
 * 			- Not important
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		String resultText = "";
		// 1. Ask expert level 
		// we expect a callback here
		if (this.currentStep == 1) {
			String skiType = updates.getCallbackQuery().getData();
			logger.info(skiType);
			user.setPreferredSkiType(skiType);
			if (!skiType.equals("All")) {
				resultText += "Great, I also used to like " 
									+ skiType + " in the past!\n";		
			}
			resultText += "How good are your skills? 1 - beginner, 10 - expert level.";
			msg.setReplyMarkup(getMarkupExpertLevel(10));
		}
		// 2. Ask budget limit
		else if (this.currentStep == 2) {
			int expertLevel = Integer.parseInt(updates.getCallbackQuery().getData());
			logger.info(Integer.toString(expertLevel));
			user.setExpertLevel(expertLevel);
			if (expertLevel > 5) {
				resultText += "Wow, pretty good!\n";
			} else {
				resultText += "Well, I guess you will become an expert after "
						+ "you go to a ski resort in Trentino ;)/n";
			}
			resultText += "Let me ask you, how much you would like to spend in one day?"
						+ " Just put a number, for instance 50 or 100 (NB: currency is EUR).\nPS:"
						+ "to skip answer '-'";			
		}
		// 3. Ask close to Trento or not
		else if (this.currentStep == 3) {
			try {
				int budget= Integer.parseInt(updates.getMessage().getText());
				user.setBudget(budget);
				msg.setText("Great, thank you! Last question now.");
			} catch(Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
				resultText += "Okay, thanks, and my last question.";
			}
			 resultText += "Would you like to visit a resort that is located close to Trento?";
			 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
			 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
			 List<InlineKeyboardButton> rowInline = new ArrayList<>();
			 rowInline.add(new InlineKeyboardButton().setText("Yes")
				.setCallbackData("true"));
			 rowInline.add(new InlineKeyboardButton().setText("Not important")
				.setCallbackData("false"));
			 rowsInline.add(rowInline);
			 markupInline.setKeyboard(rowsInline);
			 msg.setReplyMarkup(markupInline);
		} 
		
		// recommend the first ski resort
		// finish the flow
		else if (this.currentStep == 4) {
			boolean isClose = Boolean.parseBoolean(updates.getCallbackQuery().getData());
			user.setNearTrento(isClose);
			try {
				userService.setPreferences(user);
				resultText += "Great, thank you very much for responses!. And yep,";
						
			} catch (ApiException_Exception e) {
				logger.info(e.getMessage());
				resultText += "Ooops, something went wrong :(\n Anyway, ";
				e.printStackTrace();
			}
			resultText += "I might have something you might be interested in";
			
			logger.info("dialog finished.");
			this.isFinished = true;
		}
		logger.info(resultText);
		msg.setText(resultText);
		this.currentStep += 1;
		
		return msg;
	}
	
	/**
	 * Create an inline keyboard markup with 10 buttons in 2 columns.
	 * @param range Recommended to set to 10.
	 */
	private static InlineKeyboardMarkup getMarkupExpertLevel(int range) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		 for (int j = 0; j < range; j += 2) {
			 String n1 = Integer.toString(j);
			 String n2 = Integer.toString(j+1);
			 rowsInline.add(new ArrayList<InlineKeyboardButton>(
					 Arrays.asList(new InlineKeyboardButton().setText(n1)
						 							.setCallbackData(n1),
							 		new InlineKeyboardButton().setText(n2)
							 						.setCallbackData(n2))));			 
		 }
		 markupInline.setKeyboard(rowsInline);
		 
		 return markupInline;
	}
	
}
