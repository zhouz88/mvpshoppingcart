package com.example.mvp.ui.home;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvp.bean.Goods;

import java.util.List;

public class HomeSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {
    List<Goods> data;

    public HomeSpanSizeLookUp(List<Goods> data) {
        this.data = data;
    }
    @Override
    public int getSpanSize(int position) {
        return data.get(position).getSpanSize();
    }

    public void update(List<Goods> data) {
        this.data.clear();
        this.data.addAll(data);
    }
}
