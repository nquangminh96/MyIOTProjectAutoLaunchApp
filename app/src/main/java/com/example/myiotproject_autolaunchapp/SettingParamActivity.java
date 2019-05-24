package com.example.myiotproject_autolaunchapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingParamActivity extends AppCompatActivity {
    public static final String IDHOME = "Home1";
    public static final String nameRoom = "Phòng Khách";
    TextView txtDate, txtTime, maxT, minT, maxH, minH;
    DatabaseReference myData;
    Button tangMaxT, giamMaxT, tangMinT, giamMinT, tangMaxH, giamMaxH,tangMinH,giamMinH;
    int maxHumi , minHumi , maxTemp , minTemp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settingparam);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getView();
        setDateAndTime();
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child("Max").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humi = dataSnapshot.getValue().toString();
                maxHumi = Integer.parseInt(humi);
                humi = "MAX: " + humi + "%";
                maxH.setText(humi);
                tangMaxH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        maxHumi++;
                        maxH.setText("MAX: " + maxHumi + "%");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).
                                child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child("Max").setValue(maxHumi);
                    }
                });
                giamMaxH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        maxHumi--;
                        maxH.setText("MAX: " + maxHumi + "%");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).
                                child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child("Max").setValue(maxHumi);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child("Min").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humi = dataSnapshot.getValue().toString();
                minHumi = Integer.parseInt(humi);
                humi = "MIN: " + humi + "%";

                minH.setText(humi);
                tangMinH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minHumi++;
                        minH.setText("MIN: " + minHumi + "%");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom)
                                .child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child("Min").setValue(minHumi);
                    }
                });
                giamMinH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minHumi--;
                        minH.setText("MIN: " + minHumi + "%");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom)
                                .child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child("Min").setValue(minHumi);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child("Max").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue().toString();
                maxTemp = Integer.parseInt(temp);
                temp = "MAX: " + temp + (char) 0x00B0 + "C";
                maxT.setText(temp);
                tangMaxT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        maxTemp++;
                        maxT.setText("MAX: " + maxTemp + (char) 0x00B0 + "C");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom)
                                .child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child("Max").setValue(maxTemp);
                    }
                });
                giamMaxT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        maxTemp--;
                        maxT.setText("MAX: " + maxTemp + (char) 0x00B0 + "C");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom)
                                .child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child("Max").setValue(maxTemp);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child("Min").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue().toString();
                minTemp = Integer.parseInt(temp);
                temp = "MIN: " + temp + (char) 0x00B0 + "C";
                minT.setText(temp);
                tangMinT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minTemp++;
                        minT.setText("MIN: " + minTemp + (char) 0x00B0 + "C");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom)
                                .child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child("Min").setValue(minTemp);
                    }
                });
                giamMinT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minTemp--;
                        minT.setText("MIN: " + minTemp + (char) 0x00B0 + "C");
                        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom)
                                .child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child("Min").setValue(minTemp);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getView() {
        txtDate = findViewById(R.id.textDate);
        txtTime = findViewById(R.id.textTime);
        maxH = findViewById(R.id.maxHumi);
        minH = findViewById(R.id.minHumi);
        maxT = findViewById(R.id.maxTemp);
        minT = findViewById(R.id.minTemp);
        tangMaxH = findViewById(R.id.upMaxH);
        giamMaxH = findViewById(R.id.downMaxH);
        tangMinH = findViewById(R.id.upMinH);
        giamMinH = findViewById(R.id.downMinH);
        tangMaxT = findViewById(R.id.upMaxT);
        giamMaxT = findViewById(R.id.downMaxT);
        tangMinT = findViewById(R.id.upMinT);
        giamMinT = findViewById(R.id.downMinT);

        myData = FirebaseDatabase.getInstance().getReference();
    }

    private void setDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        txtDate.setText(currentDate);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String curentTime = format.format(calendar.getTime());
        txtTime.setText(curentTime);
    }
}
