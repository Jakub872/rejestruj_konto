package com.example.rejestruj_konto;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class druga extends AppCompatActivity {

    ListView lista;
    ArrayList<String> listaElementow = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Button powrot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_druga);

        lista = findViewById(R.id.lista);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaElementow);
        lista.setAdapter(adapter);
        powrot = findViewById(R.id.powrot);

        try{
            FileInputStream plik = openFileInput("plik.txt");
            InputStreamReader streamReader = new InputStreamReader(plik);
            BufferedReader reader = new BufferedReader(streamReader);

            String linia;
            while ( (linia = reader.readLine()) != null ){
                listaElementow.add(linia);
            }
            adapter.notifyDataSetChanged();

        } catch ( IOException e){
            Toast.makeText(this, "Błąd podczas wczytywania pliku: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        powrot.setOnClickListener(v -> {
            finish();
        });
    }
}