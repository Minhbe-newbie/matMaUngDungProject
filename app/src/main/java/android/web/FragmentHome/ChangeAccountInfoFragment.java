package android.web.FragmentHome;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.web.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeAccountInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChangeAccountInfoFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_account_info, container, false);
    }
}