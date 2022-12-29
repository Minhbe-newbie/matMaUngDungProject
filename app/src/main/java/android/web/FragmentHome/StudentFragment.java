package android.web.FragmentHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.widget.ImageView;

import FragmentStudent.CalenderActivity;
import FragmentStudent.CheckScoreActivity;
import FragmentStudent.SignSubjectActivity;
import FragmentStudent.StudyProgramActivity;

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
                System.out.println("Đã chọn vào TCD");
                clickTCD();
            }
        });

        ivLCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn và LCN");
                clickLCN();
            }
        });

        ivDKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào DKT");
                clickDKT();

            }
        });

        ivCTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào CTH");
                clickCTH();
            }
        });

        ivTTSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào TTSV");
                clickTTSV();
            }
        });

    }



    private void clickTCD() {
        Intent intent = new Intent(getContext(), StudyProgramActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }

    private void clickDKT() {
        Intent intent = new Intent(getContext(), SignSubjectActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }

    private void clickCTH() {
        Intent intent = new Intent(getContext(), SignSubjectActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }

    private void clickLCN() {
        Intent intent = new Intent(getContext(), CalenderActivity.class);
        startActivity(intent);
        System.out.println("Click True");
    }


    private void clickTTSV() {
        System.out.println("Click True");
    }
}