package com.example.mvp.ui.home;

import com.example.mvp.bean.BaseBean;
import com.example.mvp.bean.Goods;
import com.example.mvp.bean.GoodsService;
import com.example.mvp.network.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class HomeModel implements HomeContract.IHomeModel{
    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        return RetrofitClient.getInstance().getService(GoodsService.class).getGoods();
    }
}
