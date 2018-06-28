package com.example.dsm2016.brain.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-06-26.
 */

public class DB_TTS_check extends RealmObject {
    private String key;
    private int TTS_answer;
    private String date;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTTS_answer() {
        return TTS_answer;
    }

    public void setTTS_answer(int TTS_answer) {
        this.TTS_answer = TTS_answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
