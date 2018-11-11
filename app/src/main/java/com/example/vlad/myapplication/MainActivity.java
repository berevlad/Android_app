package com.example.vlad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vlad.myapplication.CharDir.CharInfo;
import com.example.vlad.myapplication.RealmDir.RealmStatus;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Button realm_status = findViewById(R.id.realm_status);
        Button buton_cauta = findViewById(R.id.buton_cauta);
        Button button_setting = findViewById(R.id.buton_setting);

        realm_status.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),RealmStatus.class);
                startActivity(i);
                
            }
        });

        button_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Settings.class);
                startActivity(i);

            }
        });

        buton_cauta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText text_char = findViewById(R.id.text_char);
                EditText text_realm = findViewById(R.id.text_realm);
                Intent i = new Intent(getApplicationContext(),CharInfo.class);
                String textc = text_char.getText().toString();
                String textr = text_realm.getText().toString();

                i.putExtra("char", text_char.getText().toString());
                i.putExtra("realm", text_realm.getText().toString());
                if(TextUtils.isEmpty(textc) || TextUtils.isEmpty(textr))
                    Toast.makeText(MainActivity.this, "Field/s are empty!", Toast.LENGTH_LONG).show();
                else startActivity(i);

            }
        });
    }


}
