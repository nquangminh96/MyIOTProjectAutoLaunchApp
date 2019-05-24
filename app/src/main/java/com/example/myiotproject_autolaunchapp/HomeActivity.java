package com.example.myiotproject_autolaunchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

public class HomeActivity extends AppCompatActivity {
    public static final String IDHOME = "Home1";
    public static final String nameRoom = "Phòng Khách";
    TextView txtDate, txtTime, txtTemp, txtHumidity , txtRoom;
    CardView cardViewDevice, cardViewSetup;

    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findView();
        setDateAndTime();
        txtRoom.setText(nameRoom);
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.TEMP).child(allKeyStringsInApp.NOWTEMP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue().toString();
                temp = temp + (char) 0x00B0 + "C";
                txtTemp.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.INFO).child(allKeyStringsInApp.HUMIDITY).child(allKeyStringsInApp.NOWHUMIDITY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humidity = dataSnapshot.getValue().toString();
                humidity = humidity + "%";
                txtHumidity.setText(humidity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        cardViewDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListDeviceActivity.class);
                intent.putExtra("idHome" , IDHOME);
                intent.putExtra("room" , nameRoom);
                startActivity(intent);
            }
        });
        cardViewSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingParamActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findView() {
        txtDate = findViewById(R.id.textDate);
        txtTime = findViewById(R.id.textTime);
        txtTemp = findViewById(R.id.txtNhietDo);
        txtHumidity = findViewById(R.id.txtDoAm);
        cardViewDevice = findViewById(R.id.cardDevice);
        cardViewSetup = findViewById(R.id.cardSetup);
        txtRoom = findViewById(R.id.textRoom);
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
