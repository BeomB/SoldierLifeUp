package com.soldier.soldierlifeup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyPage_Fragment extends Fragment {
    long StartDate = Calendar.getInstance().getTimeInMillis();
    String strFormat = "yyyyMMdd";    //strStartDate 와 strEndDate 의 format


    MembershipOpenHelper openHelper;
    ViewGroup v;

    SQLiteDatabase db;
    static String tid, tpw, tarmyname, tarmyfield, tarmynumber, tarmyage, tarmyphone,
            tenlistmentday, tdischargeday="";
    Cursor cursor;
    TextView armyname, armynumber,armyfield, enlistmentday, dischargeday, armycurrentday, armyfinishday,armypercent;
    static String ob= LoginActivity.id;
    double total=0;
    double current=0;
    double percent=1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = (ViewGroup) inflater.inflate(R.layout.mypage, container, false);

        armyname= (TextView)v.findViewById(R.id.armyname);
        armynumber= (TextView)v.findViewById(R.id.armynumber);
        armyfield= (TextView)v.findViewById(R.id.armyfield);
        enlistmentday= (TextView)v.findViewById(R.id.enlistmentday);
        dischargeday= (TextView)v.findViewById(R.id.dischargeday);
        armycurrentday= (TextView)v.findViewById(R.id.armycurrentday);
        armyfinishday= (TextView)v.findViewById(R.id.armyfinishday);
        armypercent=(TextView)v.findViewById(R.id.armyfinishpercentday);
        System.out.println("---------------------------------------------------------------"+ob+"---------------------------------------------------------------");
        openHelper = new MembershipOpenHelper(getActivity());
        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from membership where _id= '" + ob + "' ", null);
        tarmyname = "";
        tid = "";



        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String pw = cursor.getString(1);
            String armyname = cursor.getString(2);
            String armyfield = cursor.getString(3);
            String armynum = cursor.getString(4);
            String age = cursor.getString(5);
            String phone = cursor.getString(6);
            String enlistmentday = cursor.getString(7);
            String dischargeday = cursor.getString(8);

            tid = id;
            tpw = pw;
            tarmyname = armyname;
            tarmyfield = armyfield;
            tarmynumber = armynum;
            tarmyage = age;
            tarmyphone = phone;
            tenlistmentday = enlistmentday;
            tdischargeday = dischargeday;
        }
        cursor.close();

        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);


        armyname.setText(tarmyname + " [ " + tid + " ] ");
        armynumber.setText(tarmynumber);
        armyfield.setText(tarmyfield);
        enlistmentday.setText(tenlistmentday);
        dischargeday.setText(tdischargeday);

        try{

            Date dischargedate= sdf.parse(tdischargeday);
            Date enlistmentdate=sdf.parse(tenlistmentday);
            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.

            long diffDay = (dischargedate.getTime()-StartDate) / (24*60*60*1000);
            long diffDay2=(StartDate-enlistmentdate.getTime())/(24*60*60*1000); //528일

            long test=dischargedate.getTime()-enlistmentdate.getTime();
            long test2=test/(24*60*60*1000);
            test2=Math.abs(test2);



            armycurrentday.setText((int) diffDay2+"일");//현재벅믈
            armyfinishday.setText((int)(diffDay+1)+"일");//남은
            String m1 = (armycurrentday.getText().toString());
            double math1=(double) diffDay2;
            double math2=(double) diffDay+1;
            total = math1+math2;
            current = math1;
            percent = ((double)(current/total)*100);

            String strNumber=String.format("%.1f",percent);
            armypercent.setText(Double.parseDouble(strNumber)+"%");
            System.out.println("!!!!!!!!!!!!!!!!!"+total+"!!!!!!!!!!!!!!!!!"+current+"!"+percent+"!"+math1+"!"+math2);
        }catch(ParseException e){
            e.printStackTrace();
        }



        return v;
    }
}