package com.TBX.tvs.waschen;

import android.content.Context;
import android.icu.util.HebrewCalendar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.TBX.tvs.waschen.FaqPOJO.Datum;

import java.util.ArrayList;
import java.util.List;


public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder> {


    Context context;

    List<Datum>list = new ArrayList<>();

    public FaqAdapter(Context context , List<Datum>list ){

        this.context = context;

        this.list = list;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.faq_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Datum item = list.get(position);
        holder.q.setText(item.getQuestion());
        holder.ans.setText(item.getAnswer());

    }

    public void Setgrid(List<Datum>list){

        this.list = list;
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView q,ans;

        public MyViewHolder(View itemView) {
            super(itemView);

            q = (TextView)itemView.findViewById(R.id.q);
            ans = (TextView)itemView.findViewById(R.id.ans);
        }
    }
}
