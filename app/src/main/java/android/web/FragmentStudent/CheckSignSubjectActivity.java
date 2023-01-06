package android.web.FragmentStudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.List;

public class CheckSignSubjectActivity extends AppCompatActivity {

    ImageView backRed;
    TextView tvSignUp;
    Button btnBeenSignUp;
    TableLayout tableSignedSubject;
    Gson gson = new Gson();

    String MSV;
    String TenLop;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sign_subject);
        getInfor();
        initViews();
        addEvent();

        ResultSet result = (ResultSet) DatabaseClass.getSignedSubjectByMSV(MSV);
        List<JSONObject> classObj = JSONServices.getFormattedResult(result);
        System.out.println("Log from 52"+ classObj);
        tableSignedSubject = findViewById(R.id.tableSignedSubject);
        if(!classObj.isEmpty()){
            for(int i =0; i < classObj.size(); i++){
                try {
                    TenLop = (String) classObj.get(i).get("TenLop");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TableRow row = new TableRow(this);
                TextView sttTv = new TextView(this);
                sttTv.setLayoutParams(new TableRow.LayoutParams(150, 60));
                sttTv.setPadding(30, 0, 0, 0);



                TextView tvTenLop = new TextView(this);
                tvTenLop.setLayoutParams( new TableRow.LayoutParams(750, ViewGroup.LayoutParams.WRAP_CONTENT));
                tvTenLop.setPadding(10, 0, 10, 0);

                sttTv.setText(Integer.toString(i+1));
                tvTenLop.setText(TenLop);

                row.addView(sttTv);
                row.addView(tvTenLop);

                tableSignedSubject.addView(row);
            }
        }
    }



    private void addEvent() {
        backRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDK();
            }
        });


    }
    private void clickDK() {
        Intent intent = new Intent(getApplicationContext(),SignSubjectActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }

    private void initViews() {
        backRed= findViewById(R.id.backRed);
        tvSignUp= findViewById(R.id.tvSignUp);
        btnBeenSignUp= findViewById(R.id.btnBeenSignUp);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}