package com.TBX.tvs.waschen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.TBX.tvs.waschen.ViewMorePOJO.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvs on 11/17/2017.
 */

public class ViewAdpter extends RecyclerView.Adapter<ViewAdpter.MyViewHolder> {


    Context context;

    List<Datum>list = new ArrayList<>();



    public ViewAdpter(Context context , List<Datum>list){

        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.viewmore_list_more , parent , false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Datum item = list.get(position);

        holder.name.setText(item.getProductName());
        holder.total.setText("INR " + item.getTotalPrice());
        holder.unit.setText( "INR " +item.getUnitPrice()+ " x " + item.getQuantity());

    }

    public void setgrid(List<Datum>list)
    {

        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , unit , total;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            unit = (TextView)itemView.findViewById(R.id.unit);
            total = (TextView)itemView.findViewById(R.id.total);
        }
    }
}
