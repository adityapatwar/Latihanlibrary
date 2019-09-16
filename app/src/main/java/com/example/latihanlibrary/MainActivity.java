package com.example.latihanlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
    }

    public void lat1(View view) {
        Intent lat1 = new Intent (this,LibraryZxing.class);
        startActivity (lat1);
    }

    public void lat2(View view) {
        Intent lat2 = new Intent (this,IText_Lat.class);
        startActivity (lat2);
    }
}
