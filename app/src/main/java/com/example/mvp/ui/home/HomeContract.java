package com.example.mvp.ui.home;

import com.example.mvp.bean.BaseBean;
import com.example.mvp.bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HomeContract {
    interface IHomePresenter {
        void getData();
    }

    interface IHomeModel {
        Flowable<BaseBean<List<Goods>>> getData();
    }

    interface IHomeView {
        void onGoodsSuccess(List<Goods> goods);
        void onGoodsFailure(Throwable throwable);
    }
}
