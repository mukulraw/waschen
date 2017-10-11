package com.TBX.tvs.waschen;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.OrderDetailPOJO.DetailBean;
import com.TBX.tvs.waschen.OrderPojo.Datum;
import com.TBX.tvs.waschen.OrderPojo.OrderBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class HistoryFrag extends Fragment{


    RecyclerView recyclerView;
    GridLayoutManager manager;
    HistoryAdapter adapter;
    ProgressBar bar;
    List<Datum>list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_history , container , false);

        recyclerView = (RecyclerView)v. findViewById(R.id.recycler);
        manager = new GridLayoutManager(getContext() ,1);

        bar = (ProgressBar)v.findViewById(R.id.progress);

        list = new ArrayList<>();
        adapter = new HistoryAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();


       bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getContext(). getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<OrderBean> call = cr.order(b.userid);
        call.enqueue(new Callback<OrderBean>() {
            @Override
            public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {

                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                adapter.setgrid(response.body().getData());

                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<OrderBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


    }


    public static class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {


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

}
