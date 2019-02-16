package com.example.memorygame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

    public class MainActivity extends AppCompatActivity {

        private static final int REQUEST_IMAGE_CAPTURE = 1;
        private static final int REQUEST_TAKE_PHOTO = 1;

        public static final String EXTRA_MESSAGE = "com.example.memorygame.MESSAGE.PLAY";

        private String mCurrentPhotoPath;
        private ArrayList<String> photosPathsList;
        private ArrayList<Bitmap> photosBitmaps;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            photosPathsList = new ArrayList<>();
            photosBitmaps = new ArrayList<>();

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                photosPathsList.add(mCurrentPhotoPath);
            }
        }

        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(MainActivity.this, " Error occurred while creating the File...", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }

        private File createImageFile() throws IOException {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imageFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = imageFile.getAbsolutePath();
            return imageFile;
        }

        protected void onClickButtonTakePhoto(View view) {
            dispatchTakePictureIntent();
        }

        protected void onClickPlay(View view) {
            if (photosPathsList.size() == 4) {
                Intent intent = new Intent(this, MemoryActivity.class);
                intent.putStringArrayListExtra(EXTRA_MESSAGE, photosPathsList);
                startActivity(intent);
            } else {
                alert();
            }
        }

        private void alert() {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("INCORRECT AMOUNT OF PHOTOS")
                    .setMessage("You need 4 photos to play.")
                    .show();
        }

    }