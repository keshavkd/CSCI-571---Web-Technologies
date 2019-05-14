package com.example.productsearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.XViewHolder> {

    Context mContext;
    List<Entry> listItems;
    String filename = "file10.txt";
    ViewGroup vx;
    WishFragment fragment;

    public class XViewHolder extends RecyclerView.ViewHolder {

        public TextView titlex, zipx, shipx, condx, pricex;
        public ImageView imgx, igx;
        public String itemID, titleVal, zipVal, imgVal, shipVal, condVal, priceVal, keyword;
        int flag;

        public XViewHolder(View view) {
            super(view);
            titlex = (TextView)view.findViewById(R.id.prodTitle);
            zipx = (TextView)view.findViewById(R.id.prodZip);
            imgx = (ImageView)view.findViewById(R.id.prodImg);
            shipx = (TextView)view.findViewById(R.id.prodShip);
            condx = (TextView)view.findViewById(R.id.prodCond);
            pricex = (TextView)view.findViewById(R.id.prodPrice);
            igx = view.findViewById(R.id.cartIcon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Intent ix = new Intent(mContext, SpecificDetails.class);
                    ix.putExtra("ID", itemID);
                    ix.putExtra("SHIP", shipVal);
                    ix.putExtra("ZIP", zipVal);
                    ix.putExtra("COND", condVal);
                    ix.putExtra("PRICE", priceVal);
                    ix.putExtra("TITLE", titleVal);
                    ix.putExtra("IMG", imgVal);
                    ix.putExtra("FLAG", flag);
                    ix.putExtra("KEYWORD", keyword);
                    Log.d("ID:", itemID);
                    mContext.startActivity(ix);
                }
            });
        }
    }

    public WishAdapter(Context mC, List<Entry> lItems, WishFragment f) {
        this.mContext = mC;
        this.listItems = lItems;
        this.fragment = f;
    }

    @Override
    public XViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        vx = parent;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new WishAdapter.XViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull XViewHolder holder, int position) {
        Log.d("BACKBUTTON", "TEST");
        final XViewHolder hx = holder;
        holder.titlex.setText(listItems.get(position).getTitleSave());

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String imgURL = listItems.get(position).getImgSave();
        String temp = "https://www.w3schools.com/w3css/img_snowtops.jpg";
    //        GlideApp.with(mContext).load(imgURL).placeholder(R.mipmap.ic_launcher_round).into(holder.imgx);
        Glide.with(mContext).load(imgURL).apply(options).dontAnimate().into(holder.imgx);

        holder.zipx.setText(listItems.get(position).getZipSave());
        holder.condx.setText(listItems.get(position).getCondSave());
        holder.shipx.setText(listItems.get(position).getShipSave());
        holder.pricex.setText(listItems.get(position).getPriceSave());

        holder.itemID = listItems.get(position).getItemId();
        holder.shipVal = listItems.get(position).getShipSave();
        holder.titleVal = listItems.get(position).getTitleSave();
        holder.zipVal = listItems.get(position).getZipSave();
        holder.priceVal = listItems.get(position).getPriceSave();
        holder.condVal = listItems.get(position).getCondSave();
        holder.imgVal = listItems.get(position).getImgSave();
        holder.keyword = listItems.get(position).getKeyword();

        try {
            FileInputStream fs = new FileInputStream(new File(mContext.getFilesDir(), filename));
            Log.d("FILEDIR", (mContext.getFilesDir()).toString());
            ObjectInputStream os = new ObjectInputStream(fs);
            List<Entry> ogJS = (List<Entry>)os.readObject();
            Log.d("ARRAYOBJ", ogJS.toString());
            int i;
            for (i = 0; i < ogJS.size(); i++) {
                String xtemp = ogJS.get(i).getItemId();
                Log.d("ITEMIDCHECK", xtemp + " : " + holder.itemID);
                if (xtemp.equals(holder.itemID)) {
                    Log.d("SETCART", "REMOVE");
                    Drawable d = VectorDrawableCompat.create(mContext.getResources(), R.drawable.cart_remove, null);
                    d = DrawableCompat.wrap(d);
                    d.mutate().setColorFilter(Color.parseColor("#FC5830"), PorterDuff.Mode.SRC_IN);
                    holder.igx.setImageDrawable(d);

                    holder.flag = -1;

                    holder.igx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeFromList(hx, hx.itemID);
                            fragment.refreshAdapter();
                        }
                    });
                    break;
                }
            }
            if (i == ogJS.size()) {
                holder.flag = 1;

                Log.d("SETCART", "PLUS");
                Drawable d = VectorDrawableCompat.create(mContext.getResources(), R.drawable.cart_plus, null);
                d = DrawableCompat.wrap(d);
                d.mutate().setColorFilter(Color.parseColor("#AAAAAA"), PorterDuff.Mode.SRC_IN);
                holder.igx.setImageDrawable(d);

                holder.igx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addToList(hx, hx.itemID);
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFromList(WishAdapter.XViewHolder holder, String id) {
        final WishAdapter.XViewHolder hx = holder;
        try {
            FileInputStream fs = new FileInputStream(new File(mContext.getFilesDir(), filename));
            ObjectInputStream os = new ObjectInputStream(fs);
            List<Entry> ogJS = (List<Entry>)os.readObject();
            Log.d("ARRAYOBJ", ogJS.toString());
            int i;
            for (i = 0; i < ogJS.size(); i++) {
                String xtemp = ogJS.get(i).getItemId();
                Log.d("ITEMIDCHECK", xtemp + " : " + holder.itemID);
                if (xtemp.equals(holder.itemID)) {
                    ogJS.remove(i);

                    FileOutputStream fsx = new FileOutputStream(new File(mContext.getFilesDir(), filename));
                    ObjectOutputStream osx = new ObjectOutputStream(fsx);
                    osx.writeObject(ogJS);
                    osx.close();

                    Log.d("SETCART", "ADD");
                    Drawable d = VectorDrawableCompat.create(mContext.getResources(), R.drawable.cart_plus, null);
                    d = DrawableCompat.wrap(d);
                    d.mutate().setColorFilter(Color.parseColor("#AAAAAA"), PorterDuff.Mode.SRC_IN);
                    holder.igx.setImageDrawable(d);
                    holder.flag = 1;

                    Toast.makeText(mContext, holder.titleVal + " removed from wishlist!", Toast.LENGTH_SHORT).show();

                    holder.igx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addToList(hx, hx.itemID);
                        }
                    });
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToList(WishAdapter.XViewHolder holder, String id) {
        final WishAdapter.XViewHolder hx = holder;
        try {
            Entry tryJS = new Entry(holder.itemID, holder.titleVal, holder.imgVal, holder.zipVal, holder.shipVal, holder.priceVal, holder.condVal, holder.keyword);
            FileInputStream fsx = new FileInputStream(new File(mContext.getFilesDir(), filename));
            ObjectInputStream osx = new ObjectInputStream(fsx);
            List<Entry> ogJS = (List<Entry>)osx.readObject();
            ogJS.add(tryJS);
            osx.close();
            FileOutputStream fs = new FileOutputStream(new File(mContext.getFilesDir(), filename));
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(ogJS);
            os.close();

            Log.d("SETCART", "REMOVE");
            Drawable d = VectorDrawableCompat.create(mContext.getResources(), R.drawable.cart_remove, null);
            d = DrawableCompat.wrap(d);
            d.mutate().setColorFilter(Color.parseColor("#FC5830"), PorterDuff.Mode.SRC_IN);
            holder.igx.setImageDrawable(d);
            holder.flag = -1;

            Toast.makeText(mContext, holder.titleVal + " added to wishlist!", Toast.LENGTH_SHORT).show();

            holder.igx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFromList(hx, hx.itemID);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

}
