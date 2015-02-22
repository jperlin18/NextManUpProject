package com.devingotaswitch.nextmanup.utilities;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlayerUtils {
	
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
		teams.put("cin", "Cincinnati Bengals");
		teams.put("cincinnati", "Cincinnati Bengals");
		teams.put("bengals", "Cincinnati Bengals");
		teams.put("pit", "Pittsburgh Steelers");
		teams.put("pittsburgh", "Pittsburgh Steelers");
		teams.put("steelers", "Pittsburgh Steelers");
		teams.put("cle", "Cleveland Browns");
		teams.put("clv", "Cleveland Browns");
		teams.put("cleveland", "Cleveland Browns");
		teams.put("browns", "Cleveland Browns");
		teams.put("bal", "Baltimore Ravens");
		teams.put("blt", "Baltimore Ravens");
		teams.put("baltimore", "Baltimore Ravens");
		teams.put("ravens", "Baltimore Ravens");
		teams.put("mia", "Miami Dolphins"); 
		teams.put("miami", "Miami Dolphins");
		teams.put("dolphins", "Miami Dolphins");
		teams.put("nwe", "New England Patriots");
		teams.put("ne", "New England Patriots");
		teams.put("nep", "New England Patriots");
		teams.put("new england", "New England Patriots");
		teams.put("england", "New England Patriots");
		teams.put("patriots", "New England Patriots");
		teams.put("pats", "New England Patriots");
		teams.put("nyj", "New York Jets");
		teams.put("jets", "New York Jets");
		teams.put("ny jets", "New York Jets");
		teams.put("n.y. jets", "New York Jets");
		teams.put("buf", "Buffalo Bills");
		teams.put("buffalo", "Buffalo Bills");
		teams.put("bills", "Buffalo Bills");
		teams.put("ind", "Indianapolis Colts");
		teams.put("indianapolis", "Indianapolis Colts");
		teams.put("colts", "Indianapolis Colts");
		teams.put("jac", "Jacksonville Jaguars");
		teams.put(" jac", "Jacksonville Jaguars");
		teams.put("jac ", "Jacksonville Jaguars");
		teams.put("jax", "Jacksonville Jaguars");
		teams.put("jacksonville", "Jacksonville Jaguars");
		teams.put("jaguars", "Jacksonville Jaguars");
		teams.put("hou", "Houston Texans");
		teams.put("houston", "Houston Texans");
		teams.put("hst", "Houston Texans");
		teams.put("texans", "Houston Texans");
		teams.put("ten", "Tennessee Titans");
		teams.put("tennessee", "Tennessee Titans");
		teams.put("titans", "Tennessee Titans");
		teams.put("kc", "Kansas City Chiefs");
		teams.put("kcc", "Kansas City Chiefs");
		teams.put("kansas", "Kansas City Chiefs");
		teams.put("kansas city", "Kansas City Chiefs");
		teams.put("chiefs", "Kansas City Chiefs");
		teams.put("oak", "Oakland Raiders");
		teams.put("oakland", "Oakland Raiders");
		teams.put("raiders", "Oakland Raiders");
		teams.put("den", "Denver Broncos");
		teams.put("denver", "Denver Broncos");
		teams.put("broncos", "Denver Broncos");
		teams.put("sd", "San Diego Chargers");
		teams.put("sdc", "San Diego Chargers");
		teams.put("san diego", "San Diego Chargers");
		teams.put("chargers", "San Diego Chargers");
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
			return "Kansas City Chiefs";
		}
		else if(low.contains("diego")) {
			return "San Diego Chargers";
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
			return "New England Patriots";
		}
		else if(low.contains("nyj")) {
			return "New York Jets";
		}
		else if(low.contains("tb")) {
			return "Tampa Bay Buccaneers";
		}
		else if(low.contains("mia")) {
			return "Miami Dolphins";
		}
		return team;
	}
}
