package com.example.productsearch;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPage extends AppCompatActivity {

    String key, dist, zip, ack, details;
    boolean n, u, x, f, l, ensb, btx, btn;
    int spx, count;
    ProgressBar pr;
    TextView tx, rx;

    JSONArray main, items;

    TextView tn;
    RecyclerView rv;
    ItemAdapter adap;

    List<ProductItems> acList = new ArrayList<>();;

    final static String TAG = "ProductPage: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        setTitle("Search Results");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pr = findViewById(R.id.progressBar);
        tx = findViewById(R.id.searchText);
        rx = findViewById(R.id.detailsText);
        pr.setVisibility(View.VISIBLE);
        tx.setVisibility(View.VISIBLE);
        rx.setVisibility(View.GONE);
        rv = findViewById(R.id.rec_view);
        rv.setVisibility(View.GONE);
        tn = findViewById(R.id.no_records);
        tn.setVisibility(View.GONE);

        try {
            generateURL();
        } catch (Exception e) {
            Log.d("ERROR:", "Can't get to server");
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void generateURL() throws UnsupportedEncodingException {
        key = getIntent().getStringExtra("KEY");
        dist = getIntent().getStringExtra("DIST");
        zip = getIntent().getStringExtra("ZIP");;
        spx = getIntent().getIntExtra("CAT", 0);
        n = getIntent().getBooleanExtra("NEW", false);
        u = getIntent().getBooleanExtra("USED", false);
        x = getIntent().getBooleanExtra("UNS", false);
        f = getIntent().getBooleanExtra("FREE", false);
        l = getIntent().getBooleanExtra("LOC", false);
        ensb = getIntent().getBooleanExtra("ENS", false);
        btx = getIntent().getBooleanExtra("CUR", true);
        btn = getIntent().getBooleanExtra("CUS", false);

        String mainURL1 = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=";
        String mainURL2 = "RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&paginationInput.entriesPerPage=50&keywords=";
        String t1 = URLEncoder.encode(key, "UTF-8");
        mainURL2 += t1 + "&";
        String catValue;
        switch(spx) {
            case 1:
                catValue = "550";
                break;
            case 2:
                catValue = "2984";
                break;
            case 3:
                catValue = "267";
                break;
            case 4:
                catValue = "11450";
                break;
            case 5:
                catValue = "58058";
                break;
            case 6:
                catValue = "26395";
                break;
            case 7:
                catValue = "11233";
                break;
            case 8:
                catValue = "1249";
                break;
            default:
                catValue = "0";
        }
        if (catValue != "0")
            mainURL2 += "categoryId=" + catValue + "&";

        int i = 0, j = 0;

        if (ensb) {
            if (btx) {
                mainURL2 += "buyerPostalCode=90089&";
            } else {
                mainURL2 += "buyerPostalCode=" + zip + "&";
            }

            if (dist.isEmpty())
                mainURL2 += "itemFilter(" + i + ").name=MaxDistance&itemFilter(" + i + ").value=10&";
            else
                mainURL2 += "itemFilter(" + i + ").name=MaxDistance&itemFilter(" + i + ").value=" + dist + "&";
            i++;
        }

        if (n || u || x) {
            mainURL2 += "itemFilter(" + i + ").name=Condition&";
            if (n) {
                mainURL2 += "itemFilter(" + i + ").value(" + j + ")=New&";
                j += 1;
            }
            if (u) {
                mainURL2 += "itemFilter(" + i + ").value(" + j + ")=Used&";
                j += 1;
            }
            if (x) {
                mainURL2 += "itemFilter(" + i + ").value(" + j + ")=Unspecified&";
                j += 1;
            }
            i++;
        }

        if (f) {
            mainURL2 += "itemFilter(" + i + ").name=FreeShippingOnly&itemFilter(" + i + ").value=true&";
            i++;
        }
        if (l) {
            mainURL2 += "itemFilter(" + i + ").name=LocalPickupOnly&itemFilter(" + i + ").value=true&";
            i++;
        }
        mainURL2 += "itemFilter(" + i + ").name=HideDuplicateItems&itemFilter(" + i + ").value=true";
        mainURL2 += "&itemFilter(" + i + ").value=true&outputSelector(0)=SellerInfo&outputSelector(1)=StoreInfo";
        Log.d(TAG, "URL: " + mainURL1 + mainURL2);

//        http://newnodejs.dfxrbpp2hp.us-east-2.elasticbeanstalk.com/
//        String acURL ="http://10.0.2.2:8081/ebaySearchForm?init="+ URLEncoder.encode(mainURL1, "UTF-8") + "&end=" + URLEncoder.encode(mainURL2, "UTF-8");
        String acURL ="http://newnodejs.dfxrbpp2hp.us-east-2.elasticbeanstalk.com/ebaySearchForm?init="+ URLEncoder.encode(mainURL1, "UTF-8") + "&end=" + URLEncoder.encode(mainURL2, "UTF-8");
        String jsURL = "http://10.0.2.2:8081/ebaySearchForm";

        RequestQueue queue = Volley.newRequestQueue(this);

        final String m1 = mainURL1;
        final String m2 = mainURL2;
        try {
            JSONObject jsMap = new JSONObject();
            jsMap.put("init", mainURL1);
            jsMap.put("end", mainURL2);
            final String requestBody = jsMap.toString();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, acURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                pr.setVisibility(View.GONE);
                                tx.setVisibility(View.GONE);
                                try {
                                    main = response.getJSONArray("findItemsAdvancedResponse");
                                    ack = main.getJSONObject(0).getJSONArray("ack").getString(0);
                                    count = main.getJSONObject(0).getJSONArray("searchResult").getJSONObject(0).
                                            getJSONArray("item").length();
                                    items = main.getJSONObject(0).getJSONArray("searchResult").getJSONObject(0).
                                            getJSONArray("item");
                                    details = "Showing " + count + " results for " + key;
                                    SpannableString editDetails = new SpannableString(details);
                                    editDetails.setSpan(new ForegroundColorSpan(Color.RED), 8, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                                    editDetails.setSpan(new ForegroundColorSpan(Color.RED), details.length() - key.length(), details.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                    rx.setText(editDetails);
                                    rx.setVisibility(View.VISIBLE);

                                    Log.d(TAG, items.toString());

                                    String a, b, c, d, e, f, g;
                                    Log.d("ITEMYO", items.toString());
                                    for (int i = 0; i < count; i++) {
                                        try {
//                                            Log.d("ITEMID", items.getJSONObject(i).getJSONArray("itemId").get(0).toString());
                                            a = items.getJSONObject(i).getJSONArray("itemId").get(0).toString();
                                        } catch (Exception e1) {
//                                            Log.d("ITEMID", "N/A");
                                            a = "N/A";
                                        }
                                        try {
//                                            Log.d("TITLE", items.getJSONObject(i).getJSONArray("title").get(0).toString());
                                            b = items.getJSONObject(i).getJSONArray("title").get(0).toString();
                                        } catch (Exception e2) {
//                                            Log.d("TITLE", "N/A");
                                            b = "N/A";
                                        }
                                        try {
//                                            Log.d("GALLERY", items.getJSONObject(i).getJSONArray("galleryURL").get(0).toString());
                                            c = items.getJSONObject(i).getJSONArray("galleryURL").get(0).toString();
                                        } catch (Exception e3) {
//                                            Log.d("GALLERY", "N/A");
                                            c = "N/A";
                                        }
                                        try {
//                                            Log.d("ZIP", items.getJSONObject(i).getJSONArray("postalCode").get(0).toString());
                                            d = "Zipcode: " + items.getJSONObject(i).getJSONArray("postalCode").get(0).toString();
                                        } catch (Exception e4) {
//                                            Log.d("ZIP", "N/A");
                                            d = "Zipcode: N/A";
                                        }
                                        try {
//                                            Log.d("SHIPPING", items.getJSONObject(i).getJSONArray("shippingInfo").
//                                                    getJSONObject(0).getJSONArray("shippingServiceCost").getJSONObject(0).
//                                                    getString("__value__"));
                                            if (items.getJSONObject(i).getJSONArray("shippingInfo").
                                                    getJSONObject(0).getJSONArray("shippingServiceCost").getJSONObject(0).
                                                    getString("__value__").equals("0.0"))
                                                e = "Free Shipping";
                                            else
                                                e = "Shipping Cost: $" + items.getJSONObject(i).getJSONArray("shippingInfo").
                                                    getJSONObject(0).getJSONArray("shippingServiceCost").getJSONObject(0).
                                                    getString("__value__");
                                        } catch (Exception e5) {
//                                            Log.d("SHIPPING", "N/A");
                                            e = "N/A";
                                        }
                                        try {
//                                            Log.d("CONDITION", items.getJSONObject(i).getJSONArray("condition").getJSONObject(0).
//                                                    getJSONArray("conditionDisplayName").get(0).toString());
                                            f = items.getJSONObject(i).getJSONArray("condition").getJSONObject(0).
                                                    getJSONArray("conditionDisplayName").get(0).toString();
                                        } catch (Exception e6) {
//                                            Log.d("CONDITION", "N/A");
                                            f = "N/A";
                                        }
                                        try {
//                                            Log.d("PRICE", items.getJSONObject(i).getJSONArray("sellingStatus").
//                                                    getJSONObject(0).getJSONArray("currentPrice").getJSONObject(0).
//                                                    getString("__value__"));
                                            g = "$" + items.getJSONObject(i).getJSONArray("sellingStatus").
                                                    getJSONObject(0).getJSONArray("currentPrice").getJSONObject(0).
                                                    getString("__value__");
                                        } catch (Exception e7) {
//                                            Log.d("PRICE", "N/A");
                                            g = "$N/A";
                                        }

                                        acList.add(new ProductItems(items.getJSONObject(i), a, b, c, d, e, f, g, key));
                                    }

                                    rv = findViewById(R.id.rec_view);
                                    rv.setVisibility(View.VISIBLE);
                                    adap = new ItemAdapter(getApplicationContext(), acList);
                                    rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                                    rv.setAdapter(adap);
                                    adap.notifyDataSetChanged();
                                } catch (Exception e) {
                                    Log.d(TAG, "No Records");
                                    rv = findViewById(R.id.rec_view);
                                    rv.setVisibility(View.GONE);
                                    tn = findViewById(R.id.no_records);
                                    tn.setVisibility(View.VISIBLE);
                                    Log.d(TAG, "oh yeah man");
                                }
                                Log.d(TAG, "onResponse: " + response.toString());

                            } catch (Exception e) {
                                Log.d(TAG, "Error1: " + e.getMessage() + e.toString());
                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Make sure a connection is present", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        Log.d("TERMINATOR", "I'M BACK");

        RecyclerView rcx = findViewById(R.id.rec_view);
        rcx.setVisibility(View.VISIBLE);
        ItemAdapter asx = new ItemAdapter(getApplicationContext(), acList);
        rcx.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rcx.setAdapter(asx);

        Log.d("FAST", "BUT FURIOUS");
    }
}
