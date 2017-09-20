package com.project.xiaodong.mytimeapp.network;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by xiaodong.jin on 2017/9/20.
 */

public interface ApiService extends BaseApiService {

    /**
     * retrofit2.0登录
     */
    @POST("")
    Call<String> login();
}
