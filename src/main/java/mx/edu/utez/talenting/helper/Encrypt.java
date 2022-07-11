package mx.edu.utez.talenting.helper;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Encrypt {
	
	
	public static String encrypt(String string) {
		return string = Hashing.sha256()
				.hashString(string, StandardCharsets.UTF_8)
				.toString();
	}

}
