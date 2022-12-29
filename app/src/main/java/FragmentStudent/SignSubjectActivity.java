package FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.ImageView;

public class SignSubjectActivity extends AppCompatActivity {



    ImageView backRedSign;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_subject);

        initViews();
        addEvent();
    }



    private void addEvent() {
        backRedSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void initViews() {
        backRedSign= findViewById(R.id.backRedSign);
    }
}