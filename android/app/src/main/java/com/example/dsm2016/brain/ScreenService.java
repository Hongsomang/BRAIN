package com.example.dsm2016.brain;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ghdth on 2018-05-26.
 */

public class ScreenService extends Service {
    private ScreenReciver mReciver = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mReciver = new ScreenReciver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReciver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent != null) {
            if (intent.getAction() == null) {

                if (mReciver == null) {

                    mReciver = new ScreenReciver();

                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);

                    registerReceiver(mReciver, filter);

                }


            }

        }
        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mReciver!=null){
            unregisterReceiver(mReciver);
        }
    }
}