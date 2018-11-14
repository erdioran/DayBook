package com.erdioran.daybook.OtherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.erdioran.daybook.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_activity);


        // Keyboard otomatik açık gelmesin
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final EditText your_name = findViewById(R.id.contact_your_name);
        final EditText your_email = findViewById(R.id.contact_your_email);
        final EditText your_subject = findViewById(R.id.contact_your_subject);
        final EditText your_message = findViewById(R.id.contact_your_message);

        Button postButton = findViewById(R.id.post_message);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = your_name.getText().toString();
                String email = your_email.getText().toString();
                String subject = your_subject.getText().toString();
                String message = your_message.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    your_name.setError("Adınızı Girin");
                    your_name.requestFocus();
                    return;
                }
                Boolean onError = false;
                if (!isValidEmail(email)) {
                    onError = true;
                    your_email.setError("Email Adresi Girin");
                    return;

                }
                if (TextUtils.isEmpty(subject)) {
                    your_subject.setError("Konu Girin");
                    your_subject.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(message)) {
                    your_message.setError("Mesajınızı Girin");
                    your_message.requestFocus();
                    return;
                }
                Intent sendEmail=new Intent(android.content.Intent.ACTION_SEND);

                sendEmail.setType("text/plain");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{"erdioran@gmail.com"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,"ad: "+name+'\n'+"Email: "+email+'\n'+"Mesaj: "+'\n'+message);

                startActivityForResult(Intent.createChooser(sendEmail,"Mesajın gönderildi! "),1);

            }
        });
    }


    public void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
