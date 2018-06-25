package com.example.dsm2016.brain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.dsm2016.brain.Fragment.Fragment_Result_All;
import com.example.dsm2016.brain.Fragment.Fragment_Result_Qna;
import com.example.dsm2016.brain.Fragment.Fragment_Result_Test;

/**
 * Created by ghdth on 2018-06-16.
 */

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    private Button result_all,result_test,result_qna;
    private android.app.FragmentManager fm;
    private android.app.FragmentTransaction ft;
    private Fragment_Result_All fragment1;
    private Fragment_Result_Test fragment2;
    private Fragment_Result_Qna fragment3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_all=(Button)findViewById(R.id.result_btn1);
        result_test=(Button)findViewById(R.id.result_btn2);
        result_qna=(Button)findViewById(R.id.result_btn3);

        result_all.setOnClickListener(this);
        result_test.setOnClickListener(this);
        result_qna.setOnClickListener(this);

        fragment1=new Fragment_Result_All();
        fragment2=new Fragment_Result_Test();
        fragment3=new Fragment_Result_Qna();

        setlayout(0);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.result_btn1:
                setlayout(0);
                break;
            case R.id.result_btn2:
                setlayout(1);
                break;
            case R.id.result_btn3:
                setlayout(2);
                break;
        }
    }
    public void setlayout(int n){
        switch (n){
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment1).commit();
                result_all.setBackgroundResource(R.drawable.radio_btn_red);
                result_qna.setBackgroundResource(R.drawable.radio_btn_gray);
                result_test.setBackgroundResource(R.drawable.radio_btn_gray);
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment2).commit();
                result_all.setBackgroundResource(R.drawable.radio_btn_gray);
                result_qna.setBackgroundResource(R.drawable.radio_btn_gray);
                result_test.setBackgroundResource(R.drawable.radio_btn_red);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment3).commit();
                result_all.setBackgroundResource(R.drawable.radio_btn_gray);
                result_qna.setBackgroundResource(R.drawable.radio_btn_red);
                result_test.setBackgroundResource(R.drawable.radio_btn_gray);


        }
    }
}

