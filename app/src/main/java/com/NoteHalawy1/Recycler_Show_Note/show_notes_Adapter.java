package com.NoteHalawy1.Recycler_Show_Note;

import com.NoteHalawy1.DataBase.Adapter_Table_Tasks;

import java.util.ArrayList;

public class show_notes_Adapter {
    int id;
    String title;
    String describe;
    String date;
    String label;
    String image;
    String Color;
    ArrayList<Adapter_Table_Tasks> adapter_table_tasks=new ArrayList<>();

    public show_notes_Adapter(int id, String title, String describe, String date, String label, String image, String color, ArrayList<Adapter_Table_Tasks> adapter_table_tasks) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.label = label;
        this.image = image;
        Color = color;
        this.adapter_table_tasks = adapter_table_tasks;
    }

    public show_notes_Adapter(String title, String describe, String date, String label, String image, String color, ArrayList<Adapter_Table_Tasks> adapter_table_tasks) {
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.label = label;
        this.image = image;
        Color = color;
        this.adapter_table_tasks = adapter_table_tasks;
    }

    public show_notes_Adapter(String title, String describe, String date, String label, String color) {
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.label = label;
        Color = color;
    }

    public show_notes_Adapter(String title, String describe, String date, String label, String color,
                              ArrayList<Adapter_Table_Tasks> adapter_table_tasks) {
        this.title = title;
        this.describe = describe;
        this.date = date;
        this.label = label;
        Color = color;
        this.adapter_table_tasks = adapter_table_tasks;


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
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public ArrayList<Adapter_Table_Tasks> getAdapter_table_tasks() {
        return adapter_table_tasks;
    }

    public void setAdapter_table_tasks(ArrayList<Adapter_Table_Tasks> adapter_table_tasks) {
        this.adapter_table_tasks = adapter_table_tasks;
    }

}
