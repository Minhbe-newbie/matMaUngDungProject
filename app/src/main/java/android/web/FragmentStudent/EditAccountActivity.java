package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.Login.LoginActivity;
import android.web.Login.ResetPasswordActivity;
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

import java.sql.ResultSet;
import java.util.List;

public class EditAccountActivity extends AppCompatActivity {
    String name, MSV, pass, dob, phone, email, address;
    Gson gson = new Gson();
    String editPhone, editEmail, editAddress;
    Button btnSaveInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        getInfor();
        TextView edtStudentCode = findViewById(R.id.edtStudentCode);
        TextView edtStudentName = findViewById(R.id.edtStudentName);
        TextView edtStudentBirth = findViewById(R.id.edtStudentBirth);
        EditText edtStudentPhoneNumber = findViewById(R.id.edtStudentPhoneNumber);
        EditText edtStudentEmail = findViewById(R.id.edtStudentEmail);
        EditText edtHomeTown = findViewById(R.id.edtHomeTown);

        //set init infor for EditText
        edtStudentCode.setText(MSV);
        edtStudentName.setText(name);
        edtStudentBirth.setText(dob);
        edtStudentPhoneNumber.setText(phone);
        edtStudentEmail.setText(email);
        edtHomeTown.setText(address);

        btnSaveInfor = findViewById(R.id.btnSaveInfor);
        btnSaveInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPhone =edtStudentPhoneNumber.getText().toString();
                editEmail =edtStudentEmail.getText().toString();
                editAddress =edtHomeTown.getText().toString();
                int rs = DatabaseClass.updateInfor(MSV, editPhone, editEmail, editAddress);
                if(rs == 1){
                    SharedPreferences.Editor editor = getSharedPreferences("preference_user",MODE_PRIVATE).edit();
                    ResultSet res;
                    res = (ResultSet) DatabaseClass.getUserByMSV(MSV);
                    List<JSONObject> resObj = JSONServices.getFormattedResult(res);
                    try {
                        phone = (String) resObj.get(0).get("SoDienThoai");
                        email = (String) resObj.get(0).get("Email");
                        address = (String) resObj.get(0).get("QueQuan");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject updateUserObject = new JSONObject();
                    try {
                        updateUserObject.put("MaSinhVien", MSV);
                        updateUserObject.put("TenSinhVien", name);
                        updateUserObject.put("NgaySinh", dob);
                        updateUserObject.put("SoDienThoai", phone);
                        updateUserObject.put("Email", email);
                        updateUserObject.put("QueQuan", address);
                        updateUserObject.put("Pass", pass);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String json = gson.toJson(updateUserObject);
                    editor.remove("user").apply();
                    editor.putString("user", json);
                    editor.apply();
                    Toast.makeText(EditAccountActivity.this, "Update success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditAccountActivity.this, AccountActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(EditAccountActivity.this, "Update fail", Toast.LENGTH_SHORT).show();
                }
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
            pass = (String) userObj.get("Pass");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}