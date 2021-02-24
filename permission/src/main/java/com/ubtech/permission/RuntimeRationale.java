package com.ubtech.permission;

import android.content.Context;
import android.text.TextUtils;

import com.ubtech.base_lib.imp.IActionCallBack2;
import com.ubtech.ui_lib.dialog.DialogBuilder;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

/**
 * Created by Ian on 18-8-1.
 */

public final class RuntimeRationale implements Rationale<List<String>> {

    @Override
    public void showRationale(Context context, List<String> permissions, final RequestExecutor executor) {
        List<String> permissionNames = PermissionUtils.transformText(context, permissions);
        String message = context.getString(R.string.permission_message_permission_rationale, TextUtils.join("\n", permissionNames));

//        PermissionBaseDialog.create(context)
//                .setContent(message)
//                .setRightButtonText(context.getString(R.string.permission_text_permission_rationale_positive_btn))
//                .setLeftButtonText(context.getString(R.string.permission_dialog_negative_btn_text))
//                .setOnClickListener(new DialogOnClickListener() {
//                    @Override
//                    public void onClickLeftButton() {
//                        executor.cancel();
//                        PermissionBaseDialog.dismissDialog();
//                    }
//
//                    @Override
//                    public void onClickRightButton() {
//                        executor.execute();
//                        PermissionBaseDialog.dismissDialog();
//                    }
//                })
//                .showDialog();

        new DialogBuilder()
                .content(message)
                .rightBtnDesc(context.getString(R.string.permission_text_permission_rationale_positive_btn))
                .leftBtnDesc(context.getString(R.string.permission_dialog_negative_btn_text))
                .aotoDismiss(true)
                .actionCallBack02(new IActionCallBack2() {
                    @Override
                    public void onAction1() {
                        executor.cancel();
                    }

                    @Override
                    public void onAction2() {
                        executor.execute();
                    }
                }).build(context).show();
    }
}
