package net.sf.libgrowl.internal;


import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.libgrowl.internal.Encryption;

public class TestEncryption {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testMD5() {
		// Verified the output with
		// http://jlpoutre.home.xs4all.nl/BoT/Javascript/Utils/md5_hashing.html
		String testMd5 = "123456789";
		
		String results = Encryption.md5(testMd5.getBytes());
		assertEquals("25f9e794323b453885f5181f1b624d0b".toUpperCase(), results);
	}
	
	@Test
	public void testGenerateKeyHash() {
		String pass = "987654321";
		String keyhash = "";
		
		String results = Encryption.generateKeyHash(pass);
		assertNotNull(results);
		
		String[] resutlsArray = results.split("\\.");
		
		byte[] salt = unBuildHexString(resutlsArray[1]);
		
	    ByteBuffer bb = ByteBuffer.allocate( pass.getBytes().length + salt.length);
	    bb.put(pass.getBytes());
	    bb.put(salt);
	  
	    try {
		  MessageDigest md5 = MessageDigest.getInstance("MD5");
	      md5.reset();
		  md5.update(bb.array());
	  
		  final byte[] result = md5.digest();
		  keyhash = Encryption.md5(result);
		} catch (NoSuchAlgorithmException e) {
			fail();
		}
			
		assertEquals(keyhash, resutlsArray[0]);
	}
	
	private byte[] unBuildHexString(String hexString) {
	    int length = hexString.length() / 2;
	    byte[] raw = new byte[length];
	    for (int i = 0; i < length; i++) {
	      int high = Character.digit(hexString.charAt(i * 2), 16);
	      int low = Character.digit(hexString.charAt(i * 2 + 1), 16);
	      int value = (high << 4) | low;
	      if (value > 127)
	        value -= 256;
	      raw[i] = (byte) value;
	    }
	    return raw;
	}	    
}
