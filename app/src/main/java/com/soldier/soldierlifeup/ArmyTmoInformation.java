package com.soldier.soldierlifeup;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ArmyTmoInformation extends AppCompatActivity {
    TextView tTmoName, tTelNum, twdayTime, twendTime, tLocationexplane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armytmoinformation);

        tTmoName = (TextView)findViewById(R.id.tTmoName);
        tTelNum = (TextView)findViewById(R.id.tTelNum);
        twdayTime = (TextView)findViewById(R.id.twdayTime);
        twendTime = (TextView)findViewById(R.id.twendTime);
        tLocationexplane = (TextView)findViewById(R.id.tLocationexplane);

        tTmoName.setText(ArmyTmo.rTmoName);
        tTelNum.setText(ArmyTmo.rTelNum);
        twdayTime.setText(ArmyTmo.rwdayTime);
        twendTime.setText(ArmyTmo.rwendTime);
        tLocationexplane.setText(ArmyTmo.rLocationexplane);

    }
}
