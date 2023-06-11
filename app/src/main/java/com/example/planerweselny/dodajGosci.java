package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class dodajGosci extends AppCompatActivity {

    private EditText editTextTextPersonName3;

    private CheckBox checkBox9;
    private Button button41;
    private ArrayList<String> nameList;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_gosci);

        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        checkBox9 = findViewById(R.id.checkBox9);
        button41 = findViewById(R.id.button41);
        nameList = new ArrayList<>();
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        loadSavedNames();

        button41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextTextPersonName3.getText().toString();
                boolean hasCompanion = checkBox9.isChecked();

                if(nameList.contains(name)){
                    Toast.makeText(dodajGosci.this, "Ta osoba już jest na liście!", Toast.LENGTH_SHORT).show();
                } else{
                    nameList.add(name);
                    saveNames();
                    Intent intent = new Intent(dodajGosci.this, ListaGosci.class);
                    intent.putExtra("name", name);
                    intent.putExtra("checked", hasCompanion);

                    startActivity(intent);
                }


            }
        });
    }
    private void saveNames() {
        Set<String> namesSet = new HashSet<>(nameList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("names", namesSet);
        editor.apply();
    }
    private void loadSavedNames() {
        Set<String> namesSet = sharedPreferences.getStringSet("names", new HashSet<>());
        nameList.addAll(namesSet);
    }

    public void przejscieOdliczaj(View v){ //przejście przyciskiem do okna home
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }
    public void przejscieSzukaj(View v){ //przejście przyciskiem do okna szukaj
        Intent i = new Intent(this,SzukajActivity.class);
        startActivity(i);
    }
    public void przejscieKalendarz(View v){  ////przejście przyciskiem do okna kalendarz
        Intent i = new Intent(this,KalendarzActivity.class);
        startActivity(i);
    }
    public void przejsciePlanuj(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,PlanujActivity.class);
        startActivity(i);
    }
    public void przejscieKonto(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,KontoActivity.class);
        startActivity(i);
    }
    public void przejscieDodawanieGosci(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,dodajGosci.class);
        startActivity(i);
    }
}