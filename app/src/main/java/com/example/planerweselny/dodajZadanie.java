package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class dodajZadanie extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "notki";
    private EditText editTextNote;
    private Button button59;
    private ListView listViewNotes2;

    private ArrayList<String> notki;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zadanie);

        // Inicjalizacja widoków
        editTextNote = findViewById(R.id.editTextNote);
        button59 = findViewById(R.id.button59);
        listViewNotes2 = findViewById(R.id.listViewNotes2);

        // Inicjalizacja listy notatek
        notki = new ArrayList<>();

        // Inicjalizacja adaptera
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notki);
        listViewNotes2.setAdapter(adapter);

        // Inicjalizacja SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        // Odczyt notatek z SharedPreferences
        Set<String> savedNotes = sharedPreferences.getStringSet(SHARED_PREFS_KEY, null);
        if (savedNotes != null) {
            notki.addAll(savedNotes);
            adapter.notifyDataSetChanged();
        }

        // Obsługa dodawania notatki
        button59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editTextNote.getText().toString();
                if (!noteText.isEmpty()) {
                    notki.add(noteText);
                    adapter.notifyDataSetChanged();
                    editTextNote.setText("");

                    // Zapis notatek do SharedPreferences
                    Set<String> notesSet = new HashSet<>(notki);
                    sharedPreferences.edit().putStringSet(SHARED_PREFS_KEY, notesSet).apply();
                }
            }
        });

        // Obsługa usuwania notatki po kliknięciu
        listViewNotes2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notki.remove(position);
                adapter.notifyDataSetChanged();

                // Zapis notatek do SharedPreferences po usunięciu
                Set<String> notesSet = new HashSet<>(notki);
                sharedPreferences.edit().putStringSet(SHARED_PREFS_KEY, notesSet).apply();
            }
        });
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