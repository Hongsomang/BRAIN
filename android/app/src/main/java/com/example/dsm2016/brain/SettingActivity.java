package com.example.dsm2016.brain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by ghdth on 2018-05-26.
 */

public class SettingActivity extends AppCompatActivity {
    private Switch aSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        aSwitch=(Switch)findViewById(R.id.lock_switch);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    Intent intent=new Intent(SettingActivity.this,ScreenService.class);
                    startService(intent);
                    Toast.makeText(getApplicationContext(),"실행",Toast.LENGTH_LONG).show();

                }
                else{
                    Intent intent=new Intent(SettingActivity.this,ScreenService.class);
                    stopService(intent);
                    Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
