package com.example.helloandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NewBegin_1 extends AppCompatActivity {

    private Button btn_jumpToSDKTest, button4, button5;
    private MyButton myButton;
    private TextView textView;
    private ImageView imageView;
    private EditText editTextTextPersonName2, editTextTextPersonName3;
    private LinearLayout linearLayout;
    int count = 1;

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
        myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(NewBegin_1.this, "Test Toast", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.TOP | Gravity.LEFT, 100, 200);
                toast.setMargin(0.01f, 0.5f);
                toast.show();

                Dialog alertDialog = new AlertDialog.Builder(NewBegin_1.this).setTitle("AlertDialog").setView(new EditText(NewBegin_1.this)).setMessage("This is a alert dialog").setIcon(R.drawable.chat).setPositiveButton("Yes", null).setNegativeButton("No", null).create();
                alertDialog.show();

                Intent intent = new Intent(NewBegin_1.this, SDKTest_mainactivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NewBegin_1.this, 0, intent, 0);


                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                int i = 1;

//                Notification notification = new NotificationCompat.Builder(NewBegin_1.this).setContentTitle("测试通知标题" + String.valueOf(i)).setContentText("测试通知内容显示" + String.valueOf(i)).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.chat).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.chat_highlight)).setAutoCancel(true).setContentIntent(pendingIntent).build();
//                Notification notification = new NotificationCompat.Builder(NewBegin_1.this)
//                        .setContentTitle("测试通知标题" + String.valueOf(i))
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("//                Notification notification = new NotificationCompat.Builder(NewBegin_1.this).setContentTitle(\"测试通知标题\" + String.valueOf(i)).setContentText(\"测试通知内容显示\" + String.valueOf(i)).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.chat).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.chat_highlight)).setAutoCancel(true).setContentIntent(pendingIntent).build();\n"))
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.drawable.chat)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.chat_highlight))
//                        .setAutoCancel(true)
//                        .setContentIntent(pendingIntent).build();

                Notification notification = new NotificationCompat.Builder(NewBegin_1.this)
                        .setContentTitle("测试通知标题" + String.valueOf(i))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.tween_animation_robotoperate)))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.chat)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.chat_highlight))
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setContentIntent(pendingIntent).build();
                Log.e("Error info", "Test Error level Log");
                Log.v("Verbose info", "Test Verbose level Log");
                Log.w("Warning info", "Test Warning level Log");
                Log.i("Information info", "Test Information level Log");
                Log.d("Debug info", "Test Debug level Log");
                notificationManager.notify(i, notification);


//                for (int i = 0; i < 10; i++) {
//                    try {
//                        Notification notification = new NotificationCompat.Builder(NewBegin_1.this).setContentTitle("测试通知标题" + String.valueOf(i)).setContentText("测试通知内容显示" + String.valueOf(i)).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.chat).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.chat_highlight)).build();
//                        notificationManager.notify(i, notification);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
            }
        });

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);

        editTextTextPersonName2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (keyEvent.getAction()) {
                    case KeyEvent.ACTION_DOWN:
                        String strLabel = editTextTextPersonName2.getText().toString();
                        if (strLabel.matches("\\w+@+\\w+\\.\\w+")) {
                            imageView.setImageResource(R.drawable.chat_highlight);
                        } else {
                            imageView.setImageResource(R.drawable.chat);
                        }
                        break;
                    case KeyEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        linearLayout = findViewById(R.id.linearLayout);
        button4 = findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(NewBegin_1.this);
                editText.setHint("创建第" + String.valueOf(count) + "个联系人");
                linearLayout.addView(editText);
                Toast.makeText(NewBegin_1.this, "当前已经创建" + String.valueOf(count) + "个联系人", Toast.LENGTH_SHORT).show();
                count++;
            }
        });

        button5 = findViewById(R.id.button5);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextTextPersonName3.getText().toString();
                if (!"".equals(url)) {
                    Uri uri = Uri.parse("http://" + url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewBegin_1.this, "请输入网址以进行跳转打开", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            textView.setText("触控位置：(" + String.valueOf(x) + " , " + String.valueOf(y) + ")");
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}