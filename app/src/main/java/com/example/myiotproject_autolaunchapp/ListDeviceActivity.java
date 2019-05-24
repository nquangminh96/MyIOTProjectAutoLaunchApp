package com.example.myiotproject_autolaunchapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DialogTitle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myiotproject_autolaunchapp.Adapter.GridViewDeviceAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListDeviceActivity extends AppCompatActivity {
    public static final String IDHOME = "Home1";
    public static final String nameRoom = "Phòng Khách";
    TextView txtDate, txtTime, txtTemp, txtHumidity , txtRoom;
    CardView cardViewDevice, cardViewSetup;
    GridView gridView;
    DatabaseReference myData;
    GridViewDeviceAdapter gridViewDeviceAdapter;
    ArrayList<String> dataGridView = new ArrayList<>();
    ArrayList<String> dataInserver = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_device);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findView();
        setDateAndTime();
        txtRoom.setText(nameRoom);
        gridViewDeviceAdapter = new GridViewDeviceAdapter(this, R.layout.item_gridview_device , dataGridView);
        gridView.setAdapter(gridViewDeviceAdapter);
        myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.DEVICE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataGridView.clear();
                for (DataSnapshot postData : dataSnapshot.getChildren()) {
                    //ThietBi thietBi = new ThietBi(postData.getKey(), Integer.parseInt(postData.getValue().toString()));
                    String name = postData.getKey();
                    dataGridView.add(name);
                    gridViewDeviceAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String deviceName = dataGridView.get(position);
                myData.child(allKeyStringsInApp.LISTID).child(IDHOME).child(nameRoom).child(allKeyStringsInApp.DEVICE).child(deviceName).child("Type").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int type = Integer.parseInt(dataSnapshot.getValue().toString());
                        if (type == 5){
                            Dialog dialog = new Dialog(ListDeviceActivity.this);
                            dialog.setContentView(R.layout.dialog_curtain);
                            dialog.show();
                        }
                        else {
                            Dialog dialog = new Dialog(ListDeviceActivity.this);
                            dialog.setContentView(R.layout.dialog_device);
                            dialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private void findView() {
        txtDate = findViewById(R.id.textDate);
        txtTime = findViewById(R.id.textTime);
        txtRoom = findViewById(R.id.textRoom);
        gridView = findViewById(R.id.grdDevice);
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
