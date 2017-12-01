package com.example.dyonatha.findbook;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by dyonatha on 29/11/17.
 */

public class MapView extends AppCompatActivity{

    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.IMAGEID); //activity_map tem um src com a imagem, tirar
        // aquilo e pegar a imagem do servidor...

        Bitmap shelf =  MainActivity.getInstance().shelf;
        imageView.setImageBitmap(shelf);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);

        photoView.update();



    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(MapView.this, MainActivity.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
