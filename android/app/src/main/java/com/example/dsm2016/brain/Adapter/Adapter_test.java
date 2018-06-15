package com.example.dsm2016.brain.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dsm2016.brain.Item.Item_Test_result;
import com.example.dsm2016.brain.R;

import java.util.ArrayList;

/**
 * Created by ghdth on 2018-06-15.
 */

public class Adapter_test extends RecyclerView.Adapter<Adapter_test.MyViewHolder> {
    private Context context;
    private ArrayList<Item_Test_result> mItem;
    public Adapter_test(Context context,ArrayList<Item_Test_result> mItem){
        this.context=context;
        this.mItem=mItem;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_test_result,parent,false);
        Adapter_test.MyViewHolder vh=new Adapter_test.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.num.setText(mItem.get(position).getNumber());
        holder.image1.setImageResource(mItem.get(position).getImage());
        holder.image2.setImageResource(mItem.get(position).getImage2());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image1,image2;
        private TextView num;
        public MyViewHolder(View itemView) {
            super(itemView);
            image1=(ImageView)itemView.findViewById(R.id.forget_check_iv1);
            image2=(ImageView)itemView.findViewById(R.id.dementia_check_iv1);
            num=(TextView)itemView.findViewById(R.id.result_number);

        }
    }
}
