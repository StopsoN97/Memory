package com.example.memorygame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;


public class MemoryActivity extends AppCompatActivity {

    private static final int PHOTO_WIDTH = 300;
    private static final int PHOTO_HEIGHT = 300;

    private ArrayList<Bitmap> photoBitmaps;

    private int[] position = {0, 1, 2, 3, 0, 1, 2, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        Intent intent = getIntent();
        ArrayList<String> photoFilePaths = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);
        photoBitmaps = listToBitmap(photoFilePaths);
        mixArray(position);
    }

    private ArrayList<Bitmap> listToBitmap(ArrayList<String> photoFilePaths) {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for (String photo : photoFilePaths) {
            bitmaps.add(photoToBitmap(photo));
        }
        return bitmaps;
    }

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


