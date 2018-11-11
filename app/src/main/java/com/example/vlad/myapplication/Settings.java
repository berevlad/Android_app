package com.example.vlad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vlad.myapplication.CharDir.CharInfo;

public class Settings extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button buton_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addListenerOnButton();

    }
    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radiobuton);
        buton_confirm = (Button) findViewById(R.id.buton_confirm);

        buton_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(Settings.this,"Zone changed to "+
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),CharInfo.class);
                i.putExtra("zone", radioButton.getText().toString());
            }

        });

    }
}

