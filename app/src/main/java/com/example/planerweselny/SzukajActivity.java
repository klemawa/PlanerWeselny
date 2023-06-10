package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
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
    public void saleWeselne(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/sale-weselne,1/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void fotografSlubny(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/fotograf-slubny,37/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void zespolyWeselne(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/oprawa-muzyczna,51/zespoly-weselne,52/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void kamerzysta(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/kamerzysta-na-wesele,38/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void salonSukienSlubnych(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/salon-sukien-slubnych,35/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void djNaWesele(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/oprawa-muzyczna,51/dj-na-wesele,54/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void atrakcjeWeselne(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/atrakcje-weselne,42/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void dekoracjeSlubne(View v) {
        Uri u = Uri.parse("https://www.weselezklasa.pl/dekoracje-slubne,39/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void suknieSlubne(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/1/suknie-slubne/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void butySlubne(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/10/buty-slubne/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void dekoracje(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/22/dekoracje-slubne/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void garnitury(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/5/garnitury-slubne/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void zaproszenia(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/4/zaproszenia-slubne/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void zareczynowy(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/7/pierscionek-zareczynowy/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void obraczki(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/11/obraczki-slubne/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }
    public void podziewkowania(View v) {
        Uri u = Uri.parse("https://produkty.weselezklasa.pl/kategoria/14/podziekowania-dla-rodzicow/");
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        startActivity(i);
    }

}