package util;

import soap.ws.botuser.BotUser;

public class BotUserUtil {

	public static String getUserAsString(BotUser u) {
		String res = "";
		if (u.getName() != null) {
			res += u.getName();
		}
		if (u.getAge() != 0) {
			res += "\nAge: " + Integer.toString(u.getAge());
		}
		if (u.getOccupation() != null) {
			res += "\nIn Trento you are: " + u.getOccupation();
		}
		if (u.getPreferredSkiType() != null) {
			res += "\nPreferred ski type: " + u.getPreferredSkiType();
		}
		if (u.getExpertLevel() != 0) {
			res += "\nExpert Level: " + Integer.toString(u.getExpertLevel());
		}
		if (u.getBudget() != 0) {
			res += "\nYou would like to spend: " + 
							Integer.toString(u.getBudget()) + " EUR";
		}
		if( u.isNearTrento() ) {
			res += "\nYou would like to go to a ski resort near Trento city.";
		}
		return res;
	}
}
