package com.erdioran.daybook.StartLogin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erdioran.daybook.MainActivity;
import com.erdioran.daybook.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextUserName, editTextUserPassword;
    private Button buttonLogin,buttonRegister;
    private TextView txtUser, txtEmail;
    private ImageView imgProfile;
    private String userName, userPassword;
    private LoginButton loginButton;

    private CallbackManager callbackManager;

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextUserPassword = (EditText) findViewById(R.id.editTextUserPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser(); // authenticated user

        if (firebaseUser != null) { // check user session

            Intent i = new Intent(StartActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = editTextUserName.getText().toString();
                userPassword = editTextUserPassword.getText().toString();
                if (userName.isEmpty() || userPassword.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Lütfen gerekli alanları doldurunuz!", Toast.LENGTH_SHORT).show();

                } else {

                    loginFunc();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "dentest.erdioran.com.dentest",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);



        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.btnFacebookIn);


        loginButton.setReadPermissions("email", "public_profile");//user_status, publish_actions..
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(StartActivity.this, "Sign In canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(StartActivity.this, "Something bad happened", Toast.LENGTH_SHORT).show();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (AccessToken.getCurrentAccessToken() != null)
                    Toast.makeText(StartActivity.this, AccessToken.getCurrentAccessToken().getExpires().toString(), Toast.LENGTH_SHORT).show();
                if (user != null) {
                    String email = user.getEmail();
                    String userName = user.getDisplayName();
                    txtEmail.setText(email);
                    txtUser.setText(userName);
                    Picasso.with(StartActivity.this).load(user.getPhotoUrl()).into(imgProfile);
                    loginButton.setVisibility(View.GONE);

                } else {
                    Log.d("TG", "SIGNED OUT");
                    //             txtEmail.setText("");
                    //          txtUser.setText("");
                    //        imgProfile.setImageBitmap(null);
                    loginButton.setVisibility(View.VISIBLE);

                }
            }
        };


    }

    private void loginFunc() {

        mAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(StartActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent i = new Intent(StartActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            // hata
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        Log.d(TAG, "ZZZZZZZ: " + accessToken.getToken());
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(StartActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        Intent intent1 = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,
                resultCode, data);
    }

    @Override
    public void onClick(View v) {
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();
    }
}
