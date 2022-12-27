package android.web.FragmentHome;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeAccountInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChangeAccountInfoFragment extends Fragment {

    private View view;
    private Button btnVerificationCodeSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_account_info, container, false);

        initUI();

        return view;
    }

    private void initUI() {
    btnVerificationCodeSubmit = view.findViewById(R.id.btnVerificationCodeSubmit);
    btnVerificationCodeSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("Đã bấm nút xác nhận");
            clickSubmit();
        }
    });

    }

    private void clickSubmit() {
        System.out.println("Đã bấm vào nút này");
    }
}