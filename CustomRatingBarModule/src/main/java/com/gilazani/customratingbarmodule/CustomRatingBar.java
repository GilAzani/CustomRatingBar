package com.gilazani.customratingbarmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

public class CustomRatingBar extends LinearLayout {

    private Drawable iconDrawable;
    private float iconSize;
    private int ratingCount;
    private int animationStyle;
    private int selectedRating;

    private int selectedColor;
    private int unselectedColor;

    private OnRatingChangeListener onRatingChangeListener; // Field for listener


    public CustomRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupRatingBar(context, attrs);

        createRatingIcons();
    }

    public void setOnRatingChangeListener(OnRatingChangeListener listener) {
        this.onRatingChangeListener = listener;
    }

    private void setupRatingBar(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRatingBar, 0, 0);

        try {
            iconDrawable = typedArray.getDrawable(R.styleable.CustomRatingBar_iconDrawable);
            iconSize = typedArray.getDimension(R.styleable.CustomRatingBar_iconSize, 50f);
            ratingCount = typedArray.getInt(R.styleable.CustomRatingBar_ratingCount, 5);
            animationStyle = typedArray.getInt(R.styleable.CustomRatingBar_animationStyle, 0);

            // Get the custom colors from the user
            selectedColor = typedArray.getColor(R.styleable.CustomRatingBar_selectedColor,
                    ContextCompat.getColor(context, R.color.default_selected)); // Default color
            unselectedColor = typedArray.getColor(R.styleable.CustomRatingBar_unselectedColor,
                    ContextCompat.getColor(context, R.color.default_unselected)); // Default color
        } finally {
            typedArray.recycle();
        }

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    private void createRatingIcons() {
        for (int i = 1; i <= ratingCount; i++) {
            final ImageView icon = new ImageView(getContext());
            // Clone the drawable for each icon
            Drawable iconCopy = iconDrawable.mutate().getConstantState().newDrawable().mutate();
            icon.setImageDrawable(iconCopy);
            LayoutParams layoutParams = new LayoutParams((int) iconSize, (int) iconSize);
            icon.setLayoutParams(layoutParams);
            final int rating = i;
            icon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(rating);
                }
            });
            addView(icon);
        }
    }

    private void setRating(int rating) {
        selectedRating = rating;
        Log.d("CustomRatingBar", "Selected Rating: " + rating);
        for (int i = 0; i < ratingCount; i++) {
            ImageView icon = (ImageView) getChildAt(i);
            if (i < rating) {
                Log.d("CustomRatingBar", "Selected i: " + i);
                applyAnimation(icon);
                // Apply the color filter
                icon.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN);
            } else {
                icon.setColorFilter(unselectedColor, PorterDuff.Mode.SRC_IN);
            }
            // Log the current state of each icon
            Log.d("CustomRatingBar", "Icon " + i + " selected: " + (i < rating));
        }

        if (onRatingChangeListener != null) {
            onRatingChangeListener.onRatingChanged(rating); // Notify the listener
        }
    }

    private void applyAnimation(ImageView icon) {
        switch (animationStyle) {
            case 1: // Scale Animation
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        0.8f, 1.2f, 0.8f, 1.2f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                scaleAnimation.setDuration(300);
                scaleAnimation.setFillAfter(true);
                icon.startAnimation(scaleAnimation);
                break;

            case 2: // Rotate Animation
                RotateAnimation rotateAnimation = new RotateAnimation(
                        0f, 360f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotateAnimation.setDuration(300);
                rotateAnimation.setFillAfter(true);
                icon.startAnimation(rotateAnimation);
                break;

            default: // No Animation
                break;
        }
    }
}
