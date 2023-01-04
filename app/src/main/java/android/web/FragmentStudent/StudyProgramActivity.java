package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.widget.ImageView;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;


public class StudyProgramActivity extends AppCompatActivity {

    ImageView backRed;
    TableLayout table;
    String MaMon, TenMon, SoTin, HocKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_program);

        table = (TableLayout) findViewById(R.id.tableStudyProgram);

        ResultSet rs;
        rs = (ResultSet) DatabaseClass.getStudyProgram();
        List<JSONObject> resObj = JSONServices.getFormattedResult(rs);
        if(!resObj.isEmpty()){
            System.out.println(resObj);
            for (int i=0; i< resObj.size(); i++){
                try {
                    MaMon = (String) resObj.get(i).get("MaMon");
                    TenMon = (String) resObj.get(i).get("TenMon");
                    SoTin = (String) resObj.get(i).get("SoTin");
                    HocKy = (String) resObj.get(i).get("HocKy");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TableRow row = new TableRow(this);

                TextView sttTv = new TextView(this);
                sttTv.setLayoutParams(new TableRow.LayoutParams(110, 60));
                sttTv.setPadding(30, 0, 0, 0);

                TextView MaMonTv = new TextView(this);
                MaMonTv.setLayoutParams(new TableRow.LayoutParams(250, 60));
                MaMonTv.setPadding(10, 0, 10, 0);

                TextView TenMonTv = new TextView(this);
                TenMonTv.setLayoutParams(new TableRow.LayoutParams(550, 60));
                TenMonTv.setPadding(10, 0, 10, 0);

                TextView SoTinTv = new TextView(this);
                SoTinTv.setLayoutParams(new TableRow.LayoutParams(100, 60));

                TextView HocKyTv = new TextView(this);
                HocKyTv.setLayoutParams(new TableRow.LayoutParams(150, 60));

                sttTv.setText(Integer.toString(i+1));
                MaMonTv.setText(MaMon);
                TenMonTv.setText(TenMon);
                SoTinTv.setText(SoTin);
                HocKyTv.setText(HocKy);


                row.addView(sttTv);
                row.addView(MaMonTv);
                row.addView(TenMonTv);
                row.addView(SoTinTv);
                row.addView(HocKyTv);

                table.addView(row);
            }




        }


        backRed = findViewById(R.id.backRed);
        addEvent();

    }



    private void addEvent() {
        backRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}