package com.example.dsm2016.brain.Item;

/**
 * Created by ghdth on 2018-06-21.
 */

public class Item_Result {
    private String title;
    private String date;
    private int kind;
    public Item_Result(String title,String date ,int kind){
        this.title=title;
        this.date=date;
        this.kind=kind;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
