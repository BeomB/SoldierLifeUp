package com.soldier.soldierlifeup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ArmySale extends AppCompatActivity {
    static String tname;
    String a;
    static String rLocal, rFacility, reventName, rDetail, rHomepage, rTel, rstartDate, rendDate;   // D
    private MyDBHelper mHelper;
    private SQLiteDatabase db;
    private final static int DATABASE_VERSION = 1;
    private Button btnSearch;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armysale);
        Intent intent = getIntent();


        mHelper = new MyDBHelper(this, DATABASE_VERSION);
        db = mHelper.getWritableDatabase();

        final ListView listview = (ListView) findViewById(R.id.Armysalelistview);

        final List<String> list = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        Cursor cursor = db.rawQuery("select distinct `시설명` from `armysale` ", null);
        tname = "";

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            tname += name;
            list.add(tname);
            tname = "";
        }
        listview.setAdapter(adapter);
        cursor.close();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override   // position 으로 몇번째 것이 선택됐는지 값을 넘겨준다
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object ob = (Object) parent.getAdapter().getItem(position);
                Intent it = new Intent(ArmySale.this, ArmySaleInformation.class);   // 인텐트 처리
                it.putExtra("list", list.get(position));
                startActivity(it);
                SQLiteDatabase db;
                db = mHelper.getWritableDatabase();
                Cursor cursor;
                cursor = db.rawQuery("select `지역` ,`시설명` , `할인행사명` , `시작일`,`종료일`, `연락처`, `홈페이지`, `상세설명` from `armysale` WHERE `시설명` ='"+ob+"' ", null);

                rLocal = "";
                rFacility = "";
                reventName= "";
                rstartDate= "";
                rendDate= "";
                rTel= "";
                rHomepage= "";
                rDetail= "";





                while (cursor.moveToNext()) {
                    String tLocal = cursor.getString(0);
                    String tFacility = cursor.getString(1);
                    String teventName = cursor.getString(2);
                    String tstartDate = cursor.getString(3);
                    String tendDate = cursor.getString(4);
                    String tTel = cursor.getString(5);
                    String tHomepage = cursor.getString(6);
                    String tDetail = cursor.getString(7);

                    rLocal += tLocal;
                    rFacility += tFacility;
                    reventName += teventName;
                    rstartDate += tstartDate;
                    rendDate += tendDate;
                    rTel += tTel;
                    rHomepage += tHomepage;
                    rDetail += tDetail;

                    cursor.close();
                }
            }
        });

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        ArrayAdapter<String> searchList = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        autoCompleteTextView.setAdapter(searchList);

        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    a = autoCompleteTextView.getText().toString().trim();
                    autoCompleteTextView.setText("");

                    return true;
                }
                return false;
            }
        });

        btnSearch = (Button) findViewById(R.id.edtBtn);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = true;
                db = mHelper.getWritableDatabase();
                a = autoCompleteTextView.getText().toString().trim();
                autoCompleteTextView.setText("");
                if (a.equals("")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (a.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "검색 할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                }  else {

                    while( flag ) {
                        Cursor cursor = db.rawQuery("SELECT `시설명` from `armysale` where `시설명` LIKE '%"+a+"%'",null);
                        tname = "";
                        list.clear();

                        while (cursor.moveToNext()) {
                            String name = cursor.getString(0);
                            tname += name;
                            list.add(tname);
                            tname = "";
                        }
                        listview.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(),"'"+a+"'로 검색된 제목 결과입니다.",Toast.LENGTH_SHORT).show();
                        cursor.close();

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override   // position 으로 몇번째 것이 선택됐는지 값을 넘겨준다
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                SQLiteDatabase db;
                                db = mHelper.getWritableDatabase();
                                Object ob = (Object) parent.getAdapter().getItem(position);
                                Intent it = new Intent(ArmySale.this, ArmySaleInformation.class);   // 인텐트 처리
                                it.putExtra("list", list.get(position));
                                startActivity(it);

                                Cursor cursor;
                                cursor = db.rawQuery("select `지역` ,`시설명` , `할인행사명` , `시작일`,`종료일`, `연락처`, `홈페이지`, `상세설명` from `armysale` WHERE `시설명`='" + ob + "' ", null);

                                rLocal = "";
                                rFacility = "";
                                reventName= "";
                                rstartDate= "";
                                rendDate= "";
                                rTel= "";
                                rHomepage= "";
                                rDetail= "";

                                while (cursor.moveToNext()) {
                                    String tLocal = cursor.getString(0);
                                    String tFacility = cursor.getString(1);
                                    String teventName = cursor.getString(2);
                                    String tstartDate = cursor.getString(3);
                                    String tendDate = cursor.getString(4);
                                    String tTel = cursor.getString(5);
                                    String tHomepage = cursor.getString(6);
                                    String tDetail = cursor.getString(7);

                                    rLocal += tLocal;
                                    rFacility += tFacility;
                                    reventName += teventName;
                                    rstartDate += tstartDate;
                                    rendDate += tendDate;
                                    rTel += tTel;
                                    rHomepage += tHomepage;
                                    rDetail += tDetail;


                                    list.clear();
                                    cursor = db.rawQuery("select distinct `시설명` from `armysale` ", null);
                                    tname = "";

                                    while (cursor.moveToNext()) {
                                        String name = cursor.getString(0);
                                        tname += name;
                                        list.add(tname);
                                        tname = "";
                                    }
                                    listview.setAdapter(adapter);
                                    cursor.close();
                                    mHelper.close();
                                }
                            }
                        });
                        flag = false;
                    }
                }
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override   // position 으로 몇번째 것이 선택됐는지 값을 넘겨준다
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object ob = (Object) parent.getAdapter().getItem(position);
                Intent it = new Intent(ArmySale.this, ArmySaleInformation.class);   // 인텐트 처리
                it.putExtra("list", list.get(position));
                startActivity(it);
                db = mHelper.getWritableDatabase();
                Cursor cursor;
                cursor = db.rawQuery("select `지역` ,`시설명` , `할인행사명` , `시작일`,`종료일`, `연락처`, `홈페이지`, `상세설명` from `armysale` WHERE `시설명` = '" + ob + "' ", null);

                rLocal = "";
                rFacility = "";
                reventName= "";
                rstartDate= "";
                rendDate= "";
                rTel= "";
                rHomepage= "";
                rDetail= "";


                while (cursor.moveToNext()) {
                    String tLocal = cursor.getString(0);
                    String tFacility = cursor.getString(1);
                    String teventName = cursor.getString(2);
                    String tstartDate = cursor.getString(3);
                    String tendDate = cursor.getString(4);
                    String tTel = cursor.getString(5);
                    String tHomepage = cursor.getString(6);
                    String tDetail = cursor.getString(7);

                    rLocal += tLocal;
                    rFacility += tFacility;
                    reventName += teventName;
                    rstartDate += tstartDate;
                    rendDate += tendDate;
                    rTel += tTel;
                    rHomepage += tHomepage;
                    rDetail += tDetail;
                }
            }
        });

    }
}
