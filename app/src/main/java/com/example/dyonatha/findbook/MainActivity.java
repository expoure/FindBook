package com.example.dyonatha.findbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;
import android.os.Handler;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    AlertDialog dialog;
    private EditText editBusca;
    private ListView listView;
    private List<Map<String, Object>> info;
    private static MainActivity static_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        this.editBusca = findViewById(R.id.editBusca);
        this.listView = (ListView) findViewById(R.id.lista);

        static_ref = this;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MapView.class);
                //putExtra...
                startActivityForResult(intent, 1);
            }
        });
    }

    public void buscarOnClick(View view){
        final String nomeLivro = editBusca.getText().toString();
        if (nomeLivro.isEmpty()) {
            Utils.showModalInfo(this, "Nome do livro não informado", "Atenção");
            return;
        }

        limparListView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    JSONArray jsonResponse = BookApi.searcBooks(nomeLivro);
                    info = BookApi.convertJsonToListView(jsonResponse);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            if (info != null && info.size() <= 0) {
                                Utils.showModalInfo(static_ref, "Nenhum livro encontrado", "Atenção");
                                return;
                            }
                            atualizarListView(info);
                        }
                    });
                }
            }
        }).start();

        Utils.hideKeyboard(static_ref);
        dialog = new SpotsDialog(this, "Buscando livros");
        dialog.show();

    }

    public void atualizarListView(List<Map<String, Object>> info) {
        SimpleAdapter adapter = new SimpleAdapter(this, info, R.layout.listview_book,
                new String []{"obra","exemplaresDisponiveis"},
                new int [] { R.id.obra,R.id.exemplaresDisponives });

        listView.setAdapter(adapter);
    }

    public void limparListView() {
        listView.setAdapter(null);
    }

}
