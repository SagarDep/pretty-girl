package me.zsj.pretty_girl;

import java.util.List;

import me.zsj.pretty_girl.model.GirlData;
import me.zsj.pretty_girl.model.Image;
import rx.functions.Func1;

/**
 * Created by zsj on 2016/3/20.
 */
public class Results {

    public static Func1<GirlData, Boolean> DATA_FUNC = new Func1<GirlData, Boolean>() {
        @Override
        public Boolean call(GirlData data) {
            return !data.error && data.results != null;
        }
    };

    public static Func1<GirlData, Boolean> isSuccess() {
        return DATA_FUNC;
    }

    public static Func1<List<Image>, Boolean> IMAGE_FUNC = new Func1<List<Image>, Boolean>() {
        @Override
        public Boolean call(List<Image> images) {
            return images.size() != 0 && images != null;
        }
    };

    public static Func1<List<Image>, Boolean> isNull() {
        return IMAGE_FUNC;
    }

    private Results() {
        throw new AssertionError("no instances ");
    }
}
