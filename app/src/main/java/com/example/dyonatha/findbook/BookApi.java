package com.example.dyonatha.findbook;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by will on 11/30/17.
 */

public class BookApi {
    private static final String ENDPOINT = "http://nsoudouglas.ga:3000/book/find/";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONArray searcBooks(String bookName) {
        String url = ENDPOINT + bookName;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(url).build();

        String jsonDeResposta = null;
        try {
            Response response = client.newCall(request).execute();
            jsonDeResposta = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = null;
        if (jsonDeResposta != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonDeResposta);
//                JSONObject jsonObject = new JSONObject(jsonDeResposta);

                jsonArray = jsonObject.getJSONArray("jsonResponse");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;
    }

    public static List<Map<String, Object>> convertJsonToListView(JSONArray jsonArray) {

        List<Map<String, Object>> lista = null;
        if (jsonArray != null) {
            lista = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, Object> mapa = new HashMap<>();
                try {
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

                    String obra = jsonObject.getString("obra");
                    int acervo = jsonObject.getInt("acervo");
                    String nChamada = jsonObject.getString("nChamada");
                    int exemplaresDisponiveis = jsonObject.getInt("exemplaresDisponiveis");

                    mapa.put("obra", obra);
                    mapa.put("acervo", acervo);
                    mapa.put("nChamada", nChamada.split(" ")[0]);
                    mapa.put("exemplaresDisponiveis", "Exemplares disponíveis: " + exemplaresDisponiveis);

                    lista.add(mapa);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return lista;
    }

}
