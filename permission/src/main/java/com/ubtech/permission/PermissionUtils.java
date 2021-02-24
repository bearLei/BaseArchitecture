package com.ubtech.permission;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yanzhenjie.permission.Permission.ACCESS_COARSE_LOCATION;
import static com.yanzhenjie.permission.Permission.ACCESS_FINE_LOCATION;
import static com.yanzhenjie.permission.Permission.BODY_SENSORS;
import static com.yanzhenjie.permission.Permission.CALL_PHONE;
import static com.yanzhenjie.permission.Permission.CAMERA;
import static com.yanzhenjie.permission.Permission.GET_ACCOUNTS;
import static com.yanzhenjie.permission.Permission.PROCESS_OUTGOING_CALLS;
import static com.yanzhenjie.permission.Permission.READ_CALENDAR;
import static com.yanzhenjie.permission.Permission.READ_CALL_LOG;
import static com.yanzhenjie.permission.Permission.READ_CONTACTS;
import static com.yanzhenjie.permission.Permission.READ_EXTERNAL_STORAGE;
import static com.yanzhenjie.permission.Permission.READ_PHONE_STATE;
import static com.yanzhenjie.permission.Permission.READ_SMS;
import static com.yanzhenjie.permission.Permission.RECEIVE_MMS;
import static com.yanzhenjie.permission.Permission.RECEIVE_SMS;
import static com.yanzhenjie.permission.Permission.RECEIVE_WAP_PUSH;
import static com.yanzhenjie.permission.Permission.RECORD_AUDIO;
import static com.yanzhenjie.permission.Permission.SEND_SMS;
import static com.yanzhenjie.permission.Permission.USE_SIP;
import static com.yanzhenjie.permission.Permission.WRITE_CALENDAR;
import static com.yanzhenjie.permission.Permission.WRITE_CALL_LOG;
import static com.yanzhenjie.permission.Permission.WRITE_CONTACTS;
import static com.yanzhenjie.permission.Permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Ian on 18-8-13.
 */

public class PermissionUtils {

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, String... permissions) {
        return transformText(context, Arrays.asList(permissions));
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        return transformText(context, permissionList);
    }


    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, List<String> permissions) {
        List<String> textList = new ArrayList<>();
        for (String permission : permissions) {
            switch (permission) {
                case READ_CALENDAR:
                case WRITE_CALENDAR: {
                    String message = context.getString(R.string.permission_name_calendar);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }

                case CAMERA: {
                    String message = context.getString(R.string.permission_camera);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case READ_CONTACTS:
                case WRITE_CONTACTS:
                case GET_ACCOUNTS: {
                    String message = context.getString(R.string.permission_name_contacts);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case ACCESS_FINE_LOCATION:
                case ACCESS_COARSE_LOCATION: {
                    String message = context.getString(R.string.permission_location);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case RECORD_AUDIO: {
                    String message = context.getString(R.string.permission_record);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case READ_PHONE_STATE:
                case CALL_PHONE:
                case READ_CALL_LOG:
                case WRITE_CALL_LOG:
                case USE_SIP:
                case PROCESS_OUTGOING_CALLS: {
                    String message = context.getString(R.string.permission_name_phone);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case BODY_SENSORS: {
                    String message = context.getString(R.string.permission_name_sensors);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case SEND_SMS:
                case RECEIVE_SMS:
                case READ_SMS:
                case RECEIVE_WAP_PUSH:
                case RECEIVE_MMS: {
                    String message = context.getString(R.string.permission_name_sms);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case READ_EXTERNAL_STORAGE:
                case WRITE_EXTERNAL_STORAGE: {
                    String message = context.getString(R.string.permission_storage);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
            }
        }
        return textList;
    }


}
