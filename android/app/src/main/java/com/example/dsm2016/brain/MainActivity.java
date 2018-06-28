package com.example.dsm2016.brain;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsm2016.brain.Adapter.Adapter_main;
import com.example.dsm2016.brain.DB.DB_Qna;
import com.example.dsm2016.brain.Dialog.Dialog_main;
import com.example.dsm2016.brain.Item.Item_Main;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity  {
    Button plus;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Item_Main> list;
    private RecyclerView.Adapter madapter;
    private Dialog_main dialog_main;
    private Realm mRealm;
    private static Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        ///int width = dm.widthPixels; //디바이스 화면 너비
        //int height = dm.heightPixels; //디바이스 화면 높이
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        dialog_main=new Dialog_main(this);
        WindowManager.LayoutParams wm = dialog_main.getWindow().getAttributes();
        wm.copyFrom(dialog_main.getWindow().getAttributes());


        plus=(Button)findViewById(R.id.plus_btn);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_main.show();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        madapter=new Adapter_main(list,getApplicationContext());
        recyclerView.setAdapter(madapter);
        try{
            mRealm = Realm.getDefaultInstance();
        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }

        RealmResults<DB_Qna> results=mRealm.where(DB_Qna.class).findAll();
        for(int i=0;i<results.size();i++){
            DB_Qna db_qna=results.get(i);
            Log.d(TAG, "protocol : " +db_qna);
            list.add(new Item_Main(db_qna.getQuestion(),db_qna.getAnswer()));

        }
       // list.add(new Item_Main("gkgkkkg","fkfkfkkffk"));
       // list.add(new Item_Main("eekekeke","dkdkkdkds"));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*RealmResults<DB_Qna> delete=mRealm.where(DB_Qna.class).findAll();
                mRealm.beginTransaction();
          delete.deleteAllFromRealm();
                mRealm.commitTransaction();*/
                      list.clear();
                //madapter.notifyDataSetChanged();
                try{
                    mRealm = Realm.getDefaultInstance();
                }catch (Exception e){
                    RealmConfiguration config=new RealmConfiguration.Builder()
                            .deleteRealmIfMigrationNeeded()
                            .build();
                    mRealm=Realm.getInstance(config);
                }
                RealmResults<DB_Qna> results=mRealm.where(DB_Qna.class).findAll();
                for(int i=0;i<results.size();i++){
                    DB_Qna db_qna=results.get(i);
                    Log.d(TAG, "protocol : " +db_qna);
                    list.add(new Item_Main(db_qna.getQuestion(),db_qna.getAnswer()));

                }



                madapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
     }


    public void menu_OnClick(View v){
        Log.d("dkdfkdf","dffd");

        PopupMenu popup=new PopupMenu(getApplicationContext(),v);
        getMenuInflater().inflate(R.menu.menu,popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.test:
                        Toast.makeText(getApplication(),"메뉴1", Toast.LENGTH_SHORT).show();
                        intent=new Intent(getApplicationContext(), TestAcitivity.class);
                        startActivity(intent);
                        break;
                    case R.id.exam:
                        Toast.makeText(getApplication(),"메뉴2", Toast.LENGTH_SHORT).show();
                        intent=new Intent(getApplicationContext(),TTSAcitivity.class);
                        startActivity(intent);
                        break;
                    case R.id.result:
                        Toast.makeText(getApplication(),"메뉴3", Toast.LENGTH_SHORT).show();
                        intent =new Intent(getApplicationContext(),ResultActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.setting:
                        Toast.makeText(getApplication(),"메뉴4",Toast.LENGTH_LONG).show();
                        intent=new Intent(getApplicationContext(),SettingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;

                }
                return false;
            }
        });
        popup.show();
    }

}
