package com.example.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class GameActivity extends AppCompatActivity {

    private Button buttonPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonPhoto = findViewById(R.id.buttonTakePhotos);

        //ImageView myImageView = (ImageView) findViewById(R.id.imageView6);
        //myImageView.setImageResource(R.drawable.android_background);
    }
}
