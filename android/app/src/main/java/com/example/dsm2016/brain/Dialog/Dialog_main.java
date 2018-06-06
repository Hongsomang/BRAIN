package com.example.dsm2016.brain.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dsm2016.brain.DB.DB_Qna;
import com.example.dsm2016.brain.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-04-27.
 */

public class Dialog_main extends Dialog {
   private EditText question_et,answer_et;
   private Button save_btn,cancel_btn;

    private static final String TAG = Dialog_main.class.getSimpleName();
    private Realm mRealm;
    private Context context;
    private int id=0;
    public Dialog_main(final Context context){
        super(context);
        this.context=context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawableResource(R.drawable.cover_white2);

        setContentView(R.layout.dialog_plus);
        question_et=(EditText)findViewById(R.id.question_et);
        answer_et=(EditText)findViewById(R.id.answer_et);
        save_btn=(Button)findViewById(R.id.plus_save);
        cancel_btn=(Button) findViewById(R.id.plus_cancel);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                Toast.makeText(context,"저장",Toast.LENGTH_LONG).show();
                final String question=question_et.getText().toString();
                final String answer=answer_et.getText().toString();

                Realm.init(context);
                Realm();
                mRealm.beginTransaction();
                DB_Qna qna=mRealm.createObject(DB_Qna.class);
                qna.setQuestion(question);
                qna.setAnswer(answer);
                qna.setId(id);
                mRealm.commitTransaction();
                RealmResults<DB_Qna> results=mRealm.where(DB_Qna.class).findAll();

                for(int i=0;i<results.size();i++){
                    DB_Qna db_qna=results.get(i);
                    Log.d(TAG, "protocol : " +db_qna);
                    Log.d(TAG, "protocol : " +db_qna.getQuestion());
                    Log.d(TAG, "protocol : " +db_qna.getAnswer());
                    Log.d(TAG, "protocol : " +db_qna.getId());



                }
                question_et.setText(null);
                answer_et.setText(null);
                //init(question,answer);
                dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_et.setText(null);
                answer_et.setText(null);
                Toast.makeText(context,"취소",Toast.LENGTH_LONG).show();
                dismiss();
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



