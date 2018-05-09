package com.example.dj.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyService myService;
    boolean isbind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Intent i = new Intent(this,MyService.class);
        bindService(i,connection, Context.BIND_AUTO_CREATE);
    }





    public void getSecondServiceMessage(View view) {
        String message;
        message = myService.getSecondMessage();
        textView.setText(message);


    }

    public void getFirstMessage(View view) {
        String message;
        message = myService.getFirstMessage();
        textView.setText(message);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.LocalService localService = (MyService.LocalService) service;
            myService = localService.getService();
            isbind = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isbind = false;

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isbind){
            unbindService(connection);
            isbind = false;
        }
    }
}
