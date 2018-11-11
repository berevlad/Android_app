package com.example.vlad.myapplication.TitleDir;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.service.quicksettings.Tile;
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


public class TitleStatus extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private TitleAdapter titleAdapter;
    private ArrayList<Tile> tileArrayList;
    private RequestQueue mRequestQueue;
    private TitleDB myDb = new TitleDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_status);

        mRecycleView = findViewById(R.id.recycler_view);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        boolean mobileDataAllowed = Settings.Secure.getInt(getContentResolver(), "mobile_data", 1) == 1;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if(mobileDataAllowed == true) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(IOUtils.toString(new URL("https://eu.api.battle.net/wow/character/draenor/Wishcraft?fields=titles&locale=en_GB&apikey=3g8wdwqrzz3ntwabfe3ween4x6ra87aa"), Charset.forName("UTF-8")));


                JSONArray jsonArray = jsonObject.getJSONArray("titles");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject title = jsonArray.getJSONObject(i);


                    String name = title.getString("name");

                    myDb.insertData(name);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myDb = new TitleDB(this);
        titleAdapter = new TitleAdapter(myDb.getAllData(), this, mRecycleView);
        mRecycleView.setAdapter(titleAdapter);

    }
}