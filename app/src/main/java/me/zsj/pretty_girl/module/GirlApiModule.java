package me.zsj.pretty_girl.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.zsj.pretty_girl.GirlApi;
import me.zsj.pretty_girl.GirlRetrofit;

/**
 * Created by zsj on 2016/3/5.
 */

@Module
public class GirlApiModule {

    @Provides @Singleton
    public GirlApi provideGirlApi() {
        return new GirlRetrofit().getGirlApi();
    }
}
