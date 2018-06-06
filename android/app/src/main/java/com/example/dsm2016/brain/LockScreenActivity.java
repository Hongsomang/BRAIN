package com.example.dsm2016.brain;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dsm2016.brain.DB.DB_Qna;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-05-04.
 */

public class LockScreenActivity extends AppCompatActivity {
    private Button lock;
    private TextView title;
    private Realm mRealm;
    private int id=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.acitivity_lock_screen);
        title=(TextView)findViewById(R.id.lock_qna) ;
        Realm();
        DB_Qna db_qna=mRealm.where(DB_Qna.class).findFirst();
        title.setText(db_qna.getQuestion());
        Log.d("실행","제발");
        lock=(Button)findViewById(R.id.lock_btn);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                finish();
            }
        });
    }
    public void Realm(){
        try{
            mRealm = Realm.getDefaultInstance();
        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }

    }



}
