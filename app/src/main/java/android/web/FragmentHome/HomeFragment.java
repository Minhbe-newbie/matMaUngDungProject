package android.web.FragmentHome;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    String name;
    Gson gson = new Gson();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getInfor();
        TextView tvHomeFragmentStudentName = view.findViewById(R.id.tvHomeFragmentStudentName);
        tvHomeFragmentStudentName.setText("Chào "+name);
        return view;
    }
    private void getInfor(){
        SharedPreferences prefs = getActivity().getSharedPreferences("preference_user", MODE_PRIVATE);
        String json = prefs.getString("user", "");
        JSONObject otpObj = gson.fromJson(json, JSONObject.class);
        System.out.println(otpObj);
        try {
            if (otpObj == null){
                return;
            }
            name = (String) otpObj.get("TenSinhVien");

            System.out.println(" Username: " + name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}