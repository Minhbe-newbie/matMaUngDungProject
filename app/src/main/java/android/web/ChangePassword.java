package android.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ChangePassword extends AppCompatActivity {

    ImageView backRed;
    private EditText edtCurPass, edtNewPass, edtNewPassAgain;
    private Button btnChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initViews();
        addEvent();
    }

    private void initViews() {
        backRed = findViewById(R.id.backRed);
        edtCurPass = findViewById(R.id.edtCurPass);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtNewPassAgain = findViewById(R.id.edtNewPassAgain);
        btnChangePass = findViewById(R.id.btnChangePass);
    }


    private void addEvent() {

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Ừ bấm vào đổi mật khẩu rồi hurrayyyyy");
            }
        });

        backRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

    }
}



