package com.example.happyselect.adapter;

import com.example.happyselect.R;

/**
 * Created on 2019-11-22.
 *
 * @author DengJianMing
 * @describe
 */
public class Item {

    private String itemText;
    private int imageButtonId;


    public Item(String itemText){
        this.itemText = itemText;
        imageButtonId = R.id.delete_item_btn;
    }

    public String getItemText() {
        return itemText;
    }


    public int getImageButtonId() {
        return imageButtonId;
    }


}
