package me.zsj.pretty_girl;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by zsj on 2015/11/20 0020.
 */
public class GirlRetrofit {

    private GirlApi girlApi;
    private OkHttpClient client = new OkHttpClient();

    public GirlRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://119.29.45.113:1024/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        girlApi = retrofit.create(GirlApi.class);
    }

    public GirlApi getGirlApi() {
        return girlApi;
    }
}
