package me.zsj.pretty_girl.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.squareup.picasso.Picasso;

import me.zsj.pretty_girl.R;
import me.zsj.pretty_girl.databinding.PictureActivityBinding;
import me.zsj.pretty_girl.widget.PullBackLayout;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zsj on 2015/11/21 0021.
 */
public class PictureActivity extends AppCompatActivity implements PullBackLayout.PullCallBack {

    private boolean systemUiIsShow = true;
    private ColorDrawable background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PictureActivityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.picture_activity);

        String mGirlUrl = getIntent().getExtras().getString("url");

        ViewCompat.setTransitionName(binding.ivPhoto, "girl");

        Picasso.with(this).load(mGirlUrl)
                .into(binding.ivPhoto);

        background = new ColorDrawable(Color.BLACK);

        binding.pullBackLayout.getRootView().setBackground(background);

        PhotoViewAttacher mViewAttacher = new PhotoViewAttacher(binding.ivPhoto);

        binding.pullBackLayout.setPullCallBack(this);

        mViewAttacher.setOnViewTapListener((view, x, y) -> {
            if (systemUiIsShow) {
                hideSystemUI();
                systemUiIsShow = false;
            } else {
                showSystemUI();
                systemUiIsShow = true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        showSystemUI();
        return super.onKeyDown(keyCode, event);
    }

    private static final int FLAG_HIDE_SYSTEM_UI = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            | View.SYSTEM_UI_FLAG_IMMERSIVE;

    private static final int FLAG_SHOW_SYSTEM_UI = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;


    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(FLAG_HIDE_SYSTEM_UI);
    }

    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(FLAG_SHOW_SYSTEM_UI);
    }

    @Override
    public void onPullStart() {
        showSystemUI();
    }

    @Override
    public void onPull(float progress) {
        showSystemUI();
        background.setAlpha((int) (0xff * (1f - progress)));
    }

    @Override
    public void onPullCompleted() {
        showSystemUI();
        PictureActivity.super.onBackPressed();
    }
}