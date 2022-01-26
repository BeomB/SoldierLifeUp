package com.soldier.soldierlifeup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private MainBackPressCloseHandler mainBackPressCloseHandler;
    MembershipOpenHelper openHelper;
    EditText et_id, et_pass;
    Button btn_login, btn_join, btn_search_id_pw;
    SQLiteDatabase db;
    static String id="a";
    String shared = "soldier";

    @Override
    public void onBackPressed() {
        mainBackPressCloseHandler.onBackPressed();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        onResume();
        mainBackPressCloseHandler = new MainBackPressCloseHandler(this);
        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);

        final String value_id = sharedPreferences.getString("ID", "");
        final String value_pw = sharedPreferences.getString("PW", "");

        et_id = findViewById(R.id.login_et_id);
        et_pass = findViewById(R.id.login_et_pw);

        et_id.setText(value_id);
        et_pass.setText(value_pw);

        btn_login = findViewById(R.id.loginbutton);
        btn_join = findViewById(R.id.memberbutton);
        btn_search_id_pw = findViewById(R.id.id_pw_search_button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String Member_id, Member_pw, Member_name;
                boolean flag = false;

                id=et_id.getText().toString();

                Cursor cursor = db.rawQuery("SELECT _id,_pw,_name FROM membership", null);

                while (cursor.moveToNext()) {
                    Member_id = cursor.getString(0);
                    Member_pw = cursor.getString(1);
                    Member_name = cursor.getString(2);



                    if (Member_id.equals(userID) && Member_pw.equals(userPass)) {
                        Toast.makeText(LoginActivity.this, Member_name + "님 환영합니다", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(main);
                        finish();
//                        et_id.setText("");
//                        et_pass.setText("");
                        flag = true;
                        if (flag) {

                            //Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    } else
                        flag = false;
                }
                if (!flag) {
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
                    et_id.setText("");
                    et_pass.setText("");
                }
            }
        });

        btn_search_id_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, IDPWSearchActivity.class));
            }
        });


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value_id = et_id.getText().toString();
        String value_pw = et_pass.getText().toString();
        editor.putString("ID", value_id);
        editor.putString("PW", value_pw);
        editor.commit();
    }
}