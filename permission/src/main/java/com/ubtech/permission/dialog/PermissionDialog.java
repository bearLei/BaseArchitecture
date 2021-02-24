package com.ubtech.permission.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ubtech.base_lib.imp.IActionCallBack2;
import com.ubtech.permission.PermissionRequestListener;
import com.ubtech.permission.PermissionUtils;
import com.ubtech.permission.R;
import com.ubtech.ui_lib.dialog.DialogBuilder;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Setting;

import java.util.List;

/**
 * Created by Ian on 18-8-8.
 */

public class PermissionDialog {

    private static final String TAG = "PermissionDialog";

    /**
     * Display setting dialog.
     */
    public static void showSettingDialog(final Context context, final List<String> permissions,final PermissionRequestListener listener, final DialogStateListener dialogListener) {
        List<String> permissionNames = PermissionUtils.transformText(context, permissions);
        String message = context.getString(R.string.permission_prompt_open_permission_by_settings, TextUtils.join("\n", permissionNames));
        new DialogBuilder()
                .content(message)
                .rightBtnDesc(context.getString(R.string.permission_text_permission_denied_forever_positive_btn))
                .leftBtnDesc(context.getString(R.string.permission_dialog_negative_btn_text))
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
                        goSettingSetPermission(context,listener);
                        if (dialogListener != null) {
                            dialogListener.onDialogDismissed();
                        }
                    }
                }).build(context).show();
        if (dialogListener != null) {
            dialogListener.onDialogShow();
        }
    }

    /**
     * Set permissions.
     */
    private static void goSettingSetPermission(final Context context,final PermissionRequestListener listener) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        //Toast.makeText(context, "message_setting_comeback", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "message_setting_comeback");
                        listener.onDenied();
                    }
                })
                .start();
    }

    public static void showRequestNotificationDialog(final Context context, final List<String> permissions) {
        List<String> permissionNames = PermissionUtils.transformText(context, permissions);
        String message = context.getString(R.string.permission_message_permission_denied, TextUtils.join("\n", permissionNames));
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
