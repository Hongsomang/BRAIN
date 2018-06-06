package com.example.dsm2016.brain.Item;

/**
 * Created by ghdth on 2018-05-26.
 */

public class Item_Test {
    private String id;
    private String question_content;
    private String forget_content; //건망증
    private String dementia_content; //치매

    public Item_Test(String id, String question_content, String forget_content,String dementia_content){
        this.id=id;
        this.question_content=question_content;
        this.forget_content=forget_content;
        this.dementia_content=dementia_content;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
