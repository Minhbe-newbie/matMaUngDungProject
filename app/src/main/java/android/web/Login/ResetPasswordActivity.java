package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.MainHomeActivity;
import android.web.R;
import android.web.database.DatabaseClass;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText edtResetPasswordPassword, edtResetPasswordRetypePassword;
    Button btnResetPasswordChangePassword;
    private String password, retypePassword;
    private String email, username;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        edtResetPasswordPassword = findViewById(R.id.edtResetPasswordPassword);
        edtResetPasswordRetypePassword = findViewById(R.id.edtResetPasswordRetypePassword);
        btnResetPasswordChangePassword = findViewById(R.id.btnResetPasswordChangePassword);
        btnResetPasswordChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click on btnResetPasswordChangePassword");
                password = edtResetPasswordPassword.getText().toString();
                retypePassword = edtResetPasswordRetypePassword.getText().toString();
                if(password.isEmpty() || retypePassword.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Please fill all field", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(retypePassword)){
                    Toast.makeText(ResetPasswordActivity.this, "Those passwords did not match. Try again", Toast.LENGTH_SHORT).show();
                }else{
                    getInfor();
                    btnResetPasswordChangePassword.setEnabled(false);
                    String hashPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
                    int rs = DatabaseClass.updatePassword(hashPassword, username );
                    if(rs == 1){
                        Intent i = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(i);
                    }else{
                        btnResetPasswordChangePassword.setEnabled(true);
                        System.out.println("Err when update");
                        Toast.makeText(ResetPasswordActivity.this, "Error when update password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void getInfor(){
        SharedPreferences prefs = getSharedPreferences("preference_otp", MODE_PRIVATE);
        String json = prefs.getString("otp", "");
        JSONObject otpObj = gson.fromJson(json, JSONObject.class);
        System.out.println(otpObj);
        try {
            username = (String) otpObj.get("username");
            System.out.println(" Username: " + username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}