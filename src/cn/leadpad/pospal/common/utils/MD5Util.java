/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Type: cn.leadpad.pospal.common.utils.MD5Util
 * @ClassName: MD5Util
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:32:09
 * @Description: <br/>
 */
public class MD5Util {
	private static Logger log = Logger.getLogger(MD5Util.class);
	public static String encryptToMd5String(String content,String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return encryptToMd5String(StringUtils.trimToEmpty(salt)+StringUtils.trimToEmpty(content));
	}
	public static String encryptToMd5String(String content) {
		String md5String = null;
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("md5");
			messageDigest.update(content.getBytes("UTF-8"));
			md5String = parseByte2HexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}

		return md5String;
	}
	
	private static String parseByte2HexString(byte buf[]) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			stringBuffer.append(hex.toUpperCase());
		}
		return stringBuffer.toString();
	}
}
