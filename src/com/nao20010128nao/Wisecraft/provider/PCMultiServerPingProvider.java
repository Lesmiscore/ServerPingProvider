package com.nao20010128nao.Wisecraft.provider;

import java.util.ArrayList;
import java.util.List;

import com.nao20010128nao.Wisecraft.Server;

public class PCMultiServerPingProvider implements ServerPingProvider {
	List<PCServerPingProvider> objects = new ArrayList<>();
	int count = 0;

	public PCMultiServerPingProvider(int parallels) {
		for (int i = 0; i < parallels; i++) {
			objects.add(new PCServerPingProvider());
		}
	}

	@Override
	public void putInQueue(Server server, ServerPingProvider.PingHandler handler) {
		// TODO: Implement this method
		objects.get(count).putInQueue(server, handler);
		count++;
		count = count % objects.size();
	}

	@Override
	public int getQueueRemain() {
		// TODO: Implement this method
		int i = 0;
		for (ServerPingProvider spp : objects) {
			i += spp.getQueueRemain();
		}
		return i;
	}
}
