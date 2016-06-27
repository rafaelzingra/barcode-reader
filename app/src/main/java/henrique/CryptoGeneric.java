package henrique;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class CryptoGeneric {
	private MessageDigest messageDigest;

	protected void useAlgorithm(String algorithm) throws NoSuchAlgorithmException {
		if (messageDigest == null || messageDigest.getAlgorithm() != algorithm) {
			messageDigest = MessageDigest.getInstance(algorithm);
		}

	}

	protected String encryptByAlgorithm(String algorithm, String value) throws NoSuchAlgorithmException {
		if (value == null) {
			throw new IllegalArgumentException("The value is null.");
		}

		useAlgorithm(algorithm);
		byte hash[] = messageDigest.digest(value.getBytes());
		// ----------------------------------------------------------------------

		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			hexString.append(Integer.toHexString(0xFF & hash[i]));
		}
		// ----------------------------------------------------------------------
		return hexString.toString();
	}

}
