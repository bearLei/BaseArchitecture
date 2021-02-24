package com.ubtech.permission;

/**
 * Created by Ian on 18-8-10.
 */

public interface PermissionRequestListener {

    void onGranted();

    void onDenied();

}
