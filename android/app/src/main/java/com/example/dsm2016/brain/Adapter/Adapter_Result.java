package com.example.dsm2016.brain.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dsm2016.brain.Item.Item_Result;
import com.example.dsm2016.brain.Item.Item_Test_result;
import com.example.dsm2016.brain.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghdth on 2018-06-21.
 */

 public class Adapter_Result extends RecyclerView.Adapter<Adapter_Result.MyViewHolder> {
    private ArrayList<Item_Result> mItem;
    private Context context;

    public Adapter_Result(ArrayList<Item_Result> mItem,Context context){
        this.mItem=mItem;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.test_name.setText(mItem.get(position).getTitle());
        holder.date.setText(mItem.get(position).getDate());
        setLayout(holder);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView test_name,date;
        private RecyclerView test_rv,qna_rv;
        private RecyclerView.Adapter test_madater,qna_madater;
        private RecyclerView.LayoutManager layoutManager;
        private ArrayList<Item_Test_result> test_item;


        private LinearLayout test_content_layout,qna_content_layout,title_layout;
        private CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

             test_rv=(RecyclerView)itemView.findViewById(R.id.result_test_ct_rv);
             layoutManager=new LinearLayoutManager(context);
             test_rv.setLayoutManager(layoutManager);

             test_item=new ArrayList<>();
             test_madater=new Adapter_test(context,test_item);
             test_rv.setAdapter(test_madater);

            test_item.add(new Item_Test_result("1",R.drawable.not_check,R.drawable.check));
            test_item.add(new Item_Test_result("2",R.drawable.not_check,R.drawable.check));

            test_name=(TextView)itemView.findViewById(R.id.test_name_tv);
             date=(TextView)itemView.findViewById(R.id.test_date_tv);

             test_content_layout=(LinearLayout)itemView.findViewById(R.id.result_test_content);
             qna_content_layout=(LinearLayout)itemView.findViewById(R.id.result_qna_content);
             title_layout=(LinearLayout)itemView.findViewById(R.id.title_layout);

             cardView=(CardView)itemView.findViewById(R.id.result_cv);
        }

    }
    private void setLayout(final Adapter_Result.MyViewHolder holder){
        holder.title_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.test_content_layout.getVisibility()==View.VISIBLE){
                    holder.test_content_layout.setVisibility(View.GONE);
                }
                else{
                    holder.test_content_layout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

 }
