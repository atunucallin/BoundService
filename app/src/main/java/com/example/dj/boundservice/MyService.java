package com.example.dj.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service{
    private final IBinder mbinder = new LocalService();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }


    public class LocalService extends Binder{

        MyService getService(){
            return MyService.this;
        }


    }


    public String getFirstMessage(){
        return " hello first message";
    }


    public String getSecondMessage(){
        return " hello Second message";
    }

}
