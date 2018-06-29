package com.example.dsm2016.brain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsm2016.brain.DB.DB_Result_All;
import com.example.dsm2016.brain.DB.DB_Test;
import com.example.dsm2016.brain.DB.DB_Test_Check;
import com.example.dsm2016.brain.Dialog.Dialog_test_result;
import com.example.dsm2016.brain.Item.Item_Dialog_test_reuslt;
import com.example.dsm2016.brain.Item.Item_Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-05-11.
 */

public class TestAcitivity extends AppCompatActivity {
    private Button back,next;
    private TextView question_num, question_content;
    private RadioGroup radioGroup;
    private RadioButton forget_btn,dementia_btn;
    private ArrayList<Item_Test> item_tests=new ArrayList<>();
    private List<String> mlist;
    private Realm mRealm=null;
    private LinearLayout linearLayout;
    int count=0;

    private long mNow;
    private Date mDate;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private String[] qustion={
            "물건 잘못 놓기",
            "언어장애",
            "시간 장소에 대한 언어장애",
            "직업 수행에 영향을 주는 기억장애",
            "친숙하던 작업수행능력 저하",
            "판단력 장애",
            "기분 또는 행동의 변화",
            "성격 변화",
            "추상적 사고능력의 장애",
            "동기 상실"
    };
    private String[] foget={
            "열쇠나 지갑",
            "평소 하던 말이 쉽게 잘 안떠오른다.",
            "지금 어디로 가야하는지, 오늘이 무슨 요일인지 깜박 잊는다.",
            "요리하려고 준비한 고기, 당근 등을 냉장고에 놓아두고 그냥 식사 준비를 한다.",
            "빨래를 하면서 주전자 끓는 것을 잊는 등 어떤 일에 몰두하면 일시적으로 다른일을 잊어버린다.",
            "비가 오면 슬퍼지는 등 때에 따라 감성과 행동이 변한다.",
            "나이가 들면서 환경에 따라 조금씩 성격이 변한다.",
            "신용카드 명세서나 세금계산서 정리에 곤란을 겪는다.",
            "신용카드 명세서나 세금계산서 정리에 곤란을 겪는다.",
            "가정이나 직장 일에 지쳐 있다가 시간이 지나면 다시 의욕이 솟는다."

    };
    private String[] dementia={
            "다리미를 냉장고 안에 집어넣거나 커피 통에 손목시계를 둔다.",
            "전혀 엉뚱한 단어를 사용해 문장 전체가 이해되지 않는다. 아주 쉬운 단어도 생각나지 않는다.",
            "집으로 가는 길을 잃거나 직장 근처에서 사무실을 찾느라 헤맨다.",
            "사업계약 등 약속을 까맣게 잊고 그 사실도 생각나지 않는다.",
            "요리 순서를 기억 못하고 자신이 요리를 준비했다는 사실조차 잊어버린다.",
            "아이를 업은 채로 셔츠를 몇 벌 겹쳐 입는다.",
            "감정변화가 급격하다. 조용히 있던 사람이 갑자기 통곡하거나 격렬히 화를 낸다.",
            "갑자기 대인관계를 기피하는 겁먹은 사람이 되거나 남을 많이 의심한다.",
            "은행에서 인출하는데 비밀번호가 생각나지 않거나 출금방법을 몰라 쩔쩔맨다.",
            "무기력증에 빠져 수동적이 되거나 일을 하도록 북돋아줘야 일을 하게 된다."
    };
    private Dialog_test_result dialog_test_result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        back=(Button)findViewById(R.id.test_back);
        next=(Button)findViewById(R.id.test_next_btn);
        //linearLayout=(LinearLayout)findViewById(R.id.result_layout);
        question_num=(TextView)findViewById(R.id.question_num);
        question_content=(TextView)findViewById(R.id.question_content);

        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);

        forget_btn=(RadioButton)findViewById(R.id.forget_btn);
        dementia_btn=(RadioButton)findViewById(R.id.dementia_btn);



        next.setVisibility(View.GONE);

        mlist=new ArrayList<>();
