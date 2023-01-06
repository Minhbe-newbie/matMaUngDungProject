package android.web.FragmentAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.FragmentHome.AccountFragment;
import android.web.Login.LoginActivity;
import android.web.Login.ResetPasswordActivity;
import android.web.MainHomeActivity;
import android.web.R;
import android.web.database.DatabaseClass;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class ChangePassword extends AppCompatActivity {

    ImageView backRed;
    private EditText edtCurPass, edtNewPass, edtNewPassAgain;
    private Button btnChangePass;
    Gson gson = new Gson();
    String MSV, pass;
    String curPass,newPass ,newPassAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getInfor();
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
                System.out.println("Click on ChangPassword");
                curPass = edtCurPass.getText().toString();
                newPass = edtNewPass.getText().toString();
                newPassAgain = edtNewPassAgain.getText().toString();


                if(curPass.isEmpty() || newPass.isEmpty() || newPassAgain.isEmpty()){
                    Toast.makeText(ChangePassword.this, "Please fill all field", Toast.LENGTH_SHORT).show();
                }else if(!newPass.equals(newPassAgain)){
                    Toast.makeText(ChangePassword.this, "New password and New password again doesn't match", Toast.LENGTH_SHORT).show();
                }else{
                    BCrypt.Result res = BCrypt.verifyer().verify(curPass.toCharArray(), pass);
                    System.out.println(pass);
                    if(res.verified){
                        String hashPassword = BCrypt.withDefaults().hashToString(12, newPass.toCharArray());
                        System.out.println(hashPassword);
                        int rs = DatabaseClass.updatePassword(hashPassword, MSV );
                        System.out.println(rs);
                        if(rs == 1){
                            Intent i = new Intent(ChangePassword.this, MainHomeActivity.class);

                            startActivity(i);
                        }else{
                            System.out.println("Err when update");
                            Toast.makeText(ChangePassword.this, "Error when update password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ChangePassword.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
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
    private void getInfor(){
        SharedPreferences prefs = getSharedPreferences("preference_user", MODE_PRIVATE);
        String json = prefs.getString("user", "");
        JSONObject userObj = gson.fromJson(json, JSONObject.class);
        try {
            if (userObj == null){
                return;
            }
            MSV = (String) userObj.get("MaSinhVien");
            pass = (String) userObj.get("Pass");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



