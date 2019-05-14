package com.example.productsearch;

import java.io.Serializable;

public class Entry implements Serializable {

    String itemId, titleSave, imgSave, zipSave, shipSave, priceSave, condSave, keyword;

    public Entry(String itemId, String titleSave, String imgSave, String zipSave, String shipSave, String priceSave, String condSave, String keyword) {
        this.itemId = itemId;
        this.titleSave = titleSave;
        this.imgSave = imgSave;
        this.zipSave = zipSave;
        this.shipSave = shipSave;
        this.priceSave = priceSave;
        this.condSave = condSave;
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitleSave() {
        return titleSave;
    }

    public void setTitleSave(String titleSave) {
        this.titleSave = titleSave;
    }

    public String getImgSave() {
        return imgSave;
    }

    public void setImgSave(String imgSave) {
        this.imgSave = imgSave;
    }

    public String getZipSave() {
        return zipSave;
    }

    public void setZipSave(String zipSave) {
        this.zipSave = zipSave;
    }

    public String getShipSave() {
        return shipSave;
    }

    public void setShipSave(String shipSave) {
        this.shipSave = shipSave;
    }

    public String getPriceSave() {
        return priceSave;
    }

    public void setPriceSave(String priceSave) {
        this.priceSave = priceSave;
    }

    public String getCondSave() {
        return condSave;
    }

    public void setCondSave(String condSave) {
        this.condSave = condSave;
    }
}
