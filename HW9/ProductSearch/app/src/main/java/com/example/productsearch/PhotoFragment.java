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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            ((SpecificDetails)getActivity()).generateSingleItem();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            ((SpecificDetails)getActivity()).googlePhotoGen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
