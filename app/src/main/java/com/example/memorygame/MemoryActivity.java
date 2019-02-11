package com.example.memorygame;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MemoryActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_memory);


        }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClickComeBack(View view) {
            this.finishAndRemoveTask();
    }
}


