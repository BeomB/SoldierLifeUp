package com.soldier.soldierlifeup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PWSearchActivity extends AppCompatActivity {
    SQLiteDatabase db;
    MembershipOpenHelper openHelper;
    EditText et_id,et_name,et_phone,et_changePass,et_age;
    Button btn_ok,btn_change,btnback;
    String userID,userName,userPhone,userAge;
    String userChangePass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwsearch);

        openHelper = new MembershipOpenHelper(this);
        db=openHelper.getReadableDatabase();

        et_id = findViewById(R.id.pwsearch_et_id);  // ID
        et_name = findViewById(R.id.pwsearch_et_name); // 이름
        et_phone = findViewById(R.id.pwsearch_et_phone); // 핸드폰 번호
        et_age = findViewById(R.id.pwsearch_et_age);

        btn_ok = findViewById(R.id.pwsearch_ok); // 확인 버튼

        et_changePass = findViewById(R.id.pwsearch_et_changepass); // 비밀번호 변경하는 칸 (false)

        btnback=findViewById(R.id.btnback);
        btn_change =findViewById(R.id.pwssearch_changebtn);  // 변경 버튼 (false)

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = et_id.getText().toString();
                userName = et_name.getText().toString();
                userPhone = et_phone.getText().toString();
                userAge = et_age.getText().toString();

                String Member_id,Member_name,Member_phone,Member_age;
                boolean flag = false;

                Cursor cursor = db.rawQuery("SELECT _id,_name,_phone,_age FROM membership",null);
                while(cursor.moveToNext()) {
                    Member_id = cursor.getString(0);
                    Member_name = cursor.getString(1);
                    Member_phone = cursor.getString(2);
                    Member_age = cursor.getString(3);


                    if (Member_name.equals(userName) && Member_id.equals(userID) && Member_phone.equals(userPhone) && Member_age.equals((userAge))) {
                        Toast.makeText(PWSearchActivity.this, "입력한 정보가 회원 정보와 일치합니다.", Toast.LENGTH_SHORT).show();
                        et_changePass.setEnabled(true);
                        btn_change.setEnabled(true);
                        flag = true;
                        if (flag) {
                            Toast.makeText(PWSearchActivity.this, "비밀번호 변경 시도 성공", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    } else
                        flag = false;

                }
                if( !flag )
                    Toast.makeText(PWSearchActivity.this, "입력한 정보가 회원 정보와 일치하지 않습니다 ", Toast.LENGTH_SHORT).show();

            }
        });
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = et_id.getText().toString();
                userName = et_name.getText().toString();
                userPhone = et_phone.getText().toString();
                userAge = et_age.getText().toString();
                String userChangePass = et_changePass.getText().toString();

                db.execSQL("UPDATE membership set _pw='"+userChangePass+"'where _id='"+userID+"' and _phone='"+userPhone+"' and _name='"+userName+"' and _age='"+userAge+"'");
                Toast.makeText(PWSearchActivity.this,"회원님의 비밀번호가 '"+userChangePass+"' 로 변경되었습니다 !",Toast.LENGTH_LONG).show();
                startActivity(new Intent(PWSearchActivity.this, LoginActivity.class));
                finish();
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PWSearchActivity.this,IDPWSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

