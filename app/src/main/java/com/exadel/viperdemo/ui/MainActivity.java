package com.exadel.viperdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.exadel.viperdemo.R;
import com.exadel.viperdemo.module.goodbye.view.GoodbyeActivity;
import com.exadel.viperdemo.module.welcome.view.WelcomeActivity;

/**
 * Main Activity.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onEnter(View view) {
        startActivity(new Intent(this, WelcomeActivity.class));
    }
    
    public void onExit(View view) {
        startActivity(new Intent(this, GoodbyeActivity.class));
    }
}
