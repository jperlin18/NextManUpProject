package com.devingotaswitch.nextmanup.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A psuedo enum to have the positions/teams in one place, so 
 * future formatting changes won't need changing in more 
 * than one place.
 */
public class Constants {
	//General constants
	public static final String ALL = "All";
	public static List<String> teams = new ArrayList<String>();
	public static List<String> positions = new ArrayList<String>();

	//Position constants
	public static final String QUARTERBACK = "QB";
	public static final String RUNNING_BACK = "RB";
	public static final String WIDE_RECEIVER = "WR";
	public static final String TIGHT_END = "TE";
	public static final String DEFENSE = "D/ST";
	public static final String KICKER = "K";
	
	//Team constants
	//AFC North
	public static final String BENGALS = "Cincinnati Bengals";
	public static final String STEELERS = "Pittsburgh Steelers";
	public static final String RAVENS = "Baltimore Ravens";
	public static final String BROWNS = "Cleveland Browns";
	//AFC East
	public static final String DOLPHINS = "Miami Dolphins";
	public static final String PATRIOTS = "New England Patriots";
	public static final String JETS = "New York Jets";
	public static final String BILLS = "Buffalo Bills";
	//AFC South
	public static final String COLTS = "Indianapolis Colts";
	public static final String JAGUARS = "Jacksonville Jaguars";
	public static final String TITANS = "Tennessee Titans";
	public static final String TEXANS = "Houston Texans";
	//AFC West
	public static final String BRONCOS = "Denver Broncos";
	public static final String CHARGERS = "San Diego Chargers";
	public static final String CHIEFS = "Kansas City Chiefs";
	public static final String RAIDERS = "Oakland Raiders";
	//NFC North	
	public static final String BEARS = "Chicago Bears";
	public static final String PACKERS = "Green Bay Packers";
	public static final String VIKINGS = "Minnesota Vikings";
	public static final String LIONS = "Detroit Lions";
	//NFC East
	public static final String EAGLES = "Philadelphia Eagles";
	public static final String COWBOYS = "Dallas Cowboys";
	public static final String GIANTS = "New York Giants";
	public static final String REDSKINS = "Washington Redskins";
	//NFC South
	public static final String BUCCANEERS = "Tampa Bay Buccaneers";
	public static final String FALCONS = "Atlanta Falcons";
	public static final String SAINTS = "New Orleans Saints";
	public static final String PANTHERS = "Carolina Panthers";
	//NFC West
	public static final String FORTYNINERS = "San Francisco 49ers";
	public static final String RAMS = "St. Louis Rams";
	public static final String SEAHAWKS = "Seattle Seahawks";
	public static final String CARDINALS = "Arizona Cardinals";
	
	/**
	 * A boring helper to get the string keys for the various positions, to 
	 * be used in case of a dropdown for sorting...etc.
	 * 
	 * @return a list holding all of the positional values
	 */
	public static List<String> getAllPositions(){
		if(positions.size() == 0){
			//For the sake of a dropdown, all is an obvious necessity
			positions.add(ALL);
			//Real positional options
			positions.add(QUARTERBACK);
			positions.add(RUNNING_BACK);
			positions.add(WIDE_RECEIVER);
			positions.add(TIGHT_END);
			positions.add(DEFENSE);
			positions.add(KICKER);
		}
		return positions;
	}
	
	/**
	 * A boring helper to get the string keys for all of the teams, to 
	 * be used in case of a dropdown for sorting...etc.
	 * 
	 * @return a list holding all of the teams
	 */
	public static List<String> getAllTeams(){
		if(teams.size() == 0){
			//For the sake of a dropdown, all is an obvious necessity
			teams.add(ALL);
			//Real teams, alphabetical on team name after sorted on division
			//AFC North
			teams.add(BENGALS);
			teams.add(BROWNS);
			teams.add(RAVENS);
			teams.add(STEELERS);
			//AFC East
			teams.add(BILLS);
			teams.add(DOLPHINS);
			teams.add(JETS);
			teams.add(PATRIOTS);
			//AFC South
			teams.add(COLTS);
			teams.add(JAGUARS);
			teams.add(TEXANS);
			teams.add(TITANS);
			//AFC West
			teams.add(BRONCOS);
			teams.add(CHARGERS);
			teams.add(CHIEFS);
			teams.add(RAIDERS);
			//NFC North
			teams.add(BEARS);
			teams.add(LIONS);
			teams.add(PACKERS);
			teams.add(VIKINGS);
			//NFC East
			teams.add(COWBOYS);
			teams.add(EAGLES);
			teams.add(GIANTS);
			teams.add(REDSKINS);
			//NFC South
			teams.add(BUCCANEERS);
			teams.add(FALCONS);
			teams.add(PANTHERS);
			teams.add(SAINTS);
			//NFC West
			teams.add(CARDINALS);
			teams.add(FORTYNINERS);
			teams.add(RAMS);
			teams.add(SEAHAWKS);
		}
		return teams;
	}
}
