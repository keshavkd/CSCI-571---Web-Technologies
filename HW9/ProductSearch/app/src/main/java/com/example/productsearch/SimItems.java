package com.example.productsearch;

import android.widget.TextView;

public class SimItems {

    String simTitle, simShip, simDays, simPrice, simImg;
    String itemId, url;

    public SimItems(String url, String itemId, String simTitle, String simShip, String simDays, String simPrice, String simImg) {
        this.url = url;
        this.itemId = itemId;
        this.simTitle = simTitle;
        this.simShip = simShip;
        this.simDays = simDays;
        this.simPrice = simPrice;
        this.simImg = simImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSimTitle() {
        return simTitle;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setSimTitle(String simTitle) {
        this.simTitle = simTitle;
    }

    public String getSimShip() {
        return simShip;
    }

    public void setSimShip(String simShip) {
        this.simShip = simShip;
    }

    public String getSimDays() {
        return simDays;
    }

    public void setSimDays(String simDays) {
        this.simDays = simDays;
    }

    public String getSimPrice() {
        return simPrice;
    }

    public void setSimPrice(String simPrice) {
        this.simPrice = simPrice;
    }

    public String getSimImg() {
        return simImg;
    }

    public void setSimImg(String simImg) {
        this.simImg = simImg;
    }
}
