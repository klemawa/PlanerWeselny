package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KontoActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konto);
        tv = findViewById(R.id.textView7);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KontoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    public void przejsciePlanuj(View v) {
        Intent i = new Intent(this, PlanujActivity.class);
        startActivity(i);
    }

    public void przejscieSzukaj(View v) {
        Intent i = new Intent(this, SzukajActivity.class);
        startActivity(i);
    }

    public void przejscieKalendarz(View v) {
        Intent i = new Intent(this, KalendarzActivity.class);
        startActivity(i);
    }

    public void przejscieOdliczaj(View v) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

}




