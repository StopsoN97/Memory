package com.example.memorygame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MemoryActivity extends AppCompatActivity {

    private static final int PHOTO_WIDTH = 150;
    private static final int PHOTO_HEIGHT = 150;

    private ArrayList<Bitmap> photoBitmaps;
    private ImageView currentView = null;
    private int countDoubles = 0;
    private int[] position = {0, 1, 2, 3, 0, 1, 2, 3};
    private int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        Intent intent = getIntent();
        ArrayList<String> photoFilePaths = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);
        photoBitmaps = listToBitmap(photoFilePaths);
        mixArray(position);

        PictureAdapter picAdapter = new PictureAdapter(this);
        GridView gridView = findViewById(R.id.gridviewAB);
        gridView.setAdapter(picAdapter);
        gridView.setOnItemClickListener(gridViewOnItemClickListener);
    }

    private ArrayList<Bitmap> listToBitmap(ArrayList<String> photoFilePaths) {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for (String photo : photoFilePaths) {
            bitmaps.add(photoToBitmap(photo));
        }
        return bitmaps;
    }

    private AdapterView.OnItemClickListener gridViewOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (currentPosition < 0) {
                currentPosition = position;
                currentView = (ImageView) view;
                Bitmap bitmap = photoBitmaps.get(MemoryActivity.this.position[position]);
                ((ImageView) view).setImageBitmap(bitmap);

            } else {
                if (currentPosition == position) {
                    ((ImageView) view).setImageResource(R.drawable.logo);
                } else if (MemoryActivity.this.position[currentPosition] != MemoryActivity.this.position[position]) {
                    Toast.makeText(MemoryActivity.this, "Not Match!", Toast.LENGTH_LONG).show();

                    Bitmap bitmap = photoBitmaps.get(MemoryActivity.this.position[position]);
                    ((ImageView) view).setImageBitmap(bitmap);
                    final View viewHandler = view;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((ImageView) viewHandler).setImageResource(R.drawable.logo);
                            currentView.setImageResource(R.drawable.logo);
                        }
                    }, 800); // Millisecond 1000 = 1 sec

                } else {
                    Bitmap bitmap = photoBitmaps.get(MemoryActivity.this.position[position]);
                    ((ImageView) view).setImageBitmap(bitmap);
                    Toast.makeText(MemoryActivity.this, "Match!", Toast.LENGTH_LONG).show();
                    countDoubles++;
                    if (countDoubles == 4) {
                        Toast.makeText(MemoryActivity.this, "You Win!", Toast.LENGTH_LONG).show();
                    }
                }
                currentPosition = -1;
            }
        }

    };

    private Bitmap photoToBitmap(String mCurrentPhotoPath) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoWidth = bmOptions.outWidth;
        int photoHeight = bmOptions.outHeight;

        int scaleFactor = Math.min(photoWidth / MemoryActivity.PHOTO_WIDTH, photoHeight / MemoryActivity.PHOTO_HEIGHT);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
    }

    private static void mixArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}


