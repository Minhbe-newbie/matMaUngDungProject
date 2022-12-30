package FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckSignSubjectActivity extends AppCompatActivity {

    ImageView backRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sign_subject);

        initViews();
        addEvent();

        View textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click on ĐĂNG KÝ");
            }
        });
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