package com.soldier.soldierlifeup;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ArmyDictionaryInformation extends AppCompatActivity {
    TextView tmenuName, tTitle, tContent, tRegistrant, tSize, tmSummary, taddItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armydictionaryinformation);

        tmenuName = (TextView)findViewById(R.id.tmenuName);
        tTitle = (TextView)findViewById(R.id.tTitle);
        tContent = (TextView)findViewById(R.id.tContent);
        tRegistrant = (TextView)findViewById(R.id.tRegistrant);
        tSize = (TextView)findViewById(R.id.tSize);
        tmSummary = (TextView)findViewById(R.id.tmSummary);
        taddItem = (TextView)findViewById(R.id.taddItem);

        tmenuName.setText(ArmyDictionary.rmenuName);
        tTitle.setText(ArmyDictionary.rTitle);
        tContent.setText(ArmyDictionary.rContent);
        tRegistrant.setText(ArmyDictionary.rRegistrant);
        tSize.setText(ArmyDictionary.rSize);
        tmSummary.setText(ArmyDictionary.rmSummary);
        taddItem.setText(ArmyDictionary.raddItem);
    }
}
