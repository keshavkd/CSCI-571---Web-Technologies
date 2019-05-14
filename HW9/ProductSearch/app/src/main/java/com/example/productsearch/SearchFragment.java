package com.example.productsearch;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    EditText key, dist;

    RadioGroup grp;
    RadioButton btn, btx;
    AutoCompleteTextView atx;
    LinearLayout ensx;
    CheckBox n, u, x, f, l, ensb;
    Spinner spx;
    TextView tx1, tx2;
    List<String> autoZip = new ArrayList<>();
    ScrollView sx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spx = getView().findViewById(R.id.category);
        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(super.getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spx.setAdapter(catAdapter);

        btx = getView().findViewById(R.id.cur_loc);
        btx.setChecked(true);

        atx = getView().findViewById(R.id.zipcode);
        atx.setEnabled(false);

        ensx = getView().findViewById(R.id.ens_enable_layout);
        ensx.setVisibility(LinearLayout.GONE);

        tx1 = getView().findViewById(R.id.error1);
        tx2 = getView().findViewById(R.id.error2);
        tx1.setVisibility(getView().GONE);
        tx2.setVisibility(getView().GONE);

        atx = view.findViewById(R.id.zipcode);

        atx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if (s.toString().length() < 5) {
                    String autoGeo = "http://api.geonames.org/postalCodeSearchJSON?postalcode_startsWith=" + s.toString()
                            + "&username=keshavkd&country=US&maxRows=5";
                    RequestQueue queue = Volley.newRequestQueue(getActivity());

                    JsonObjectRequest jsonObjectReqest = new JsonObjectRequest(Request.Method.GET, autoGeo, null,
                            new Response.Listener<JSONObject>() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        autoZip = new ArrayList<>();
                                        Log.d("AUTOJSON", response.toString());
                                        JSONArray autoArray = response.getJSONArray("postalCodes");
                                        Log.d("ARRAYJSON", autoArray.toString());
                                        for (int i = 0; i < autoArray.length(); i++) {
                                            autoZip.add(autoArray.getJSONObject(i).getString("postalCode"));
                                        }
                                        Log.d("AUTOARRAY", autoZip.toString());
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                (getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, autoZip) {
                                            @Override
                                            public View getView(int position,View convertView, ViewGroup parent) {
                                                View view = super.getView(position,
                                                        convertView, parent);
                                                TextView text = view.findViewById(android.R.id.text1);
                                                text.setTextColor(Color.BLACK);
                                                return view;
                                            }
                                        };
                                        atx.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();

                                    } catch (Exception e) {

                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("AUTOERROR", error.getMessage());
                                }
                            }
                    );
                    queue.add(jsonObjectReqest);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
