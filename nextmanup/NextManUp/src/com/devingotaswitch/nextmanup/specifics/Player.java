package com.devingotaswitch.nextmanup.specifics;

/**
 * A player will hold the basic information about them, as well as 
 * consume a rankings object to further analyze the numbers. The 
 * teamId can be used to map to team specific data (such as team name, 
 * weekly SOS, opponent...etc.)
 */
public class Player {
	
	private final int playerId;

	private PlayerRankings rankings;
	
	private String name;
	private String position;
	
	private int teamId;
	private int age;

	public Player(PlayerRankings rankings, String name, String position, 
			int teamId, int playerId, int age) {
		this.rankings = rankings;
		this.position = position;
		this.name = name;
		this.teamId = teamId;
		this.playerId = playerId;
		this.age = age;
	}
	
	public PlayerRankings getRankings(){
		return rankings;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPosition(){
		return position;
	}
	
	public int getTeamId(){
		return teamId;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public int getAge(){
		return age;
	}	
}
