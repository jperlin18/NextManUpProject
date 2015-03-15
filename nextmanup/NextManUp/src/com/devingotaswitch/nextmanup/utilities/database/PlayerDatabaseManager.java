package com.devingotaswitch.nextmanup.utilities.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.devingotaswitch.nextmanup.specifics.Player;
import com.devingotaswitch.nextmanup.specifics.PlayerRankings;
import com.devingotaswitch.nextmanup.specifics.Team;
import com.devingotaswitch.nextmanup.utilities.Constants;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerDatabaseManager extends SQLiteOpenHelper {

	// Database Version (incrementing will trigger onUpgrade)
	// V1: initial work
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "PlayerData";
	
	// SharedPreferences Key
	private final String SP_KEY = "bevin_is_a_ditch";
	private final String WATCH_LIST_KEY = ".watchListed";

	// Table names
	private static final String TEAM_INFO_TABLE = "TEAM_INFO";
	private static final String PLAYER_BASIC_INFO_TABLE = "PLAYER_BASIC_INFO";
	private static final String PLAYER_STATS_TABLE = "PLAYER_STATS";

	private Context context;

	public PlayerDatabaseManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	/**
	 * Deletes the old tables and re-reads from text files to populate them
	 * again.
	 */
	public void reCreateTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		deleteTablesIfExist(db);
		onCreate(db);
	}

	/**
	 * Creates tables and inserts the drinks, ingredients, and matchings into
	 * database
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the basic team info table, with a name, opponent (id),
		// sos numbers for each position (for sake of opponent lookup),
		// and id as the key. For the sake of space, this should probably
		// be an independent map from the players
		String createTeamInfoTable = "CREATE TABLE " + TEAM_INFO_TABLE + " "
				+ "(" + "ID             INTEGER    PRIMARY KEY,"
				+ "NAME           TEXT       NOT NULL,"
				+ "OPPONENT       INTEGER    NOT NULL,"
				+ "QB_SOS         INTEGER    NOT NULL,"
				+ "RB_SOS         INTEGER    NOT NULL,"
				+ "WR_SOS         INTEGER    NOT NULL,"
				+ "TE_SOS         INTEGER    NOT NULL,"
				+ "DEF_SOS        INTEGER    NOT NULL,"
				+ "K_SOS          INTEGER    NOT NULL" + ")";
		db.execSQL(createTeamInfoTable);

		// Create the basic player info table, with a name, team id, position,
		// age, and id as the key
		String createPlayerBasicInfoTable = "CREATE TABLE "
				+ PLAYER_BASIC_INFO_TABLE + " " + "("
				+ "ID             INTEGER   PRIMARY KEY,"
				+ "NAME           TEXT      NOT NULL"
				+ "POSITION       TEXT      NOT NULL"
				+ "TEAM_ID        INTEGER   NOT NULL"
				+ "AGE            INTEGER   NOT NULL" + ")";
		db.execSQL(createPlayerBasicInfoTable);

		// Create the player stats table to keep track of the various values.
		// As things are added, this will probs need updating.
		String createPlayerStatsTable = "CREATE TABLE " + PLAYER_STATS_TABLE
				+ " " + "(" + "ID             INTEGER    PRIMARY KEY,"
				+ "ECR            INT        NOT NULL,"
				+ "PROJECTION     INT        NOT NULL" + ")";
		db.execSQL(createPlayerStatsTable);
	}

	/**
	 * Deletes and repopulates tables if database was upgraded
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		deleteTablesIfExist(db);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Deletes tables from db if they already exist
	 * 
	 * @param db
	 *            this database
	 */
	private void deleteTablesIfExist(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_INFO_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_BASIC_INFO_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_STATS_TABLE);
	}
	
	/**
	 * Saves all of the teams to the database, to be used in case of 
	 * initial fetching or mass update
	 * 
	 * @param teams the list of teams to save
	 */
	public void saveTeams(List<Team> teams){
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			db.beginTransaction();
			for(Team team : teams){
				ContentValues values = new ContentValues();
				values.put("ID", team.getTeamId());
				values.put("NAME", team.getTeamName());
				values.put("OPPONENT", team.getOpponentId());
				Map<String, Integer> sos = team.getSosMap();
				values.put("QB_SOS", sos.get(Constants.QUARTERBACK));
				values.put("RB_SOS", sos.get(Constants.RUNNING_BACK));
				values.put("WR_SOS", sos.get(Constants.WIDE_RECEIVER));
				values.put("TE_SOS", sos.get(Constants.TIGHT_END));
				values.put("DEF_SOS", sos.get(Constants.DEFENSE));
				values.put("K_SOS", sos.get(Constants.KICKER));
				db.insert(TEAM_INFO_TABLE, null, values);
			}
    	} catch (SQLException e) {}
    	finally{
    		db.endTransaction();
    	}
	}
	
	/**
	 * Saves a single team to the database, to be used in case of an 
	 * update to a single team's information
	 * @param team
	 */
	public void saveTeam(Team team){
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			db.beginTransaction();
			db.insert(TEAM_INFO_TABLE, null, getTeamDataToSave(team));
    	} catch (SQLException e) {}
    	finally{
    		db.endTransaction();
    	}
	}
	
	private ContentValues getTeamDataToSave(Team team){
		ContentValues values = new ContentValues();
		values.put("ID", team.getTeamId());
		values.put("NAME", team.getTeamName());
		values.put("OPPONENT", team.getOpponentId());
		Map<String, Integer> sos = team.getSosMap();
		values.put("QB_SOS", sos.get(Constants.QUARTERBACK));
		values.put("RB_SOS", sos.get(Constants.RUNNING_BACK));
		values.put("WR_SOS", sos.get(Constants.WIDE_RECEIVER));
		values.put("TE_SOS", sos.get(Constants.TIGHT_END));
		values.put("DEF_SOS", sos.get(Constants.DEFENSE));
		values.put("K_SOS", sos.get(Constants.KICKER));
		return values;
	}
	
	/**
	 * Saves all of the player info to the player info table and stats to the 
	 * stats table, to be used in case of initial fetch or mass update
	 * 
	 * @param players the players to save to the database
	 */
	public void savePlayers(List<Player> players){
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			db.beginTransaction();
			for(Player player : players){
				db.insert(PLAYER_BASIC_INFO_TABLE, null, getPlayerInfoToSave(player));
				db.insert(PLAYER_STATS_TABLE, null, getPlayerStatsToSave(player));
			}
    	} catch (SQLException e) {}
    	finally{
    		db.endTransaction();
    	}
	}
	/**
	 * Saves all of the info for a single player to both the stats table 
	 * and the player info table, to be used in case of a single update
	 * 
	 * @param player the player to update
	 */
	public void savePlayer(Player player){
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			db.beginTransaction();
			db.insert(PLAYER_BASIC_INFO_TABLE, null, getPlayerInfoToSave(player));
			db.insert(PLAYER_STATS_TABLE, null, getPlayerStatsToSave(player));
    	} catch (SQLException e) {}
    	finally{
    		db.endTransaction();
    	}
	}
	
	/**
	 * A helper to generate the ContentValues for player info to save to the database 
	 * so saving for batches and saving for a single player isn't duplicated
	 * 
	 * @param player the player to get the content values for
	 * @return the content values of the player info
	 */
	private ContentValues getPlayerInfoToSave(Player player){
		ContentValues playerValues = new ContentValues();
		playerValues.put("ID", player.getPlayerId());
		playerValues.put("NAME", player.getName());
		playerValues.put("POSITION", player.getPosition());
		playerValues.put("TEAM_ID", player.getTeamId());
		playerValues.put("AGE", player.getAge());
		return playerValues;
	}
	
	/**
	 * A helper to generate the ContentValues for player stats to save to the database 
	 * so saving for batches and saving for a single player isn't duplicated
	 * 
	 * @param player the player to get the content values for
	 * @return the content values of the player info
	 */
	private ContentValues getPlayerStatsToSave(Player player){
		ContentValues statsValues = new ContentValues();
		PlayerRankings stats = player.getRankings();
		statsValues.put("ID", player.getPlayerId());
		statsValues.put("ECR", stats.getEcr());
		statsValues.put("PROJECTION", stats.getProjection());
		return statsValues;
	}

	/**
	 * A helper to load all teams into a map, to get (in constant time) a team
	 * given a teamId. This should be independent of the players structure, as
	 * each has a teamId and opponentId.
	 * 
	 * @return a map of all 32 team objects
	 */
	public Map<Integer, Team> loadAllTeams() {
		Map<Integer, Team> teams = new HashMap<Integer, Team>();
		String selectQuery = "SELECT * FROM " + TEAM_INFO_TABLE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				int teamId = cursor.getInt(0);
				String teamName = cursor.getString(1);
				int opponentId = cursor.getInt(2);
				int qbSos = cursor.getInt(3);
				int rbSos = cursor.getInt(4);
				int wrSos = cursor.getInt(5);
				int teSos = cursor.getInt(6);
				int defSos = cursor.getInt(7);
				int kSos = cursor.getInt(8);
				Team team = new Team(teamId, teamName, opponentId, qbSos,
						rbSos, wrSos, teSos, defSos, kSos);
				teams.put(teamId, team);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return teams;
	}
	
	/**
	 * A helper to load all players and respective stats from the database.
	 * It will handle the loading of the stats as well - the list of players 
	 * it returns will be complete. 
	 * 
	 * Note - this will need updating as more stats are added.
	 * 
	 * @return a list of player objects with basic info and stats
	 */
	public List<Player> loadAllPlayers() {
		List<Player> players = new ArrayList<Player>();
		String selectQuery = "SELECT * FROM " + PLAYER_BASIC_INFO_TABLE + " " +  
				"INNER JOIN " + PLAYER_STATS_TABLE + " " + 
				"ON " + PLAYER_BASIC_INFO_TABLE + ".ID=" + PLAYER_STATS_TABLE + ".ID";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				int playerId = cursor.getInt(0);
				String name = cursor.getString(1);
				String position = cursor.getString(2);
				int teamId = cursor.getInt(3);
				int age = cursor.getInt(4);
				int ecr = cursor.getInt(5);
				int projection = cursor.getInt(6);
				Player player = new Player(new PlayerRankings(playerId, ecr, projection), 
						name, position, teamId, playerId, age);
				players.add(player);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return players;
	}
	
	/**
	 * Gets all of the ids of players who are currently watch listed
	 * 
	 * @return a list of player ids who have been watch listed
	 */
	public List<Integer> getWatchListedPlayerIds(){
		List<Integer> watchListedPlayerIds = new ArrayList<Integer>();
		Set<String> keySet = getSP().getAll().keySet();
		for(String key : keySet){
			if(key.contains(WATCH_LIST_KEY)){
				String idString = key.split(WATCH_LIST_KEY)[0];
				watchListedPlayerIds.add(Integer.parseInt(idString));
			}
		}
		return watchListedPlayerIds;
	}
	
	/**
	 * A check from the preferences cache if a given player is watch listed
	 * 
	 * @param playerId the id to be checked
	 * @return true if so, false if not
	 */
	public boolean isPlayerWatchListed(int playerId){
		return getSP().contains(playerId + WATCH_LIST_KEY);
	}
	
	/**
	 * Adds the player to the watch list. If he was already there, no issues 
	 * will arise. 
	 * 
	 * @param playerId the id of the player to add to the watch list
	 */
	public void addPlayerToWatchList(int playerId){
		getSP().edit().putBoolean(playerId + WATCH_LIST_KEY, true).apply();
	}
	
	/**
	 * Removes the player from the watch list. If he was not in it, no issues 
	 * will arise.
	 * 
	 * @param playerId the id of the player to remove from the watch list
	 */
	public void removePlayerFromWatchList(int playerId){
		getSP().edit().remove(playerId + WATCH_LIST_KEY).apply();
	}
	
	/**
	 * Grab the applications shared preferences
	 * 
	 * @return the sharedpreferences, keyed by devin's sucking nature
	 */
	private SharedPreferences getSP(){
		return context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
	}
}
