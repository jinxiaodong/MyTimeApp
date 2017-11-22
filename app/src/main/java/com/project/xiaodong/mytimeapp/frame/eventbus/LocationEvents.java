package com.project.xiaodong.mytimeapp.frame.eventbus;

/**
 * Created by xiaodong.jin on 2017/11/17.
 */

public class LocationEvents extends EventCenter {

    public static int code = 1001;
    public static String target = "USER_CUTOVER_CITY";

    public LocationEvents(int code, Object data) {
        super(code, data);

    }
}
