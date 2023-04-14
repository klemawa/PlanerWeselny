package com.example.planerweselny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edNazwauzyt, edEmail, edHaslo, edPotwierdz;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edNazwauzyt = findViewById(R.id.reglogin);
        edHaslo = findViewById(R.id.reghaslo);
        edEmail = findViewById(R.id.regemail);
        edPotwierdz = findViewById(R.id.reghaslo2);
        btn = findViewById(R.id.zarejestrujsie);
        tv = findViewById(R.id.powrot);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                String nazwauzyt = edNazwauzyt.getText().toString();
                String email = edEmail.getText().toString();
                String haslo = edHaslo.getText().toString();
                String potwierdz = edPotwierdz.getText().toString();
                BazaDanych db = new BazaDanych(getApplicationContext(), "Planner weselny", null, 1);


                if (nazwauzyt.length() == 0 || email.length() == 0 || potwierdz.length() == 0 || haslo.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Uzupełnij dane", Toast.LENGTH_SHORT).show();
                } else {
                    if (haslo.compareTo(potwierdz) == 0) {
                        if (jestPoprawne(haslo)) {
                            db.register(nazwauzyt, email, haslo);
                            Toast.makeText(getApplicationContext(), "Zarejestrowano się pomyślnie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Hasło musi zawierać conajmniej 8 znaków -liter, cyfr i znaków", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Hasło i potwierdź hasło nie są takie same", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public static Boolean jestPoprawne(String haslo1) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (haslo1.length() < 8) {
            return false;
        } else {
            for (int a = 0; a < haslo1.length(); a++) {
                if (Character.isLetter(haslo1.charAt(a))) {
                    f1 = 1;
                }
            }
            for (int b = 0; b < haslo1.length(); b++) {
                if (Character.isDigit(haslo1.charAt(b))) {
                    f2 = 1;
                }
            }
            for (int c = 0; c < haslo1.length(); c++) {
                char s = haslo1.charAt(c);
                if (s >= 33 && s <= 46 || s == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }

}