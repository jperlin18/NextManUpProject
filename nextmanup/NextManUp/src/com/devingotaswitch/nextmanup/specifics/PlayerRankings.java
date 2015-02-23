package com.devingotaswitch.nextmanup.specifics;

/**
 * The object that will have all of the various rankings for a player. 
 * This will be consumed by Player objects to be analyzed.
 */
public class PlayerRankings {
	
	private final int playerId;
	// Average expert ranking on the player that week (positionally)
	private int ecr;
	// Average expert projection on the player that week
	private int projection;
	
	public PlayerRankings(int playerId, int ecr, int projection){
		this.playerId = playerId;
		this.ecr = ecr;
		this.projection = projection;
	}

	public int getPlayerId(){
		return playerId;
	}
	
	public int getEcr(){
		return ecr;
	}
	
	public int getProjection(){
		return projection;
	}
}
