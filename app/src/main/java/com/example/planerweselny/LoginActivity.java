package com.example.planerweselny;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edHaslo, edNazwauzyt;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edNazwauzyt=findViewById(R.id.loguser);
        edHaslo=findViewById(R.id.loghaslo);
        btn=findViewById(R.id.zalogujsie);
        tv=findViewById(R.id.logzarejestrujsie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nazwauzyt=edNazwauzyt.getText().toString();
                String haslo=edHaslo.getText().toString();
                BazaDanych db = new BazaDanych(getApplicationContext(), "Planner weselny",null,1);

                if (nazwauzyt.length()==0||haslo.length()==0) {
                    Toast.makeText(getApplicationContext(),  "Uzupełnij dane!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.Login(nazwauzyt,haslo)==1) {
                        Toast.makeText(getApplicationContext(), "Zalogowano się", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("użytkownik", nazwauzyt);
                        // zapisanie danych z kluczem i wartością
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),  "Nieprawidłowa nazwa użytkownika i hasło", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });


    }
}