package com.project.xiaodong.mytimeapp.frame.base.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.project.xiaodong.mytimeapp.R;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.project.xiaodong.mytimeapp.frame.constants.RC.RC_FOR_PERMISSIONS;
import static com.project.xiaodong.mytimeapp.frame.constants.RC.RC_SETTINGS_SCREEN;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class BasePermissionsActivity extends FragmentActivity implements EasyPermissions.PermissionCallbacks {


    protected String[] iNeedPermissions() {
        return null;
    }

    protected String iNeededReason() {
        return "";
    }

    /**
     * 用户同意授权后做什么
     */
    protected void onUserPermitDonig() {

    }

    /**
     * 用户拒绝授权后做什么
     */
    protected void onUserRejectDoing() {

    }


    @AfterPermissionGranted(RC_FOR_PERMISSIONS)
    public void requestPermissions() {
        if (EasyPermissions.hasPermissions(this, iNeedPermissions())) {
            onUserPermitDonig();
        } else {
            EasyPermissions.requestPermissions(this, iNeededReason(), RC_FOR_PERMISSIONS, iNeedPermissions());
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && null != iNeedPermissions() && iNeedPermissions().length > 0) {
            requestPermissions();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SETTINGS_SCREEN) {
            // Do something after user returned from app settings screen, like showing a Toast.
            // Toast.makeText(this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT).show();
            if (!EasyPermissions.hasPermissions(this, iNeedPermissions())) {
                onUserRejectDoing();
            } else {
                onUserPermitDonig();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        onUserPermitDonig();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, getString(R.string.rationale_ask_again))
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onUserRejectDoing();
                        }
                    } /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }
}
