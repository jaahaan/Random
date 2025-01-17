package com.example.hello.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.cloudinary.android.MediaManager;
import com.example.hello.D.D;
import com.example.hello.MainActivity;
import com.example.hello.R;
import com.example.hello.auth.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

        setContentView(R.layout.activity_splash_screen);
        try {
            initConfig();
        } catch (Exception e) {
            Log.d("Media", String.valueOf(e));
        }
        auth = FirebaseAuth.getInstance();
        new Handler().postDelayed(() -> {
            if(auth.getCurrentUser()!= null && auth.getCurrentUser().isEmailVerified()){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else{
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
            finish();
        }, 1500);
    }

    private void initConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "");
        config.put("api_key", "");
        config.put("api_secret", "");
//        config.put("secure", true);
        MediaManager.init(this, config);
    }
}