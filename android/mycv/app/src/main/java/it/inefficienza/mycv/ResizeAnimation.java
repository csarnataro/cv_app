package it.inefficienza.mycv;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author Christian Sarnataro
 */
public class ResizeAnimation extends Animation {
    final int startWidth;
    final float targetWidth;
    View view;

    public ResizeAnimation(View view, int targetWidth) {
        this.view = view;
        // this.targetWidth = targetWidth;
        Resources r = view.getResources();
        this.targetWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, targetWidth, r.getDisplayMetrics());

        startWidth = view.getWidth();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
        view.getLayoutParams().width = newWidth;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
