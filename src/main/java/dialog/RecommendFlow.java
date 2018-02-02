package dialog;

import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class RecommendFlow extends AbstractFlow {

	private static Logger logger = Logger.getLogger("RecommendFlow");
	
	// TODO: add REST service to send POST requests to get recommendations
	
	public RecommendFlow(long chatId) {
		super(chatId);
	}

	@Override
	public SendMessage initFlow() {
		this.currentStep += 1;
		return new SendMessage().setChatId(this.chatId)
						.setText("Welcome to our service! What is your name?");
	}

	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage msg = new SendMessage().setChatId(this.chatId);
		return null;
	}

}
