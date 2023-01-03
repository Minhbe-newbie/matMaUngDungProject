package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.ImageView;

public class CalenderActivity extends AppCompatActivity {

    ImageView backRed;

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
    }

    private void initViews() {
        backRed = findViewById(R.id.backRed);
    }
}