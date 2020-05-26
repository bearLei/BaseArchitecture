package com.ubtech.base_lib.utils;


import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by albert.wu on 2019/8/1.
 */

public class FileUtil {

    public static void copy(@NonNull File source, @NonNull File target) {
        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            targetChannel = new FileOutputStream(target).getChannel();
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (sourceChannel != null) {
                    sourceChannel.close();
                }
                if (targetChannel != null) {
                    targetChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static boolean isImageFile(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return false;
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return false;
        }
        String ext = fileName.substring(index);
        if (TextUtils.isEmpty(ext)) {
            return false;
        }
        return ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".png")
                || ext.equalsIgnoreCase(".bmp") || ext.equalsIgnoreCase(".gif")
                || ext.equalsIgnoreCase(".jpeg");
    }
}
