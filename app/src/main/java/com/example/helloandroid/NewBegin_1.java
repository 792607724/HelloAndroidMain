package com.example.helloandroid;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NewBegin_1 extends AppCompatActivity {

    private Button btn_jumpToSDKTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_begin2);

//        btn_jumpToSDKTest = findViewById(R.id.btn_jumpToSDKTest);
//        btn_jumpToSDKTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(NewBegin_1.this, SDKTest_mainactivity.class);
//                startActivity(intent);
//
//
//            }
//        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}