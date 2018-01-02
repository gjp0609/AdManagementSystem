package me.rainbow.utils;

import java.security.MessageDigest;

/**
 * @author guojinpeng
 * @date 18.1.2 16:05
 */
public class SecurityUtil {

    /**
     * MD5加密
     */
    public static String encryptMD5(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            StringBuilder result = new StringBuilder();
            for (byte value : s) {
                result.append(Integer.toHexString((0x000000FF & value) | 0xFFFFFF00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
