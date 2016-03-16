package com.nao20010128nao.Wisecraft;

public class Utils {

	public static <T> T requireNonNull(T obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		return obj;
	}
}