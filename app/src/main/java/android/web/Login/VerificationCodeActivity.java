package android.web.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.web.helper.MailAPI;
import android.web.helper.RandomDigits;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.concurrent.TimeUnit;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class VerificationCodeActivity extends AppCompatActivity {
    Button btnVerificationCodeSubmit;
    EditText edtVerificationCode1, edtVerificationCode2,edtVerificationCode3, edtVerificationCode4, edtVerificationCode5, edtVerificationCode6;
    String otp;
    private String input1, input2,input3,input4,input5,input6;
    Gson gson = new Gson();
    private String hashOTP;
    private long otpTime, now;
    private String otpTimeString;
    private String email, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        btnVerificationCodeSubmit = findViewById(R.id.btnVerificationCodeSubmit);
        edtVerificationCode1 = findViewById(R.id.edtVerificationCode1);
        edtVerificationCode2 = findViewById(R.id.edtVerificationCode2);
        edtVerificationCode3 = findViewById(R.id.edtVerificationCode3);
        edtVerificationCode4 = findViewById(R.id.edtVerificationCode4);
        edtVerificationCode5 = findViewById(R.id.edtVerificationCode5);
        edtVerificationCode6 = findViewById(R.id.edtVerificationCode6);

        btnVerificationCodeSubmit.setOnClickListener(view -> {
            input1 = (edtVerificationCode1.getText().toString());
            input2 = (edtVerificationCode2.getText().toString());
            input3 = (edtVerificationCode3.getText().toString());
            input4 = (edtVerificationCode4.getText().toString());
            input5 = (edtVerificationCode5.getText().toString());
            input6 = (edtVerificationCode6.getText().toString());
            System.out.println("CLick on btnVerificationCodeSubmit");
            if(input1.isEmpty() ||input2.isEmpty() ||input3.isEmpty() ||input4.isEmpty() ||input5.isEmpty() ||input6.isEmpty() ){
                Toast.makeText(VerificationCodeActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }else{
                SharedPreferences prefs = getSharedPreferences("preference_otp", MODE_PRIVATE);
                String json = prefs.getString("otp", "");
                JSONObject otpObj = gson.fromJson(json, JSONObject.class);
                try {
                     hashOTP = String.valueOf(otpObj.get("otp"));
                     otpTimeString = String.valueOf(otpObj.get("currentTime"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                otpTime = Long.parseLong(otpTimeString);
                otp = input1+input2+input3+input4+input5+input6;
                now  = System.currentTimeMillis()/1000;
                long diff = now - otpTime;
                if(diff < 600){
                    BCrypt.Result result = BCrypt.verifyer().verify(otp.toCharArray(), hashOTP);
                   if(result.verified){
                       Intent i = new Intent(VerificationCodeActivity.this, ResetPasswordActivity.class);
                       startActivity(i);
                   }else{
                       System.out.println("Sai OTP");
                       Toast.makeText(VerificationCodeActivity.this, "OTP is not valid", Toast.LENGTH_SHORT).show();
                   }
                }else{
                    System.out.println("OTP hết hạn");
                    Toast.makeText(VerificationCodeActivity.this, "OTP is estimated", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View btnVerificationCodeResendOTP = findViewById(R.id.btnVerificationCodeResendOTP);
        btnVerificationCodeResendOTP.setOnClickListener(view -> {
            System.out.println("CLick on btnVerificationCodeResendOTP ");
            JSONObject otpObj = new JSONObject();
            getInfor();
            RandomDigits str = new RandomDigits();
            String otp  = str.RandomOTP();
            System.out.println("OTP: "+otp);
            sendMail(otp);
            String currentTime = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            String hashOTP = BCrypt.withDefaults().hashToString(12, otp.toCharArray());
            try {
                otpObj.put("currentTime", currentTime);
                otpObj.put("otp", hashOTP);
                otpObj.put("email", email);
                otpObj.put("username", username);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json = gson.toJson(otpObj);
            SharedPreferences.Editor editor = getSharedPreferences("preference_otp",MODE_PRIVATE).edit();

            editor.remove("otp").apply();

            editor.putString("otp", json);
            editor.apply();
            Toast.makeText(VerificationCodeActivity.this, "OTP was sent to your email", Toast.LENGTH_SHORT).show();
        });
    }
    public void getInfor(){
        SharedPreferences prefs = getSharedPreferences("preference_otp", MODE_PRIVATE);
        String json = prefs.getString("otp", "");
        JSONObject otpObj = gson.fromJson(json, JSONObject.class);
        System.out.println(otpObj);
        try {
            username = (String) otpObj.get("username");
            email = (String) otpObj.get("email");
            System.out.println("Email: "+ email + " Username: " + username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void sendMail(String otp) {
        MailAPI mailAPI = new MailAPI(this,email, otp );
        mailAPI.execute();
    }
}