package servlets;

import java.math.BigInteger;
import java.security.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Utility{
	
	public static String escape(String raw) throws Exception{
		raw = raw.replace("\"", "\\\"");
		raw = raw.replace("\'", "\\\'");
		return raw;
	}
	
	public static String MD5Hash(String raw) throws Exception{
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(raw.getBytes());
			return new BigInteger(1 , md5.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException NSAE){
			throw (Exception)NSAE;
		}
	}
	public static int StrToTimeStamp(String s){
		String[] comp= s.split("/");
		int year = Integer.parseInt(comp[2]);
		int month = Integer.parseInt(comp[1]);
		int day = Integer.parseInt(comp[0]);
		Calendar cal = new GregorianCalendar(year,(month-1),day,12,0);
		return (int)(cal.getTimeInMillis()/((long)3600*1000));
	}
	
	public static String TimeStampToStr(int tStamp){
		long millis = (((long)tStamp) * ((long)3600*1000));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		return sdf.format(new Date(millis));
	}
}