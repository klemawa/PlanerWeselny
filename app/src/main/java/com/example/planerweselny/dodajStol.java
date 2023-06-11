package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class dodajStol extends AppCompatActivity {

    private LinearLayout checkboxContainer3;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> namesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_stol);
        checkboxContainer3 = findViewById(R.id.checkboxContainer3);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        namesList = new ArrayList<>();

        loadSavedNames();

        for (String name : namesList) {
            CheckBox checkBox = createCheckBox(name);
            checkboxContainer3.addView(checkBox);
        }

    }
    private CheckBox createCheckBox(String name) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(name);
        checkBox.setChecked(false);
        return checkBox;
    }

    private void loadSavedNames() {
        Set<String> namesSet = sharedPreferences.getStringSet("names", new HashSet<>());
        namesList.addAll(namesSet);
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
}