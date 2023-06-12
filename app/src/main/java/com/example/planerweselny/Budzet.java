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
import android.widget.Toast;

public class Budzet extends AppCompatActivity {

    private EditText editTextNumber;
    private EditText editTextExpense;
    private SharedPreferences sharedPreferences;
    private Button button31;
    private TextView textView;
    private String previousValue = "";
    private String hint;
    private Button buttonAddExpense;
    private double budzet = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budzet);
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextExpense = findViewById(R.id.editTextNumber2);
        button31 = findViewById(R.id.button31);
        textView = findViewById(R.id.textView13);
        buttonAddExpense = findViewById(R.id.button42);
        hint = "Podaj budżet";
        // Przywrócenie poprzedniej wartości z SharedPreferences
        String previousValue = sharedPreferences.getString("previousValue", "");
        textView.setText(previousValue + " zł");
        editTextNumber.setText(previousValue);

        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editTextNumber.getText().toString();
                if (!input.isEmpty()) {
                    budzet = Double.parseDouble(input);
                    String newValue = String.format("%.2f", budzet);
                    textView.setText(newValue + " zł");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("previousValue", newValue);
                    editor.apply();

                    editTextNumber.setText("");
                }
            }
        });
        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editTextExpense.getText().toString();
                if (!input.isEmpty()) {
                    double wydatek = Double.parseDouble(input);
                    budzet -= wydatek;
                    String newValue = String.format("%.2f", budzet);
                    textView.setText(newValue + " zł");

                    editTextExpense.setText("");
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