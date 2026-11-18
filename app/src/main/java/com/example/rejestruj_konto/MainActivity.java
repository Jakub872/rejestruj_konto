package com.example.rejestruj_konto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView komunikaty;
    EditText haslo1, haslo2, email;
    Button zapisz;

    String textEmail, textHaslo1, textHaslo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        komunikaty = findViewById(R.id.komunikaty);
        komunikaty.setText("Autor: Jakub Olszewski");
        zapisz = findViewById(R.id.zapisz);

        zapisz.setOnClickListener(v -> {
            email = findViewById(R.id.email);
            haslo1 = findViewById(R.id.haslo1);
            haslo2 = findViewById(R.id.haslo2);
            textEmail = email.getText().toString();
            textHaslo1 = haslo1.getText().toString();
            textHaslo2 = haslo2.getText().toString();

            if( !textEmail.contains("@") ) {
                komunikaty.setText("Nieprawidłowy adres e-mail");
                return;
            }

            if( textHaslo1.isEmpty() || textHaslo2.isEmpty() ){
                komunikaty.setText("Hasła nie mogą pyć puste");
                return;
            }

            if( !textHaslo1.equals(textHaslo2) ){
                komunikaty.setText("Hasła się różnią");
                return;
            }

            email.setText("");
            haslo1.setText("");
            haslo2.setText("");
            komunikaty.setText("Witaj " + textEmail);

            try{
                FileOutputStream plik = openFileOutput("plik.txt", MODE_APPEND);
                OutputStreamWriter streamWriter = new OutputStreamWriter(plik);
                BufferedWriter writer = new BufferedWriter(streamWriter);

                writer.write(textEmail);
                writer.newLine();
                writer.write(textHaslo1);
                writer.newLine();
                writer.close();
            } catch ( IOException e){
                Toast.makeText(this, "Błąd podczas zapisu pliku: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sprawdz(View view){
        startActivity(new Intent(this, druga.class));
    }
}