package com.ubtech.permission;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ubtech.base_lib.imp.IActionCallBack2;
import com.ubtech.permission.dialog.DialogStateListener;
import com.ubtech.permission.dialog.PermissionDialog;
import com.ubtech.ui_lib.dialog.DialogBuilder;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

/**
 * Created by Ian on 18-8-1.
 */

public class PermissionUtil {

    private static final String TAG = "PermissionUtil";

    /**
     * Judgment already has the target permission.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        return AndPermission.hasPermissions(context, permissions);
    }

    public static void requestStoragePermission(final Context context, final PermissionRequestListener listener) {

        if (AndPermission.hasPermissions(context, Permission.Group.STORAGE)) {
            if (listener != null) {
                listener.onGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (listener != null) {
                            listener.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, Permission.Group.STORAGE)) {
                            PermissionDialog.showSettingDialog(context, data,listener, null);
                        } else {
                            PermissionDialog.showRequestNotificationDialog(context, data);
                        }

                        if (listener != null) {
                            listener.onDenied();
                        }

                    }
                })
                .start();
    }

    public static void requestCameraPermission(final Activity context, final PermissionRequestListener listener) {

        if (AndPermission.hasPermissions(context, Permission.Group.CAMERA)) {
            if (listener != null) {
                listener.onGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(Permission.Group.CAMERA)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //Toast.makeText(context, "user agree storage permission", Toast.LENGTH_SHORT).show();
                        if (listener != null) {
                            listener.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, Permission.Group.CAMERA)) {
                            PermissionDialog.showSettingDialog(context, data,listener, null);
                        } else {
                            PermissionDialog.showRequestNotificationDialog(context, data);
                        }

                        if (listener != null) {
                            listener.onDenied();
                        }
                    }
                })
                .start();
    }

    public static void requestRecordPermission(final Context context, final PermissionRequestListener listener) {

        if (AndPermission.hasPermissions(context, Permission.Group.MICROPHONE)) {
            if (listener != null) {
                listener.onGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(Permission.Group.MICROPHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (listener != null) {
                            listener.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, Permission.Group.MICROPHONE)) {
                            PermissionDialog.showSettingDialog(context, data,listener, null);
                        } else {
                            //PermissionDialog.showRequestNotificationDialog(context, data);
                        }
                        if (listener != null) {
                            listener.onDenied();
                        }
                    }
                })
                .start();
    }


    /**
     * 申请录音权限，但不会根据用户拒绝情况弹窗提示
     *
     * @param context
     * @param listener
     */
    public static void requestRecordPermissionIngnoreResultShow(final Context context, final PermissionRequestListener listener) {

        if (AndPermission.hasPermissions(context, Permission.Group.MICROPHONE)) {
            if (listener != null) {
                listener.onGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(Permission.Group.MICROPHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (listener != null) {
                            listener.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (listener != null) {
                            listener.onDenied();
                        }
                    }
                })
                .start();
    }

    public static void requestLocationPermission(final Activity context, final PermissionRequestListener listener, final DialogStateListener dialogListener) {

        if (AndPermission.hasPermissions(context, Permission.Group.LOCATION)) {
            if (listener != null) {
                listener.onGranted();
            }
            return;
        }

        if (AndPermission.hasAlwaysDeniedPermission(context, Permission.Group.LOCATION)) {
            doRequestLocationPermission(context, listener, dialogListener);
            return;
        }


        //提示用户授予权限弹框
        String message = context.getString(R.string.permission_message_permission_rationale,
                PermissionUtils.transformText(context, Permission.Group.LOCATION));

        new DialogBuilder()
                .content(message)
                .rightBtnDesc(context.getString(R.string.permission_text_permission_rationale_positive_btn))
                .leftBtnDesc(context.getString(R.string.permission_dialog_negative_btn_text))
                .showLeftBtn(true)
                .showRightBtn(true)
                .aotoDismiss(true)
                .actionCallBack02(new IActionCallBack2() {
                    @Override
                    public void onAction1() {
                        if (dialogListener != null) {
                            dialogListener.onDialogDismissed();
                        }
                        if (listener != null) {
                            listener.onDenied();
                        }
                    }

                    @Override
                    public void onAction2() {
                        doRequestLocationPermission(context, listener, dialogListener);
                        if (dialogListener != null) {
                            dialogListener.onDialogDismissed();
                        }
                    }
                }).build(context).show();
        if (dialogListener != null) {
            dialogListener.onDialogShow();
        }
    }


    public static void requestCallPhonePermission(final Activity context, final PermissionRequestListener listener) {

        if (AndPermission.hasPermissions(context, Permission.CALL_PHONE)) {
            if (listener != null) {
                listener.onGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(Permission.CALL_PHONE)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (listener != null) {
                            listener.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, Permission.CALL_PHONE)) {
                            PermissionDialog.showSettingDialog(context, data,listener, null);
                        } else {
                            PermissionDialog.showRequestNotificationDialog(context, data);
                        }

                        if (listener != null) {
                            listener.onDenied();
                        }

                    }
                })
                .start();
    }


    private static void doRequestLocationPermission(final Activity context, final PermissionRequestListener listener, final DialogStateListener dialogListener) {
        Log.d(TAG, "request location permission");

        AndPermission.with(context)
                .runtime()
                .permission(Permission.Group.LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //Toast.makeText(context, "user agree storage permission", Toast.LENGTH_SHORT).show();
                        if (listener != null) {
                            listener.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Log.d(TAG, "on location permission denied");
                        if (AndPermission.hasAlwaysDeniedPermission(context, Permission.Group.LOCATION)) {
                            PermissionDialog.showSettingDialog(context, data,listener, dialogListener);
                        } else {
                            PermissionDialog.showRequestNotificationDialog(context, data);
                            if (listener != null) {
                                listener.onDenied();
                            }
                        }
                    }
                })
                .start();
    }

}
