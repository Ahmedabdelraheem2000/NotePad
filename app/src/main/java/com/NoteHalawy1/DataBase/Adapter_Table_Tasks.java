package com.NoteHalawy1.DataBase;

public class Adapter_Table_Tasks {
    int id_task;
    String task_name;
    int id_note;
    int binary;

    public Adapter_Table_Tasks(int id_task, String task_name, int id_note, int binary) {
        this.id_task = id_task;
        this.task_name = task_name;
        this.id_note = id_note;
        this.binary = binary;
    }

    public Adapter_Table_Tasks(String task_name, int binary) {
        this.task_name = task_name;
        this.binary = binary;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getBinary() {
        return binary;
    }

    public void setBinary(int binary) {
        this.binary = binary;
    }
}
