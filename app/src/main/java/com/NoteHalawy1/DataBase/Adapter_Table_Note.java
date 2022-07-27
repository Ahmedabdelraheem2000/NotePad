package com.NoteHalawy1.DataBase;

public class Adapter_Table_Note {

    int id;
    String title;
    String describe;
    String date;
    String label;
    String image;
    String color;

    public Adapter_Table_Note(int id, String title, String describe, String date, String label,String image, String color) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.label = label;
        this.image = image;
        this.color = color;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
