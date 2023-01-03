package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


import android.widget.AdapterView.OnItemSelectedListener;

public class CheckScoreActivity extends AppCompatActivity {

    ImageView backRed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_score);

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
        backRed= findViewById(R.id.backRed);
    }
}