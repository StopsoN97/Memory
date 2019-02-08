package com.example.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final ImageView imageView = new ImageView(this);
        //imageView.setImageResource(R.drawable.zygzak);
       // setContentView(imageView);

    }

    protected void onClickButtonTakePhoto(View view) {

    }

    public void onClickStartGame(View v){

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
}
