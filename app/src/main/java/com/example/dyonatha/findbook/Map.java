package com.example.dyonatha.findbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by dyonatha on 29/11/17.
 */

public class Map extends AppCompatActivity{

    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        imageView = (ImageView) findViewById(R.id.IMAGEID); //activity_map tem um src com a imagem, tirar
        // aquilo e pegar a imagem do servidor...

        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);

        photoView.update();

    }

}
