package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.ImageView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;



public class StudyProgramActivity extends AppCompatActivity implements OnItemSelectedListener{

    ImageView backRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_program);

        initViews();
        addEvent();
        Spinner spinnerLH  = (Spinner) findViewById(R.id.spinnerLH); //loai hinh
        Spinner spinnerHDT = (Spinner) findViewById(R.id.spinnerHDT);  // he dao tao
        Spinner spinnerKH = (Spinner) findViewById(R.id.spinnerKH); // khoa hoc
        Spinner spinnerCN  = (Spinner) findViewById(R.id.spinnerCN); // chuyen ngành


        // Spinner click listener
        spinnerLH.setOnItemSelectedListener(this);
        spinnerHDT.setOnItemSelectedListener(this);
        spinnerKH.setOnItemSelectedListener(this);
        spinnerCN.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerLH.setAdapter(dataAdapter);
        spinnerHDT.setAdapter(dataAdapter);
        spinnerKH.setAdapter(dataAdapter);
        spinnerCN.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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

    private void initViews() {
        backRed = findViewById(R.id.backRed);
    }
}