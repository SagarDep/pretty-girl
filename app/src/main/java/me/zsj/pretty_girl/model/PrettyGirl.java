package me.zsj.pretty_girl.model;

import java.util.List;

/**
 * Created by zsj on 2015/11/16 0016.
 */
public class PrettyGirl {

    public int id;
    public String created_at;
    public Meta meta;
    public String url;
    public String channel;


    public static class Meta {
        public String type;
        public int width;
        public int height;
        public List<String> colors;
    }
}
