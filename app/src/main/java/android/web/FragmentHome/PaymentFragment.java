package android.web.FragmentHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;



public class PaymentFragment extends Fragment {

    private View view;

    private TextView edtHaveToPay,edtHavePay,edtRemainAmountNeedToPay;
    private Button btnTransfer;

    Connection connect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment, container, false);

        initUi();

        return view;
    }

    private void initUi() {
        edtHaveToPay = view.findViewById(R.id.edtHaveToPay);
        edtHavePay = view.findViewById(R.id.edtHavePay);
        edtRemainAmountNeedToPay = view.findViewById(R.id.edtRemainAmountNeedToPay);

        btnTransfer = view.findViewById(R.id.btnTransfer);
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Đã bấm nút này");
                setMakeEverythingComeTrue();
            }
        });
    }

    private void setMakeEverythingComeTrue() {
//        edtHavePay.setText("Thêm thông tin zô đây");
        edtHavePay.setText("42.000.000 vnd");
        edtRemainAmountNeedToPay.setText("0 vnd");

    }
}