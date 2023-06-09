package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlanujActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planuj);
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

    public void przejscieKonto(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,KontoActivity.class);
        startActivity(i);
    }
    public void przejscieAnkieta(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,Ankieta.class);
        startActivity(i);
    }

    public void przejscieListaGosci(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,ListaGosci.class);
        startActivity(i);
    }
    public void przejsciePlanStolow(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,PlanStolow.class);
        startActivity(i);
    }
    public void przejscieListaZadan(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,ListaZadan.class);
        startActivity(i);
    }
    public void przejscieQA(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,QA.class);
        startActivity(i);
    }
    public void przejscieBudzet(View v) { //przejście przyciskiem do okna konto
        Intent i = new Intent(this, Budzet.class);
        startActivity(i);
    }

}