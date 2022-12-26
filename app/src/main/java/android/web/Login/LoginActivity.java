package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.MainHomeActivity;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Locale;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class LoginActivity extends AppCompatActivity {
    private String loginUserName , loginPassword ;

    EditText edtLoginUserName,edtLoginPassword;
    Button btnLogin;

    Connection connect;
    String ConnResult = "";
    Gson gson = new Gson();
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
                System.out.println("CLick on btnLogin");
                loginUserName = (edtLoginUserName.getText().toString().toUpperCase(Locale.ROOT));
                loginPassword = (edtLoginPassword.getText().toString());
                if(loginUserName.isEmpty() || loginPassword.isEmpty()){
                    Toast.makeText(v.getContext(), "Username or password can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    btnLogin.setEnabled(false);
                    ResultSet rs;
                    rs = (ResultSet) DatabaseClass.getUserByMSV(loginUserName);
                    List<JSONObject> resObj = JSONServices.getFormattedResult(rs);
                    if(resObj.isEmpty() == true){
                        btnLogin.setEnabled(true);
                        System.out.println("Sai username");
                        Toast.makeText(getBaseContext(), "Login fail: Username is incorrect", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            String dbPass = (String) resObj.get(0).get("Pass");
                            BCrypt.Result res = BCrypt.verifyer().verify(loginPassword.toCharArray(), dbPass);
                            if(res.verified){
                                String json = gson.toJson(resObj.get(0));
                                SharedPreferences.Editor editor = getSharedPreferences("preference_user",MODE_PRIVATE).edit();
                                editor.putString("user", json);
                                editor.commit();
                                Intent i = new Intent(LoginActivity.this, MainHomeActivity.class);
                                startActivity(i);
                            }else{
                                btnLogin.setEnabled(true);
                                System.out.println("Login fail: Password is incorrect");
                                Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            btnLogin.setEnabled(true);
                            e.printStackTrace();
                        }
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
