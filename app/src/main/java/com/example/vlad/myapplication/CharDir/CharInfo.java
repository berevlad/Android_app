package com.example.vlad.myapplication.CharDir;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vlad.myapplication.MountDir.MountStatus;
import com.example.vlad.myapplication.R;
import com.example.vlad.myapplication.TitleDir.TitleStatus;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static com.example.vlad.myapplication.CharDir.CharDB.COL_2;
import static com.example.vlad.myapplication.CharDir.CharDB.COL_3;
import static com.example.vlad.myapplication.CharDir.CharDB.COL_4;
import static com.example.vlad.myapplication.CharDir.CharDB.COL_5;
import static com.example.vlad.myapplication.CharDir.CharDB.COL_6;

public class CharInfo extends AppCompatActivity {

    private String numeJSON;
    private String realmJSON;
    private int clasaJSON;
    private String clasaJSON_string;
    private int factionJSON;
    private String factionJSON_string;
    private int raceJSON;
    private String raceJSON_string;
    public String cheie = "3g8wdwqrzz3ntwabfe3ween4x6ra87aa";
    private CharDB mydb = new CharDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_info);
        Button title_status = findViewById(R.id.buton_title);
        Button mount_status = findViewById(R.id.buton_mount);
        mount_status.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), MountStatus.class);
                startActivity(i);

            }
        });

        title_status.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), TitleStatus.class);
                startActivity(i);

            }
        });



            try {

                TextView nume = findViewById(R.id.nume);
                TextView realm = findViewById(R.id.realm);
                TextView clasa = findViewById(R.id.clasa);
                TextView race = findViewById(R.id.race);
                TextView faction = findViewById(R.id.faction);

                String text_char = getIntent().getStringExtra("char");
                String text_realm = getIntent().getStringExtra("realm");
                String zone = getIntent().getStringExtra("zone");

                //NetworkOnMainThreadException--The exception that is thrown when an application attempts to perform a networking operation on its main thread.

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                boolean mobileDataAllowed = Settings.Secure.getInt(getContentResolver(), "mobile_data", 1) == 1;
                if (mobileDataAllowed == true) {
                    // luam URL si il facem in string sa il putem folosi in JSONObject
                    JSONObject jsonObject = new JSONObject(IOUtils.toString(new URL("https://eu.api.battle.net/wow/character/" + text_realm + "/" + text_char + "?locale=en_" + zone + "&apikey=" + cheie), Charset.forName("UTF-8")));


                    numeJSON = (String) jsonObject.get("name");
                    realmJSON = (String) jsonObject.get("realm");
                    clasaJSON = (int) jsonObject.get("class");
                    factionJSON = (int) jsonObject.get("faction");
                    raceJSON = (int) jsonObject.get("race");


                    //region Clasa translatare
                    if (clasaJSON == 1)
                        clasaJSON_string = "Warrior";
                    else if (clasaJSON == 2)
                        clasaJSON_string = "Paladin";
                    else if (clasaJSON == 3)
                        clasaJSON_string = "Hunter";
                    else if (clasaJSON == 4)
                        clasaJSON_string = "Rogue";
                    else if (clasaJSON == 5)
                        clasaJSON_string = "Priest";
                    else if (clasaJSON == 6)
                        clasaJSON_string = "Death Knight";
                    else if (clasaJSON == 7)
                        clasaJSON_string = "Shaman";
                    else if (clasaJSON == 8)
                        clasaJSON_string = "Mage";
                    else if (clasaJSON == 9)
                        clasaJSON_string = "Warlock";
                    else if (clasaJSON == 10)
                        clasaJSON_string = "Monk";
                    else if (clasaJSON == 11)
                        clasaJSON_string = "Druid";
                    else if (clasaJSON == 12)
                        clasaJSON_string = "Demon Hunter";
                    //endregion

                    //region Race translatare
                    if (raceJSON == 1)
                        raceJSON_string = "Human";
                    else if (raceJSON == 2)
                        raceJSON_string = "Orc";
                    else if (raceJSON == 3)
                        raceJSON_string = "Dwarf";
                    else if (raceJSON == 4)
                        raceJSON_string = "Night Elf";
                    else if (raceJSON == 5)
                        raceJSON_string = "Undead";
                    else if (raceJSON == 6)
                        raceJSON_string = "Tauren";
                    else if (raceJSON == 7)
                        raceJSON_string = "Gnome";
                    else if (raceJSON == 8)
                        raceJSON_string = "Troll";
                    else if (raceJSON == 9)
                        raceJSON_string = "Goblin";
                    else if (raceJSON == 10)
                        raceJSON_string = "Blood Elf";
                    else if (raceJSON == 11)
                        raceJSON_string = "Draenei";
                    else if (raceJSON == 22)
                        raceJSON_string = "Worgen";
                    else if (raceJSON == 24)
                        raceJSON_string = "Pandaren";
                    else if (raceJSON == 25)
                        raceJSON_string = "Pandaren";
                    else if (raceJSON == 26)
                        raceJSON_string = "Pandaren";
                    else if (raceJSON == 27)
                        raceJSON_string = "Nightborne";
                    else if (raceJSON == 28)
                        raceJSON_string = "Highmountain Tauren";
                    else if (raceJSON == 29)
                        raceJSON_string = "Void Elf";
                    else if (raceJSON == 30)
                        raceJSON_string = "Lightforged Draenei";
                    //endregion

                    //region Faction trasnslatare
                    if (factionJSON == 1)
                        factionJSON_string = "Horde";
                    else if (factionJSON == 0)
                        factionJSON_string = "Alliance";
                    //endregion


                    mydb.insertData(numeJSON, realmJSON, clasaJSON_string, raceJSON_string, factionJSON_string);
                }



                Cursor cursor = mydb.getData(numeJSON, realmJSON);
                if (cursor.moveToFirst()) {
                    do {


                        nume.setText("Name: " + cursor.getString(cursor.getColumnIndex(COL_2)));
                        realm.setText("Realm: " + cursor.getString(cursor.getColumnIndex(COL_3)));
                        clasa.setText("Class: " + cursor.getString(cursor.getColumnIndex(COL_4)));
                        race.setText("Race: " + cursor.getString(cursor.getColumnIndex(COL_5)));
                        faction.setText("Faction: " + cursor.getString(cursor.getColumnIndex(COL_6)));

                    } while (cursor.moveToNext());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }



