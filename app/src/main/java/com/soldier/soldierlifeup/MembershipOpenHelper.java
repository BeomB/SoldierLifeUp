package com.soldier.soldierlifeup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MembershipOpenHelper extends SQLiteOpenHelper {
    Context context;

    public MembershipOpenHelper(Context context) {
        super(context, "membership.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "create table membership (" +
                    "_id varchar(40) not null primary key," +
                    "_pw varchar(72) not null , _name varchar(40) not null, _armyfield varchar(40) not null,_armynum varchar(72) not null,  _age varchar(40) not null, _phone varchar(72) not null, _enlistment varchar(72) not null, _discharge varchar(72) not null)";
            db.execSQL(sql);
            //Toast.makeText(context, "[회원정보] 테이블 생성 ", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}