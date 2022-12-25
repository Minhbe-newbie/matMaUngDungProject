package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.web.helper.MailAPI;
import android.web.helper.RandomDigits;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class ForgotPasswordActivity extends AppCompatActivity {
    private String email, username;
    Button btnSendMail;
    EditText edtForgotPasswordUsername, edtForgotPasswordEmail;

    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        edtForgotPasswordUsername = findViewById(R.id.edtForgotPasswordUsername);
        edtForgotPasswordEmail = findViewById(R.id.edtForgotPasswordEmail);
        btnSendMail = findViewById(R.id.btnSendMail);
        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = (edtForgotPasswordEmail.getText().toString());
                username = (edtForgotPasswordUsername.getText().toString().toUpperCase(Locale.ROOT));
                btnSendMail.setEnabled(false);
                if (username.isEmpty() || email.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else {
                    ResultSet rs;
                    rs = (ResultSet) DatabaseClass.getUserByMSV(username);
                    List<JSONObject> resObj = JSONServices.getFormattedResult(rs);
                    btnSendMail.setEnabled(true);
                    try {
                        if(resObj.isEmpty() == true){
                            Toast.makeText(getBaseContext(), "Username is incorrect", Toast.LENGTH_SHORT).show();
                        }
                       else {
                            if (Objects.equals(resObj.get(0).get("Email"), email)) {
                                RandomDigits str = new RandomDigits();
                                JSONObject otpObj = new JSONObject();

                                String otp = str.RandomOTP();
                                Date currentTime = Calendar.getInstance().getTime();
                                sendMail(otp);
                                String hashOTP = BCrypt.withDefaults().hashToString(12, otp.toCharArray());
                                otpObj.put("currentTime", currentTime);
                                otpObj.put("otp", hashOTP);
                                String json = gson.toJson(otpObj);
                                SharedPreferences.Editor editor = getSharedPreferences("preference_otp",MODE_PRIVATE).edit();
                                editor.putString("otp", json);
                                editor.commit();
                                System.out.println("finish save to local");
                                Intent i = new Intent(ForgotPasswordActivity.this, VerificationCodeActivity.class);
                                startActivity(i);
                            } else {
                                System.out.println("Sai email");
                                Toast.makeText(ForgotPasswordActivity.this, "Email is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        Log.e("Error from 83", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });


        View btnReturnLogin = (TextView)findViewById(R.id.btnReturnLogin);
        btnReturnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private void sendMail(String otp) {
        MailAPI mailAPI = new MailAPI(this,email, otp );
        mailAPI.execute();
    }
}