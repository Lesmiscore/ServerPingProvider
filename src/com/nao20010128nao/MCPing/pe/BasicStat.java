package com.nao20010128nao.MCPing.pe;

import com.nao20010128nao.MCPing.ServerPingResult;
import com.nao20010128nao.MCPing.Utils;

public class BasicStat implements ServerPingResult {
	static byte NULL = 00;
	static byte SPACE = 20;

	// for simple stat
	private String motd, gameMode, mapName;
	private int onlinePlayers, maxPlayers;
	private short port;
	private String hostname;

	public BasicStat(byte[] data) {
		data = Utils.trim(data);
		byte[][] temp = Utils.split(data);

		motd = new String(Utils.subarray(temp[0], 1, temp[0].length - 1));
		gameMode = new String(temp[1]);
		mapName = new String(temp[2]);
		onlinePlayers = Integer.parseInt(new String(temp[3]));
		maxPlayers = Integer.parseInt(new String(temp[4]));
		port = Utils.bytesToShort(temp[5]);
		hostname = new String(Utils.subarray(temp[5], 2, temp[5].length - 1));

	}

	@Override
	public String toString() {
		String delimiter = ", ";
		StringBuilder str = new StringBuilder();
		str.append(motd);
		str.append(delimiter);
		str.append(gameMode);
		str.append(delimiter);
		str.append(mapName);
		str.append(delimiter);
		str.append(onlinePlayers);
		str.append(delimiter);
		str.append(maxPlayers);
		str.append(delimiter);
		str.append(port);
		str.append(delimiter);
		str.append(hostname);
		return str.toString();
	}

	/**
	 * @return the MOTD, as displayed in the client
	 */
	public String getMOTD() {
		return motd;
	}

	public String getGameMode() {
		return gameMode;
	}

	public String getMapName() {
		return mapName;
	}

	public int getOnlinePlayers() {
		return onlinePlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}
}
