package com.example.productsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GoogleAdapter extends RecyclerView.Adapter<GoogleAdapter.OurViewHolder> {

    private Context oContext;
    private List<GoogleItems> oData;

    public GoogleAdapter(Context oContext, List<GoogleItems> oData) {
        this.oContext = oContext;
        this.oData = oData;
    }

    @NonNull
    @Override
    public OurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater oInflater = LayoutInflater.from(oContext);
        view = oInflater.inflate(R.layout.google_card_view, parent, false);
        return new OurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurViewHolder holder, int i) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String imgURL = oData.get(i).getgImg();
        Glide.with(oContext).load(imgURL).apply(options).dontAnimate().into(holder.googleImgView);
    }

    @Override
    public int getItemCount() {
        return oData.size();
    }

    public static class OurViewHolder extends RecyclerView.ViewHolder {

        ImageView googleImgView;

        public OurViewHolder(@NonNull View itemView) {
            super(itemView);

            googleImgView = itemView.findViewById(R.id.gPhoto);
        }
    }

}
