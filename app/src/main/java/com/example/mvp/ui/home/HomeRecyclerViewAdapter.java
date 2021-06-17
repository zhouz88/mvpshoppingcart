package com.example.mvp.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvp.R;
import com.example.mvp.bean.Goods;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Goods> data;

    public HomeRecyclerViewAdapter(Context context, List<Goods> data) {
        this.context = context;
        this.data = data;
    }

    public void setGoods(List<Goods> goods) {
        this.data.clear();
        this.data.addAll(goods);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.home_recycler_image_text) {
            return new MultiViewHolder(view);
        }
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case R.layout.home_recycler_banner:
                ((Banner) holder.itemView).setAdapter(new BannerImageAdapter<String>(data.get(position).getBanners()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String url, int position, int size) {
                        Glide.with(holder.itemView)
                                .load(url)
                                .apply(RequestOptions.centerCropTransform())
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver((LifecycleOwner) context)
                        .setIndicator(new CircleIndicator(context));
                break;
            case R.layout.home_recycler_image:
                Glide.with(holder.itemView)
                        .load(data.get(position).getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into((ImageView) holder.itemView);
                break;
            case R.layout.home_recycler_text:
                ((TextView) holder.itemView).setText(data.get(position).getText());
                break;
            case R.layout.home_recycler_image_text:
                MultiViewHolder mh = (MultiViewHolder) holder;
                mh.textView.setText(data.get(position).getText());
                Glide.with(holder.itemView)
                        .load(data.get(position).getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(mh.imageView);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods = data.get(position);
        if (goods.getBanners() != null) {
            return R.layout.home_recycler_banner;
        } else if (goods.getImageUrl() == null) {
            return R.layout.home_recycler_text;
        } else if (goods.getText() == null) {
            return R.layout.home_recycler_image;
        } else {
            return R.layout.home_recycler_image_text;
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    private static class SingleViewHolder extends RecyclerView.ViewHolder {

        public SingleViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class MultiViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MultiViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
