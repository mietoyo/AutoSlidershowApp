package jp.techacademy.mie.toyo.autoslidershowapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                getContentsInfo();
            } else {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
            }

        } else {
            getContentsInfo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContentsInfo();
                }
                break;
            default:
                break;
        }
    }

    private void getContentsInfo() {


        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // データの種類
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {

                int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                Long id = cursor.getLong(fieldIndex);
                Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageURI(imageUri);


                Log.d("ANDROID", "URI : " + imageUri.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
    }



    @Override
public void onCreate(View v){
        Log.d("Android", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Android", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Android", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Android", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Android", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Android", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Android", "onRestart");
    }
}