package com.example.mvp.ui.home;

import com.example.mvp.bean.BaseBean;
import com.example.mvp.bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalModel implements HomeContract.IHomeModel{
    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        return null;
    }
}
