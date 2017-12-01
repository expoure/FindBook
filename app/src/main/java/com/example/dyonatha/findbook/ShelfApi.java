package com.example.dyonatha.findbook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by will on 12/1/17.
 */

public class ShelfApi {
    private static final String ENDPOINT = "http://nsoudouglas.ga:3000/shelf/find/";

    public static Bitmap getShelf(String nChamada) {

        String url = ENDPOINT + nChamada;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(url).build();

        Bitmap bitmap = null;
        try {
            Response response = client.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
