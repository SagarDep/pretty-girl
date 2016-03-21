package me.zsj.pretty_girl;

import javax.inject.Singleton;

import dagger.Component;
import me.zsj.pretty_girl.module.GirlApiModule;

/**
 * Created by zsj on 2016/3/5.
 */

@Singleton
@Component(modules = GirlApiModule.class)
public interface GirlApiComponent extends GirlGraph{

    final class Initializer {
        private Initializer() {

        }

        public static GirlApiComponent init() {
            return DaggerGirlApiComponent.builder()
                    .girlApiModule(new GirlApiModule())
                    .build();
        }
    }
}
