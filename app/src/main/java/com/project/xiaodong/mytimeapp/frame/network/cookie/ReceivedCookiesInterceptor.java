package com.project.xiaodong.mytimeapp.frame.network.cookie;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.utils.PreferencesUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wulin on 2016/9/5.
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            PreferencesUtils.putStringSet(BaseApplication.getContext(), cookies);
//            PreferenceHelper.getDefaultPreferences().edit()
//                    .putStringSet(PreferenceHelper.PREF_COOKIES, cookies)
//                    .apply();

        }

        return originalResponse;
    }
}
