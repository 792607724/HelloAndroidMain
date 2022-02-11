package com.example.helloandroid;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity12 extends AppCompatActivity {

    private CircleImageView tween_anim_circleImageView;
    private TextView textViewFormatSystem, textViewScale, textViewTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tween_anim_circleImageView = findViewById(R.id.tween_anim_circleImageView);
        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.tween_animation_alpha);
        tween_anim_circleImageView.startAnimation(alpha);

        textViewFormatSystem = findViewById(R.id.textViewFormatSystem);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.tween_animation_rotate);
        textViewFormatSystem.startAnimation(rotate);

        textViewScale = findViewById(R.id.textViewScale);
        Animation scale = AnimationUtils.loadAnimation(this, R.anim.tween_animation_scale);
        textViewScale.startAnimation(scale);


        textViewTranslate = findViewById(R.id.textViewTranslate);
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.tween_animation_translate);
        textViewTranslate.startAnimation(translate);
    }
}