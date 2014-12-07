package com.devingotaswitch.nextmanup.specifics;

public class Player {
	
	private final String name;
	private final String team;
	private final String position;
	
	/**
	 * Not entirely sure if this will be necessary, 
	 * but it could be internally
	 */
	public Player() {
		name = "unknown";
		team = "unknown";
		position = "unknown";
	}

	public Player(String name, String team, String position) {
		this.name = name;
		this.team = team;
		this.position = position;
	}
	
	/**
	 * Player name with team appended is a unique way to 
	 * identify a player (i.e. there were two Steve Smith's 
	 * who played WR recently)
	 * 
	 * @return name.team
	 */
	public String getIdentifier(){
		return name + "." + team;
	}
}
