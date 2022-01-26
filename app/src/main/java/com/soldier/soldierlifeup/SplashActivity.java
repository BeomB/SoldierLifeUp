package com.soldier.soldierlifeup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 6000;

    Animation cycle, rotate, translate, alpha, alpha2, alpha3, alpha4_view,loadfadein, loadfadeout, linear_fadein;
    ImageView shot1, shot2, shot3, soldier_life_up;
    TextView Slogan;
    View view1, view2;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        cycle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cycle);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        alpha2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha2);
        alpha3= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha3);
        alpha4_view = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha4_view);
        loadfadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loadfadein);
        loadfadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loadfadeout);
        linear_fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.linear_fadein);


        shot1 = (ImageView) findViewById(R.id.shot1);
        shot2 = (ImageView) findViewById(R.id.shot2);
        shot3 = (ImageView) findViewById(R.id.shot3);
//        soldier_life_up = (ImageView) findViewById(R.id.soldier_life_up);
        Slogan = (TextView) findViewById(R.id.Slogan);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        linearLayout = (LinearLayout) findViewById(R.id.container);

        shot1.setAnimation(alpha);
        shot2.setAnimation(alpha2);
        shot3.setAnimation(alpha3);
//        soldier_life_up.setAnimation(translate);
        Slogan.setAnimation(translate);
        view1.setAnimation(alpha4_view);
        view2.setAnimation(alpha4_view);
        linearLayout.setAnimation(linear_fadein);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.loadfadein, R.anim.loadfadeout);
            }
        }, SPLASH_TIME_OUT);


//        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.slide_up);

// Start animation
//        linearLayout.startAnimation(cycle);
    }
}
