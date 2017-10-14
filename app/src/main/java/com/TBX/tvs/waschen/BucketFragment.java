package com.TBX.tvs.waschen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.AddBucketPOJO.AddBean;
import com.TBX.tvs.waschen.SProductPOJO.Datum;
import com.TBX.tvs.waschen.SProductPOJO.ProductBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class BucketFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    BucketAdapter adapter;
    TextView clearcart , addmore ;
    List<Datum> list;
    ProgressBar bar;

    LinearLayout hide;

    String catId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bucket_fragment , container ,false);

        catId = getArguments().getString("catid");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        bar = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getContext(),1);

        hide = (LinearLayout) ((MainActivity)getContext()).findViewById(R.id.hide);

        list = new ArrayList<>();
        adapter = new BucketAdapter(getContext() , list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        addmore = (TextView)view.findViewById(R.id.addmore);
        clearcart = (TextView)view.findViewById(R.id.clear);
        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*FragmentTransaction ft = fragmentManager.beginTransaction();
                Home fragment = new Home();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();*/

            }
        });


        bar.setVisibility(View.VISIBLE);
        Bean b = (Bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);

        Log.d("catid" , catId);

        Call<ProductBean> call = cr.product(catId);

        call.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {

                adapter.setgrid(response.body().getData());

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


        return view;
    }

    public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.MyViewHolder> {

        Context context;
        List<Datum> list = new ArrayList<>();


        public BucketAdapter(Context context , List<Datum>list){
            this.list = list;
            this.context = context;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.mybucket_list_model,parent,false);
            return new MyViewHolder(view);
        }

        public void setgrid(List<Datum>list){

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            final Datum item = list.get(position);

            holder.name.setText(item.getProductName());
            holder.price.setText("Rs. " + item.getPrice());

            holder.up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String q = holder.quantity.getText().toString();

                    int q1 = Integer.parseInt(q);

                    if (q1!=1)
                    {
                        q1--;
                    }

                    holder.quantity.setText(String.valueOf(q1));

                }
            });


            holder.down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String q = holder.quantity.getText().toString();

                    int q1 = Integer.parseInt(q);

                    q1++;

                    holder.quantity.setText(String.valueOf(q1));

                }
            });


            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("kdsg" , "response");

                    bar.setVisibility(View.VISIBLE);
                    Bean b = (Bean)context.getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    allAPIs cr = retrofit.create(allAPIs.class);
                    Call<AddBean> call = cr.add(b.userid , item.getProductId() , holder.quantity.getText().toString() , holder.price.getText().toString());
                    call.enqueue(new Callback<AddBean>() {
                        @Override
                        public void onResponse(Call<AddBean> call, Response<AddBean> response) {

                            Toast.makeText(getContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);

                            Bean b = (Bean)context.getApplicationContext();

                            b.cartid = response.body().getCartid();

                            String cou = response.body().getBucketCount();

                            int count = Integer.parseInt(cou);


                            if (count > 0 )
                            {
                                hide.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                hide.setVisibility(View.GONE);
                            }



                        }

                        @Override
                        public void onFailure(Call<AddBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                            Log.d("hmm" , t.toString());


                        }
                    });

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name , price , quantity;
            ImageButton up , down ;
            ImageButton add;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                price = (TextView)itemView.findViewById(R.id.price);
                up = (ImageButton)itemView.findViewById(R.id.up);
                down = (ImageButton)itemView.findViewById(R.id.down);
                add = (ImageButton) itemView.findViewById(R.id.add);



            }
        }

    }

}
