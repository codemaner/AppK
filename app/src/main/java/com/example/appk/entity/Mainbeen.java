package com.example.appk.entity;

/**
 * Created by 那个谁 on 2018/1/20.
 */
public class Mainbeen {
    private String avthor;
    private String time;

    public Mainbeen(String avthor, String time) {
        this.avthor = avthor;
        this.time = time;
    }

    public String getAvthor() {
        return avthor;
    }

    public void setAvthor(String avthor) {
        this.avthor = avthor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
