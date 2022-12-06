package android.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    private String loginEmail , loginPassword ;

    EditText edtLoginEmail,edtLoginPassword;
    Button btnLogin;
    View.OnClickListener ClickEvent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginEmail = (EditText) findViewById(R.id.edtLoginEmail);
        edtLoginPassword = (EditText) findViewById(R.id.edtLoginPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail = (edtLoginEmail.getText().toString());
                loginPassword = (edtLoginPassword.getText().toString());
                System.out.println(loginEmail + " and " + loginPassword + " is:");
            }
        });

    }
}
