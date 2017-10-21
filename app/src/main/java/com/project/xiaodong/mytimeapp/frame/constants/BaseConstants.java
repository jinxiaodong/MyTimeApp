package com.project.xiaodong.mytimeapp.frame.constants;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;

/**
 * Created by xiaodong.jin on 2017/10/21.
 */
public class BaseConstants {

    public static boolean isTest = false;
    public static final String PACKAGE_NAME = BaseApplication.getInstance().getPackageName();
    public static int APP_VERSION_CODE = 1;
    public static String APP_VERSION_NAME = "1.0";

    public BaseConstants() {
    }
}
