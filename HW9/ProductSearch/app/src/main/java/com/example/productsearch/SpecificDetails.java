package com.example.productsearch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wssholmes.stark.circular_score.CircularScoreView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecificDetails extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private String gObj;
    private JSONObject jObj;

    String ack;
    JSONObject item;

    Spinner s1, s2;

//    Product Initialization
    TextView itemTitle, subPrice, subShip, textHigh, brandT, brandS, subT,
        subS, priceT, priceS, textSpec, specList;
    Layout prodLayout;
    HorizontalScrollView carouselView;
    ImageView picImage, iconHigh, iconSpec;
    LinearLayout brandLayout, subLayout, highLayout, picLayout,
            subTitleLayout, contentLayout, con1Layout, con2Layout, priceLayout, specLayout;

    TextView a1, a2, b1, b2, b3, b4, c1, c2, c3, c4;
    CircularScoreView a3;
    ImageView a4;
    LinearLayout al1, al2, al3, al4, bl1, bl2, bl3, bl4, cl1, cl2, cl3, cl4;
    LinearLayout m1, m2, m3;
    int ix;
    String googlePhoto;
    String facebookURL = "http://facebook.com/";
    String fbTitle = "", fbPrice = "";
    RecyclerView gRv;

    final List<GoogleItems> imgList = new ArrayList<>();
    List<SimItems> simItemsList = new ArrayList<>();

    String filename = "file10.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            gObj = getIntent().getStringExtra("OBJ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String titleSave, imgSave, zipSave, shipSave, priceSave, condSave, itemSave, keyword;

        titleSave = getIntent().getStringExtra("TITLE");
        imgSave = getIntent().getStringExtra("IMG");
        zipSave = getIntent().getStringExtra("ZIP");
        shipSave = getIntent().getStringExtra("SHIP");
        priceSave = getIntent().getStringExtra("PRICE");
        condSave = getIntent().getStringExtra("COND");
        itemSave = getIntent().getStringExtra("ID");
        keyword = getIntent().getStringExtra("KEYWORD");

        int i = getIntent().getIntExtra("FLAG", 1);
        Log.d("IVAL", "" + i);


