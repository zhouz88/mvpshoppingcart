package com.example.mvp.ui.home;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvp.R;
import com.example.mvp.bean.Goods;
import com.example.mvp.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeContract.IHomeView {
    HomeContract.IHomePresenter presenter;
    HomeRecyclerViewAdapter adapter;
    HomeSpanSizeLookUp lookup;
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initViews() {
        SwipeRefreshLayout swipe = find(R.id.home_swiperefresh);
        RecyclerView rv = find(R.id.home_recyclerview);
        swipe.setOnRefreshListener(this);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),
                4);
        List<Goods> goods = new ArrayList<>();
        lookup = new HomeSpanSizeLookUp(goods);
        manager.setSpanSizeLookup(lookup);
        rv.setLayoutManager(manager);
        adapter = new HomeRecyclerViewAdapter(getActivity(), goods);
        rv.setAdapter(adapter);
        presenter = new HomePresenter(this);
        presenter.getData();
    }

    @Override
    public void onRefresh() {
        presenter.getData();
    }

    @Override
    public void onGoodsSuccess(List<Goods> goods) {
        Log.e("right", goods.size()+"onGoods");
        lookup.update(goods);
        adapter.setGoods(goods);
    }

    @Override
    public void onGoodsFailure(Throwable throwable) {

    }
}
