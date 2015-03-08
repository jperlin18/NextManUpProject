package com.devingotaswitch.nextmanup.utilities.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devingotaswitch.nextmanup.specifics.Player;
import com.devingotaswitch.nextmanup.specifics.PlayerRankings;
import com.devingotaswitch.nextmanup.specifics.Team;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerDatabaseManager extends SQLiteOpenHelper {

	// Database Version (incrementing will trigger onUpgrade)
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "PlayerData";

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
}
