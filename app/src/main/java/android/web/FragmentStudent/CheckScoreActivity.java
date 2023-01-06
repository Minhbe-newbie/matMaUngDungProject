package android.web.FragmentStudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.web.helper.Utils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.List;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckScoreActivity extends AppCompatActivity {

    ImageView backRed;
    Gson gson = new Gson();
    String MSV;
    TableLayout table;
    String nameSbj;
    String tkhpSbj;
    Float tkhpSbjF;
    String dbcSbj;
    String sttSbj;
    String note;
    int j=1;
    float density = Resources.getSystem().getDisplayMetrics().density;
    Float DTB10;
    String DTBC;
    Float DTB4;
    Float sum = 0f;
    int count=0;
    String countS;
    int sumCount = 0;
    TextView tvDTB10, tvDTB4, tvDTBC;

    String name, tkhp, dbc, stS, tp1Str, tp2Str, thiStr, MaMonStr;
    Float tkhpF, tp1F, tp2F, thiF;

    TextView tvTenMon, tvHocPhan, tvSoTinChi, tvDanhGia, tvTP1, tvTP2, tvTHI, tvTKHP, tvDBC;
    DecimalFormat df = new DecimalFormat("0.00");
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_score);

        table = (TableLayout) findViewById(R.id.tableScore);
        tvDTB10 = findViewById(R.id.tvDTB10);
        tvDTB4 = findViewById(R.id.tvDTB4);
        tvDTBC = findViewById(R.id.tvDTBC);
        getInfor();


        ResultSet rs;
        rs = (ResultSet) DatabaseClass.getScoreByMSV(MSV);
        List<JSONObject> resObj = JSONServices.getFormattedResult(rs);

        if(!resObj.isEmpty()){
            System.out.println(resObj);
            for(int i = 0; i< resObj.size(); i++){
                try {
                    note = (String)resObj.get(i).get("GhiChu");
                    nameSbj = (String)resObj.get(i).get("TenMon");
                    tkhpSbj = (String)resObj.get(i).get("TKHP");
                    tkhpSbjF= Float.parseFloat(tkhpSbj);
                    dbcSbj = (String)resObj.get(i).get("Diem_Bang_Chu");
                    countS = (String) resObj.get(i).get("SoTin");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(note.equals("0")){
                    TableRow row = new TableRow(this);
                    int finalI = i;
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog scoreDialog = new AlertDialog.Builder(CheckScoreActivity.this).create();
                            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
                            View dialogView =  layoutInflater.inflate(R.layout.popup_score, null);
                            try {
                                name = (String)resObj.get(finalI).get("TenMon");
                                tkhp = (String)resObj.get(finalI).get("TKHP");
                                tkhpF= Float.parseFloat(tkhp);
                                dbc = (String)resObj.get(finalI).get("Diem_Bang_Chu");
                                stS = (String) resObj.get(finalI).get("SoTin");
                                tp1Str = (String) resObj.get(finalI).get("TP1");
                                tp1F = Float.parseFloat(tp1Str);
                                tp2Str = (String) resObj.get(finalI).get("TP2");
                                tp2F = Float.parseFloat(tp2Str);
                                thiStr  = (String) resObj.get(finalI).get("THI");
                                thiF = Float.parseFloat(thiStr);
                                MaMonStr = (String) resObj.get(finalI).get("MaMon");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            tvTenMon = dialogView.findViewById(R.id.tvTenMon);
                            tvHocPhan = dialogView.findViewById(R.id.tvHocPhan);
                            tvSoTinChi = dialogView.findViewById(R.id.tvSoTinChi);
                            tvDanhGia = dialogView.findViewById(R.id.tvDanhGia);
                            tvTP1 = dialogView.findViewById(R.id.tvTP1);
                            tvTP2 = dialogView.findViewById(R.id.tvTP2);
                            tvTHI = dialogView.findViewById(R.id.tvTHI);
                            tvTKHP  = dialogView.findViewById(R.id.tvTKHP );
                            tvDBC = dialogView.findViewById(R.id.tvDBC);

                            tvTenMon.setText(name);
                            tvHocPhan.setText(MaMonStr);
                            tvSoTinChi.setText(stS);
                            tvDanhGia.setText("DAT");
                            tvTP1.setText(Float.toString(tp1F));
                            tvTP2.setText(Float.toString(tp2F));
                            tvTHI.setText(Float.toString(thiF));
                            tvTKHP.setText(Float.toString(tkhpF));
                            tvDBC.setText(dbc);


                            scoreDialog.setView(dialogView);
                            scoreDialog.show();
                        }
                    });
                    TextView sttTv = new TextView(this);
                    sttTv.setLayoutParams(new TableRow.LayoutParams(110, 60));
                    sttTv.setPadding(30, 0, 0, 0);

                    TextView nameSbjTv = new TextView(this);
                    nameSbjTv.setLayoutParams(new TableRow.LayoutParams(550, ViewGroup.LayoutParams.WRAP_CONTENT));
                    nameSbjTv.setPadding(10, 0, 10, 0);

                    TextView tkhpSbjTv = new TextView(this);
                    tkhpSbjTv.setLayoutParams(new TableRow.LayoutParams(150, 60));

                    TextView dbcSbjTv = new TextView(this);
                    dbcSbjTv.setLayoutParams(new TableRow.LayoutParams(150, 60));

//                    ImageView imgView = new ImageView(this);
//                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
//                    imgView.setLayoutParams(lp);
//                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.info__1__1));

                    sttSbj = Integer.toString(j);
                    sttTv.setText(sttSbj);
                    j++;
                    nameSbjTv.setText(nameSbj);
                    tkhpSbjTv.setText(Float.toString(tkhpSbjF));
                    dbcSbjTv.setText(dbcSbj);

                    count = Integer.parseInt(countS);

                    sum += count * tkhpSbjF;
                    sumCount += count;

                    row.addView(sttTv);
                    row.addView(nameSbjTv);
                    row.addView(tkhpSbjTv);
                    row.addView(dbcSbjTv);
//                    row.addView(imgView);



                    table.addView(row);

                }
                DTB10 = sum / sumCount;
                DTBC = Utils.convert10toC(DTB10);
                DTB4 = Utils.convert10to4(DTB10);
                tvDTB10.setText(df.format(DTB10));
                tvDTB4.setText(Float.toString(DTB4));
                tvDTBC.setText(DTBC);
            }
        }






        backRed= findViewById(R.id.backRed);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}