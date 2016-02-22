package CTFEncryptDecrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CTFEncryptDecrypt {

	SecretKeySpec theKey;
	public String ErrorMessage = "";
	
	public CTFEncryptDecrypt(String sKey)
	{
		try
		{
			theKey = 	generateKey( sKey) ;
		}
		catch (NoSuchAlgorithmException ex)
		{
			ErrorMessage = ex.getMessage();
		}
	}
	
	
    private   SecretKeySpec generateKey(String theKey) throws NoSuchAlgorithmException {
    	/* KeyGenerator generator;
		generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom());
		key = generator.generateKey();
    	 */
    	try
    	{
    		MessageDigest md5 = MessageDigest.getInstance("MD5");
    			md5.update(theKey.getBytes());
    	   
    			return (new SecretKeySpec(md5.digest(), "AES"));
    	}
    	catch (Exception ex)
    	{
    		ErrorMessage = ex.getMessage();
    		return null;
    	}
    }
 
    public  String encrypt(String message) throws IllegalBlockSizeException,
	    BadPaddingException, NoSuchAlgorithmException,
	    NoSuchPaddingException, InvalidKeyException,
	    UnsupportedEncodingException
	{
    	String base64 = "";
    	try
    	{
    		// Get a cipher object.
    		Cipher cipher = Cipher.getInstance("AES");
    		cipher.init(Cipher.ENCRYPT_MODE, theKey);
 
    		// Gets the raw bytes to encrypt, UTF8 is needed for
    		// having a standard character set
    		byte[] stringBytes = message.getBytes("UTF8");
 
    		// encrypt using the cypher
    		byte[] raw = cipher.doFinal(stringBytes);
 
    		// converts to base64 for easier display.
    		Base64 encoder = new Base64();
    		base64 = encoder.encodeToString(raw);
    	}
    	catch (Exception ex)
    	{
			ErrorMessage = ex.getMessage();    		
    	}
    	
 
    	return base64;
    }
 
    public  String decrypt(String encrypted  ) throws InvalidKeyException,
	    NoSuchAlgorithmException, NoSuchPaddingException,
	    IllegalBlockSizeException, BadPaddingException, IOException 
	{
    	String clear = "";
    	try
    	{
    		// Get a cipher object.
    		Cipher cipher = Cipher.getInstance("AES");
    		cipher.init(Cipher.DECRYPT_MODE, theKey);
 
    		//decode the BASE64 coded message
    		Base64 decoder = new Base64();
    		byte[] raw = decoder.decode(encrypted);
 
    		//decode the message
    		byte[] stringBytes = cipher.doFinal(raw);
 
    		//converts the decoded message to a String
    		clear = new String(stringBytes, "UTF8");
    	}
    	catch (Exception ex)
    	{
    		ErrorMessage = ex.getMessage();    		
    	}
			return clear;
    }
}

