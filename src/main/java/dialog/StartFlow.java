package dialog;

import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class StartFlow extends AbstractFlow{

	private static Logger logger = Logger.getLogger("StartFlow");
	
	public StartFlow(long chatId) {
		super(chatId);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SendMessage initFlow() {
		this.currentStep += 1;
		return new SendMessage().setChatId(chatId)
						.setText("Welcome to our service! What is your name?");
	}

	@Override
	public SendMessage continueFlow(Update updates) {
		return new SendMessage().setChatId(this.chatId)
				.setText("Hello, " + updates.getMessage().getText());
	}
}
