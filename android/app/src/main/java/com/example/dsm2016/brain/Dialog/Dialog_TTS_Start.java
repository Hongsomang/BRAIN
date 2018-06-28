package com.example.dsm2016.brain.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.dsm2016.brain.Adapter.TTSDialogListener;
import com.example.dsm2016.brain.R;
import com.example.dsm2016.brain.TTSAcitivity;

/**
 * Created by ghdth on 2018-06-27.
 */

public class Dialog_TTS_Start extends Dialog{
    private Context context;
    private Button start_btn;
    private TTSDialogListener dialogListener;

    public void setDialogListener(TTSDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }
    public Dialog_TTS_Start(@NonNull Context context) {
        super(context);
        this.context=context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        setContentView(R.layout.dialog_tts_start);
        start_btn=(Button)findViewById(R.id.tts_start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("다이얼로그","start");
                dialogListener.onPositiveClicked(true);
                dismiss();
            }
        });
    }
}
