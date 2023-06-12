package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class KontoActivity extends AppCompatActivity {
    TextView tv, tv1;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konto);
        tv = findViewById(R.id.textView7);

        datePicker = findViewById(R.id.datePicker);
        datePicker.init(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), null);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KontoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    public void selectDate(View view) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Intent intent = new Intent(KontoActivity.this, KalendarzActivity.class);
        intent.putExtra("selectedDay2", day);
        intent.putExtra("selectedMonth2", month);
        intent.putExtra("selectedYear2", year);
        startActivity(intent);

        finish();
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
