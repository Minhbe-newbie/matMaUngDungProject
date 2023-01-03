package android.web;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class User_Info extends Fragment {
    private View view;
    private ImageView ivDMK, ivDX, ivTTUD, ivDG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_user__info, container, false);
        initUI();
    return view;
    }

    private void initUI() {
        ivDMK = view.findViewById(R.id.ivDMK);
        ivDX  = view.findViewById(R.id.ivDX);
        ivTTUD  = view.findViewById(R.id.ivTTUD);
        ivDG  = view.findViewById(R.id.ivDG);


        ivDX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào DX"); // Cái print này k có tác dụng trên giao diện app đâu
                Toast.makeText(getContext(), "Đã chọn vào DX", Toast.LENGTH_SHORT).show();
                clickDX();
            }
        });

        ivDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào DG"); // Cái print này k có tác dụng trên giao diện app đâu
                Toast.makeText(getContext(), "Đã chọn vào DG", Toast.LENGTH_SHORT).show();
                clickDG();
            }
        });

        ivTTUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào TTUD"); // Cái print này k có tác dụng trên giao diện app đâu
                Toast.makeText(getContext(), "Đã chọn vào TTUD", Toast.LENGTH_SHORT).show();
                clickTTUD();
            }
        });


        ivDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã chọn vào DMK"); // Cái print này k có tác dụng trên giao diện app đâu
                Toast.makeText(getContext(), "Đã chọn vào DMK", Toast.LENGTH_SHORT).show();
                clickDMK();
            }
        });



    }

    private void clickDX() {
        System.out.println("Click on DX");
    }

    private void clickDG() {
        System.out.println("Click on DG");
    }

    private void clickTTUD() {
        System.out.println("Click TTUD");
    }

    private void clickDMK() {
        System.out.println("Click true");
    }
}