package util;

import soap.ws.skiresortitem.SkiResortItem;

public class SkiItemUtil {

	
	public static String getSkiResortItemAsString(SkiResortItem i, boolean displayMap) {
		String res = "";
		res += "<b>" + i.getName() + "</b>\n"; // name
		if ( i.getOfficialWebsite()!= null && !i.getOfficialWebsite().isEmpty()) {
			res += "<a href=\"" + i.getOfficialWebsite() + "\">" + i.getOfficialWebsite() + "</a>\n";
		}
		if (i.getLiftCount() != 0) {
			res += "<b>Lift count:</b>" + Integer.toString(i.getLiftCount()) + "\n";
		}
		if (i.getLongestRun() != 0) {
			res += "<b>Longest run:</b>" + Integer.toString(i.getLongestRun()) + " meters\n";
		}
		if (i.getTop() != 0) {
			res += "<b>Top:</b>" + Integer.toString(i.getTop()) + "m\n";
		}
		if (i.isTerrainPark()) {
			res += "<b>Has Terrain Park!</b>\n";
		} 
		if (i.isNightSkiing()) {
			res += "<b>Night skiing is available!</b>\n";
		}
		if (displayMap && i.getSkiMapUrl() != null) {
			res += "<a href=\"" + i.getSkiMapUrl() + "\">Map</a>";
		}
		return res;
	}
	
}
