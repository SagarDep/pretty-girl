package me.zsj.pretty_girl;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by zsj on 2015/11/20 0020.
 */
public class GirlRetrofit {

    private final GirlApi girlApi;
    private final OkHttpClient client = new OkHttpClient();

    private static final String GANK_URL = "http://gank.io/api/";

    public GirlRetrofit() {

        client.setConnectTimeout(20, TimeUnit.SECONDS);
        client.setReadTimeout(15, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GANK_URL)
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