package com.project.xiaodong.mytimeapp.frame.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
     * Get
     */
    @GET("{url}")
    Observable<ResponseBody> get(@Path(value = "url",encoded = true) String url,
                                 @QueryMap Map<String, String> maps);

    /**
     * Post
     */
    @POST("{url}")
    Observable<ResponseBody> post(@Path("url") String url,
                                  @FieldMap Map<String, String> maps);


    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(@Path("url") String url,
                                        @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    @POST("{url}")
    Call<ResponseBody> uploadFiles(@Path("url") String url,
                                   @Part("filename") String description,
                                   @PartMap() Map<String, RequestBody> maps);


}















