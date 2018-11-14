package com.erdioran.daybook;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.erdioran.daybook.Fragments.AddFragment;
import com.erdioran.daybook.Fragments.HomeFragment;
import com.erdioran.daybook.Fragments.SettingsFragment;
import com.erdioran.daybook.OtherActivities.ContactUsActivity;
import com.erdioran.daybook.OtherActivities.OtherActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private Menu optionsMenu;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(this);


        try {
            PackageInfo info = getPackageManager().getPackageInfo("dentest.erdioran.com.dentest", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        loadFragment(new HomeFragment());

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_add:
                fragment = new AddFragment();
                break;
            case R.id.navigation_third:
                fragment = new SettingsFragment();
                break;
        }

        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void onBackPressed() {
    }


    /** REFRESH **/
    /*

    public void refresh(){
        setRefreshActionButtonState(true);  //Progress bar refresh iconla değişecek
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefreshActionButtonState(false); //3.sn sonra refresh iconu geri gelecek
            }
        },3000);
    }


    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu!=null){
            final MenuItem logoItem=optionsMenu.findItem(R.id.item1);
            if (logoItem!=null){
                if (refreshing){
                    logoItem.setActionView(R.layout.actionbar_refresh_progress);
                }else {
                    logoItem.setActionView(null);
                }
            }
        }
    }

     */
}
