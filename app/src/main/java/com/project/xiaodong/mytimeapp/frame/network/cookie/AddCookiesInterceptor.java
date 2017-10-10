package com.project.xiaodong.mytimeapp.frame.network.cookie;

import android.util.Log;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.utils.PreferencesUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wulin on 2016/9/5.
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
//        HashSet<String> preferences = (HashSet) PreferenceHelper.getDefaultPreferences().getStringSet(PreferenceHelper.PREF_COOKIES, new HashSet<String>());
        HashSet<String> preferences = (HashSet) PreferencesUtils.getStringSet(BaseApplication.getContext());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}