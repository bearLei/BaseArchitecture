package com.ubtech.base_lib.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by Yxtao on 2017/9/20.
 */

public class Md5Utils {
    //加密成32位字符
    public static String encode(String text) {
        if (TextUtils.isEmpty(text)) return "";
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            return byteToHexString(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String byteToHexString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1)
                buffer.append('0');
            buffer.append(hex);
        }
        return buffer.toString();
    }

    public static String encode(String text, int num) {
        if (TextUtils.isEmpty(text)) return "";
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            String s = byteToHexString(digest.digest());
            if (TextUtils.isEmpty(s)) return "";
            if (s.length() <= num) return s;
            return s.substring(s.length()-num,s.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return byteToHexString(digest.digest());
    }

    public static boolean checkMd5(File file, String md5) {
        if (!TextUtils.isEmpty(md5)) {
            return md5.equals(getFileMD5(file));
        }
        return false;
    }
}
