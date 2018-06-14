package com.example.dsm2016.brain.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.dsm2016.brain.R;

/**
 * Created by ghdth on 2018-06-14.
 */

public class Dialog_test_result extends Dialog {
    private  Context context;
    public Dialog_test_result(final Context context){
        super(context);
        this.context=context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        setContentView(R.layout.dialog_test_result);
        getWindow().setBackgroundDrawableResource(R.drawable.cover_white2);


    }
}
