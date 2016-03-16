package com.nao20010128nao.Wisecraft.provider;
import com.nao20010128nao.Wisecraft.ServerListActivity;

public interface ServerPingProvider {
	public void putInQueue(ServerListActivity.Server server, PingHandler handler);
	public int getQueueRemain();
	public static interface PingHandler {
		void onPingArrives(ServerListActivity.ServerStatus stat);
		void onPingFailed(ServerListActivity.Server server);
	}
}
