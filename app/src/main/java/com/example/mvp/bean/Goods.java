package com.example.mvp.bean;

import java.util.List;

public class Goods {

    private Integer goodsId;

    private Integer spanSize;

    private List<String> banners = null;

    private String text;

    private String imageUrl;

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
    public int getGoodsId() {
        return goodsId;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
    public int getSpanSize() {
        return spanSize;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }
    public List<String> getBanners() {
        return banners;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}