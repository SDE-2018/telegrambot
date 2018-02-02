package dialog;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;


/**
 * General interface for creating a response to a flow implementing
 * certain behavior.
 * @author ivan
 *
 */
public interface DialogFlow {
	
	/**
	 * This method is called when a user types a command that is initiating a flow.
	 * @return First message to the user from the flow.
	 */
	public SendMessage initFlow();
	
	
	/**
	 * This method gives all consequent responses to a user within the flow.
	 * It intentionally does not throw any exception, in order to encourage
	 * a developer to deal with user interactions in a safe way!
	 * @param updates contain user message related info.
	 * @return Next response to the user.
	 */
	public SendMessage continueFlow(Update updates);
	
	/**
	 * All dialog flows must maintain internal state and let other
	 * classes know if the current flow is finished or no.
	 * @return true if the flow has been finished, false otherwise.
	 */
	public boolean isFinished();
	
}
