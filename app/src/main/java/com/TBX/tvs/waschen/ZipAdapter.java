package com.TBX.tvs.waschen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.TBX.tvs.waschen.GetZipPOJO.DeliveryMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvs on 10/28/2017.
 */

public class ZipAdapter extends RecyclerView.Adapter<ZipAdapter.MyViewHolder> {

    Context context;

    List<DeliveryMethod> list = new ArrayList<>();


    public ZipAdapter(Context context ,  List<DeliveryMethod> list){

        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.zip_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DeliveryMethod item = list.get(position);

        holder.zip.setText(item.getCoupon());

    }

    public void setdata( List<DeliveryMethod> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView zip;


        public MyViewHolder(View itemView) {
            super(itemView);

            zip = (TextView)itemView.findViewById(R.id.zip);
        }
    }
}
