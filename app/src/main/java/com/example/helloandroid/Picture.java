package com.example.helloandroid;

public class Picture {

    private String name;
    private int imageId;

    public Picture(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
