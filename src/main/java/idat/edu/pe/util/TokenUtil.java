package idat.edu.pe.util;

import java.util.UUID;

public class TokenUtil {

	 public static String generateToken() {
	        return UUID.randomUUID().toString();
	    }
}
