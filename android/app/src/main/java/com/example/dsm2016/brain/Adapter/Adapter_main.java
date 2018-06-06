package com.example.dsm2016.brain.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dsm2016.brain.Item.Item_Main;
import com.example.dsm2016.brain.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Adapter_main  extends RecyclerView.Adapter<Adapter_main.MyViewHolder>{
    ArrayList<Item_Main> list=new ArrayList<Item_Main>();
    Context context;
    public Adapter_main(ArrayList<Item_Main> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        MyViewHolder vh=new MyViewHolder(v);

        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.question.setText(list.get(position).getQuestion());
     holder.answer.setText(list.get(position).getAnswer());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView question,answer;
        public CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.main_cv);
            question=(TextView)itemView.findViewById(R.id.question_tv);
            answer=(TextView)itemView.findViewById(R.id.answer_tv);
        }
    }
}
