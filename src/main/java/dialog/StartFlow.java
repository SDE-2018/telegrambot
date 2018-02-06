package dialog;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import dialog.connection.ServiceProvider;
import soap.ws.botuser.*;

/**
 * StartFlow manages the dialog flow of the command '/start'.
 * The flow looks like this:
 * Init: 
 * 		0. Greet a user and ask a name. (open question)
 * Continue:
 * 		1. Ask for age. (open question)
 * 		2. Ask what he/she is doing in Trentino. (open question)
 * 		3. Thank a user and give list of the commands, 
 *    		propose to choose his/her ski preferences,
 *    		set Callback and finish the dialog flow.
 *    
 * @author ivan
 *
 */
public class StartFlow extends AbstractFlow{

	private static Logger logger = Logger.getLogger(StartFlow.class.getName());
	
	private IBotUserService  userService;
	
	private BotUser user;
	
	public StartFlow(long chatId) {
		super(chatId);
		
		userService = ServiceProvider.getOrCreateIBotUserService();
        
        user = new BotUser();
		user.setChatId((int)this.chatId);
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar dateGregorian = null;
		try {
			dateGregorian = DatatypeFactory
					.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		user.setRegisterDate(dateGregorian);
	}
	
	@Override
	public List<SendMessage> initFlow() {    
		this.currentStep += 1;
		List<SendMessage> res = new ArrayList<>();
		res.add(new SendMessage().setChatId(this.chatId)
				.setText("Welcome to our service! What is your name?"));
		return res;
	}

	/**
	 * TODO: add check if the user already did /start before
	 * TODO: check '-' input
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		
		//  Get a user name
		if (currentStep == 1) {
			String name = updates.getMessage().getText();
			user.setName(name);
			
			msg.setText("Hello, " + name + 
					"!\n I'd like to ask a few questions to know better who you are."
					+ " If you don't want to answer - put '-' :)\n"
					+ "How old are you?");
		}
		// Get a user age
		else if (currentStep == 2) {
			// try to parse the age
			try {
				int age = Integer.parseInt(updates.getMessage().getText());
				user.setAge(age);
				msg.setText("Cool! And what do you do in Trento? (e.g. student,"
					 	+ " working at public service, travelling, etc)");
			} catch(Exception e) {
				logger.info(e.getMessage());
				msg.setText("Okay, and what do you do in Trento?");
			}
			
		}
		// Get a user occupation
		// List commands, propose to set preferences, 
		// set flag to finish the flow
		else if (currentStep == 3) {
			String occupation = updates.getMessage().getText();
			user.setOccupation(occupation);
			try {
				if(userService.createBotUser(user)) {
				msg.setText("hmm, I see. Probably I know some people like you,"
						+ " and that may help me to make a better ski resort recommendation!\n"
						+ "Now go ahead and navigate my other commands, search for ski resorts."
						+ " However, I'd invite you to set"
						+ " your preferences firstly.\n Just type '/preferences' :)");
				DialogManager.userHasProfile.put(new Long(chatId), new Boolean(true));
				logger.info("user created!");
				}
			} catch (ApiException_Exception e) {
				logger.info(e.getMessage());
				msg.setText("Something went wrong.. Anyway!\n"
						+ "Now you can navigate my other commands, search for ski resorts,"
						+ " but I'd invite you to set"
						+ " your preferences firstly. Just type '/preferences' :)");
			} 
			logger.info("dialog finished.");
			this.isFinished = true;
			
		}
		
		this.currentStep += 1;
		return msg;
	}
}
