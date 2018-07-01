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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText answer_et;
    private Realm mRealm;
    private String answer,question,answer_db;
    private int id=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acitivity_lock_screen);
        title=(TextView)findViewById(R.id.lock_qna) ;
        answer_et=(EditText)findViewById(R.id.lock_answer);

        Realm();
        DB_Qna db_qna=mRealm.where(DB_Qna.class).findFirst();
        question=db_qna.getQuestion();
        answer_db=db_qna.getAnswer();
        title.setText(db_qna.getQuestion());
        Log.d("실행","제발");
        lock=(Button)findViewById(R.id.lock_btn);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer=answer_et.getText().toString();
                if(answer.equals(answer_db)){
                    Toast.makeText(getApplication(),"성공",Toast.LENGTH_LONG);

                    finish();

                }else{
                    Toast.makeText(getApplication(),"실패",Toast.LENGTH_LONG);
                }
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
