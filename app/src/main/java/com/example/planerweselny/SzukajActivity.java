package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SzukajActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szukaj);


    }

    public void przejsciePlanuj(View v){ //przejście przyciskiem do okna planuj
        Intent i = new Intent(this,PlanujActivity.class);
        startActivity(i);
    }
    public void przejscieOdliczaj(View v){ //przejście przyciskiem do okna szukaj
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }
    public void przejscieKalendarz(View v){  ////przejście przyciskiem do okna kalendarz
        Intent i = new Intent(this,KalendarzActivity.class);
        startActivity(i);
    }

    public void przejscieKonto(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,PlanujActivity.class);
        startActivity(i);
    }
}