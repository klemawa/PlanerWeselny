package com.example.planerweselny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class KalendarzActivity extends AppCompatActivity {
    private static final int REQUEST_DATE = 1;

    private CalendarAdapter calendarAdapter;
    private TextView monthTextView;
    private int selectedDay = -1;
    private SharedPreferences sharedPreferences;
    private ArrayAdapter<String> eventListAdapter;
    private int selectedDayIndex = -1;
    private int selectedDay2 = -1;
    private int selectedMonth2 = -1;
    private int selectedYear2 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendarz);
        GridView gridView = findViewById(R.id.gridView);
        monthTextView = findViewById(R.id.monthTextView);

        calendarAdapter = new CalendarAdapter(this);
        gridView.setAdapter(calendarAdapter);

        updateMonthTextView();

        ListView eventListView = findViewById(R.id.listView);
        eventListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        eventListView.setAdapter(eventListAdapter);

        sharedPreferences = getSharedPreferences("events", MODE_PRIVATE);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String event = eventListAdapter.getItem(position);
                Toast.makeText(KalendarzActivity.this, event, Toast.LENGTH_SHORT).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedDay2 = extras.getInt("selectedDay2", -1);
            selectedMonth2 = extras.getInt("selectedMonth2", -1);
            selectedYear2 = extras.getInt("selectedYear2", -1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DATE && resultCode == RESULT_OK) {
            int selectedDay = data.getIntExtra("day", -1);
            int selectedMonth = data.getIntExtra("month", -1);
            int selectedYear = data.getIntExtra("year", -1);

            if (selectedDay != -1 && selectedMonth != -1 && selectedYear != -1) {
                // Zapisz datę w kalendarzu
                saveDate(selectedDay, selectedMonth, selectedYear);
            }
        }
    }
    private void saveDate(int day, int month, int year) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String dateKey = String.format(Locale.getDefault(), "%02d-%02d-%04d", day, (month + 1), year);

        String previousDateKey = sharedPreferences.getString("selected_date", "");
        if (!previousDateKey.isEmpty()) {
            editor.remove(previousDateKey);
        }
        editor.putString(dateKey, "Wybrana data");
        editor.putString("selected_date", dateKey);
        editor.apply();
        calendarAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Data zapisana dla: " + dateKey, Toast.LENGTH_SHORT).show();
    }

    public void openDatePicker(View view) {
        Intent intent = new Intent(this, KontoActivity.class);
        startActivityForResult(intent, REQUEST_DATE);
    }
    private void updateMonthTextView() {
        DateFormatSymbols symbols = new DateFormatSymbols();
        String monthName = symbols.getMonths()[calendarAdapter.getMonth()];
        String year = String.valueOf(calendarAdapter.getYear());
        String monthYearText = monthName + " " + year;
        monthTextView.setText(monthYearText);
    }

    public void previousMonth(View view) {
        calendarAdapter.previousMonth();
        updateMonthTextView();
    }

    public void nextMonth(View view) {
        calendarAdapter.nextMonth();
        updateMonthTextView();
    }

    private class CalendarAdapter extends BaseAdapter {

        private Context context;
        private Calendar calendar;
        private int month;
        private int year;
        private int daysInMonth;
        private int currentDayOfMonth;

        public CalendarAdapter(Context context) {
            this.context = context;
            calendar = Calendar.getInstance();
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        @Override
        public int getCount() {
            return daysInMonth + getStartDay();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView dayOfMonth;
            if (convertView == null) {
                dayOfMonth = new TextView(context);
                int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
                dayOfMonth.setLayoutParams(new GridView.LayoutParams(size, size));
            } else {
                dayOfMonth = (TextView) convertView;
            }


            if (position < getStartDay()) {
                dayOfMonth.setBackgroundColor(Color.GRAY);
                dayOfMonth.setTextColor(Color.WHITE);
                dayOfMonth.setText("");
            } else {
                final int day = position - getStartDay() + 1;
                dayOfMonth.setBackgroundColor(Color.GRAY);
                dayOfMonth.setTextColor(Color.BLACK);
                dayOfMonth.setText(String.valueOf(day));

                int selectedDay2 = KalendarzActivity.this.selectedDay2;
                int selectedMonth2 = KalendarzActivity.this.selectedMonth2;
                int selectedYear2 = KalendarzActivity.this.selectedYear2;


                if (day == selectedDay2 && month == selectedMonth2 && year == selectedYear2) {
                    dayOfMonth.setBackgroundColor(Color.BLUE);
                    dayOfMonth.setTextColor(Color.BLACK);
                } else if (position == selectedDayIndex) {
                    dayOfMonth.setBackgroundColor(Color.BLUE);
                    dayOfMonth.setTextColor(Color.BLACK);
                } else {
                    dayOfMonth.setBackgroundColor(Color.GRAY);
                    dayOfMonth.setTextColor(Color.BLACK);
                }


                if (day == currentDayOfMonth && month == calendar.get(Calendar.MONTH) && year == calendar.get(Calendar.YEAR)) {
                    dayOfMonth.setBackgroundColor(Color.MAGENTA);
                    dayOfMonth.setTextColor(Color.BLACK);
                } else {
                    dayOfMonth.setBackgroundColor(Color.GRAY);
                    dayOfMonth.setTextColor(Color.BLACK);
                }

                // Sprawdzanie czy dla tego dnia jest zapisana informacja w SharedPreferences
                String event = sharedPreferences.getString(String.valueOf(day), "");
                if (!event.isEmpty()) {
                    dayOfMonth.setBackgroundColor(Color.RED);
                    dayOfMonth.setTextColor(Color.WHITE);
                }

                dayOfMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedDay = day;
                        selectedDayIndex = position;
                        notifyDataSetChanged();
                        showInputDialog();
                    }

                });
            }

            dayOfMonth.setGravity(Gravity.CENTER);
            return dayOfMonth;
        }
        private int getStartDay() {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.set(year, month, 1);
            int startDayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
            return startDayOfWeek - 1;
        }

        public void previousMonth() {
            if (month == 0) {
                month = 11;
                year--;
            } else {
                month--;
            }
            daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            selectedDayIndex = -1;
            notifyDataSetChanged();
        }

        public void nextMonth() {
            if (month == 11) {
                month = 0;
                year++;
            } else {
                month++;
            }
            daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            selectedDayIndex = -1;
            notifyDataSetChanged();
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
    }


    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informacje");

        // Tworzenie widoku layoutu dla dialogu
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        builder.setView(layout);

        // Pobieranie informacji dla wybranego dnia z SharedPreferences
        String event = sharedPreferences.getString(String.valueOf(selectedDay), "");

        // Tworzenie TextView dla wyświetlenia istniejących informacji
        final TextView existingInfoTextView = new TextView(this);
        existingInfoTextView.setText(event);
        layout.addView(existingInfoTextView);

        // Dodanie pola EditText do wprowadzania nowej informacji
        final EditText input = new EditText(this);
        layout.addView(input);

        // Dodanie przycisku "Dodaj"
        builder.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newInfo = input.getText().toString();

                // Aktualizacja istniejących informacji
                String existingInfo = existingInfoTextView.getText().toString();
                String updatedInfo = existingInfo.isEmpty() ? newInfo : existingInfo + ", " + newInfo;
                existingInfoTextView.setText(updatedInfo);

                // Zapisanie informacji w SharedPreferences dla aktualnie zaznaczonej daty
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(String.valueOf(selectedDay), updatedInfo);
                editor.apply();

                // Wyświetlanie zaznaczonego elementu
                Toast.makeText(KalendarzActivity.this, "Informacja dodana dla dnia " + selectedDay, Toast.LENGTH_SHORT).show();
            }
        });

        // Dodanie przycisku "Anuluj"
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void przejsciePlanuj(View v){ //przejście przyciskiem do okna planuj
        Intent i = new Intent(this,PlanujActivity.class);
        startActivity(i);
    }
    public void przejscieSzukaj(View v){ //przejście przyciskiem do okna szukaj
        Intent i = new Intent(this,SzukajActivity.class);
        startActivity(i);
    }
    public void przejscieOdliczaj(View v){  ////przejście przyciskiem do okna home
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }

    public void przejscieKonto(View v){ //przejście przyciskiem do okna konto
        Intent i = new Intent(this,KontoActivity.class);
        startActivity(i);
    }

}


