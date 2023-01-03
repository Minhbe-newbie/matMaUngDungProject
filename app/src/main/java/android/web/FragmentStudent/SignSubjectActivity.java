package android.web.FragmentStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.web.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SignSubjectActivity extends AppCompatActivity implements OnItemSelectedListener{



    ImageView backRed;
    Button btnSignUp;
    TextView tvBeenSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_subject);

        initViews();
        addEvent();


        Spinner spinnerCK  = (Spinner) findViewById(R.id.spinnerCK);
        Spinner spinnerCCN = (Spinner) findViewById(R.id.spinnerCCN);
        Spinner spinnerCHP = (Spinner) findViewById(R.id.spinnerCHP);
        Spinner spinnerCNH  = (Spinner) findViewById(R.id.spinnerCNH);


        // Spinner click listener
        spinnerCK.setOnItemSelectedListener(this);
        spinnerCCN.setOnItemSelectedListener(this);
        spinnerCHP.setOnItemSelectedListener(this);
        spinnerCNH.setOnItemSelectedListener(this);


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
        spinnerCK.setAdapter(dataAdapter);
        spinnerCCN.setAdapter(dataAdapter);
        spinnerCHP.setAdapter(dataAdapter);
        spinnerCNH.setAdapter(dataAdapter);
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


}

