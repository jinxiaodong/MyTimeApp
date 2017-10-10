package com.project.xiaodong.mytimeapp.business.home.apiserveice;

import com.project.xiaodong.mytimeapp.business.home.bean.TopModuleBean;
import com.project.xiaodong.mytimeapp.frame.bean.TimeBaseEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by xiaodong.jin on 2017/9/20.
 */

public interface ApiService {


    /**
     * home
     */
    @GET("{url}")
    Observable<TopModuleBean> get(@Path(value = "url", encoded = true) String url,
                                                  @QueryMap HashMap<String, Object> maps);

    /**
     * Post
     */
    @POST("{url}")
    Observable<TimeBaseEntity> post(@Path(value = "url", encoded = true) String url,
                                    @FieldMap Map<String, String> maps);


    /**
     * 上传单个文件
     *
     * @param url
     * @param requestBody
     * @return
     */
    @Multipart
    @POST("{url}")
    Observable<TimeBaseEntity> upLoadFile(@Path(value = "url", encoded = true) String url,
                                          @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    @POST("{url}")
    Observable<TimeBaseEntity> uploadFiles(@Path(value = "url", encoded = true) String url,
                                           @Part("filename") String description,
                                           @PartMap() Map<String, RequestBody> maps);


}














