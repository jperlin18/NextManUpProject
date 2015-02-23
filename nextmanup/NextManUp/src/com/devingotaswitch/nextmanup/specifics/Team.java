package com.devingotaswitch.nextmanup.specifics;

import java.util.HashMap;
import java.util.Map;

/**
 * Team objects have some basic team info (name, id of opponent, and 
 * the sos map from position to ranking). Separate from players for 
 * space reasons
 */
public class Team {
	
	private final int teamId;
	
	private String teamName;
	
	private int opponentId;
	
	private Map<String, Integer> sosMap;
	
	public Team(int teamId, String teamName, int opponentId, int qbSos, int rbSos, 
			int wrSos, int teSos, int defSos, int kSos){
		this.teamId = teamId;
		this.teamName = teamName;
		this.opponentId = opponentId;
		
		this.sosMap = new HashMap<String, Integer>();
		this.sosMap.put("QB", qbSos);
		this.sosMap.put("RB", rbSos);
		this.sosMap.put("WR", wrSos);
		this.sosMap.put("TE", teSos);
		this.sosMap.put("D/ST", defSos);
		this.sosMap.put("K", kSos);
	}
	
	public int getTeamId(){
		return teamId;
	}
	
	public String getTeamName(){
		return teamName;
	}
	
	public int getOpponentId(){
		return opponentId;
	}
	
	public Map<String, Integer> getSosMap(){
		return sosMap;
	}

}
