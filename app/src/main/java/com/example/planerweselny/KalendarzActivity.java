package com.example.planerweselny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;
import java.util.Calendar;
public class KalendarzActivity extends AppCompatActivity {

    private CalendarAdapter calendarAdapter;
    private TextView monthTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendarz);
        GridView gridView = findViewById(R.id.gridView);
        monthTextView = findViewById(R.id.monthTextView);

        calendarAdapter = new CalendarAdapter(this);
        gridView.setAdapter(calendarAdapter);

        updateMonthTextView();
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
                int day = position - getStartDay() + 1;
                dayOfMonth.setBackgroundColor(Color.WHITE);
                dayOfMonth.setTextColor(Color.BLACK);
                dayOfMonth.setText(String.valueOf(day));

                if (day == currentDayOfMonth && month == calendar.get(Calendar.MONTH) && year == calendar.get(Calendar.YEAR)) {
                    dayOfMonth.setBackgroundColor(Color.MAGENTA);
                    dayOfMonth.setTextColor(Color.BLACK);
                }

                dayOfMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedDay = position - getStartDay() + 1;
                        String message = "Selected Date: " + selectedDay + "/" + (month + 1) + "/" + year;
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
            notifyDataSetChanged();
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
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


