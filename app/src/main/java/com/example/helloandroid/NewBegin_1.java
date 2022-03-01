package com.example.helloandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NewBegin_1 extends AppCompatActivity {

    private Button btn_jumpToSDKTest;
    private MyButton myButton;

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
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}