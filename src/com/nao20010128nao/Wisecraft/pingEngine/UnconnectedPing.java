package com.nao20010128nao.Wisecraft.pingEngine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.nao20010128nao.MCPing.PingHost;
import com.nao20010128nao.MCPing.ServerPingResult;

public class UnconnectedPing {
	public static final byte UCP_PID = 0x01;
	public static final int MAGIC_1ST = 0x00ffff00;
	public static final int MAGIC_2ND = 0xfefefefe;
	public static final int MAGIC_3RD = 0xfdfdfdfd;
	public static final int MAGIC_4TH = 0x12345678;

	public static UnconnectedPingResult doPing(String ip, int port) throws IOException {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			ds.setSoTimeout(2500);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(25);
			DataOutputStream dos = new DataOutputStream(baos);
			dos.write(UCP_PID);
			dos.writeLong(System.currentTimeMillis());
			dos.writeInt(MAGIC_1ST);
			dos.writeInt(MAGIC_2ND);
			dos.writeInt(MAGIC_3RD);
			dos.writeInt(MAGIC_4TH);
			dos.flush();

			long t = System.currentTimeMillis();
			DatagramPacket dp = new DatagramPacket(baos.toByteArray(), baos.size(), InetAddress.getByName(ip), port);
			ds.send(dp);

			DatagramPacket recDp = new DatagramPacket(new byte[1000], 1000);
			ds.receive(recDp);
			byte[] recvBuf = new byte[recDp.getLength()];
			System.arraycopy(recDp.getData(), 0, recvBuf, 0, recvBuf.length);
			t = System.currentTimeMillis() - t;

			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(recvBuf));
			if (dis.readByte() != 0x1c) {
				throw new IOException("Server replied with invalid data");
			}
			dis.readLong();// Ping ID
			dis.readLong();// Server ID
			dis.readLong();// MAGIC
			dis.readLong();// MAGIC
			String s = dis.readUTF();
			return new UnconnectedPingResult(s, t);
		} catch (IOException e) {
			throw e;
		} finally {
			if (ds != null)
				ds.close();
		}
	}

	public static class UnconnectedPingResult implements ServerPingResult, PingHost {
		String[] serverInfos;
		String raw;
		long latestPing;

		public UnconnectedPingResult(String s, long elapsed) {
			serverInfos = (raw = s).split("\\;");
			latestPing = elapsed;
		}

		public String getServerName() {
			return serverInfos[1];
		}

		public String getRaw() {
			return raw;
		}

		@Override
		public long getLatestPingElapsed() {
			// TODO: Implement this method
			return latestPing;
		}
	}
}