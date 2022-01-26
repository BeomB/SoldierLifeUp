package com.soldier.soldierlifeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private MainBackPressCloseHandler mainBackPressCloseHandler;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Home_Fragment fragmentHome = new Home_Fragment();
    private Internet_Fragment fragmentInternet = new Internet_Fragment();
    private MyPage_Fragment fragmentMyPage = new MyPage_Fragment();
    private Community_Fragment fragmentCommunity = new Community_Fragment();

    @Override
    public void onBackPressed() {
        mainBackPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBackPressCloseHandler = new MainBackPressCloseHandler(this);


        //--------------------------------------------------------- 뒤로 가기 메세지

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_bottom: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag, fragmentHome).commit();
                        return true;
                    }
                    case R.id.internet_bottom: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag, fragmentInternet).commit();
                        return true;
                    }
                    case R.id.community_bottom: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag, fragmentCommunity).commit();
                        return true;
                    }
                    case R.id.mypage_bottom: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag, fragmentMyPage).commit();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }
}
