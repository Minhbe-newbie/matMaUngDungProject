package android.web.FragmentStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity {

    ImageView backRed;
    private TextView text_info;
    private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        initViews();
        addEvent();
    }



    private void addEvent() {
        backRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String sql= "SELECT * FROM heroku_f330b4f895ee47c.tin_chi2";


                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                text_info.setText(date);

            }
        });

    }




    private void initViews() {
        backRed = findViewById(R.id.backRed);
        calendarView = findViewById(R.id.calendarView);
        text_info = findViewById(R.id.text_info);
    }
}