package com.erdioran.daybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.erdioran.daybook.OtherActivities.ContactUsActivity;
import com.erdioran.daybook.OtherActivities.OtherActivity;

public class OCRActivity extends AppCompatActivity {



    private static final String TAG = OCRActivity.class.getSimpleName();

    private TextView detectedTextView;

    private Menu optionsMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu=menu;
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actions_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                settings();
                break;
            case R.id.contact:
                contact();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    public void contact() {
        Intent intent = new Intent(getApplicationContext(), ContactUsActivity.class);
        startActivity(intent);
    }

    public void settings() {
        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ocr_activity);


        detectedTextView = (TextView) findViewById(R.id.detected_text);
        detectedTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
