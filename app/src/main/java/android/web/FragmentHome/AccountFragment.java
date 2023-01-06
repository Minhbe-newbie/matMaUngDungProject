package android.web.FragmentHome;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.FragmentAccount.ChangePassword;
import android.web.FragmentAccount.GroupInforActivity;
import android.web.Login.LoginActivity;
import android.web.R;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountFragment extends Fragment {

    private View view;
    private ImageView ivDMK, ivDX, ivTTUD, ivDG;
    Gson gson = new Gson();
    TextView tvStudentName, tvStudentCode;
    String name, MSV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user__info, container, false);
        getInfor();
        tvStudentName = view.findViewById(R.id.tvStudentName);
        tvStudentName.setText(name);

        tvStudentCode = view.findViewById(R.id.tvStudentCode);
        tvStudentCode.setText(MSV);

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
                clickDX();
            }
        });

        ivDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDG();
            }
        });

        ivTTUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTTUD();
            }
        });

        ivDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDMK();
            }
        });

    }

    private void clickDX() {

        System.out.println("Click on DX");

        //Xoá user trong SharedPreferences
        SharedPreferences.Editor editor = this.getContext().getSharedPreferences("preference_user",MODE_PRIVATE).edit();

        editor.remove("user").apply();
        //Chuyển về trang Login

        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
    }

    private void clickDG() {

        System.out.println("Click on DG");

        // show toast Đánh giá thành công
        Toast.makeText(getContext(), "Cảm ơn vì đã đánh giá 5 sao >-<", Toast.LENGTH_SHORT).show();
    }

    private void clickTTUD() {

        System.out.println("Click TTUD");
        //chuyển đến GroupInfor Activity
        Intent i = new Intent(getContext(), GroupInforActivity.class);
        startActivity(i);
    }

    private void clickDMK() {

        System.out.println("Click DMK");
        //Chuyển đến ChangePasswordActivity
        Intent i = new Intent(getActivity(), ChangePassword.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);


    }
    private void getInfor(){
        SharedPreferences prefs = getContext().getSharedPreferences("preference_user", MODE_PRIVATE);
        String json = prefs.getString("user", "");
        JSONObject userObj = gson.fromJson(json, JSONObject.class);
        try {
            if (userObj == null){
                return;
            }
            MSV = (String) userObj.get("MaSinhVien");
            name = (String) userObj.get("TenSinhVien");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}