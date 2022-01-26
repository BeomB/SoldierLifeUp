package com.soldier.soldierlifeup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IDPWSearchActivity extends AppCompatActivity {

    Button btnidsearch,btnpwsearch, btnback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idpwsearch);

        btnidsearch = findViewById(R.id.id_search_button);
        btnpwsearch = findViewById(R.id.pw_search_button);
        btnback=findViewById(R.id.btnback);

        btnidsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IDPWSearchActivity.this , IDSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnpwsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IDPWSearchActivity.this,PWSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IDPWSearchActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}