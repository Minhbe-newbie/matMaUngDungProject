package android.web.FragmentHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.FragmentStudent.AccountActivity;
import android.web.R;
import android.widget.ImageView;
import android.widget.Toast;

import android.web.FragmentStudent.CalenderActivity;
import android.web.FragmentStudent.CheckScoreActivity;
import android.web.FragmentStudent.SignSubjectActivity;
import android.web.FragmentStudent.StudyProgramActivity;

public class StudentFragment extends Fragment {

    private View view;
    private ImageView ivTTSV,ivCTH,ivDKT,ivLCN,ivTCD;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student, container, false);

        initUI();

        return view;
    }

    private void initUI() {
        ivTTSV = view.findViewById(R.id.ivTTSV);
        ivCTH  = view.findViewById(R.id.ivCTH);
        ivDKT  = view.findViewById(R.id.ivDKT);
        ivLCN  = view.findViewById(R.id.ivLCN);
        ivTCD  = view.findViewById(R.id.ivTCD);

        ivTCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTCD();
            }
        });

        ivLCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLCN();
            }
        });

        ivDKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDKT();

            }
        });

        ivCTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCTH();
            }
        });

        ivTTSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountActivity.class); // sao l???i StudyPr Activity  trong khi n?? l?? tra c???u ??i???m
                startActivity(intent);
            }
        });

    }



    private void clickTCD() {
        Intent intent = new Intent(getContext(),CheckScoreActivity.class); // sao l???i StudyPr Activity  trong khi n?? l?? tra c???u ??i???m
        startActivity(intent);
    }

    private void clickDKT() {
        Intent intent = new Intent(getContext(), SignSubjectActivity.class);
        startActivity(intent);
    }

    private void clickCTH() {
        Intent intent = new Intent(getContext(), StudyProgramActivity.class);
        startActivity(intent);
    }

    private void clickLCN() {
        Intent intent = new Intent(getContext(), CalenderActivity.class);
        startActivity(intent);
    }


    private void clickTTSV() {
        System.out.println("Click True");
    }
}