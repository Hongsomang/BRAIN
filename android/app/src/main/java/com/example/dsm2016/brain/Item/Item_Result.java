package com.example.dsm2016.brain.Item;

/**
 * Created by ghdth on 2018-06-21.
 */

public class Item_Result {
    private String title;
    private String date;
    public Item_Result(String title,String date){
        this.title=title;
        this.date=date;
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
}
