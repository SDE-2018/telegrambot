package dialog;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface DialogFlow {
	
	public SendMessage initFlow();
	
	public SendMessage continueFlow(Update updates);
	
}
