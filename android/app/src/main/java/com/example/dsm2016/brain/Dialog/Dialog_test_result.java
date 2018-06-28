package com.example.dsm2016.brain.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dsm2016.brain.Adapter.Adapter_test;
import com.example.dsm2016.brain.DB.DB_Test_Check;
import com.example.dsm2016.brain.Item.Item_Dialog_test_reuslt;
import com.example.dsm2016.brain.Item.Item_Test_result;
import com.example.dsm2016.brain.R;
import com.example.dsm2016.brain.TestAcitivity;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-06-14.
 */

public class Dialog_test_result extends Dialog {
    private  Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item_Test_result> mItem;
    private Button button;
    private Realm mRealm;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;
    public Dialog_test_result(final Context context){
        super(context);
        this.context=context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        setContentView(R.layout.dialog_test_result);
        getWindow().setBackgroundDrawableResource(R.drawable.cover_white2);
        recyclerView=(RecyclerView)findViewById(R.id.test_result_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        mItem=new ArrayList<>();
        mAdapter=new Adapter_test(context,mItem);
        recyclerView.setAdapter(mAdapter);
        mRealm.init(context);

        button=(Button)findViewById(R.id.dialog_test_result_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                ((Activity)context).finish();

            }
        });
        Item_Dialog_test_reuslt Key_item=new Item_Dialog_test_reuslt();
        String key=Key_item.getKey();
        Log.d("key",key);
        Realm();
        RealmResults<DB_Test_Check> results=mRealm.where(DB_Test_Check.class).equalTo("key",key).findAll();
        for(int i=0;i<results.size();i++){
            DB_Test_Check db_test_check=results.get(i);
            Log.d("DB_Test_Check:",db_test_check.getCheck());
            if(db_test_check.getCheck().equals("건망증")){
                    mItem.add(new Item_Test_result(String.valueOf(i+1),R.drawable.check,R.drawable.not_check));
            }
            else if(db_test_check.getCheck().equals("치매")){
                mItem.add(new Item_Test_result(String.valueOf(i+1),R.drawable.not_check,R.drawable.check));
            }
        }

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
