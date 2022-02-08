package com.example.helloandroid;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity7 extends AppCompatActivity {

    private List<Picture> pictureList = new ArrayList<>();
    private String[] data_textview = {"发现", "视频流", "照片", "播放", "设置"};
    private int[] data_picture = {R.drawable.listview_explorer, R.drawable.listview_livestream, R.drawable.listview_picture, R.drawable.listview_play, R.drawable.listview_settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        initData();
        PictureAdapter pictureAdapter = new PictureAdapter(MainActivity7.this, R.layout.picture_item, pictureList);
        ListView listView = findViewById(R.id.list_view_parent);
        listView.setAdapter(pictureAdapter);
    }

    private void initData() {
        for (int i = 0; i < data_textview.length; i++) {
            Picture picture = new Picture(data_textview[i], data_picture[i]);
            pictureList.add(picture);
        }
        for (int i = 0; i < data_textview.length; i++) {
            Picture picture = new Picture(data_textview[i], data_picture[i]);
            pictureList.add(picture);
        }
    }
}