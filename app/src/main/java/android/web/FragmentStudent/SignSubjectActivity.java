package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class SignSubjectActivity extends AppCompatActivity{
    ImageView backRed;
    Button btnSignUp;
    TextView tvBeenSignUp;
    TableLayout tableSignSubject;

    Gson gson = new Gson();
    String MSV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_subject);
        getInfor();

        initViews();
        addEvent();

        tableSignSubject = findViewById(R.id.tableSignSubject);


    }


    private void addEvent() {
        backRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignSubjectActivity.this, "Dang ki", Toast.LENGTH_SHORT).show();
            }
        });

        tvBeenSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignSubjectActivity.this, "Da Dang ki", Toast.LENGTH_SHORT).show();
                clickDDK();
            }
        });
    }
    private void clickDDK() {
        Intent intent = new Intent(getApplicationContext(),CheckSignSubjectActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }


    private void initViews() {
        backRed= findViewById(R.id.backRed);
        btnSignUp= findViewById(R.id.btnSignUp);
        tvBeenSignUp= findViewById(R.id.tvBeenSignUp);
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

