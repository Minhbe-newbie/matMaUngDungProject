package android.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private String loginEmail , loginPassword ;

    EditText edtLoginEmail,edtLoginPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginEmail =  findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnLogin =  findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail = (edtLoginEmail.getText().toString());
                loginPassword = (edtLoginPassword.getText().toString());
                Toast.makeText(LoginActivity.this, loginEmail +loginPassword, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
