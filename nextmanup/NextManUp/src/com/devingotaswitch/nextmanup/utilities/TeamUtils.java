package com.devingotaswitch.nextmanup.utilities;

import java.util.Map;

import com.devingotaswitch.nextmanup.specifics.Team;

/**
 * A utilities class to be used to help reconcile the teams map with 
 * player objects and their respective team ids
 */
public class TeamUtils {
	
	private static Map<Integer, Team> teamMap;
	
	/**
	 * Sets the teams map to be used for team based 
	 * utilities
	 * 
	 * @param teams the teams map
	 */
	public static void setTeams(Map<Integer, Team> teams){
		teamMap = teams;
	}

	/**
	 * Gets the team object, given a team id
	 * 
	 * @param teamId the id of the team to get
	 * @return the team corresponding to that id
	 */
	private static Team getTeam(int teamId){
		return teamMap.get(teamId);
	}
	
	/**
	 * Gets the opponent of the team, given the id of the team
	 * 
	 * @param teamId the id of the team for which the opponent is wanted
	 * @return the team object of the opponent of the team 
	 */
	private static Team getOpponent(int teamId){
		return teamMap.get(teamMap.get(teamId).getOpponentId());
	}
	
	/**
	 * Gets the team's name, given a team id
	 * 
	 * @param teamId the id of the team
	 * @return the team name of the team
	 */
	public static String getTeamName(int teamId){
		return getTeam(teamId).getTeamName();
	}
	
	/**
	 * Gets the opponent team's name, given a team id
	 * 
	 * @param teamId the id of the team for which the opponent is wanted
	 * @return the team name of the opponent of the team
	 */
	public static String getOpponentName(int teamId){
		return getOpponent(teamId).getTeamName();
	}
	
	/**
	 * Given the team id and position of the player, the opponent is 
	 * checked and relevant sos returned
	 * 
	 * @param teamId the id of the team for which the opponent is wanted
	 * @param position the position of the opponent to check
	 * @return the sos of that opponent for that position
	 */
	public static Integer getSOS(int teamId, String position){
		return getOpponent(teamId).getSosMap().get(position);
	}
}
