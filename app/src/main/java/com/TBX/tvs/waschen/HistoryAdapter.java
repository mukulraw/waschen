package com.TBX.tvs.waschen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.TBX.tvs.waschen.OrderPojo.Datum;
import com.TBX.tvs.waschen.OrderPojo.OrderBean;

import java.util.ArrayList;
import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {


    Context context;

    List<Datum> list = new ArrayList();

    public HistoryAdapter(Context context ,  List<Datum> list  ){

        this.context = context;
       this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_list_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Datum item = list.get(position);
        holder.order.setText(item.getOrderId());
        holder.date.setText(item.getPlacedDate());
        holder.orderno.setText(item.getOrderId());
        holder.status.setText(item.getOrderStatus());


    }

    public void setgrid(List<Datum>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView order , orderno , date , status;

        public MyViewHolder(View itemView) {
            super(itemView);

            order = (TextView) itemView.findViewById(R.id.order);
            orderno = (TextView) itemView.findViewById(R.id.phone);
            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.pending);
        }
    }
}
