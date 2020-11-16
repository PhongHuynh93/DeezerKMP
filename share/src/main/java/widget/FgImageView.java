package widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.material.imageview.ShapeableImageView;
import com.wind.collagePhotoMaker.share.R;

public class FgImageView extends ShapeableImageView {
    private Drawable mForeground;

    public FgImageView(Context context) {
        super(context);
    }

    public FgImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inits(context, attrs);
    }

    public FgImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inits(context, attrs);
    }

    private void inits(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ForegroundView);
        Drawable foreground = a.getDrawable(R.styleable.ForegroundView_android_foreground);
        if (foreground != null) {
            setForeground(foreground);
        }
        a.recycle();
    }

    /**
     * Supply a drawable resource that is to be rendered on top of all of the child
     * views in the frame layout.
     *
     * @param drawableResId The drawable resource to be drawn on top of the children.
     */
    public void setForegroundResource(int drawableResId) {
        setForeground(getContext().getResources().getDrawable(drawableResId));
    }

    /**
     * Supply a Drawable that is to be rendered on top of all of the child
     * views in the frame layout.
     *
     * @param drawable The Drawable to be drawn on top of the children.
     */

    public void setForeground(Drawable drawable) {
        if (mForeground == drawable) {
            return;
        }
        if (mForeground != null) {
            mForeground.setCallback(null);
            unscheduleDrawable(mForeground);
        }

        mForeground = drawable;

        if (drawable != null) {
            drawable.setCallback(this);
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
        invalidate();
    }

    @Override
    public Drawable getForeground() {
        return mForeground;
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == mForeground;
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (mForeground != null) mForeground.jumpToCurrentState();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mForeground != null && mForeground.isStateful()) {
            mForeground.setState(getDrawableState());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mForeground != null) {
            mForeground.setBounds(0, 0, w, h);
            invalidate();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mForeground != null) {
            mForeground.draw(canvas);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
                if (mForeground != null)
                    mForeground.setHotspot(e.getX(), e.getY());
            }
        }
        return super.onTouchEvent(e);
    }
}
