package dialog;

import java.util.List;

import javax.ws.rs.NotSupportedException;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;


/**
 * AbstractFlow containing common fields for all dialog flows.
 * @author ivan
 *
 */
public abstract class AbstractFlow implements DialogFlow {

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
	
	/**
	 * Indicates if the flow has been finished (reached the final step) or no.
	 */
	protected boolean isFinished;
	
	public AbstractFlow(long chatId) {
		this.chatId = chatId;
	}

	@Override
	public List<SendMessage> initFlow() {
		// Auto-generated method stub
		throw new NotSupportedException();
	}

	@Override
	public SendMessage continueFlow(Update updates) {
		// Auto-generated method stub
		throw new NotSupportedException();
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
	
	
}
