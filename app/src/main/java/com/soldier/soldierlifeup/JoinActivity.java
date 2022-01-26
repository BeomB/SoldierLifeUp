package com.soldier.soldierlifeup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class JoinActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    MembershipOpenHelper openHelper;
    SQLiteDatabase db;
    EditText et_id, et_pass, et_pass_check, et_phone, et_name, et_age, register_et_armynum;
    static int optionyear=1,optionmonth=1,optionday=1;

    Button btn_register, btnback;
    private DatePickerDialog.OnDateSetListener callbackMethod1,callbackMethod2;

    Calendar pickedDate = Calendar.getInstance();
    Calendar minDate = Calendar.getInstance();
    Calendar maxDate = Calendar.getInstance();


    Button btn_enlistment, btn_discharge;
    TextView txt_enlistment, txt_discharge;
    static String getTxt_enlistment,getTxt_discharge;
    static String getDay="",getDay2="";
    Spinner army_Field;
    String[] item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();

        et_id = findViewById(R.id.register_et_id);
        et_pass = findViewById(R.id.register_et_pw);
        et_pass_check = (EditText) findViewById(R.id.register_et_pwcheck);
        et_name = findViewById(R.id.register_et_name);
        et_age = findViewById(R.id.register_et_age);
        et_phone = findViewById(R.id.register_et_phone);

        register_et_armynum = (EditText) findViewById(R.id.register_et_armynum);
//------------------------------------------------------------------------------
        btn_enlistment = (Button) findViewById(R.id.btn_enlistment);
        txt_enlistment=(TextView)findViewById(R.id.txt_enlistment);

        btn_discharge = (Button) findViewById(R.id.btn_discharge);
        txt_discharge=(TextView)findViewById(R.id.btn_discharge);



        this.InitializeView_enlistment();
        this.InitializeListener_enlistment();

        this.InitializeView_discharge();
        this.InitializeListener_discharge();

