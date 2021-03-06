package com.example.mvp.bean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsService {
    @GET("edu-lance/edu-lance.github.io/master/goods_list")
    Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("edu-lance/edu-lance.github.io/master/goods_detail")
    Flowable<BaseBean<List<Goods>>> getGoodDetail(@Query("goodsId") int goodsId);
}
