package com.example.dsm2016.brain.Item;

/**
 * Created by ghdth on 2018-06-15.
 */

public class Item_Test_result {
    private String number;
    private int image;
    private  int image2;

    public Item_Test_result(String number,int image,int image2){
        this.number=number;
        this.image=image;
        this.image2=image2;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }
}
