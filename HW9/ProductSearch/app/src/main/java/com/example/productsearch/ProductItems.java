package com.example.productsearch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductItems {

    private String title, img, zipc, shipc, condc, price, itemId, keyword;

    private JSONObject demiItem;

    public ProductItems(JSONObject demiItem, String itemId, String title, String img, String zipc, String shipc, String condc, String price, String keyword) {
        this.demiItem = demiItem;
        this.itemId = itemId;
        this.title = title;
        this.img = img;
        this.zipc = zipc;
        this.shipc = shipc;
        this.condc = condc;
        this.price = price;
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public JSONObject getDemiItem() {
        return demiItem;
    }

    public void setDemiItem(JSONObject demiItem) {
        this.demiItem = demiItem;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        Log.d("IMGXO:", img);
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getZipc() {
        return zipc;
    }

    public void setZipc(String zipc) {
        this.zipc = zipc;
    }

    public String getShipc() {
        return shipc;
    }

    public void setShipc(String shipc) {
        this.shipc = shipc;
    }

    public String getCondc() {
        return condc;
    }

    public void setCondc(String condc) {
        this.condc = condc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
