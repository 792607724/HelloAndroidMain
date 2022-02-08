package com.example.helloandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity8 extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> viewList;
    private MyPageAdapter myPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        viewPager = findViewById(R.id.viewPager);
        viewList = new ArrayList<View>();
        LayoutInflater layoutInflater = getLayoutInflater();
        viewList.add(layoutInflater.inflate(R.layout.viewpage_one, null, false));
        viewList.add(layoutInflater.inflate(R.layout.viewpage_two, null, false));
        viewList.add(layoutInflater.inflate(R.layout.viewpage_three, null, false));
        myPageAdapter = new MyPageAdapter(viewList);
        viewPager.setAdapter(myPageAdapter);
    }
}