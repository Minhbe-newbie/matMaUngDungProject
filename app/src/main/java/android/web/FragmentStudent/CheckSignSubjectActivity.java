package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckSignSubjectActivity extends AppCompatActivity {

    ImageView backRed;
    TextView tvSignUp;
    Button btnBeenSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sign_subject);

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
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckSignSubjectActivity.this, "Dang ki", Toast.LENGTH_SHORT).show();
                clickDK();
            }
        });

        btnBeenSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckSignSubjectActivity.this, "Da Dang ki", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void clickDK() {
        Intent intent = new Intent(getApplicationContext(),SignSubjectActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }

    private void initViews() {
        backRed= findViewById(R.id.backRed);
        tvSignUp= findViewById(R.id.tvSignUp);
        btnBeenSignUp= findViewById(R.id.btnBeenSignUp);
    }
}