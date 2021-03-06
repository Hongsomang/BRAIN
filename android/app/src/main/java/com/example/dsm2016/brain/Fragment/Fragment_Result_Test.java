package com.example.dsm2016.brain.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dsm2016.brain.Adapter.Adapter_Result;
import com.example.dsm2016.brain.DB.DB_Result_All;
import com.example.dsm2016.brain.DBActivity;
import com.example.dsm2016.brain.Item.Item_Result;
import com.example.dsm2016.brain.R;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-06-17.
 */

public class Fragment_Result_Test extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Item_Result> mItem;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_result_test,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.rv_test);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mItem=new ArrayList<>();
        mAdapter=new Adapter_Result(mItem,getContext());
        recyclerView.setAdapter(mAdapter);

        DBActivity.mRealm.init(getContext());
        RealmResults<DB_Result_All> result= DBActivity.mRealm.where(DB_Result_All.class).equalTo("kind",0).findAll();
        for(int i=0;i<result.size();i++){
            DB_Result_All db_result_all=result.get(i);
            mItem.add(new Item_Result(db_result_all.getKey(),db_result_all.getDate(),db_result_all.getKind()));
            mAdapter.notifyDataSetChanged();
        }
        return view;
    }
}
