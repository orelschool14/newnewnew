package com.example.myapplication345;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {
private Button play;
private  ProgressDialog progressDialog;
private Button music;
private MediaPlayer mediaPlayer;
private TextView battery;
private BroadcastReceiver batterylevelreceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        battery.setText(String.valueOf(level)+"%");
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.play);
        music=findViewById(R.id.music);



        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.song1);

        progressDialog= new ProgressDialog(this);

        progressDialog.setTitle("Loading");

music.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        mediaPlayer.setLooping(true);
        mediaPlayer.start();}




});

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));

                progressDialog.setMessage("Please wait loading");

                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
progressDialog.dismiss();
                    }
                },5000);
            }
        });
        battery = (TextView)findViewById(R.id.batterylevel);
        this.registerReceiver(this.batterylevelreceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


    }
    }
