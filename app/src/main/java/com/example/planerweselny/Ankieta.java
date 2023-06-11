package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


import java.util.ArrayList;

public class Ankieta extends AppCompatActivity {
    private ArrayList<String> selectedItems;
    private Button submitButton;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ankieta);

        selectedItems = new ArrayList<>();

        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        submitButton = findViewById(R.id.button25);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    selectedItems.add(checkBox1.getText().toString());
                }
                if (checkBox2.isChecked()) {
                    selectedItems.add(checkBox2.getText().toString());
                }
                if (checkBox3.isChecked()) {
                    selectedItems.add(checkBox3.getText().toString());
                }
                if (checkBox4.isChecked()) {
                    selectedItems.add(checkBox4.getText().toString());
                }
                if (checkBox5.isChecked()) {
                    selectedItems.add(checkBox5.getText().toString());
                }
                if (checkBox6.isChecked()) {
                    selectedItems.add(checkBox6.getText().toString());
                }

                saveDataToFile();

                Toast.makeText(Ankieta.this, "Dziękuję za wypełnienie ankiety", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveDataToFile() {
        String fileName = "Ankieta.txt";
        File file = new File(getFilesDir(), fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            for (String item : selectedItems) {
                osw.write(item + "\n");
            }

            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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