package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.web.database.ConnectionHelper;
import android.web.MainHomeActivity;
import android.web.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class LoginActivity extends AppCompatActivity {
    private String loginUserName , loginPassword ;

    EditText edtLoginUserName,edtLoginPassword;
    Button btnLogin;

    Connection connect;
    String ConnResult = "";
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
                loginUserName = (edtLoginUserName.getText().toString().toUpperCase(Locale.ROOT));
                loginPassword = (edtLoginPassword.getText().toString());
                if(loginUserName.isEmpty() || loginPassword.isEmpty()){
                    Toast.makeText(v.getContext(), "Username or password can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    btnLogin.setEnabled(false);
                    try {
                        ConnectionHelper connHelper = new ConnectionHelper();
                        connect = connHelper.ConnectionClass();
                        if(connect!= null){
                            String query = "select *  from sinh_vien where MaSinhVien = '"+ loginUserName+ "'";
                            Statement st = connect.createStatement();
                            ResultSet rs = st.executeQuery(query);
                            if (rs.next()){
                                String dbPass = rs.getString(6);
                                BCrypt.Result result = BCrypt.verifyer().verify(loginPassword.toCharArray(), dbPass);
                                if(result.verified){
                                    System.out.println("Login success");
                                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginActivity.this, MainHomeActivity.class);
                                    startActivity(i);

                                }else{
                                        btnLogin.setEnabled(true);
                                        System.out.println("Login fail: Password is incorrect");
                                        Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                btnLogin.setEnabled(true);
                                System.out.println("Login fail: Username is incorrect");
                                Toast.makeText(v.getContext(), "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e){
                        Log.e("Err from 54: ", e.getMessage());
                    }
                }
            }
        });
        View btnForgetPassword = (TextView)findViewById(R.id.btnForgetPassword);
        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }
}
