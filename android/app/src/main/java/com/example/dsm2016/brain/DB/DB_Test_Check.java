package com.example.dsm2016.brain.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-05-16.
 */

public class DB_Test_Check extends RealmObject{
    private int id;
    private String check;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
