package org.tarena.note.util;

import java.security.MessageDigest;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

public class NoteUtil {

	public static String createId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static String createToken(){
		return createId().replaceAll("-", "");//去掉"-"
	}
	public static String md5(String msg){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest();
			return Base64.encodeBase64String(output);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