//        linearLayout.setVisibility(View.GONE);
        Log.d("tag","제제발");
        try{
            mRealm = Realm.getDefaultInstance();

        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
        RealmResults<DB_Test> results=mRealm.where(DB_Test.class).findAll();
        Log.d("Db_test:",String.valueOf(results.size()));
        if(results.size()==0){
            Log.d("tag","제발");
            Realm.init(TestAcitivity.this);
            try {
                mRealm=Realm.getDefaultInstance();

            }catch (Exception e){
                RealmConfiguration config=new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();
                mRealm=Realm.getInstance(config);
            }
            mRealm.beginTransaction();
            for(int i=0;i<10;i++){
                DB_Test db_test=mRealm.createObject(DB_Test.class);
                db_test.setId(i+1);
                db_test.setQuestion_content(qustion[i]);
                db_test.setForget_content(foget[i]);
                db_test.setDementia_content(dementia[i]);
                Log.d((i+1)+"번",qustion[i]+" "+foget[i]+" "+dementia[i]);
            }
            mRealm.commitTransaction();


        }
        Log.d("countㄴㄴㄴㄴㄴㄴㄴ:",String.valueOf(count));
        content();

       /* for(int i=1;i<=10;i++){
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int id) {
                    if(id==R.id.forget_btn){
                        next.setVisibility(View.VISIBLE);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("gk","건방증");
                            }
                        });
                    }
                    else if(id==R.id.dementia_btn){
                        next.setVisibility(View.VISIBLE);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("gk","치매");
                            }
                        });
                    }
                }
            });
        }*/




    }
    public void content(){
        count++;
        Log.d("countㄴㄴㄴㄴㄴㄴㄴ:",String.valueOf(count));
       if(count>10){

            finish_btn();

       }else{
           Realm();
           DB_Test db_test=mRealm.where(DB_Test.class).equalTo("id",count).findFirst();
           Log.d("db_test:",db_test.toString());
           question_num.setText(String.valueOf(db_test.getId()));
           question_content.setText(db_test.getQuestion_content());
           forget_btn.setText(db_test.getForget_content());
           dementia_btn.setText(db_test.getDementia_content());
           radio();
       }
    }
    public void radio(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.forget_btn:
                        next.setVisibility(View.VISIBLE);
                        next_btn("건망증");
                        break;
                    case R.id.dementia_btn:
                        next.setVisibility(View.VISIBLE);
                        next_btn("치매");
                        break;
                }
            }
        });
    }
    public void next_btn(final String value){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlist.add(value);
                content();
            }
        });
    }

    public void finish_btn(){
        Count_Test c=new Count_Test();
        String date=Date();
        Realm();
        mRealm.beginTransaction();
        for(int i=0;i<mlist.size();i++){
            DB_Test_Check db_test_check=mRealm.createObject(DB_Test_Check.class);
            db_test_check.setId(i+1);
            db_test_check.setCheck(mlist.get(i));
            db_test_check.setKey("테스트"+c.count());
        }

        DB_Result_All db_result_all=mRealm.createObject(DB_Result_All.class);
        db_result_all.setKey("테스트"+c.count());
        db_result_all.setKind(0);
        db_result_all.setDate(date);
        mRealm.commitTransaction();
        Log.d("Db저장","테스트 내용");

        Toast.makeText(getApplication(),"다이얼로그",Toast.LENGTH_LONG).show();
        dialog_test_result=new Dialog_test_result();
        Bundle bundle=new Bundle();
        bundle.putString("key","test"+c.count());
        dialog_test_result.setArguments(bundle);
        dialog_test_result.show(getFragmentManager(),"test");

    }

    public String Date(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);

        return mFormat.format(mDate);
    }

    public void Realm(){
        try {
            mRealm=Realm.getDefaultInstance();

        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
    }


}
