package com.erdioran.daybook.OtherActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.erdioran.daybook.R;

import java.util.ArrayList;

public class OtherActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayList<String> menuItem = new ArrayList<>();
    public Context context=this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);


        menuItem.add("Ayarlar");
        menuItem.add("Bize Ulaşın");
        menuItem.add("Hakkımızda");
        menuItem.add("Çıkış");

        listView = findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, menuItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        try {
                            Intent intent1 = new Intent(context,SettingsActivity.class);
                            startActivity(intent1);
                        } catch (Exception e) {

                        }
                        break;
                    case 1:
                        try {
                            Intent intent2 = new Intent(context,ContactUsActivity.class);
                            startActivity(intent2);

                        } catch (Exception e) {

                        }
                        break;
                    case 2:
                        try {
                            Intent intent3 = new Intent(context,AboutActivity.class);
                            startActivity(intent3);

                        } catch (Exception e) {

                        }
                        break;

                }
            }
        });
    }
}
