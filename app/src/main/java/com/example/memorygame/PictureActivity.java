package com.example.memorygame;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public  class PictureActivity extends BaseAdapter {
    private final Context context;

        public PictureActivity(Context context){
            this.context = context;
        }

        public int getCount(){
            return 8;
        }

        public Object getItem(int position){
            return null;
        }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(R.drawable.ic_photo_black_24dp);
        return imageView;
    }
}
