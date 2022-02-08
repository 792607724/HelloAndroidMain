package com.example.helloandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PictureAdapter extends ArrayAdapter<Picture> {

    private int resourceId;

    public PictureAdapter(@NonNull Context context, int layout_item_id, List<Picture> pictureList) {
        super(context, layout_item_id, pictureList);
        resourceId = layout_item_id;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Picture picture_reference = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView pictureImage = view.findViewById(R.id.list_circleView);
        TextView pictureName = view.findViewById(R.id.list_textview);
        pictureImage.setImageResource(picture_reference.getImageId());
        pictureName.setText(picture_reference.getName());
        return view;
    }

}
