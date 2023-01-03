package android.web.FragmentStudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.Login.LoginActivity;
import android.web.MainHomeActivity;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.List;

public class AccountActivity extends AppCompatActivity {
    Gson gson = new Gson();
    String name, MSV, dob, phone, email, address;
    ImageView ivEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getInfor();
        TextView tvStudentCode = findViewById(R.id.tvStudentCode);
        TextView tvStudentName = findViewById(R.id.tvStudentName);
        TextView tvStudentBirth = findViewById(R.id.tvStudentBirth);
        TextView tvStudentPhoneNumber = findViewById(R.id.tvStudentPhoneNumber);
        TextView tvStudentEmail = findViewById(R.id.tvStudentEmail);
        TextView tvHomeTown = findViewById(R.id.tvHomeTown);
        tvStudentCode.setText(MSV);
        tvStudentName.setText(name);
        tvStudentBirth.setText(dob);
        tvStudentPhoneNumber.setText(phone);
        tvStudentEmail.setText(email);
        tvHomeTown.setText(address);


        ivEdit = (ImageView) findViewById(R.id.ivEdit);
        ivEdit.setClickable(true);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, EditAccountActivity.class);
                startActivity(i);
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
            name = (String) userObj.get("TenSinhVien");
            dob = (String) userObj.get("NgaySinh");
            phone = (String) userObj.get("SoDienThoai");
            email = (String) userObj.get("Email");
            address = (String) userObj.get("QueQuan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}