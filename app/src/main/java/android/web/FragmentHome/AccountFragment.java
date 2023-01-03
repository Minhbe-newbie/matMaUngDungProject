package android.web.FragmentHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.widget.ImageView;

public class AccountFragment extends Fragment {

    private View view;
    private ImageView ivEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_account, container, false);

        initUI();

        return view;
    }

    private void initUI() {
        ivEdit = view.findViewById(R.id.ivEdit);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã bấm nút chỉnh sửa edit");
                clickEdit();
            }
        });
    }

    private void clickEdit() {
        System.out.println("Nhập lệnh vào đây");
    }
}