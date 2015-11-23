package me.zsj.pretty_girl;

import java.util.List;

import me.zsj.pretty_girl.model.PrettyGirl;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by zsj on 2015/11/20 0020.
 */
public interface GirlApi {

    @GET("v1/channels/images.json")
    Observable<List<PrettyGirl>> fetchPrettyGirl(@Query("tags") String tags);
}
