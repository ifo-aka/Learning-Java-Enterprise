package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
  public static String hashPassword(String password) {
	  try {
	  MessageDigest mg =  MessageDigest.getInstance("SHA-256");//now its secure medium hash protected
	  byte[] hash = mg.digest(password.getBytes());
	  StringBuilder hashString = new StringBuilder();
	  
	  for(byte b: hash) {
		  String hex= Integer.toHexString(0xff & b);
		  if(hex.length() == 1)  hashString.append('0');
		  hashString.append(hex);
			  
		  		
	  }
	  return  hashString.toString();
	  
  }catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error hashing password", e);
    }
  
  }
  }
