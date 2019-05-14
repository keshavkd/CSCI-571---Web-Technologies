package com.example.productsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;


public class ShipFragment extends Fragment {


    TextView a1, a2, b1, b2, b3, b4, c1, c2, c3, c4;
    LinearLayout al1, al2, al3, al4, bl1, bl2, bl3, bl4, cl1, cl2, cl3, cl4;
    LinearLayout m1, m2, m3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            ((SpecificDetails)getActivity()).generateSingleItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
