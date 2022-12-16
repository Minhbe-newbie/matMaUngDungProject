package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private String loginUserName , loginPassword ;

    EditText edtLoginUserName,edtLoginPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginUserName =  findViewById(R.id.edtLoginUserName);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnLogin =  findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserName = (edtLoginUserName.getText().toString());
                loginPassword = (edtLoginPassword.getText().toString());
                Toast.makeText(LoginActivity.this, edtLoginUserName +loginPassword, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
