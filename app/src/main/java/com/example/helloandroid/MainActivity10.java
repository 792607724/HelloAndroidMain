package com.example.helloandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity10 extends AppCompatActivity {

    private TextView click_to_skip;
    private int num = 0;
    private boolean flag = true;
    private CardView click_skip;

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (flag) {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                try {
                    thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        click_to_skip = findViewById(R.id.click_to_skip);
        click_skip = findViewById(R.id.click_skip);

        thread.start();
        // 隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隐藏标题栏
        getSupportActionBar().hide();

        // 创建子线程
        // 使用线程实现Splash快速开屏：在当前界面暂停5s后跳转到下一个界面
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity9.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

        click_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity9.class);
                startActivity(intent);
                finish();
                myThread.interrupt();
            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    click_to_skip.setText(String.valueOf(++num) + "s");
                    break;
                default:
                    break;
            }
        }
    };
}