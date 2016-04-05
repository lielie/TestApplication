package com.example.zhg.testapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.zhg.testapplication.R;


/**
 * Created by huanghaiyan on 16/3/24.
 */
public class CircleIndicator extends View implements ViewPager.OnPageChangeListener {

    private Paint mPaint;
    private int mCurCirCleColor;
    private int mCircleColor;
    private int mRadius;
    private int mCenterX;
    private int mCenterY;
    private int mDistance;
    private int mCount = 4;

    private int mIndex = 0;

    public CircleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicator(Context context) {
        this(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.indicator);
        mCurCirCleColor = typedArray.getColor(R.styleable.indicator_selectColor,
                getContext().getResources().getColor(R.color.white));
        mCircleColor = typedArray.getColor(R.styleable.indicator_unSelectColor,
                getContext().getResources().getColor(R.color.half_white));
        int mLength = typedArray.getIndexCount();
        for(int i = 0;i < mLength; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.indicator_diameter:
                    mRadius = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics())) / 2;
                    break;
                case R.styleable.indicator_distance:
                    mDistance = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics())) + 2 * mRadius;
                    break;
                default:
                    break;
            }
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCircle(canvas);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
    }

    /**
     * draw circle
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        if (mCount > 10 || mCount <= 1) {
            return;
        }

        int xDelta = (int) (mCenterX - mDistance * (mCount + 1) / 2f);

        Log.i("myTag", mRadius + "+" + mDistance);
        mPaint.setColor(mCircleColor);
        for(int i = 1;i <= mCount; i++) {
            canvas.drawCircle(mCenterX * 2 - mDistance * i - xDelta,
                    mCenterY, mRadius, mPaint);
        }

        mPaint.setColor(mCurCirCleColor);
        canvas.drawCircle(mCenterX * 2 - mDistance * (mCount - mIndex) - xDelta,
                mCenterY, mRadius, mPaint);
    }

    public void setCount(int i) {
        mCount = i;
        postInvalidate();
    }

    public void setIndex(int index) {
        mIndex = index;
        postInvalidate();
    }

    public void setPagerListener(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public int getPageIndex() {
        return mIndex;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width,height;

        if(widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        else {
            int contentWidth = mRadius * 8 + mDistance * 3;
            width = (int)(getPaddingLeft() + contentWidth + getPaddingRight());
        }

        if(heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }
        else {
            int contentHeight = mRadius * 2;
            height = (int)(getPaddingTop() + contentHeight + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }
}
