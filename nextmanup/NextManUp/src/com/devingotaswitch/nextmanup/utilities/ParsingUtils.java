package com.devingotaswitch.nextmanup.utilities;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ParsingUtils {
	
	private static Map<String, String> teams;
	
	/**
	 * Does some initial set up. Call this before using a util 
	 * method.
	 */
	private static void init(){
		if(teams == null || teams.size() > 0){
			teams = new HashMap<String, String>();
			handleHashes();
		}
	}
	
	/**
	 * Populates the fix hashes for team names
	 */
	private static void handleHashes()
	{
		teams.clear();
		teams.put("cin", Constants.BENGALS);
		teams.put("cincinnati", Constants.BENGALS);
		teams.put("bengals", Constants.BENGALS);
		teams.put("pit", Constants.STEELERS);
		teams.put("pittsburgh", Constants.STEELERS);
		teams.put("steelers", Constants.STEELERS);
		teams.put("cle", Constants.BROWNS);
		teams.put("clv", Constants.BROWNS);
		teams.put("cleveland", Constants.BROWNS);
		teams.put("browns", Constants.BROWNS);
		teams.put("bal", Constants.RAVENS);
		teams.put("blt", Constants.RAVENS);
		teams.put("baltimore", Constants.RAVENS);
		teams.put("ravens", Constants.RAVENS);
		teams.put("mia", Constants.DOLPHINS); 
		teams.put("miami", Constants.DOLPHINS);
		teams.put("dolphins", Constants.DOLPHINS);
		teams.put("nwe", Constants.PATRIOTS);
		teams.put("ne", Constants.PATRIOTS);
		teams.put("nep", Constants.PATRIOTS);
		teams.put("new england", Constants.PATRIOTS);
		teams.put("england", Constants.PATRIOTS);
		teams.put("patriots", Constants.PATRIOTS);
		teams.put("pats", Constants.PATRIOTS);
		teams.put("nyj", Constants.JETS);
		teams.put("jets", Constants.JETS);
		teams.put("ny jets", Constants.JETS);
		teams.put("n.y. jets", Constants.JETS);
		teams.put("buf", Constants.BILLS);
		teams.put("buffalo", Constants.BILLS);
		teams.put("bills", Constants.BILLS);
		teams.put("ind", Constants.COLTS);
		teams.put("indianapolis", Constants.COLTS);
		teams.put("colts", Constants.COLTS);
		teams.put("jac", Constants.JAGUARS);
		teams.put(" jac", Constants.JAGUARS);
		teams.put("jac ", Constants.JAGUARS);
		teams.put("jax", Constants.JAGUARS);
		teams.put("jacksonville", Constants.JAGUARS);
		teams.put("jaguars", Constants.JAGUARS);
		teams.put("hou", Constants.TEXANS);
		teams.put("houston", Constants.TEXANS);
		teams.put("hst", Constants.TEXANS);
		teams.put("texans", Constants.TEXANS);
		teams.put("ten", Constants.TITANS);
		teams.put("tennessee", Constants.TITANS);
		teams.put("titans", Constants.TITANS);
		teams.put("kc", Constants.CHIEFS);
		teams.put("kcc", Constants.CHIEFS);
		teams.put("kansas", Constants.CHIEFS);
		teams.put("kansas city", Constants.CHIEFS);
		teams.put("chiefs", Constants.CHIEFS);
		teams.put("oak", Constants.RAIDERS);
		teams.put("oakland", Constants.RAIDERS);
		teams.put("raiders", Constants.RAIDERS);
		teams.put("den", Constants.BRONCOS);
		teams.put("denver", Constants.BRONCOS);
		teams.put("broncos", Constants.BRONCOS);
		teams.put("sd", Constants.CHARGERS);
		teams.put("sdc", Constants.CHARGERS);
		teams.put("san diego", Constants.CHARGERS);
		teams.put("chargers", Constants.CHARGERS);
		teams.put("chi", "Chicago Bears");
		teams.put("chicago", "Chicago Bears");
		teams.put("bears", "Chicago Bears");
		teams.put("min", "Minnesota Vikings");
		teams.put("minnesota", "Minnesota Vikings");
		teams.put("vikings", "Minnesota Vikings");
		teams.put("det", "Detroit Lions");
		teams.put("detroit", "Detroit Lions");
		teams.put("lions", "Detroit Lions");
		teams.put("gb", "Green Bay Packers");
		teams.put("gb", "Green Bay Packers");
		teams.put("gb ", "Green Bay Packers");
		teams.put(" gb", "Green Bay Packers");
		teams.put("gbp", "Green Bay Packers");
		teams.put("green bay", "Green Bay Packers");
		teams.put("packers", "Green Bay Packers");
		teams.put("nyg", "New York Giants");
		teams.put("n.y. giants", "New York Giants");
		teams.put("giants", "New York Giants");
		teams.put("ny giants", "New York Giants");
		teams.put("phi", "Philadelphia Eagles");
		teams.put("philadelphia", "Philadelphia Eagles");
		teams.put("eagles", "Philadelphia Eagles");
		teams.put("dal", "Dallas Cowboys");
		teams.put("dallas", "Dallas Cowboys");
		teams.put("cowboys", "Dallas Cowboys");
		teams.put("was", "Washington Redskins");
		teams.put("washington", "Washington Redskins");
		teams.put("redskins", "Washington Redskins");
		teams.put("atl", "Atlanta Falcons");
		teams.put("atlanta", "Atlanta Falcons");
		teams.put("falcons", "Atlanta Falcons");
		teams.put("car", "Carolina Panthers");
		teams.put("carolina", "Carolina Panthers");
		teams.put("panthers", "Carolina Panthers");
		teams.put("no", "New Orleans Saints");
		teams.put("nos", "New Orleans Saints");
		teams.put("new orleans", "New Orleans Saints");
		teams.put("saints", "New Orleans Saints");
		teams.put("tb", "Tampa Bay Buccaneers");
		teams.put("tbb", "Tampa Bay Buccaneers");
		teams.put("tampa bay", "Tampa Bay Buccaneers");
		teams.put("buccaneers", "Tampa Bay Buccaneers");
		teams.put("tampa", "Tampa Bay Buccaneers");
		teams.put("sea", "Seattle Seahawks");
		teams.put("seattle", "Seattle Seahawks");
		teams.put("seahawks", "Seattle Seahawks");
		teams.put("sf", "San Francisco 49ers");
		teams.put("sfo", "San Francisco 49ers");
		teams.put("san francisco", "San Francisco 49ers");
		teams.put("ers", "San Francisco 49ers");
		teams.put("49ers", "San Francisco 49ers");
		teams.put("stl", "St. Louis Rams");
		teams.put("st. louis", "St. Louis Rams");
		teams.put("st louis", "St. Louis Rams");
		teams.put("rams", "St. Louis Rams");
		teams.put("sl", "St. Louis Rams");
		teams.put("ari", "Arizona Cardinals");
		teams.put("arz", "Arizona Cardinals");
		teams.put("arizona", "Arizona Cardinals");
		teams.put("cardinals", "Arizona Cardinals");
	}
	
	
	/**
	 * This adjusts teams to a standard value
	 * @param team the team to check
	 * @return the possibly adjusted team string
	 */
	public static String fixTeams(String team) {
		init();
		String low = team.toLowerCase().replaceAll("[^\\x20-\\x7e]","");
		if(low.split(" ").length > 1 && (low.split(" ")[1].equals("p") 
				|| low.split(" ")[1].equals("q"))) {
			low = low.split(" ")[0];
		}
		if(teams.containsKey(low)) {
			return teams.get(low);
		}
		else if(low.contains("kansas")){
			return Constants.CHIEFS;
		}
		else if(low.contains("diego")) {
			return Constants.CHARGERS;
		}
		else if(low.contains("green")) { 
			return "Green Bay Packers";
		}
		else if(low.contains("tampa")) {
			return "Tampa Bay Buccaneers";
		}
		else if(low.contains("orleans")) {
			return "New Orleans Saints";
		}
		else if(low.contains("louis")) {
			return "St. Louis Rams";
		}
		else if(low.contains("francisco")) {
			return "San Francisco 49ers";
		}
		else if(low.contains("england")) {
			return Constants.PATRIOTS;
		}
		else if(low.contains("nyj")) {
			return Constants.JETS;
		}
		else if(low.contains("tb")) {
			return "Tampa Bay Buccaneers";
		}
		else if(low.contains("mia")) {
			return Constants.DOLPHINS;
		}
		return team;
	}
}
