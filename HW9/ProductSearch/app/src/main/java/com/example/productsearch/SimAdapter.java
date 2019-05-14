package com.example.productsearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SimAdapter extends RecyclerView.Adapter<SimAdapter.NewViewHolder> {

    private Context mContext;
    private List<SimItems> simItemsList;

    public SimAdapter(Context mContext, List<SimItems> simItemsList) {
        this.mContext = mContext;
        this.simItemsList = simItemsList;
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {

        String itemId;
        ImageView simImgView;
        TextView simTitle, simShip, simDays, simPrice;
        String url;
        LinearLayout mainSimLayout;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);

            simTitle = itemView.findViewById(R.id.simTitle);
            simShip = itemView.findViewById(R.id.simShip);
            simDays = itemView.findViewById(R.id.simDays);
            simPrice = itemView.findViewById(R.id.simPrice);
            simImgView = itemView.findViewById(R.id.simImg);
            mainSimLayout = itemView.findViewById(R.id.mainSimLayout);

            mainSimLayout.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    view.getContext().startActivity(browserIntent);
                }
            });
        }
    }

    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater oInflater = LayoutInflater.from(mContext);
        view = oInflater.inflate(R.layout.sim_card_view, parent, false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimAdapter.NewViewHolder holder, int position) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String imgURL = simItemsList.get(position).getSimImg();
        Glide.with(mContext).load(imgURL).apply(options).dontAnimate().into(holder.simImgView);
        holder.itemId = simItemsList.get(position).getItemId();
        holder.simTitle.setText(simItemsList.get(position).getSimTitle());
        if (simItemsList.get(position).getSimShip().equals("0.00")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,0);
            holder.simDays.setLayoutParams(params);
            holder.simShip.setText("Free Shipping");
        } else {
            holder.simShip.setText("$" + simItemsList.get(position).getSimShip());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(120,0,0,0);
            holder.simDays.setLayoutParams(params);
        }
        if (simItemsList.get(position).getSimDays().equals("1"))
            holder.simDays.setText(simItemsList.get(position).getSimDays() + " Day Left");
        else
            holder.simDays.setText(simItemsList.get(position).getSimDays() + " Days Left");
        holder.simPrice.setText("$" + simItemsList.get(position).getSimPrice());
        holder.url = simItemsList.get(position).getUrl();
    }

    @Override
    public int getItemCount() {
        return simItemsList.size();
    }
}
