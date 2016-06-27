package henrique;

import java.security.NoSuchAlgorithmException;

public interface Crypto {
	String encrypt(String value) throws NoSuchAlgorithmException;
}
