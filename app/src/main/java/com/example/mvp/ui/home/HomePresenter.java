package com.example.mvp.ui.home;

import android.util.Log;

import com.example.mvp.bean.BaseBean;
import com.example.mvp.bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomeContract.IHomePresenter {
    private HomeContract.IHomeModel homeModel;
    private HomeContract.IHomeView homeView;

    public HomePresenter(HomeContract.IHomeView view) {
        homeModel = new HomeModel();
        this.homeView = view;
    }

    @Override
    public void getData() {
        homeModel.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                    @Override
                    public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                        Log.e("right", "ok" + listBaseBean.getData().size());
                        homeView.onGoodsSuccess(listBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("error", "er");
                        homeView.onGoodsFailure(throwable);
                    }
                });
    }
}
