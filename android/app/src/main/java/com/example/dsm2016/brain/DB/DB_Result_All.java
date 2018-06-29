package com.example.dsm2016.brain.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-06-21.
 */

public class DB_Result_All extends RealmObject {
    private String key; //ex 테스트1
    private int kind;//테스트 종류 번호
    private String date;

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



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


