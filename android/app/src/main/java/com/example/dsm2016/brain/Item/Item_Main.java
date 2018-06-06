package com.example.dsm2016.brain.Item;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Item_Main {
    private String question;
    private String answer;

    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    private int listNumber;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Item_Main(String question, String answer){
        this.answer=answer;
        this.question=question;
        this.listNumber = listNumber;
    }
}