//        Log.d("OBJECT:", gObj);
        try {
            Log.d("SEARCH", "MOSTLY");
            jObj = new JSONObject(gObj);
            getSupportActionBar().setTitle(jObj.getJSONArray("title").get(0).toString());
            Log.d("TITLE:", jObj.getJSONArray("title").get(0).toString());
            fbTitle = jObj.getJSONArray("title").get(0).toString();
        } catch (Exception e) {
            Log.d("WISH", "MOSTLY");
            getSupportActionBar().setTitle(titleSave);
            fbTitle = titleSave;
            googlePhoto = keyword;
            e.printStackTrace();
        }

        Log.d("OUTSIDE", "YEEHAW");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.light_grey);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }

        });



        final FloatingActionButton fab = findViewById(R.id.fabx);
        if (i == -1) {
            Log.d("IND", "" + i);
            fab.setImageResource(R.drawable.cart_remove);
        } else {
            Log.d("IND", "" + i);
            fab.setImageResource(R.drawable.cart_plus);
        }

        ix = getIntent().getIntExtra("FLAG", 1);
        fab.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                Log.d("INNERFLAG", "" + ix);
                if (ix == 1) {
                    fab.setImageResource(R.drawable.cart_remove);
                    Toast.makeText(getApplicationContext(), titleSave + " added to wishlist!", Toast.LENGTH_SHORT).show();
                    saveToFile(itemSave, titleSave, imgSave, zipSave, shipSave, condSave, priceSave, keyword);
                } else {
                    fab.setImageResource(R.drawable.cart_plus);
                    Toast.makeText(getApplicationContext(), titleSave + " removed from wishlist!", Toast.LENGTH_SHORT).show();
                    removeFromFile(itemSave);
                }
                ix *= -1;
            }
        });

        try {
            generateSingleItem();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            Log.d("PIKA", "CHU");
            generateSimilar();
            Log.d("PIKA", "CHUUUUUUU");
        } catch (Exception e) {
//            TextView temp = findViewById(R.id.noRecordsP3);
//            temp.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }

    public boolean saveDecision(String id) {
        try {
            Log.d("SAVEDECISION", "BEGIN");
            FileInputStream fs = new FileInputStream(new File(getFilesDir(), filename));
            ObjectInputStream os = new ObjectInputStream(fs);
            List<Entry> ogJS = (List<Entry>)os.readObject();

            for (int i = 0; i < ogJS.size(); i++) {
                String temp = ogJS.get(i).getItemId();
                if (temp.equals(id))
                    return false;
            }

            os.close();
        } catch (Exception e) {
            //You'll need to add proper error handling here
            try {
//                FileOutputStream fsx = openFileOutput(filename, Context.MODE_PRIVATE);
//                fsx.write("".getBytes());
//                fsx.close();
                Log.d("SAVEDECISION", "LETSCREATE");
                File dir = getFilesDir();
                File nm = new File(dir, filename);
                Log.d("SAVEDECISION", "GETFILE");
                FileOutputStream fs = new FileOutputStream(nm);
                fs.write("".getBytes());
                fs.close();
                Log.d("SAVEDECISION", "WRITENEW");
            } catch (Exception e1) {
                Log.d("SAVEDECISION", "HERETOO");
                e1.printStackTrace();
            }
            Log.d("SAVEDECISION", "EMPTY");
        }
        return true;
    }

    public void readFile() {
        try {
            FileInputStream fs = new FileInputStream(new File(getFilesDir(), filename));
            ObjectInputStream os = new ObjectInputStream(fs);
            List<Entry> tryJS = (List<Entry>) os.readObject();
            Log.d("READFILE/", tryJS.toString());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String id, String title, String img, String zip, String ship, String cond, String price, String keyword) {

        if (!saveDecision(id))
            return;

        Entry tryJS = new Entry(id, title, img, zip, ship, price, cond, keyword);
        try {
            Log.d("WISHME", "START");
            FileInputStream fs = new FileInputStream(new File(getFilesDir(), filename));
            Log.d("WISHME", "OPEN");
            try {
                ObjectInputStream os = new ObjectInputStream(fs);
                Log.d("WISHME", "OBJ");
                List<Entry> ogJS = (List<Entry>)os.readObject();
                ogJS.add(tryJS);
                os.close();
                Log.d("WISHME", "ADDEDTOLIST");
                FileOutputStream fxs = new FileOutputStream(new File(getFilesDir(), filename));
                ObjectOutputStream oxs = new ObjectOutputStream(fxs);
                oxs.writeObject(ogJS);
                oxs.close();
            } catch (Exception e) {
                FileOutputStream fxs = new FileOutputStream(new File(getFilesDir(), filename));
                ObjectOutputStream oxs = new ObjectOutputStream(fxs);
                List<Entry> ogNS = new ArrayList<>();
                ogNS.add(tryJS);
                oxs.writeObject(ogNS);
                oxs.close();
            }
            Log.d("WISHME", "DONEBOI");
            readFile();
            Log.d("WISHME", "SUCCESS");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromFile(String id) {
        if (saveDecision(id))
            return;

        try {
            Log.d("REMOVE", "START");
            FileInputStream fs = new FileInputStream(new File(getFilesDir(), filename));
            ObjectInputStream os = new ObjectInputStream(fs);
            List<Entry> ogJS = (List<Entry>)os.readObject();
            for (int i = 0; i < ogJS.size(); i++) {
                String temp = ogJS.get(i).getItemId();
                if (temp.equals(id)) {
                    Log.d("REMOVE", "REMOVEDFROMLIST");
                    ogJS.remove(i);
                    break;
                }
            }
            os.close();
            FileOutputStream fxs = new FileOutputStream(new File(getFilesDir(), filename));
            ObjectOutputStream oxs = new ObjectOutputStream(fxs);
            oxs.writeObject(ogJS);
            Log.d("REMOVE", "DONEBOI");
            oxs.close();
            readFile();
            Log.d("REMOVE", "SUCCESS");
        }
        catch (Exception e) {
            //You'll need to add proper error handling here
            Log.d("REMOVE", "EMPTY");
        }
    }

    public void googlePhotoGen() {
        RequestQueue queue = Volley.newRequestQueue(this);
        try {
            String titleSearch;
            try {
                titleSearch = jObj.getJSONArray("title").get(0).toString();
            } catch (Exception e) {
                titleSearch = getIntent().getStringExtra("TITLE");
            }
//            String titleSearch =
            titleSearch = URLEncoder.encode(titleSearch, "utf-8");
            Log.d("TITLESEARCH:", titleSearch);

            JSONObject jsMap = new JSONObject();
            jsMap.put("q", titleSearch);
            final String requestBody = jsMap.toString();
            Log.d("REQ", requestBody);

//            http://newnodejs.dfxrbpp2hp.us-east-2.elasticbeanstalk.com/
//            String url = "http://10.0.2.2:8081/photoGen?q=" + titleSearch;
            String url = "http://newnodejs.dfxrbpp2hp.us-east-2.elasticbeanstalk.com/photoGen?q=" + titleSearch;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("GIMGLIST: ", response.toString());

                                for (int i = 0; i < response.getJSONArray("items").length(); i++) {
                                    Log.d("GRES" + i, response.getJSONArray("items").getJSONObject(i).toString());
                                    imgList.add(new GoogleItems(response.getJSONArray("items").getJSONObject(i).getString("link")));
                                }

                                gRv = findViewById(R.id.photoRec);
                                gRv.setVisibility(View.VISIBLE);
                                GoogleAdapter adap = new GoogleAdapter(getApplicationContext(), imgList);
                                gRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                gRv.setAdapter(adap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), "Object does not exist", Toast.LENGTH_SHORT).show();
                    Log.d("Error2: ", error.toString());
                }
            }) { //no semicolon or coma
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return params;
                }

                @Override
                public byte[] getBody() {
                    try {
                        Log.d("inGetBody: ", requestBody);
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        Log.d("ERROR:", "You're screwed bud");
                        return null;
                    }
                }
            };
            queue.add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }

    public void generateSimilar() throws UnsupportedEncodingException {
        Log.d("TEMP", "TOASTY");
        final String simID = getIntent().getStringExtra("ID");
//        String init = "http://svcs.ebay.com/MerchandisingService?OPERATION-NAME=getSimilarItems&SERVICE-NAME=MerchandisingService&SERVICE-VERSION=1.1.0&CONSUMER-ID=";
//        String end = "&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&itemId=" + simID + "&maxResults=20";
//        final String simURL = "http://10.0.2.2:8081/similarItemJSON?init=" + init + "&end=" + end;
//        Log.d("SIMURL", simURL);

//        String simURL = "http://10.0.2.2:8081/similarItemJSON?init=" + simID;
        String simURL = "http://newnodejs.dfxrbpp2hp.us-east-2.elasticbeanstalk.com/similarItemJSON?init=" + simID;
        RequestQueue qx = Volley.newRequestQueue(this);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, simURL, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("ABHI", response.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("SREE", error.toString());
//                    }
//                });
//        qx.add(jsonObjectRequest);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, simURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsSim;
                                Log.d("SIMRES", response);

//                                String hR = response.replace("\"", "\\").replace("\\\\", "\"").replace("\\", "");
//                                Log.d("ARCEUS", hR);
                                jsSim = new JSONObject(response);

                                Log.d("SIMJS", jsSim.toString());
                                String ack = jsSim.getJSONObject("getSimilarItemsResponse").getString("ack");
                                if (ack.equals("Success")) {
                                    String simXId, simT, simS, simD, simP, simImg, simXUrl;

                                    Log.d("SUCCESS", "YEEHAW");
                                    JSONArray simItem = jsSim.getJSONObject("getSimilarItemsResponse").getJSONObject("itemRecommendations").getJSONArray("item");

                                    for (int i = 0; i < simItem.length(); i++) {
                                        simXId = simItem.getJSONObject(i).getString("itemId");
                                        simT = simItem.getJSONObject(i).getString("title");
                                        simS = simItem.getJSONObject(i).getJSONObject("shippingCost").getString("__value__");
                                        simD = simItem.getJSONObject(i).getString("timeLeft");
                                        simD = simD.substring(simD.indexOf("P") + 1, simD.indexOf("D"));
                                        simP = simItem.getJSONObject(i).getJSONObject("buyItNowPrice").getString("__value__");
                                        simImg = simItem.getJSONObject(i).getString("imageURL");
                                        simXUrl = simItem.getJSONObject(i).getString("viewItemURL");
                                        simItemsList.add(new SimItems(simXUrl, simXId, simT, simS, simD, simP, simImg));
                                    }

                                    if (simItem.length() == 0) {
                                        Log.d("RAI", "CHU");
                                        findViewById(R.id.simRec).setVisibility(View.GONE);
                                        findViewById(R.id.nosimilar).setVisibility(View.VISIBLE);
                                        Log.d("RAI", "CHUUUU");
                                    } else {
                                        findViewById(R.id.simRec).setVisibility(View.VISIBLE);
                                        Log.d("MEWTWO", "MEWTWO");
                                        findViewById(R.id.nosimilar).setVisibility(View.GONE);
                                    }

                                    RecyclerView simRec= findViewById(R.id.simRec);
                                    simRec.setVisibility(View.VISIBLE);
                                    SimAdapter adap = new SimAdapter(getApplicationContext(), simItemsList);
                                    simRec.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                    simRec.setAdapter(adap);



                                } else {

                                }
                            } catch (Exception e) {

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            qx.add(stringRequest);
    }

    public List<SimItems> getDefault() {
        List<SimItems> adb = new ArrayList<>(simItemsList);
        return adb;
    }

    public void generateSingleItem() throws UnsupportedEncodingException {

        final String s1 = "http://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=JSON&appid=";
        final String s2 = "&siteid=0&version=967&ItemID=" + getIntent().getStringExtra("ID") + "&IncludeSelector=Details,ItemSpecifics";
        //String singleURL = "http://10.0.2.2:8081/singleItemJSON?sin1=" + URLEncoder.encode(s1, "UTF-8") + "&sin2=" + URLEncoder.encode(s2, "UTF-8");
        String singleURL = "http://newnodejs.dfxrbpp2hp.us-east-2.elasticbeanstalk.com/singleItemJSON?sin1=" + URLEncoder.encode(s1, "UTF-8") + "&sin2=" + URLEncoder.encode(s2, "UTF-8");
        RequestQueue queue = Volley.newRequestQueue(this);

        try {
            JSONObject jsMap = new JSONObject();
            jsMap.put("sin1", s1);
            jsMap.put("sin2", s2);
            final String requestBody = jsMap.toString();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, singleURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("JSONOBJ: ", response.toString());
                                ack = response.getString("Ack");
                                if (ack.equals("Success")) {
                                    try {
                                        int flag1 = 0;
                                        int flag2 = 0;
                                        int x = 0;
                                        item = response.getJSONObject("Item");
                                        Log.d("ITEM:", item.toString());
                                        Log.d("NUMPIC:", "" + item.getJSONArray("PictureURL").length());
                                        facebookURL = item.getString("ViewItemURLForNaturalSearch");

                                        picLayout = findViewById(R.id.liLayout);
                                        for (int j = 0; j < item.getJSONArray("PictureURL").length(); j++) {
                                            ImageView imgz = new ImageView(getApplicationContext());
//                                            LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                                            imgz.setLayoutParams(vp);
//                                            imgz.setMaxHeight(250);

                                            LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(600, LinearLayout.LayoutParams.MATCH_PARENT);
                                            vp.setMargins(5, 5, 5, 5);
                                            imgz.setLayoutParams(vp);
//                                            imgz.setImageResource(R.drawable.ic_launcher_background);

                                            RequestOptions options = new RequestOptions()
                                                    .placeholder(R.mipmap.ic_launcher_round)
                                                    .error(R.mipmap.ic_launcher_round);
                                            String imgURL = item.getJSONArray("PictureURL").getString(j);
                                            Glide.with(getApplicationContext()).load(imgURL).apply(options).dontAnimate().into(imgz);
                                            picLayout.addView(imgz);
                                        }

                                        itemTitle = findViewById(R.id.itemTitle);
                                        itemTitle.setText(item.getString("Title"));
                                        subPrice = findViewById(R.id.subPrice);
                                        subPrice.setText("$" + item.getJSONObject("CurrentPrice").getString("Value"));
                                        fbPrice = item.getJSONObject("CurrentPrice").getString("Value");
                                        subShip = findViewById(R.id.subShip);
                                        String temp = getIntent().getStringExtra("SHIP");
                                        if (temp.equals("Free Shipping")) {
                                            subShip.setText("With " + temp);
                                        } else if (temp.equals("N/A")) {
                                            subShip.setVisibility(View.GONE);
                                        } else {
                                            temp = temp.substring(temp.indexOf(":") + 1);
                                            subShip.setText("With " + temp + " Shipping");
                                        }
                                        brandLayout = findViewById(R.id.brandLayout);
                                        try {

                                            int k = 0;
                                            JSONArray brandObj = item.getJSONObject("ItemSpecifics").getJSONArray("NameValueList");
                                            for (; k < brandObj.length(); k++) {
                                                String xtemp = brandObj.getJSONObject(k).getString("Name");
                                                if (xtemp.equals("Brand"))
                                                    break;
                                            }

                                            temp = item.getJSONObject("ItemSpecifics").getJSONArray("NameValueList").
                                                    getJSONObject(k).getJSONArray("Value").getString(0);
                                            temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
                                            brandS = findViewById(R.id.brandS);
                                            brandS.setText(temp);
                                        } catch (Exception e3) {
                                            brandLayout.setVisibility(View.GONE);
                                            flag1++;
                                        }
                                        subLayout = findViewById(R.id.subLayout);
                                        try {
                                            subS = findViewById(R.id.subS);
                                            subS.setText(item.getString("SubTitle"));
                                        } catch (Exception e4) {
                                            subLayout.setVisibility(View.GONE);
                                            flag1++;
                                        }
                                        priceLayout = findViewById(R.id.priceLayout);
                                        try {
                                            priceS = findViewById(R.id.priceS);
                                            priceS.setText("$" + item.getJSONObject("CurrentPrice").getString("Value"));
                                        } catch (Exception e5) {
                                            priceLayout.setVisibility(View.GONE);
                                            flag1++;
                                        }

                                        if (flag1 == 3) {
                                            con1Layout = findViewById(R.id.conLayout1);
                                            con1Layout.setVisibility(View.GONE);
                                        }

                                        con2Layout = findViewById(R.id.conLayout2);
                                        try {
                                            // •
                                            int l = 0;
                                            JSONArray specObj = item.getJSONObject("ItemSpecifics").getJSONArray("NameValueList");
                                            for (; l < specObj.length(); l++) {
                                                String xtemp = specObj.getJSONObject(l).getString("Name");
                                                if (xtemp.equals("Brand"))
                                                    break;
                                            }
                                            String acText = "";
                                            try {
                                                String temp2 = item.getJSONObject("ItemSpecifics").getJSONArray("NameValueList").
                                                        getJSONObject(l).getJSONArray("Value").getString(0);
                                                temp2 = temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
                                                acText = "• " + temp2 + "\n";
                                            } catch (Exception e7) {

                                            }

                                            for (int k = 0; k < specObj.length(); k++) {
                                                String ttemp = specObj.getJSONObject(k).getString("Name");
                                                if (ttemp.equals("Brand"))
                                                    continue;
                                                else {
                                                    String temp3 = item.getJSONObject("ItemSpecifics").getJSONArray("NameValueList").
                                                            getJSONObject(k).getJSONArray("Value").getString(0);
                                                    temp3 = temp3.substring(0, 1).toUpperCase() + temp3.substring(1);
                                                    acText += "• " + temp3 + "\n";
                                                }
                                            }

                                            acText += "\n\n\n\n";

                                            specList = findViewById(R.id.specList);
                                            specList.setText(acText);
                                        } catch (Exception e1) {
                                            con2Layout.setVisibility(View.GONE);
                                            Log.d("ITEM:", "Item doesn't exist!");
                                        }

                                        try {
                                            m1 = findViewById(R.id.sbLayout);
                                            m2 = findViewById(R.id.shLayout);
                                            m3 = findViewById(R.id.rpLayout);
                                            int f1 = 0, f2 = 0, f3 = 0;

                                            al1 = findViewById(R.id.storeL);
                                            try {
                                                a1 = findViewById(R.id.stval1);
                                                String stName = item.getJSONObject("Storefront").getString("StoreName");
                                                SpannableString sString = new SpannableString(stName);
                                                try {
                                                    String stURL = item.getJSONObject("Storefront").getString("StoreURL");
                                                    sString.setSpan(new URLSpan(stURL), 0, stName.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                    a1.setText(sString);
                                                    a1.setMovementMethod(LinkMovementMethod.getInstance());
                                                } catch (Exception e8a) {
                                                    sString.setSpan(new URLSpan("http://www.ebay.com"), 0, stName.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                    a1.setText(sString);
                                                    a1.setMovementMethod(LinkMovementMethod.getInstance());
                                                }
                                            } catch (Exception e8) {
                                                al1.setVisibility(View.GONE);
                                                f1++;
                                            }

                                            al2 = findViewById(R.id.feedL);
                                            al3 = findViewById(R.id.popL);
                                            al4 = findViewById(R.id.starL);
                                            try {
                                                a2 = findViewById(R.id.stval2);
                                                a3 = findViewById(R.id.popS);
                                                a4 = findViewById(R.id.starTyoe);
                                                a2.setText(item.getJSONObject("Seller").getString("FeedbackScore"));
                                                int fs = item.getJSONObject("Seller").getInt("FeedbackScore");
                                                ContextCompat.getColor(getApplicationContext(), R.color.yellow);
                                                ContextCompat.getColor(getApplicationContext(), R.color.green);
                                                ContextCompat.getColor(getApplicationContext(), R.color.blue);
                                                ContextCompat.getColor(getApplicationContext(), R.color.turq);
                                                ContextCompat.getColor(getApplicationContext(), R.color.purple);
                                                ContextCompat.getColor(getApplicationContext(), R.color.red);
                                                ContextCompat.getColor(getApplicationContext(), R.color.silver);


                                                if (fs < 50) {
                                                    a4.setImageResource(R.drawable.star_circle_outlinex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.yellow)));
                                                } else if (fs < 100) {
                                                    a4.setImageResource(R.drawable.star_circle_outlinex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.blue)));
                                                } else if (fs < 500) {
                                                    a4.setImageResource(R.drawable.star_circle_outlinex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.turq)));
                                                } else if (fs < 1000) {
                                                    a4.setImageResource(R.drawable.star_circle_outlinex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.purple)));
                                                } else if (fs < 5000) {
                                                    a4.setImageResource(R.drawable.star_circle_outlinex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)));
                                                } else if (fs < 10000) {
                                                    a4.setImageResource(R.drawable.star_circle_outlinex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.green)));
                                                } else if (fs < 25000) {
                                                    a4.setImageResource(R.drawable.star_circlex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.yellow)));
                                                } else if (fs < 50000) {
                                                    a4.setImageResource(R.drawable.star_circlex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.turq)));
                                                } else if (fs < 100000) {
                                                    a4.setImageResource(R.drawable.star_circlex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.purple)));
                                                } else if (fs < 500000) {
                                                    a4.setImageResource(R.drawable.star_circlex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)));
                                                } else if (fs < 1000000) {
                                                    a4.setImageResource(R.drawable.star_circlex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.green)));
                                                } else {
                                                    a4.setImageResource(R.drawable.star_circlex);
                                                    ImageViewCompat.setImageTintList(a4, ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.silver)));
                                                }

                                                try {
                                                    String percS = item.getJSONObject("Seller").getString("PositiveFeedbackPercent");
                                                    Double percD = Double.parseDouble(percS);
                                                    int percI = percD.intValue();

                                                    a3.setScore(percI);
                                                    if (percI > 80)
                                                        a3.setPrimaryColor(Color.parseColor("#388E3C"));
                                                    else if (percI > 60)
                                                        a3.setPrimaryColor(Color.parseColor("#FBC02D"));
                                                    else if (percI > 40)
                                                        a3.setPrimaryColor(Color.parseColor("#F57C00"));
                                                    else
                                                        a3.setPrimaryColor(Color.parseColor("#d32f2f"));
                                                } catch (Exception e) {
                                                    al3.setVisibility(View.GONE);
                                                    f1++;
                                                }
                                            } catch (Exception e9) {
                                                al2.setVisibility(View.GONE);
                                                al4.setVisibility(View.GONE);
                                                f1 += 2;
                                            }

                                            if (f1 == 4)
                                                m1.setVisibility(View.GONE);

                                            bl1 = findViewById(R.id.shcostL);
                                            b1 = findViewById(R.id.shipCostS);
                                            String tzx = getIntent().getStringExtra("SHIP");
                                            if (tzx.equals("Free Shipping")) {
                                                b1.setText(tzx);
                                            } else if (tzx.equals("N/A")) {
                                                bl1.setVisibility(View.GONE);
                                                f2++;
                                            } else {
                                                tzx = tzx.substring(tzx.indexOf(":") + 2);
                                                b1.setText(tzx);
                                            }

                                            bl2 = findViewById(R.id.glL);
                                            try {
                                                b2 = findViewById(R.id.globalS);
                                                String tzax = item.getString("GlobalShipping");
                                                if (tzax.equals("false"))
                                                    b2.setText("No");
                                                else
                                                    b2.setText("Yes");
                                            } catch (Exception e10) {
                                                bl2.setVisibility(View.GONE);
                                                f2++;
                                            }

                                            bl3 = findViewById(R.id.htL);
                                            try {
                                                b3 = findViewById(R.id.handleS);
                                                int time = item.getInt("HandlingTime");
                                                if (time != 1)
                                                    b3.setText(time + " days");
                                                else
                                                    b3.setText(time + " day");
                                            } catch (Exception e11) {
                                                bl3.setVisibility(View.GONE);
                                                f2++;
                                            }

                                            bl4 = findViewById(R.id.condL);
                                            try {
                                                b4 = findViewById(R.id.condS);
                                                b4.setText(item.getString("ConditionDisplayName"));
                                            } catch (Exception e12) {
                                                bl4.setVisibility(View.GONE);
                                                f2++;
                                            }

                                            if (f2 == 4)
                                                m2.setVisibility(View.GONE);

                                            cl1 = findViewById(R.id.polL);
                                            try {
                                                c1 = findViewById(R.id.policyS);
                                                c1.setText(item.getJSONObject("ReturnPolicy").getString("ReturnsAccepted"));
                                            } catch (Exception e13) {
                                                cl1.setVisibility(View.GONE);
                                                f3++;
                                            }

                                            cl2 = findViewById(R.id.retL);
                                            try {
                                                c2 = findViewById(R.id.retWithinS);
                                                c2.setText(item.getJSONObject("ReturnPolicy").getString("ReturnsWithin"));
                                            } catch (Exception e14) {
                                                cl2.setVisibility(View.GONE);
                                                f3++;
                                            }

                                            cl3 = findViewById(R.id.refL);
                                            try {
                                                c3 = findViewById(R.id.refModeS);
                                                c3.setText(item.getJSONObject("ReturnPolicy").getString("Refund"));
                                            } catch (Exception e14) {
                                                cl3.setVisibility(View.GONE);
                                                f3++;
                                            }

                                            cl4 = findViewById(R.id.shbL);
                                            try {
                                                c4 = findViewById(R.id.shipByS);
                                                c4.setText(item.getJSONObject("ReturnPolicy").getString("ShippingCostPaidBy"));
                                            } catch (Exception e14) {
                                                cl4.setVisibility(View.GONE);
                                                f3++;
                                            }

                                            if (f3 == 4)
                                                m3.setVisibility(View.GONE);

                                            if (f1 == 4 && f2 == 4 && f3 == 4)
                                                findViewById(R.id.noRecordsP2).setVisibility(View.VISIBLE);
                                            else
                                                findViewById(R.id.noRecordsP2).setVisibility(View.GONE);

                                        } catch (Exception e6) {
                                            LinearLayout ent = findViewById(R.id.entireShip);
                                            ent.setVisibility(View.GONE);
                                        }
                                    } catch (Exception e) {
                                        Log.d("JSONOBJ: ", "You messed up buddy!");
                                    }
                                } else {

                                }
                        } catch (Exception e) {

                            }
                    }}, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), "Object does not exist", Toast.LENGTH_SHORT).show();
                    Log.d("Error2: ", error.toString());
                }
            }) { //no semicolon or coma
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return params;
                }

                @Override
                public byte[] getBody() {
                    try {
                        Log.d("inGetBody: ", requestBody);
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        Log.d("ERROR:", "You're screwed bud");
                        return null;
                    }
                }
            };
            queue.add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_specific_details, menu);
        getMenuInflater().inflate(R.menu.menu_specific_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String hashtag = "#";
        try {
            hashtag = URLEncoder.encode("#", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://www.facebook.com/dialog/share?app_id=641157699674175&display=popup&href=";
        String url2 = "&redirect_uri=&hashtag=" + hashtag + "CSCI571Spring2019Ebay&quote=";

        Log.d("ROOT", facebookURL);
        String fburl = null;
        try {
            fburl = url + URLEncoder.encode(facebookURL, "utf-8") + url2;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fbTitle = URLEncoder.encode(fbTitle,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fbTitle = fbTitle.replace("+", "%20");
        Log.d("FBTITLE", fbTitle);
        String i1 = "Buy%20", i2 = "%20for%20%24", i3 = "%20from%20Ebay%21";
        fburl += i1 + fbTitle + i2 + fbPrice + i3;

        Log.d("FBAPI:", fburl);

        Log.d("FB", "TEST");
        if (id == R.id.fb_icon) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fburl));
            startActivity(browserIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch(position) {
                case 0:
                    f = new ProductFragment();
                    break;
                case 1:
                    f = new ShipFragment();
                    break;
                case 2:
                    f = new PhotoFragment();
                    break;
                case 3:
                    f = new SimilarFragment();
                    break;
                default:
                    f = new Fragment();
            }
            return f;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
