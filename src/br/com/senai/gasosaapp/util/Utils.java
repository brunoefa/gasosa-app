package br.com.senai.gasosaapp.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
	
	public static Date stringToDate(String stringDate) {
		Date date = null;
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = format.parse(stringDate);
		} catch (Exception e) {
			
		}
		return date;
	}
	
	public static String dateToString(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
		return df.format(date);
	}
	
	public static String doubleToString(Double value) {
		DecimalFormat df = new DecimalFormat("###,###,##0.00");
		return df.format(value);
	}
}
