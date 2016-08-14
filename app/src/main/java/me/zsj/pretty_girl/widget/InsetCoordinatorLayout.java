package me.zsj.pretty_girl.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

import me.zsj.pretty_girl.R;

/**
 * Created by zsj on 2016/5/22.
 */
public class InsetCoordinatorLayout extends CoordinatorLayout implements View.OnApplyWindowInsetsListener{
    
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    
    public InsetCoordinatorLayout(Context context) {
        this(context, null);
    }

    public InsetCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsetCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOnApplyWindowInsetsListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
        int l = insets.getSystemWindowInsetLeft();
        int t = insets.getSystemWindowInsetTop();
        int r = insets.getSystemWindowInsetRight();
        int b = insets.getSystemWindowInsetBottom();
        toolbar.setPadding(l, t, 0, 0);

        recyclerView.setPaddingRelative(recyclerView.getPaddingLeft() + l,
                recyclerView.getPaddingTop(),
                recyclerView.getPaddingRight(),
                recyclerView.getPaddingBottom() + b);

        final boolean ltr = recyclerView.getLayoutDirection() == View.LAYOUT_DIRECTION_LTR;

        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingEnd() + (ltr ? r : 0),
                getPaddingBottom()
        );

        setOnApplyWindowInsetsListener(null);
        return insets.consumeSystemWindowInsets();
    }

}
