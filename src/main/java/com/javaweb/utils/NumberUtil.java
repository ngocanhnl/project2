package com.javaweb.utils;

public class NumberUtil {
	public static boolean isNumber(String value) {
		try {
			Long number = Long.parseLong(value);
			return true;
		} catch (NumberFormatException ex) {
			// TODO: handle exception
			return false;
		}
	}
}
