package com.NoteHalawy1.radio_button;

public class radio_button_adapter {
    String radio_task;
    int bimary;

    public radio_button_adapter(String radio_task, int bimary) {
        this.radio_task = radio_task;
        this.bimary = bimary;
    }

    public String getRadio_task() {
        return radio_task;
    }

    public void setRadio_task(String radio_task) {
        this.radio_task = radio_task;
    }

    public int getBimary() {
        return bimary;
    }

    public void setBimary(int bimary) {
        this.bimary = bimary;
    }
}