//------------------------------------------------------------------------------
        army_Field = (Spinner) findViewById(R.id.army_Field);
        btnback = findViewById(R.id.btnback);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID, userPass, userPass_check, userName, userAge, userPhone;
                String user_armynum;
                String user_armyfield;
                String user_enlistment,user_discharge;


                userID = et_id.getText().toString();
                userPass = et_pass.getText().toString();
                userPass_check = et_pass_check.getText().toString();
                userName = et_name.getText().toString();
                userAge = et_age.getText().toString();
                userPhone = et_phone.getText().toString();
                user_armynum = register_et_armynum.getText().toString();
                user_armyfield=army_Field.getSelectedItem().toString();
                user_enlistment=txt_enlistment.getText().toString();
                user_discharge=txt_discharge.getText().toString();

                String Member_id, Member_pw, Member_phone, Member_name, Member_age;

                boolean flag = false;

                if (userID.equals("") && userPass.equals("") && userPass_check.equals("") &&userPhone.equals("") && userName.equals("") && userAge.equals("") && user_armynum.equals("") && user_armyfield.equals("군 종류를 선택하세요.") && user_enlistment.equals("") && user_discharge.equals("")) {
                    finish();
                    Toast.makeText(JoinActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(JoinActivity.this, JoinActivity.class));
                }

                else if (userID.length() < 2) {
                    Toast.makeText(getApplicationContext(), "ID를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (userPass.length() < 4) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 4자리 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (userPass_check.length() < 4) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인 해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (userName.length() < 2 || userName.length() > 7) {
                    Toast.makeText(getApplicationContext(), "이름을 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (user_armyfield.equals("군 종류를 선택하세요.")) {
                    Toast.makeText(getApplicationContext(), "군 종류를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (user_armynum.equals("")) {
                    Toast.makeText(getApplicationContext(), "군번을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (userAge.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "나이를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (!userPass.equals(userPass_check)) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 다시 한 번 확인해주세요", Toast.LENGTH_SHORT).show();
                }

                else if (userPhone.length() < 9) {
                    Toast.makeText(getApplicationContext(), "핸드폰 번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (user_enlistment.length() == 0) {
                    Toast.makeText(getApplicationContext(), "입영 일자를 설정해주세요.", Toast.LENGTH_SHORT).show();
                }

                else if (user_discharge.length() == 0) {
                    Toast.makeText(getApplicationContext(), "전역 일자를 설정해주세요.", Toast.LENGTH_SHORT).show();
                } else {

                    Cursor cursor = db.rawQuery("select * from membership where _id = '" + userID + "' OR _phone='" + userPhone + "'", null);

                    if (cursor.getCount() >= 1) {
                        Toast.makeText(JoinActivity.this, "1인당 1개의 계정을 소유할 수 있습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        try {
                            if (db != null) {
                                db.execSQL("Insert into membership values('" + userID + "'," + "'" + userPass + "','" + userName + "','" + user_armyfield + "','" + user_armynum + "','" + userAge + "','" + userPhone + "','" + getTxt_enlistment + "','" + getTxt_discharge + "');");
                                Toast.makeText(JoinActivity.this, userName + "님 회원가입을 축하합니다", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(JoinActivity.this, LoginActivity.class));
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        army_Field.setOnItemSelectedListener(this);
        item = new String[]{"군 종류를 선택하세요.", "육군", "해군", "공군", "해병대"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.army_item, item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        army_Field.setAdapter(adapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //입대날짜 버튼 부분-----------------------------------------------------------------------------------
    public void InitializeView_enlistment()
    {
        txt_enlistment = (TextView)findViewById(R.id.txt_enlistment);

    }
    public void InitializeListener_enlistment()
    {
        callbackMethod1 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String getYear=String.valueOf(year);
                optionyear=year;
                optionmonth=monthOfYear+1;
                optionday=dayOfMonth;
                String getMonth=String.valueOf(monthOfYear+1);
                if (getMonth=="10"||getMonth=="11"||getMonth=="12")
                {

                }
                else{
                    getMonth="0"+getMonth;
                }
                getDay=String.valueOf(dayOfMonth);

                if (getDay.equals("1")||getDay.equals("2")||getDay.equals("3")||getDay.equals("4")||getDay.equals("5")||getDay.equals("6")||getDay.equals("7")||getDay.equals("8")||getDay.equals("9"))
                {
                    getDay="0"+getDay;

                }else{
                }

                txt_enlistment.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
                getTxt_enlistment=getYear+getMonth+getDay;
            }
        };
    }

    public void OnClickHandler_enlistment(View view)
    {

        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod1, 2019,1,1);

        dialog.show();
    }
    //입대날짜 버튼 부분 end-----------------------------------------------------------------------------------

    //전역날짜 버튼 부분-----------------------------------------------------------------------------------
    public void InitializeView_discharge()
    {
        txt_discharge=(TextView)findViewById(R.id.txt_discharge);

    }
    public void InitializeListener_discharge()
    {
        callbackMethod2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String getYear2=String.valueOf(year);
                String getMonth2=String.valueOf(monthOfYear+1);
                if (getMonth2=="10"||getMonth2=="11"||getMonth2=="12")
                {

                }
                else{
                    getMonth2="0"+getMonth2;
                }
                getDay2=String.valueOf(dayOfMonth);

                if (getDay2.equals("1")||getDay2.equals("2")||getDay2.equals("3")||getDay2.equals("4")||getDay2.equals("5")||getDay2.equals("6")||getDay2.equals("7")||getDay2.equals("8")||getDay2.equals("9"))
                {
                    getDay2="0"+getDay2;
                }else{

                }

                txt_discharge.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
                System.out.println("이거2"+getDay2);
                getTxt_discharge=getYear2+getMonth2+getDay2;



            }
        };
    }

    public void OnClickHandler_discharge(View view)
    {
        minDate.set(optionyear,optionmonth-1,optionday);
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod2,optionyear+1,optionmonth+5,optionday+1);
        dialog.getDatePicker().setMinDate(minDate.getTime().getTime());
        dialog.show();
    }
    //전역날짜 버튼 부분 end--------------------------------------------------------------------------------

}