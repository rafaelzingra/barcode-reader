package henrique;

import java.security.NoSuchAlgorithmException;

public class CryptoSHA512 extends CryptoGeneric implements Crypto {
	public String encrypt(String value) throws NoSuchAlgorithmException {
		return encryptByAlgorithm("SHA-512", value);
	}

}
