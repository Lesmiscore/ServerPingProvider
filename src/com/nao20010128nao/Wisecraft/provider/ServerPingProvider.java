package com.nao20010128nao.Wisecraft.provider;

import com.nao20010128nao.Wisecraft.Server;
import com.nao20010128nao.Wisecraft.ServerStatus;

public interface ServerPingProvider {
	public void putInQueue(Server server, PingHandler handler);

	public int getQueueRemain();

	public static interface PingHandler {
		void onPingArrives(ServerStatus stat);

		void onPingFailed(Server server);
	}
}
