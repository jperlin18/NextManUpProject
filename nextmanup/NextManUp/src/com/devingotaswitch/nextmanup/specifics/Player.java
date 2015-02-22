package com.devingotaswitch.nextmanup.specifics;

public class Player {
	
	private String name;
	private String position;
	
	private int teamId;
	private int playerId;
	private int age;

	public Player(String name, int teamId, int playerId, String position, int age) {
		this.name = name;
		this.teamId = teamId;
		this.position = position;
		this.playerId = playerId;
		this.age = age;
	}
	
	public String getName(){
		return name;
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
	
	public String getPosition(){
		return position;
	}
	
}
