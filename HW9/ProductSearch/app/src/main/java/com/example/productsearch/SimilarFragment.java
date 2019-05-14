package com.example.productsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimilarFragment extends Fragment {
    int count = 0;
    Spinner sx, sy;
    RecyclerView simRec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_similar, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

//        try {
//            ((SpecificDetails)getActivity()).generateSimilar();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Log.d("SPINNER", "TEST1");

        final Spinner s1 = view.findViewById(R.id.type);
        final Spinner s2 = view.findViewById(R.id.order);
        sx = view.findViewById(R.id.type);
        sy = view.findViewById(R.id.order);
        simRec = view.findViewById(R.id.simRec);
        simRec.setVisibility(View.VISIBLE);

        Log.d("Spinner1", s1.getSelectedItem().toString());
        Log.d("Spinner2", s2.getSelectedItem().toString());

        sortSimilar(view, s1.getSelectedItemPosition(), s2.getSelectedItemPosition());
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int x = s2.getSelectedItemPosition();
                sortSimilar(view, position, x);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int x = s1.getSelectedItemPosition();
                sortSimilar(view, x, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortSimilar(View v, int a, int b) {
        List<SimItems> defaultObj = new ArrayList<>();

        try {
            defaultObj = ((SpecificDetails)getActivity()).getDefault();
        } catch (Exception e) {
            Log.d("GIVEUP", "I'M DONE");
        }

        if (defaultObj.isEmpty()) {
            Log.d("FIRE", "ARCANINE");
            sy.setEnabled(false);
            sy.setClickable(false);
        } else {
            Log.d("SORT", "" + a + " " + b);
            if (a == 0) {
                sy.setEnabled(false);
                sy.setClickable(false);

                defaultObj = ((SpecificDetails)getActivity()).getDefault();
                SimAdapter adap = new SimAdapter(super.getContext(), defaultObj);
                simRec.setLayoutManager(new GridLayoutManager(super.getContext(), 1));
                simRec.setAdapter(adap);
            } else {
                if (a == 1 && b == 0) {
                    Collections.sort(defaultObj, new Comparator<SimItems>() {
                        @Override
                        public int compare(SimItems obj1, SimItems obj2) {
                            return obj1.getSimTitle().compareToIgnoreCase(obj2.getSimTitle());
                        }
                    });
                } else if (a == 1 && b == 1) {
                    Collections.sort(defaultObj, new Comparator<SimItems>() {
                        @Override
                        public int compare(SimItems obj1, SimItems obj2) {
                            return obj2.getSimTitle().compareToIgnoreCase(obj1.getSimTitle());
                        }
                    });
                }

                if (a == 2 && b == 0) {
                    Collections.sort(defaultObj, new Comparator<SimItems>() {
                        @Override
                        public int compare(SimItems obj1, SimItems obj2) {
                            Double p1 = Double.parseDouble(obj1.simPrice);
                            Double p2 = Double.parseDouble(obj2.simPrice);
                            if (p1 > p2)
                                return 1;
                            else
                                return -1;
                        }
                    });
                } else if (a == 2 && b == 1) {
                    Collections.sort(defaultObj, new Comparator<SimItems>() {
                        @Override
                        public int compare(SimItems obj1, SimItems obj2) {
                            Double p1 = Double.parseDouble(obj1.simPrice);
                            Double p2 = Double.parseDouble(obj2.simPrice);
                            if (p2 > p1)
                                return 1;
                            else
                                return -1;
                        }
                    });
                }

                if (a == 3 && b == 0) {
                    Collections.sort(defaultObj, new Comparator<SimItems>() {
                        @Override
                        public int compare(SimItems obj1, SimItems obj2) {
                            int p1 = Integer.parseInt(obj1.simDays);
                            int p2 = Integer.parseInt(obj2.simDays);
                            if (p1 > p2)
                                return 1;
                            else
                                return -1;
                        }
                    });
                } else if (a == 3 && b == 1) {
                    Collections.sort(defaultObj, new Comparator<SimItems>() {
                        @Override
                        public int compare(SimItems obj1, SimItems obj2) {
                            int p1 = Integer.parseInt(obj1.simDays);
                            int p2 = Integer.parseInt(obj2.simDays);
                            if (p2 > p1)
                                return 1;
                            else
                                return -1;
                        }
                    });
                }

                sy.setEnabled(true);
                sy.setClickable(true);
            }

            SimAdapter adap = new SimAdapter(super.getContext(), defaultObj);
            simRec.setLayoutManager(new GridLayoutManager(super.getContext(), 1));
            simRec.setAdapter(adap);
        }
    }



}
