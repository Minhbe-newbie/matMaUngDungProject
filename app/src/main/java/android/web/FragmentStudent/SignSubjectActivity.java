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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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
    RadioGroup rg;
    RadioButton radioButton;

    Gson gson = new Gson();
    String MSV;

    String TenLopI, MaLopI,MaMonI,  TenLopJ, MaLopJ, MaMonJ;

    String NgayBDK, NgayKTK, ThuK, SoTietK, NgayBDH, NgayKTH, ThuH, SoTietH;
    String nameClass, idClass, idSub, nameClassC, idClassC, idSubC;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_subject);
        getInfor();

        initViews();
        addEvent();
        ResultSet rs;
        rs = (ResultSet) DatabaseClass.getSignSubjectFromTC3();
        List<JSONObject> resObj = JSONServices.getFormattedResult(rs);

        ResultSet res = (ResultSet) DatabaseClass.getClassFromTC1();
        List<JSONObject> classObj = JSONServices.getFormattedResult(res);

        tableSignSubject = findViewById(R.id.tableSignSubject);
        String[] idArr = new String[resObj.size()];
        Arrays.fill(idArr, "");
        if(!resObj.isEmpty()){
            System.out.println(resObj);
            for(int i =0; i < resObj.size(); i++){
                int finalI =i;
                try {
                    TenLopI = (String) resObj.get(finalI).get("TenLop");
                    MaLopI = (String) resObj.get(finalI).get("MaLop");
                    MaMonI = (String) resObj.get(finalI).get("MaMon");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TableRow row = new TableRow(this);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog subjectDialog = new AlertDialog.Builder(SignSubjectActivity.this).create();
                        LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                        View dialogView = layoutInflater.inflate(R.layout.popup_subject, null);


                        System.out.println(finalI);
                        try {
                           nameClass = (String) resObj.get(finalI).get("TenLop");
                           idClass = (String) resObj.get(finalI).get("MaLop");
                          idSub = (String) resObj.get(finalI).get("MaMon");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int h=0; h< classObj.size(); h++){
                            try {
                                nameClassC = (String) classObj.get(h).get("TenLop");
                                idClassC = (String) classObj.get(h).get("MaLop");
                                idSubC = (String) classObj.get(h).get("MaMon");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(idClass.equals(idClassC)){
                                //show dialog khi 2 cái id bằng nhau
                                //lấy dữ liệu ở dòng 107 đổ vào dialog


                                TextView tvHocPhan = dialogView.findViewById(R.id.tvHocPhan);
                                tvHocPhan.setText(MaLopI);
                                TextView tvTenMon = dialogView.findViewById(R.id.tvTenMon);
                                tvTenMon.setText(TenLopI);
                                subjectDialog.setView(dialogView);
                                subjectDialog.show();
                            }
                        }
                    }
                });
                TextView sttTv = new TextView(this);
                sttTv.setLayoutParams(new TableRow.LayoutParams(150, 60));
                sttTv.setPadding(30, 0, 0, 0);

                rg = new RadioGroup(this);
                radioButton = new RadioButton(this);
                rg.addView(radioButton);

                rg.setOrientation(RadioGroup.HORIZONTAL);

                TextView tvTenLop = new TextView(this);
                tvTenLop.setLayoutParams( new TableRow.LayoutParams(750, ViewGroup.LayoutParams.WRAP_CONTENT));
                tvTenLop.setPadding(10, 0, 10, 0);

                sttTv.setText(Integer.toString(i+1));
                tvTenLop.setText(TenLopI);

                row.addView(sttTv);
                row.addView(rg);
                row.addView(tvTenLop);

                tableSignSubject.addView(row);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        System.out.println(idArr);
                       // idArr[i] = MaLop;

                    }
                });
                rg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rg.clearCheck();
                    }
                });
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

