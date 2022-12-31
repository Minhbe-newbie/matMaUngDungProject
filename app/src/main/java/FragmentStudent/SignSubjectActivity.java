package FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignSubjectActivity extends AppCompatActivity {



    ImageView backRed;
    Button btnSignUp;
    TextView tvBeenSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_subject);

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
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignSubjectActivity.this, "Dang ki", Toast.LENGTH_SHORT).show();
            }
        });

        tvBeenSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignSubjectActivity.this, "Da Dang ki", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        backRed= findViewById(R.id.backRed);
        btnSignUp= findViewById(R.id.btnSignUp);
        tvBeenSignUp= findViewById(R.id.tvBeenSignUp);
    }
}