package util;

import soap.ws.botuser.BotUser;

public class BotUserUtil {

	public static String getUserAsString(BotUser u) {
		String res = "";
		if (u.getName() != null) {
			res += u.getName();
		}
		if (u.getExpertLevel() != 0) {
			res += "\nAge: " + Integer.toString(u.getExpertLevel());
		}
		if (u.getOccupation() != null) {
			res += "\nIn Trento you are: " + u.getOccupation();
		}
		if (u.getPreferredSkiType() != null) {
			res += "\nPreferred ski type: " + u.getPreferredSkiType();
		}
		if (u.getBudget() != 0) {
			res += "\nYou would like to spend: " + 
							Integer.toString(u.getBudget()) + " EUR";
		}
		return res;
	}
}
