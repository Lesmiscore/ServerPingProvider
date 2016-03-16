package com.nao20010128nao.Wisecraft.provider;
import com.nao20010128nao.Wisecraft.ServerListActivity;
import java.util.ArrayList;
import java.util.List;

public class MultiServerPingProvider implements ServerPingProvider {
	List<NormalServerPingProvider> objects=new ArrayList<>();
	int count=0;
	public MultiServerPingProvider(int parallels) {
		for (int i=0;i < parallels;i++) {
			objects.add(new NormalServerPingProvider());
		}
	}
	@Override
	public void putInQueue(ServerListActivity.Server server, ServerPingProvider.PingHandler handler) {
		// TODO: Implement this method
		objects.get(count).putInQueue(server, handler);
		count++;
		count = count % objects.size();
	}

	@Override
	public int getQueueRemain() {
		// TODO: Implement this method
		int i=0;
		for (ServerPingProvider spp:objects) {
			i += spp.getQueueRemain();
		}
		return i;
	}
}