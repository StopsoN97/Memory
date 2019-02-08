package com.example.memorygame;


import android.content.Context;

public class PictureActivity {
    private final Context context;

        public PictureActivity(Context context){
            this.context = context;
        }

        public int getAmount(){
            return 8;
        }

        public Object getItem(int position){
            return null;
        }

        public long getIdemId(int position){
            return 0;
        }
}
