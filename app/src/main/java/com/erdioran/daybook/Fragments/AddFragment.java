package com.erdioran.daybook.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.erdioran.daybook.Maps.TrackingActivity;
import com.erdioran.daybook.OCRActivity;
import com.erdioran.daybook.R;

public class AddFragment extends Fragment {

    private View v;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_fragment, container, false);

        click();


        return v;
    }


    protected void click() {

        ImageButton btn = v.findViewById(R.id.imageButton1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getActivity(), TrackingActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                }

            }
        });


        ImageButton btn1 = v.findViewById(R.id.imageButton2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                } catch (Exception e) {
                }

            }
        });

        ImageButton btn2 = v.findViewById(R.id.imageButton3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getActivity(), OCRActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                }

            }
        });


    }
}
