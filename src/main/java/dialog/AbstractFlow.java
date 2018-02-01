package dialog;

import javax.ws.rs.NotSupportedException;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class AbstractFlow implements DialogFlow {

	/**
	 * The id of the chat the flow is engaged now.
	 */
	protected long chatId;
	
	/**
	 * All flows may contain from 1 to N inner step they require a user to complete
	 * in order to finish the flow. This represents the current step in the flow,
	 * allowing to understand what should be the next response.
	 */
	protected int currentStep;
	
	
	public AbstractFlow(long chatId) {
		this.chatId = chatId;
	}

	@Override
	public SendMessage initFlow() {
		// TODO Auto-generated method stub
		throw new NotSupportedException();
	}

	@Override
	public SendMessage continueFlow(Update updates) {
		// TODO Auto-generated method stub
		throw new NotSupportedException();
	}
	

}
