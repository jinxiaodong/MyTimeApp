package com.project.xiaodong.mytimeapp.frame.utils;

import android.text.TextUtils;

/**
 * Created by xiaodong.jin on 2017/10/21.
 */

public class ImageUtils {


    /**
     * 根据宽高转换图片Uri
     */
    public static String getAdapterUri(String url, int width, int height) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }

        try {
            int i = url.indexOf("_");
            url = url.substring(0, i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //根据不同网络转换图片宽高
//        width = transformSize(width);
//        height = transformSize(height);

        if (height > 0 && width > 0) {
            return url + "_" + width + "X" + height + "X2.jpg";
        }
        return url;
    }


//    /***
//     * 转换图片的尺寸
//     * @param oldSize wifi网络尺寸
//     * @return
//     */
//    private static int transformSize(int oldSize) {
//        if (GlobalConstants.CURRENT_NETWORK_TYPE == GlobalConstants.NETWORK_2G) {
//            return oldSize * 3 / 5;
//        } else if (GlobalConstants.CURRENT_NETWORK_TYPE == GlobalConstants.NETWORK_3G) {
//            return oldSize * 4 / 5;
//        } else {
//            return oldSize;
//        }
//    }
}
