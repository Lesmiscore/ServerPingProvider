package com.nao20010128nao.Wisecraft.misc;

import com.nao20010128nao.MCPing.ServerPingResult;

public class SprPair implements ServerPingResult {
	ServerPingResult a, b;

	public void setA(ServerPingResult a) {
		this.a = a;
	}

	public ServerPingResult getA() {
		return a;
	}

	public void setB(ServerPingResult b) {
		this.b = b;
	}

	public ServerPingResult getB() {
		return b;
	}
}
