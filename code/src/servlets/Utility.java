package servlets;

import java.math.BigInteger;
import java.security.*;
import java.util.*;
import java.text.SimpleDateFormat;

import nirmaanam.Volunteer;
import nirmaanam.IncompleteFieldException;

public class Utility{
	
	/*
		SECURITY
	*/
	
	public static final long conversionFactor = (long)3600*1000;
	
	public static String escape(String raw) throws Exception{
		if(raw == null)
			return "";
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
	
	
	/*
		TIME
	*/
	public static int StrToTimeStamp(String s) throws IncompleteFieldException{
		return StrToTimeStamp( s, "12:00");
		/*String[] comp= s.split("/");
		if(comp.length < 3 )
			throw(new IncompleteFieldException("Your date format is invalid"));
		int year = Integer.parseInt(comp[2]);
		int month = Integer.parseInt(comp[1]);
		int day = Integer.parseInt(comp[0]);
		Calendar cal = new GregorianCalendar(year,(month-1),day,12,0);
		return (int)(cal.getTimeInMillis()/conversionFactor);*/
	}
	
	public static int StrToTimeStamp(String d,String t)  throws IncompleteFieldException{
		String[] dComp= d.split("/");
		String[] tComp= t.split(":");
		if(dComp.length < 3  || tComp.length < 2 )
			throw(new IncompleteFieldException("Your date/time format is invalid"));
		int year = Integer.parseInt(dComp[2]);
		int month = Integer.parseInt(dComp[1]);
		int day = Integer.parseInt(dComp[0]);
		
		int hour = Integer.parseInt(tComp[0]);
		int min = Integer.parseInt(tComp[1]);
		Calendar cal = new GregorianCalendar(year,(month-1),day,hour,min);
		return (int)(cal.getTimeInMillis()/conversionFactor);
	}
	
	public static String TimeStampToDate(int tStamp){
		long millis = (((long)tStamp) * conversionFactor);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		return sdf.format(new Date(millis));
	}
	
	public static String TimeStampToStr(int tStamp){
		long millis = (((long)tStamp) * conversionFactor);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
		return sdf.format(new Date(millis));
	}
	
	public static int TimeNow(){
		GregorianCalendar cal = new GregorianCalendar();
		return (int)(cal.getTimeInMillis()/conversionFactor);
	}
	
	
	/*
		MISC
	*/
	public static String getProfileImage(Volunteer v){
		String fullID = v.getBitsID();
		String subID = fullID.substring(0, fullID.length() -1);
		return "https://10.10.10.120/css/StudentImg/" + subID + ".jpg";
	}
}