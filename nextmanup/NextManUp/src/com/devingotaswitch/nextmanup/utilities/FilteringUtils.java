package com.devingotaswitch.nextmanup.utilities;

import java.util.ArrayList;
import java.util.List;

import com.devingotaswitch.nextmanup.specifics.Player;

public class FilteringUtils {
	
	/**
	 * Applies the dropdown sorting factors to the list of players passed in. If both 
	 * are 'All', no computational burden is added, so this could be used on any filtering.
	 * 
	 * @param teamFilter the team to filter upon, or all
	 * @param positionalFilter the position to filter upon, or all
	 * @param players the list of players to filter
	 * @return the filtered list of players
	 */
	public List<Player> applyTeamAndPositionalFilters(String teamFilter, 
			String positionalFilter, List<Player> players){
		return applyTeamFilter(teamFilter, applyPositionalFilter(positionalFilter, players));
	}
	
	/**
	 * Applies a team filter (from a dropdown) on a list of 
	 * players. If it's all, the original, parent list is returned, 
	 * but if it's a single one, only players on that team will be used
	 * 
	 * @param filter the team/all to be filtered upon
	 * @param players the parent list of players to filter
	 * @return the list of players that match the filtering factor
	 */
	private List<Player> applyTeamFilter(String filter, List<Player> players){
		if(filter.equals(Constants.ALL)){
			return players;
		}
		List<Player> filteredPlayers = new ArrayList<Player>();
		for(Player player : players){
			if(TeamUtils.getTeamName(player.getTeamId()).equals(filter)){
				filteredPlayers.add(player);
			}
		}
		return filteredPlayers;
	}

	/**
	 * Applies a positional filter (from a dropdown) on a list of players. 
	 * If it's all, the original, parent list is returned, but if it's a single one, 
	 * only players of that position will be used.
	 * 
	 * @param filter the position/all to be filtered upon
	 * @param players the parent list of players to filter
	 * @return the list of players that match the filtering factor
	 */
	private List<Player> applyPositionalFilter(String filter, List<Player> players){
		if(filter.equals(Constants.ALL)){
			return players;
		}
		List<Player> filteredPlayers = new ArrayList<Player>();
		for(Player player : players){
			if(player.getPosition().equals(filter)){
				filteredPlayers.add(player);
			}
		}
		return filteredPlayers;
	}
}
