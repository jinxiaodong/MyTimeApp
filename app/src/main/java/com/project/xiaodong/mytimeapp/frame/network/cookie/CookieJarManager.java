/**
 * @file CookieJarManager
 * @copyright (c) 2016 Macalline All Rights Reserved.
 * @author SongZheng
 * @date 2016/8/29
 */
package com.project.xiaodong.mytimeapp.frame.network.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author SongZheng
 * @description TODO
 * @date 2016/8/29
 */
public class CookieJarManager implements CookieJar {

    /*******************************************************************************
     *	Public/Protected Variables
     *******************************************************************************/

    /*******************************************************************************
     *	Private Variables
     *******************************************************************************/
    private CookieJarStore cookieStore;
    /*******************************************************************************
     *	Overrides From Base
     *******************************************************************************/
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    /**
     * 清除cookies
     * */
    public void clearCookies(){
        if(cookieStore != null){
            cookieStore.removeAll();
        }
    }
    /*******************************************************************************
     *	Public/Protected Methods
     *******************************************************************************/
    public CookieJarManager(Context context){
        cookieStore = new CookieJarStore(context);
    }
    /*******************************************************************************
     *	Private Methods
     *******************************************************************************/

    /*******************************************************************************
     *	Internal Class,Interface
     *******************************************************************************/
}
