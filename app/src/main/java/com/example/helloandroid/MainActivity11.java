package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity11 extends AppCompatActivity {

    private ImageView frame_animation_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        frame_animation_imageView = findViewById(R.id.frame_animation_imageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) frame_animation_imageView.getDrawable();
        animationDrawable.start();
    }
}