package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.web.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mInHome = new Intent(IntroActivity.this,LoginActivity.class );
                IntroActivity.this.startActivity(mInHome);
                IntroActivity.this.finish();
            }
        }, 3000);
    }
}