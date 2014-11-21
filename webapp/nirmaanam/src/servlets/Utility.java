package servlets;

import java.math.BigInteger;
import java.security.*;

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
}