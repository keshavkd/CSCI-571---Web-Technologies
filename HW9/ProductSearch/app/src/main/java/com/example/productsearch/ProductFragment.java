package com.example.productsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class ProductFragment extends Fragment {

    TextView itemTitle, subPrice, subShip, textHigh, brandT, brandS, subT,
            subS, priceT, priceS, textSpec, specList;
    Layout prodLayout;
    HorizontalScrollView carouselView;
    ImageView picImage, iconHigh, iconSpec;
    LinearLayout brandLayout, subLayout, highLayout, picLayout, subTitleLayout, contentLayout,
            con1Layout, con2Layout, priceLayout, specLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        picLayout = getView().findViewById(R.id.picLayout);
//        picLayout.setVisibility(View.VISIBLE);
        subShip = getView().findViewById(R.id.subShip);
        subShip.setVisibility(getView().VISIBLE);
        con1Layout = getView().findViewById(R.id.conLayout1);
        con1Layout.setVisibility(getView().VISIBLE);
        con2Layout = getView().findViewById(R.id.conLayout2);
        con2Layout.setVisibility(getView().VISIBLE);
        brandLayout = getView().findViewById(R.id.brandLayout);
        brandLayout.setVisibility(getView().VISIBLE);
        subLayout = getView().findViewById(R.id.subLayout);
        subLayout.setVisibility(getView().VISIBLE);
        priceLayout = getView().findViewById(R.id.highLayout);
        priceLayout.setVisibility(View.VISIBLE);

        try {
            ((SpecificDetails)getActivity()).generateSingleItem();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
