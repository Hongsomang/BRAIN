package com.example.dsm2016.brain.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ghdth on 2018-05-11.
 */

public class DB_Test extends RealmObject {

    private int id;
    private String question_content;
    private String forget_content; //건망증
    private String dementia_content; //치매

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getForget_content() {
        return forget_content;
    }

    public void setForget_content(String forget_content) {
        this.forget_content = forget_content;
    }

    public String getDementia_content() {
        return dementia_content;
    }

    public void setDementia_content(String dementia_content) {
        this.dementia_content = dementia_content;
    }
}
