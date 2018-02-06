package dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import dialog.connection.ServiceProvider;
import soap.ws.botuser.BotUser;
import soap.ws.botuser.IBotUserService;
import soap.ws.skiresortitem.ISkiResortItemService;
import soap.ws.skiresortitem.SkiResortItem;
import util.BotUserUtil;
import util.SkiItemUtil;


/**
 * This flow presents information about user. It lists his/her preferences
 * and evaluated resorts with rating equal to 5 stars.
 * 
 * @author ivan
 *
 */
public class InfoFlow extends AbstractFlow {
	
	private static Logger logger = Logger.getLogger(InfoFlow.class.getName());
	
	private IBotUserService  userService;
	
	private BotUser user;

	private ISkiResortItemService skiResortService;
	
	private List<SkiResortItem> userItems;
	
	private int rating = 5;
	
	private static int nItemsDisplay = 3;
	
	/**
	 * Number of items left to be displayed after initial query.
	 */
	private int toShowMoreItems = 0;
	
	public InfoFlow(long chatId) {
		super(chatId);
		
		userService = ServiceProvider.getOrCreateIBotUserService();
		
        // get user info
        user = userService.getBotUserById(Long.toString(chatId));
        
		// init item service
        skiResortService = ServiceProvider.getOrCreateISkiResortItemService();
        logger.info("info flow initialized");
	}

	/**
	 * Get user profile and maximum top-3 resorts with 
	 * rating equal to 5 stars.
	 */
	@Override
	public List<SendMessage> initFlow() {
		List<SendMessage> result = new ArrayList<>();
		
		// if user is not registered yet
		if (user == null) {
			this.isFinished = true;
			SendMessage msg = new SendMessage().setChatId(chatId)
						.setText("Seems I have connection problem..or your data is lost:))\n"
								+ "In case you are new here, please, follow /start firstly!");
			result.add(msg);
			return result;
        }
		// get items with rating 5, 
		userItems = skiResortService.getEvaluatedItemsWithRating(
							Long.toString(this.chatId), rating);
		
		// display user info
		String userInfo = BotUserUtil.getUserAsString(user);
		SendMessage msg = new SendMessage().setChatId(chatId)
				.setText(userInfo);
		result.add(msg);
		// display top 3 with short description (without map)
		if (userItems != null) {
			if (userItems.size() < nItemsDisplay) {
				nItemsDisplay = userItems.size();
				// meaning no items will be left to continue the flow
				this.isFinished = true;
			} else {
				toShowMoreItems = userItems.size() - nItemsDisplay;
			}
			for (int i = 0; i < nItemsDisplay; i++) {
				String text = "";
				SkiResortItem item = userItems.get(i);
				text = SkiItemUtil.getSkiResortItemAsString(item, false);
				SendMessage itMsg = new SendMessage().setChatId(chatId).enableHtml(true)
						.setText(text);
				result.add(itMsg);
			}
		} else {
			String text = "You have not rated any resort!(\n "
					+ "Type /recommend and I send you a few resorts to check out!";
		}
		// ask if user wants to get all of them
		if (toShowMoreItems > 0 ) {
			String text  = "Do you want to get all of your top  picked items? "
					+ "If so, type yes";
			SendMessage showMore = new SendMessage().setChatId(chatId)
					.setText(text);
			result.add(showMore);
		}
		return result;
	}

	/**
	 * If user wants to get all his rated items,
	 * then display all of them and finish the flow.
	 */
	@Override
	public SendMessage continueFlow(Update updates) {
		SendMessage allItems = new SendMessage().setChatId(chatId).enableHtml(true);
		String text = "";
		String answer = updates.getMessage().getText().toLowerCase();
		if (answer.equals("yes")) {
		    for (int i = nItemsDisplay; i < userItems.size(); i++) {
				SkiResortItem item = userItems.get(i);
				text += SkiItemUtil.getSkiResortItemAsString(item, false);
				text += "\n";
		    }
		}
		allItems.setText(text);
		this.isFinished = true;
		return allItems;
	}

}