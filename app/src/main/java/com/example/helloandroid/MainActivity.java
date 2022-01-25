package com.example.helloandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.iielse.switchbutton.SwitchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button_2);
        Button btn3 = findViewById(R.id.button_3);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        SwitchView switchView = findViewById(R.id.switchButton);
        switchView.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true);
                Toast.makeText(MainActivity.this, "Switch On", Toast.LENGTH_LONG).show();
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false);
                Toast.makeText(MainActivity.this, "Switch Off", Toast.LENGTH_LONG).show();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Jump to 线性布局", Toast.LENGTH_LONG).show();
            }
        });
        btn2.setOnClickListener(new MyButton());
    }

    private class MyButton implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Jump to 相对布局", Toast.LENGTH_LONG).show();
        }
    }
}