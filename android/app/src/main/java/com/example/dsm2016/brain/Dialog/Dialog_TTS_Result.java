package com.example.dsm2016.brain.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.app.DialogFragment;

import com.example.dsm2016.brain.DB.DB_TTS_check;
import com.example.dsm2016.brain.DBActivity;
import com.example.dsm2016.brain.R;

/**
 * Created by ghdth on 2018-06-27.
 */

public class Dialog_TTS_Result extends DialogFragment {
    private Button check_btn;
    private TextView check_tv;
    private int result=0;
    private View view;
    public Dialog_TTS_Result(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        view=inflater.inflate(R.layout.dialog_tts_result,container);
        check_btn=(Button)view.findViewById(R.id.tts_chekck_btn);
        check_tv=(TextView)view.findViewById(R.id.result_tv);

        Bundle bundle=getArguments();
        String key=bundle.getString("key");

        Log.d("key_tts",key);


        DBActivity.mRealm.init(getActivity());


        DBActivity.Realm();
        DB_TTS_check db_tts_check=DBActivity.mRealm.where(DB_TTS_check.class).equalTo("key",key).findFirst();
        result=db_tts_check.getTTS_answer();
        Log.d("result_tts",String.valueOf(result));
        check_tv.setText(String.valueOf(result));

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                ((Activity)getActivity()).finish();
            }
        });

        return view;

    }
}
