package com.soldier.soldierlifeup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IDSearchActivity extends AppCompatActivity {
    EditText et_name,et_phone;
    static String userName, userPhone;
    Button btn_idsearch,btnback;
    TextView result;
    SQLiteDatabase db;
    MembershipOpenHelper openHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idsearch);

        openHelper = new MembershipOpenHelper(this);
        db=openHelper.getReadableDatabase();

        et_name = findViewById(R.id.idsearch_et_name);
        et_phone = findViewById(R.id.idsearch_et_phone);
        btn_idsearch = findViewById(R.id.idsearchbtn);

        result = findViewById(R.id.idsearch_txt_id);


        btnback=findViewById(R.id.btnback);

        btn_idsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = et_name.getText().toString();
                userPhone = et_phone.getText().toString();
                String Member_id,Member_pw, Member_Phone, Member_name,Member_age;
                boolean flag = false;

                Cursor cursor = db.rawQuery("SELECT _id,_phone,_name FROM membership", null);
                while(cursor.moveToNext()) {
                    Member_id = cursor.getString(0);
                    Member_Phone = cursor.getString(1);
                    Member_name = cursor.getString(2);


                    if ( Member_name.equals(userName) && Member_Phone.equals(userPhone) )
                    {

                        result.setText(Member_name+"님의 ID : "+Member_id);
                        flag = true;
                        if( flag ){ Toast.makeText(IDSearchActivity.this, "ID 찾기 성공", Toast.LENGTH_SHORT).show(); }
                        break;
                    }
                    else
                        flag =false;
                }
                if( !flag )
                    Toast.makeText(IDSearchActivity.this, "이름 또는 전화번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IDSearchActivity.this,IDPWSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
