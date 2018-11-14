package com.erdioran.daybook.OtherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.erdioran.daybook.R;
import com.erdioran.daybook.StartLogin.Utils;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);


        social();
    }


    protected void social() {
        CircleImageView fb = findViewById(R.id.imageBtn_soc1);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(Utils.fb);
                    startActivity(intent1);
                } catch (Exception e) {
                }
            }
        });
        CircleImageView tw = findViewById(R.id.imageBtn_soc2);
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent2 = new Intent(Utils.tw);
                    startActivity(intent2);
                } catch (Exception e) {
                }
            }
        });
        CircleImageView ins = findViewById(R.id.imageBtn_soc3);
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent3 = new Intent(Utils.ins);
                    startActivity(intent3);
                } catch (Exception e) {
                }
            }
        });
        CircleImageView ln = findViewById(R.id.imageBtn_soc4);
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent4 = new Intent(Utils.ln);
                    startActivity(intent4);
                } catch (Exception e) {
                }
            }
        });
        CircleImageView gh = findViewById(R.id.imageBtn_soc5);
        gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent5 = new Intent(Utils.gh);
                    startActivity(intent5);
                } catch (Exception e) {
                }
            }
        });
    }
}
