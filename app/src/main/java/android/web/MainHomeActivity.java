package android.web;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.web.FragmentHome.AccountFragment;
import android.web.FragmentHome.HomeFragment;
import android.web.FragmentHome.PaymentFragment;
import android.web.FragmentHome.StudentFragment;

public class MainHomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    StudentFragment studentFragment = new StudentFragment();
    PaymentFragment paymentFragment = new PaymentFragment();
    AccountFragment accountFragment = new AccountFragment();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        break;
                    case R.id.Student:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, studentFragment).commit();
                        break;
                    case R.id.Payment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, paymentFragment).commit();
                        break;
                    case R.id.Account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                        break;
                }

            }
        });
    }
}