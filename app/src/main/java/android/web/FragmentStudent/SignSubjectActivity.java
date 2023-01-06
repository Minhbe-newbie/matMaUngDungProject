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

import java.sql.ResultSet;
import java.util.List;

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

    String TenLopI, MaLopI,MaMonI;
    String time="";
    String nameClass, idClass, idSub, nameClassC, idClassC, idSubC, thu, NgayBD, NgayKT, SoTiet;
    String name, id;
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
                                thu = (String) classObj.get(h).get("Thu");
                                NgayBD = (String) classObj.get(h).get("NgayBD");
                                NgayKT = (String) classObj.get(h).get("NgayKT");
                                SoTiet = (String) classObj.get(h).get("SoTiet");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(idClass.equals(idClassC)){
                                //show dialog khi 2 cái id bằng nhau
                                //lấy dữ liệu ở dòng 107 đổ vào dialog
                                System.out.println(nameClassC+ " "+ idClassC + " "+thu +" "+ NgayBD +" "+ NgayKT +" "+ SoTiet);
                                name = nameClassC;
                                id = idClassC;
                                time +="Thứ: "+thu + " Từ " + NgayBD +" đến " + NgayKT + " Tiết: "+ SoTiet + "\n" ;
                                TextView tvHocPhan = dialogView.findViewById(R.id.tvHocPhan);
                                tvHocPhan.setText(idSubC);
                                TextView tvTenMon = dialogView.findViewById(R.id.tvTenMon);
                                tvTenMon.setText(nameClassC);
                                TextView tvThoiGian = dialogView.findViewById(R.id.tvThoiGian);
                                tvThoiGian.setText(time);

                                Button btnDKH = dialogView.findViewById(R.id.btnDKH);
                                btnDKH.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println(MSV+ " "+id + " "+ name);
                                        int result = DatabaseClass.insertToDangKy(MSV, id, name);
                                        if(result == 1){
                                            Toast.makeText(SignSubjectActivity.this, "Đăng ký môn thành công", Toast.LENGTH_SHORT).show();
                                            subjectDialog.dismiss();
                                        }else{
                                            Toast.makeText(SignSubjectActivity.this, "Đăng ký môn thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                subjectDialog.setView(dialogView);
                                subjectDialog.show();
                            }
                        }
                    }
                });
                TextView sttTv = new TextView(this);
                sttTv.setLayoutParams(new TableRow.LayoutParams(150, 60));
                sttTv.setPadding(30, 0, 0, 0);



                TextView tvTenLop = new TextView(this);
                tvTenLop.setLayoutParams( new TableRow.LayoutParams(750, ViewGroup.LayoutParams.WRAP_CONTENT));
                tvTenLop.setPadding(10, 0, 10, 0);

                sttTv.setText(Integer.toString(i+1));
                tvTenLop.setText(TenLopI);

                row.addView(sttTv);
                row.addView(tvTenLop);

                tableSignSubject.addView(row);
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

