package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Budzet extends AppCompatActivity {

    private EditText editText;
    private SharedPreferences sharedPreferences;
    private Button button31;
    private TextView textView;
    private String previousValue = "";
    private String hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budzet);
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editText = findViewById(R.id.editTextTextPersonName);
        button31 = findViewById(R.id.button31);
        textView = findViewById(R.id.textView13);
        hint = "Podaj budżet";
        // Przywrócenie poprzedniej wartości z SharedPreferences
        String previousValue = sharedPreferences.getString("previousValue", "");
        textView.setText(previousValue + " zł");
        editText.setText(previousValue);

        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                if (!input.equals(previousValue)){
                    String newValue = input + " zł";
                    textView.setText(newValue);

                    // Zapisanie nowej wartości do SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("previousValue", input);
                    editor.apply();

                    editText.setText("");
                    editText.setHint(hint);
                }
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