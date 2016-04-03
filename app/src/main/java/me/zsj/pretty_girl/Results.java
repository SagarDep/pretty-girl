package me.zsj.pretty_girl;

import java.util.List;

import retrofit.Result;
import rx.functions.Func1;

/**
 * Created by zsj on 2016/3/20.
 */
public class Results {

    public static Func1<Result<?>, Boolean> DATA_FUNC = new Func1<Result<?>, Boolean>() {
        @Override
        public Boolean call(Result<?> result) {
            return !result.isError() && result.response().isSuccess();
        }
    };

    public static Func1<Result<?>, Boolean> isSuccess() {
        return DATA_FUNC;
    }

    public static Func1<List<?>, Boolean> IMAGE_FUNC = new Func1<List<?>, Boolean>() {
        @Override
        public Boolean call(List<?> images) {
            return images.size() != 0 && images != null;
        }
    };

    public static Func1<List<?>, Boolean> isNull() {
        return IMAGE_FUNC;
    }

    private Results() {
        throw new AssertionError("no instances ");
    }
}
