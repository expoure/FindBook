package com.example.dyonatha.findbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ListView list;
    List<Map<String, Object>> info;
    String[] de = {"", ""};
    int[] para = {R.id.nomeLivro, R.id.autor};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        list = (ListView) findViewById(R.id.lista);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MapView.class);
                //putExtra...
                startActivityForResult(intent, 1);
            }
        }); */

    }

    public void buscarOnClick(View view){

    }

}
