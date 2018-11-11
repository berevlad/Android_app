package com.example.vlad.myapplication.RealmDir;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.vlad.myapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class RealmStatus extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private RealmAdapter mRealmAdapter;
    private ArrayList<Realm> RealmList;
    private RequestQueue mRequestQueue;
    private RealmDB myDb = new RealmDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realm_status);

        mRecycleView = findViewById(R.id.recycler_view);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        boolean mobileDataAllowed = Settings.Secure.getInt(getContentResolver(), "mobile_data", 1) == 1;

        if(mobileDataAllowed == true) {
            JSONObject jsonObject = null;
            try {

                jsonObject = new JSONObject(IOUtils.toString(new URL("https://us.api.battle.net/wow/realm/status?locale=en_GB&apikey=3g8wdwqrzz3ntwabfe3ween4x6ra87aa"), Charset.forName("UTF-8")));


                JSONArray jsonArray = null;
                try {
                    jsonArray = jsonObject.getJSONArray("realms");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject realm = jsonArray.getJSONObject(i);
                        String name = realm.getString("name");
                        String type = realm.getString("type");
                        myDb.insertData(name, type);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myDb = new RealmDB(this);
        mRealmAdapter = new RealmAdapter(myDb.getAllData(), this, mRecycleView);
        mRecycleView.setAdapter(mRealmAdapter);

    }



    }



