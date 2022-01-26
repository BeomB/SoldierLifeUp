package com.soldier.soldierlifeup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Community_Fragment extends Fragment {
    MembershipOpenHelper openHelper;
    ViewGroup v;
    Button logout_button;
    SQLiteDatabase db;
    static String tid, tpw, tarmyname, tarmyfield, tarmynumber, tarmyage, tarmyphone,
            tenlistmentday, tdischargeday;
    Cursor cursor;

    static String ob= LoginActivity.id;


    private Button sendbt;
    private EditText editdt;

    public int count = 0;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    private TextView chattingname, chattingname2, chattingTime;
    private ListView listView;
    private ArrayAdapter<String> adapter, adapter2;
    public String input;

    List<Object> Array = new ArrayList<Object>();


    /*private static class TIME_MAXIMUM{
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }
    public static String formatTimeString(long regTime) {
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg = null;
        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금 전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_community_, container, false);
        sendbt = (Button) rootView.findViewById(R.id.btn1);
        editdt = (EditText) rootView.findViewById(R.id.edtTitleText);
        listView = (ListView) rootView.findViewById(R.id.listview);
        chattingname = (TextView) rootView.findViewById(R.id.chattingname);
        chattingname2 = (TextView) rootView.findViewById(R.id.chattingname2);


//        long mNow = System.currentTimeMillis();
//        Date mReDate = new Date(mNow);
//        SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");
//        final String formatDate = mFormat.format(mReDate);

        initDatabase();
        FirebaseApp.initializeApp(this.getActivity());

        adapter =  new ArrayAdapter<String>(this.getActivity(), R.layout.simpleitemc, new ArrayList<String>());
        listView.setAdapter(adapter);


        openHelper = new MembershipOpenHelper(getActivity());
        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from membership where _id= '" + ob + "' ", null);
        tid = "";
        tarmyfield = "";

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

        editdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input = editdt.getText().toString();
                if (input.length() > 0) {
                    sendbt.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.shape_of_chatting_btn_change));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (input.length() == 0) {
                    sendbt.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.shape_of_chatting_btn));
                }
            }
        });

        if (tarmyfield.equals("육군")) {
//            Toast.makeText(getActivity(), "육군 채팅방입니다.", Toast.LENGTH_SHORT).show();

            chattingname.setText(tarmyfield + "채팅방 사용중");
            chattingname2.setText("※ 욕설/비방/군사기밀 누설 시 처벌의 대상이 될 수 있습니다 ※");
            sendbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String msg;
                    if(editdt.length() != 0){
                        msg = tid  + "\n" + editdt.getText().toString();
                        databaseReference.child("roka").push().setValue(msg);
                        editdt.setText("");
                    }
                }
            });
            mReference = mDatabase.getReference("roka");
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String msg2 = messageData.getValue().toString();
                        Array.add(msg2);
                        adapter.add(msg2);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(adapter.getCount() - 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (tarmyfield.equals("해군")) {
            chattingname.setText(tarmyfield + "채팅방 사용중");
            chattingname2.setText("※ 욕설/비방/군사기밀 누설 시 처벌의 대상이 될 수 있습니다 ※");

            sendbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String msg;
                    if(editdt.length() != 0){
                        msg = tid  + "\n" + editdt.getText().toString();
                        databaseReference.child("rokn").push().setValue(msg);
                        editdt.setText("");
                    }
//                listView.setBackgroundColor(Color.parseColor("#545252"));
//                adapter = new ArrayAdapter<String>(getActivity(), R.layout.simpleitemc2, new ArrayList<String>());
                }
            });
            mReference = mDatabase.getReference("rokn");
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String msg2 = messageData.getValue().toString();
                        Array.add(msg2);
                        adapter.add(msg2);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(adapter.getCount() - 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (tarmyfield.equals("공군")) {
            chattingname.setText(tarmyfield + "채팅방 사용중");
            chattingname2.setText("※ 욕설/비방/군사기밀 누설 시 처벌의 대상이 될 수 있습니다 ※");
            sendbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String msg;
                    if(editdt.length() != 0){
                        msg = tid  + "\n" + editdt.getText().toString();
                        databaseReference.child("rokaf").push().setValue(msg);
                        editdt.setText("");
                    }
                }
            });
            mReference = mDatabase.getReference("rokaf");
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String msg2 = messageData.getValue().toString();
                        Array.add(msg2);
                        adapter.add(msg2);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(adapter.getCount() - 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (tarmyfield.equals("해병대")) {
            chattingname.setText(tarmyfield + "채팅방 사용중");
            chattingname2.setText("※ 욕설/비방/군사기밀 누설 시 처벌의 대상이 될 수 있습니다 ※");
            sendbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String msg;
                    if(editdt.length() != 0){
                        msg = tid  + "\n" + editdt.getText().toString();
                        databaseReference.child("rokmc").push().setValue(msg);
                        editdt.setText("");
                    }
//                listView.setBackgroundColor(Color.parseColor("#545252"));
//                adapter = new ArrayAdapter<String>(getActivity(), R.layout.simpleitemc2, new ArrayList<String>());
                }
            });
            mReference = mDatabase.getReference("rokmc");
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String msg2 = messageData.getValue().toString();
                        Array.add(msg2);
                        adapter.add(msg2);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(adapter.getCount() - 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return rootView;
    }

    private void initDatabase() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");
        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }


}