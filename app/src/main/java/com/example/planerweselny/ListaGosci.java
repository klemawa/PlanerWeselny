package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListaGosci extends AppCompatActivity {
    private LinearLayout checkboxContainer;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> namesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gosci);

        checkboxContainer = findViewById(R.id.checkboxContainer);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        namesList = new ArrayList<>();

        loadSavedNames();

        for (String name : namesList) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(name);
            checkBox.setChecked(false);

            CheckBox companionCheckBox = createCompanionCheckBox(name);
            companionCheckBox.setVisibility(View.GONE);

            Button deleteButton = createDeleteButton(name, checkBox, companionCheckBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    companionCheckBox.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                }
            });

            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.addView(checkBox);
            rowLayout.addView(companionCheckBox);
            rowLayout.addView(deleteButton);

            checkboxContainer.addView(rowLayout);
        }

    }

    private CheckBox createCompanionCheckBox(String name) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText("Osoba towarzysząca");
        checkBox.setChecked(false);
        return checkBox;
    }

    private Button createDeleteButton(String name, CheckBox checkBox, CheckBox companionCheckBox) {
        Button button = new Button(this);
        button.setText("Usuń");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxContainer.removeView((View) v.getParent());
                namesList.remove(name);
                sharedPreferences.edit().putStringSet("names", new HashSet<>(namesList)).apply();
            }
        });

        return button;
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
    public void przejscieDodawanieGosci(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,dodajGosci.class);
        startActivity(i);
    }
}