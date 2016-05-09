package me.zsj.pretty_girl.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import me.zsj.pretty_girl.R;
import me.zsj.pretty_girl.widget.PullBackLayout;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zsj on 2015/11/21 0021.
 */
public class PictureActivity extends AppCompatActivity implements PullBackLayout.PullCallBack {

    private String mGirlUrl;
    private ImageView mImageView;
    private PhotoViewAttacher mViewAttacher;
    private PullBackLayout mPullBackLayout;

    private boolean mSystemUiShow = true;
    private ColorDrawable background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        mImageView = (ImageView) findViewById(R.id.iv_photo);
        mPullBackLayout = (PullBackLayout) findViewById(R.id.pullBackLayout);

        mGirlUrl = getIntent().getExtras().getString("url");

        ViewCompat.setTransitionName(mImageView, "girl");

        Picasso.with(this).load(mGirlUrl)
                .into(mImageView);

        background = new ColorDrawable(Color.BLACK);

        mPullBackLayout.getRootView().setBackground(background);

        mViewAttacher = new PhotoViewAttacher(mImageView);

        mPullBackLayout.setPullCallBack(this);

        mViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                if (mSystemUiShow) {
                    hideSystemUI();
                    mSystemUiShow = false;
                } else {
                    showSystemUI();
                    mSystemUiShow = true;
                }
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