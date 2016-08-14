package me.zsj.pretty_girl.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.zsj.pretty_girl.R;
import me.zsj.pretty_girl.databinding.GirlItemBinding;
import me.zsj.pretty_girl.model.Image;
import me.zsj.pretty_girl.widget.RatioImageView;
import rx.functions.Action1;

/**
 * Created by zsj on 2015/11/20 0020.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder>
        implements Action1<List<Image>> {

    private Context mContext;
    private List<Image> mImages;
    private OnTouchListener onTouchListener;

    public GirlAdapter(Context context, List<Image> images) {
        this.mContext = context;
        this.mImages = images;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlViewHolder holder = new GirlViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.girl_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, int position) {
        Image image = mImages.get(position);

        holder.image = image;
        holder.binding.setImage(image);
        holder.binding.executePendingBindings();

        Glide.with(mContext)
                .load(image.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemViewType(int position) {
        Image image = mImages.get(position);
        return Math.round((float) image.width / (float) image.height * 10f);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    @Override
    public void call(List<Image> images) {
        notifyDataSetChanged();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {
        FrameLayout girlLayout;
        RatioImageView imageView;
        GirlItemBinding binding;

        Image image;

        public GirlViewHolder(View itemView) {
            super(itemView);
            imageView = (RatioImageView) itemView.findViewById(R.id.image);
            girlLayout = (FrameLayout) itemView.findViewById(R.id.girl_layout);
            binding = DataBindingUtil.bind(itemView);
            //防止手抖连续点击图片打开多个页面
            RxView.clicks(girlLayout)
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> {
                        if (onTouchListener != null) {
                            onTouchListener.onImageClick(imageView, image);
                        }
                    });
        }
    }

    public interface OnTouchListener {
        void onImageClick(View v, Image image);
    }
}
