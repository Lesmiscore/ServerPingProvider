package com.nao20010128nao.Wisecraft.provider;
import com.nao20010128nao.Wisecraft.ServerListActivity;
import java.util.ArrayList;
import java.util.List;

public class ExperimentalServerPingProvider implements ServerPingProvider {
	List<NormalServerPingProvider> objects=new ArrayList<>();
	public ExperimentalServerPingProvider(int parallels) {
		for (int i=0;i < parallels;i++) {
			objects.add(new NormalServerPingProvider());
		}
	}
	@Override
	public void putInQueue(ServerListActivity.Server server, ServerPingProvider.PingHandler handler) {
		// TODO: Implement this method
		int delta=Integer.MAX_VALUE;
		ServerPingProvider obj=null;
		for (ServerPingProvider spp:objects) {
			if (delta > spp.getQueueRemain()) {
				delta = spp.getQueueRemain();
				obj = spp;
			}
		}
		obj.putInQueue(server, handler);
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
