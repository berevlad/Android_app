package com.example.vlad.myapplication.MountDir;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vlad.myapplication.R;
import com.google.gson.JsonObject;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class MountStatus extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private MountAdapter mountAdapter;
    private ArrayList<Mount> mountArrayList;
    private RequestQueue mRequestQueue;
    private MountDB myDb = new MountDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mount_status);

        mRecycleView = findViewById(R.id.recycler_view);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        boolean mobileDataAllowed = Settings.Secure.getInt(getContentResolver(), "mobile_data", 1) == 1;

        if(mobileDataAllowed == true) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(IOUtils.toString(new URL("https://eu.api.battle.net/wow/mount/?locale=en_GB&apikey=3g8wdwqrzz3ntwabfe3ween4x6ra87aa\n"), Charset.forName("UTF-8")));


                try {
                    JSONArray mounts = jsonObject.getJSONArray("mounts");
                    for (int i = 0; i < mounts.length(); i++) {
                        JSONObject list = mounts.getJSONObject(i);
                        String name = list.getString("name");

                        myDb.insertData(name);
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
        myDb = new MountDB(this);
        mountAdapter = new MountAdapter(myDb.getAllData(), this, mRecycleView);
        mRecycleView.setAdapter(mountAdapter);
    }



}
