package com.example.dsm2016.brain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsm2016.brain.Adapter.TTSDialogListener;
import com.example.dsm2016.brain.DB.DB_Result_All;
import com.example.dsm2016.brain.DB.DB_TTS_check;
import com.example.dsm2016.brain.Dialog.Dialog_TTS_Result;
import com.example.dsm2016.brain.Dialog.Dialog_TTS_Start;
import com.example.dsm2016.brain.Dialog.Dialog_test_result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ghdth on 2018-06-26.
 */

public class TTSAcitivity extends AppCompatActivity{
    Timer tt;
    private TextView minute,second;
    private TextToSpeech tts;
    private int m=1,s=0;
    private ImageView tts_btn;
    private Random rd;
    private int answer_id;
    private Button next_btn;
    private EditText anwser_et;
    private Boolean start=false;
    private int count=0,key_count; //맞은 개수,key뒤에 들어가는 숫자

    private long mNow;
    private Date mDate;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    private Dialog_TTS_Result dialog_tts_result;
    private String[] answer={
          "햇땅콩",  "꽃무늬 ", "고구마" , "닌텐도" , "가오리", "루비듐", "무궁화" ,
            "넥타이", "샥스핀", "삼겹살", "단팥빵", "물갈퀴","백설기","산토끼","도라지"

    };
    private String[] content={
        "콩땅햇","늬무꽃","마구고","도텐닌","리오가","듐비루","화궁무",
            "넥타이","핀스샥","살겹삼","빵팥단","퀴갈물","기설백","끼토산","지라도"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        minute=(TextView)findViewById(R.id.minute);
        second=(TextView)findViewById(R.id.second);
        tts_btn=(ImageView)findViewById(R.id.tts_btn);
        next_btn=(Button)findViewById(R.id.tts_next_btn);
        anwser_et=(EditText) findViewById(R.id.tts_anwser_et);

        Dialog_TTS_Start dialog=new Dialog_TTS_Start(this);

        dialog.setDialogListener(new TTSDialogListener() {
            @Override
            public void onPositiveClicked(Boolean start_b) {
                timer_start(start_b);

            }
        });
        dialog.show();



        random();
        Log.d("random_answer_id",String.valueOf(answer_id));
        tts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setSpeechRate(0.5f);

                TTS(answer_id);
            }
        });

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });




        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer_string=anwser_et.getText().toString();
                Log.d("answer_string",answer_string);
                next(s,answer_id,answer_string);
            }
        });

        DBActivity.mRealm.init(getApplicationContext());

    }
    public void timer_start(boolean sstart){
        if(sstart==true){
            Log.d("시작","시작");
            minute.setText(String.valueOf(m));
            timer();
            TTS(answer_id);

        }
    }
    public void next(int time ,int id,String anwser_key){
        Log.d("anwser:",anwser_key);
        if(time!=0){
            Log.d("time_content",content[id]);
            Log.d("tiem_anwsere_key",anwser_key);
            if(answer[id].equals(anwser_key)){
                Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_LONG).show();
                random();
                TTS(answer_id);
                Log.d("count:맞은 개수",String.valueOf(count));
                count++;
            }
            else{
                Toast.makeText(getApplicationContext(),"다시입력해주세요",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"시간 초과",Toast.LENGTH_LONG).show();

        }

    }

    public void random(){
        rd=new Random();
        answer_id=rd.nextInt(14);
        Log.d("randomㄴㄴㄴ:",String.valueOf(answer_id));
    }





    public void TTS(int i){
        tts.speak(content[i],TextToSpeech.QUEUE_FLUSH,null);


    }


    //타이머
    public  void timer(){

        tt=new Timer();
        tt.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(m==0){
                            second.setText(String.valueOf(s));
                            s--;
                            if(s<10){
                                second.setText("0"+String.valueOf(s));
                                if(s==0){
                                    tt.cancel();
                                    finish_btn();
                                }
                            }
                        }
                        else{
                            m--;
                            minute.setText(String.valueOf(m));
                            s=59;

                        }
                    }
                });

            }
        },1000,1000);
    }


   /* Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int div = msg.what;
            if(m==0){
                second.setText(String.valueOf(s));
                s--;
                if(s<10){
                    second.setText("0"+String.valueOf(s));
                    if(s==0){
                        onDestroy();
                        finish_btn();
                    }
                }
            }
            else{
                m--;
                minute.setText(String.valueOf(m));
                s=59;

            }
            handler.sendEmptyMessageDelayed(1,1000);

        }
    };*/

    @Override
    protected void onDestroy() {
        if (tts != null) {

            tts.stop();
            tts.shutdown();
        }


        super.onDestroy();
        Log.d("onDestroy","실행");
    }


    public String Date(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);

        return mFormat.format(mDate);
    }

    public void finish_btn(){
        String date =Date();
        key_count++;

        DBActivity.Realm();
        DBActivity.mRealm.beginTransaction();

        DB_TTS_check db_tts_check=DBActivity.mRealm.createObject(DB_TTS_check.class);
        db_tts_check.setDate(date);
        db_tts_check.setKey("TTS"+key_count);
        db_tts_check.setTTS_answer(count);

        DB_Result_All db_result_all=DBActivity.mRealm.createObject(DB_Result_All.class);
        db_result_all.setKind(1);
        db_result_all.setKey("TTS"+key_count);

        DBActivity.mRealm.commitTransaction();

        Toast.makeText(getApplicationContext(),"끝",Toast.LENGTH_LONG).show();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tr=fm.beginTransaction();
        dialog_tts_result=new Dialog_TTS_Result();
        Bundle bundle =new Bundle();
        bundle.putString("key","TTS"+key_count);
        dialog_tts_result.setArguments(bundle);
       // dialog_tts_result.show(tr);
    }

}
