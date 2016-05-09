package me.zsj.pretty_girl;

import me.zsj.pretty_girl.model.GirlData;
import retrofit.Result;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by zsj on 2015/11/20 0020.
 */
public interface GirlApi {

    @GET("data/福利/10/{page}")
    Observable<Result<GirlData>> fetchPrettyGirl(@Path("page") int page);
}