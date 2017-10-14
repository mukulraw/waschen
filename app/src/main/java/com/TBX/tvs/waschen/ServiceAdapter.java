package com.TBX.tvs.waschen;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.TBX.tvs.waschen.ServicesPOJO.Datum;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    Context context;

    List<Datum> list = new ArrayList<>();

    public ServiceAdapter(Context context , List<Datum>list){

        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.service_list_model , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        final Datum item = list.get(position);
        holder.title.setText(item.getTitle());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(false).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getImage() , holder.imageView , options);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                BucketFragment fragment = new BucketFragment();

                Bundle b = new Bundle();

                b.putString("catid" , item.getUserId());

                fragment.setArguments(b);

                ft.replace(R.id.replace, fragment);



                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();


            }
        });


    }
    public void setgrid(List<Datum> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);



        }
    }


}
