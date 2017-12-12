package com.example.natepowers.photoframeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test" ;
    public ImageView imageView;
    public File directory;
    public File[] files;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "TestImages");

        files = directory.listFiles();
        Bitmap myBitmap = BitmapFactory.decodeFile(files[files.length-1].getAbsolutePath());
        Log.e(TAG, "run: " );

        imageView.setImageBitmap(myBitmap);

        handler.postDelayed(changeImage, 5000); // after every 5secs it will change image.

    }

    private final Handler handler = new Handler();

    public int count = 0;


        final Runnable changeImage = new Runnable() {
            public void run() {
                if (count >6) {
                    handler.removeCallbacks(changeImage);
                } else {

                    if (count >= 6)
                        count = 0;

                    AlphaAnimation animation1 = new AlphaAnimation(0.5f, 1.0f); //here is a bit of animation for ya ;)
                    animation1.setDuration(500);
                    animation1.setFillAfter(true);
                    imageView.startAnimation(animation1);
                    Bitmap myBitmap = BitmapFactory.decodeFile(files[count++].getAbsolutePath());
                    Log.e(TAG, "run: " );

                    imageView.setImageBitmap(myBitmap);
                    handler.postDelayed(changeImage, 5000);
                }
            }
        };

}
