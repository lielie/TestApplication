package com.example.zhg.testapplication;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.zhg.testapplication.Adapter.MyPagerViewAdapter;
import com.example.zhg.testapplication.view.CircleIndicator;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    ViewPager mViewPager;
    MyPagerViewAdapter mAdapter;
    ImageView imageView;
    ArrayList<View> mViews;
    CircleIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagerview);

        Intent intent = new Intent(MainActivity.this, RecyclerViewDemo.class);
        startActivity(intent);
        //init();
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViews = new ArrayList<>();
        mIndicator = (CircleIndicator) findViewById(R.id.indicator);

        addView(R.drawable.help1, 0);
        addView(R.drawable.help2, 1);
        addView(R.drawable.help3, 2);
        addView(R.drawable.help4, 3);

        mIndicator.setCount(mViews.size());
        mAdapter = new MyPagerViewAdapter(mViews);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(mIndicator);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.setIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void addView(int pic, final int current) {
        View view = View.inflate(this, R.layout.guide_view, null);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        imageView.setImageResource(pic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(current);
                Log.i("myTag", current+"");
            }
        });

        mViews.add(view);
        Log.i("myTag", "view");
    }

}
