package com.soldier.soldierlifeup;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ArmySaleInformation extends AppCompatActivity {
    TextView tLocal, tFacility, teventName, tstartDate, tendDate, tTel, tHomepage, tDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armysaleinformation);

        tLocal = (TextView)findViewById(R.id.tLocal);
        tFacility = (TextView)findViewById(R.id.tFacility);
        teventName = (TextView)findViewById(R.id.teventName);
        tstartDate = (TextView)findViewById(R.id.tstartDate);
        tendDate = (TextView)findViewById(R.id.tendDate);
        tTel = (TextView)findViewById(R.id.tTel);
        tHomepage = (TextView)findViewById(R.id.tHomepage);
        tDetail = (TextView)findViewById(R.id.tDetail);

        tLocal.setText(ArmySale.rLocal);
        tFacility.setText(ArmySale.rFacility);
        teventName.setText(ArmySale.reventName);
        tstartDate.setText(ArmySale.rstartDate);
        tendDate.setText(ArmySale.rendDate);
        tTel.setText(ArmySale.rTel);
        tHomepage.setText(ArmySale.rHomepage);
        tDetail.setText(ArmySale.rDetail);
    }
}
