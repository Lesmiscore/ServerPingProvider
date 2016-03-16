package com.nao20010128nao.Wisecraft.misc.compat;

import java.nio.charset.Charset;

public class CompatCharsets {
	public static final Charset UTF_8, US_ASCII;
	static {
		Charset tmp;
		try {
			tmp = (Charset) Class.forName("java.nio.charset.StandardCharsets").getField("UTF_8").get(null);
		} catch (Throwable e) {
			tmp = Charset.forName("UTF-8");
		}
		UTF_8 = tmp;
		try {
			tmp = (Charset) Class.forName("java.nio.charset.StandardCharsets").getField("US_ASCII").get(null);
		} catch (Throwable e) {
			tmp = Charset.forName("US-ASCII");
		}
		US_ASCII = tmp;
	}
}
