package util;

import soap.ws.botuser.BotUser;

public class BotUserUtil {

	public static String getUserAsString(BotUser u) {
		String res = "";
		if (u.getName() != null) {
			res += u.getName() + "\n";
		}
		if (u.getExpertLevel() != 0) {
			res += Integer.toString(u.getExpertLevel());
		}
		return res;
	}
}
