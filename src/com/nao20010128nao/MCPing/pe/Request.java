package com.nao20010128nao.MCPing.pe;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.nao20010128nao.MCPing.Utils;

public class Request {
	private ByteArrayOutputStream byteStream;
	private DataOutputStream dataStream;

	static byte[] MAGIC = { (byte) 0xFE, (byte) 0xFD };
	public byte type;
	public int sessionID;
	public byte[] payload;

	public Request() {
		int size = 1460;
		byteStream = new ByteArrayOutputStream(size);
		dataStream = new DataOutputStream(byteStream);
	}

	public Request(byte type) {
		this.type = type;
		// TODO move static type variables to Request
	}

	// convert the data in this request to a byte array to send to the server
	public byte[] toBytes() {
		byteStream.reset();

		try {
			dataStream.write(MAGIC);
			dataStream.write(type);
			dataStream.writeInt(sessionID);
			dataStream.write(payloadBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteStream.toByteArray();
	}

	private byte[] payloadBytes() {
		if (type == PEQuery.HANDSHAKE) {
			return new byte[] {}; // return empty byte array
		} else // (type == MCQuery.STAT)
		{
			return payload;
		}
	}

	public void setPayload(int load) {
		this.payload = Utils.intToBytes(load);
	}
}
