package com.vieru.vasile.lucraredean;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Vasile on 04.05.2017.
 */
public class Kebab {

    private int id;
    private String title;
    private String addrese;
    private float rating;
    private Bitmap image;
    private String description;

    public Kebab(int id, String title, String addrese, Bitmap image,float rating) {
        this.id=id;
        this.title = title;
        this.addrese = addrese;
        this.image = image;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private ArrayList<Kebab> kebabArrayList;

    public ArrayList<Kebab> getKebabArrayList() {
        return kebabArrayList;
    }

    public void setKebabArrayList(ArrayList<Kebab> kebabArrayList) {
        this.kebabArrayList = kebabArrayList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddrese() {
        return addrese;
    }

    public void setAddrese(String addrese) {
        this.addrese = addrese;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
