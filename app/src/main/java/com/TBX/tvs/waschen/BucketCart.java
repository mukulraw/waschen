package com.TBX.tvs.waschen;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;



import com.TBX.tvs.waschen.AddBucketPOJO.AddBean;
import com.TBX.tvs.waschen.GetBucketPOJO.Datum;
import com.TBX.tvs.waschen.GetBucketPOJO.GetBean;
import com.TBX.tvs.waschen.RemovePOJO.RemoveBean;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BucketCart extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    BucketAdapter adapter;
    Toolbar toolbar;
    TextView clear , addmore , pick , proceed , total;
    List<Datum>list;
    ProgressBar bar;

    String date1 = "";

    Context context;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bucket);

        bar = (ProgressBar) findViewById(R.id.progress);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        toolbar.setTitle("My Bucket");

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        clear = (TextView) findViewById(R.id.clearcart);

        addmore = (TextView) findViewById(R.id.addmore);

        proceed = (TextView) findViewById(R.id.proceed);

        total = (TextView) findViewById(R.id.total);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (date1.length()>0)
                {
                    Intent i = new Intent(BucketCart.this , Checkout.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(BucketCart.this , "Please select a Pickup date" , Toast.LENGTH_SHORT).show();
                }


            }
        });

        list = new ArrayList<>();
        adapter = new BucketAdapter(this , list);
        manager = new GridLayoutManager(this , 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        pick = (TextView) findViewById(R.id.pick_date);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(BucketCart.this);
                dialog.setContentView(R.layout.pop_up);
                dialog.setCancelable(true);
                dialog.show();

                Button ok = (Button)dialog.findViewById(R.id.ok);
                final DatePicker date = (DatePicker) dialog.findViewById(R.id.picker);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String day = String.valueOf(date.getDayOfMonth());
                        String month = String.valueOf(date.getMonth() + 1);
                        String year = String.valueOf(date.getYear());

                        date1 = day + "-" + month + "-" + year;

                        pick.setText(day + "-" + month + "-" + year);

                        dialog.dismiss();

                    }
                });
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar.setVisibility(View.VISIBLE);

                Bean b = (Bean)getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                allAPIs cr = retrofit.create(allAPIs.class);
                Call<GetBean> call = cr.clear(b.userid ,b.cartid);

                //Log.d("nsdgnfsd" , b.userid);
                //Log.d("mukul" , b.cartid);

                call.enqueue(new Callback<GetBean>() {
                    @Override
                    public void onResponse(Call<GetBean> call, Response<GetBean> response) {

                        Toast.makeText(BucketCart.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        adapter.Setgrid(response.body().getData());

                        bar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<GetBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                        Log.d("nisha" , t.toString());

                    }
                });



            }
        });

        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<GetBean> call = cr.get(b.userid ,b.cartid);

    //    Log.d("nsdgnfsd" , b.userid);
      //  Log.d("mukul" , b.cartid);

        call.enqueue(new Callback<GetBean>() {
            @Override
            public void onResponse(Call<GetBean> call, Response<GetBean> response) {

                Toast.makeText(BucketCart.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                adapter.Setgrid(response.body().getData());

                bar.setVisibility(View.GONE);

                total.setText("Rs. "  +response.body().getTotalPrice());

            }

            @Override
            public void onFailure(Call<GetBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

                Log.d("nisha" , t.toString());

            }
        });



    }



    public  class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.MyViewHolder> {

        Context context;
        List<Datum> list = new ArrayList<>();


        public BucketAdapter(Context context , List<Datum>list){
            this.list = list;
            this.context = context;
        }
        @Override
        public BucketAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.bucket_list_model,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            final Datum item = list.get(position);

            holder.name.setText(item.getProductName());
            holder.price.setText("Rs. " + item.getUnitprice());

            holder.quantity.setText(item.getQuantity());


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

                            Toast.makeText(context ,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);

                            Bean b = (Bean)context.getApplicationContext();

                            b.cartid = response.body().getCartid();

                            String cou = response.body().getBucketCount();

                            int count = Integer.parseInt(cou);






                        }

                        @Override
                        public void onFailure(Call<AddBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                            Log.d("hmm" , t.toString());


                        }
                    });






                }
            });


            holder.down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String q = holder.quantity.getText().toString();

                    int q1 = Integer.parseInt(q);

                    q1++;

                    holder.quantity.setText(String.valueOf(q1));

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

                            Toast.makeText(context ,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);

                            Bean b = (Bean)context.getApplicationContext();

                            b.cartid = response.body().getCartid();

                            String cou = response.body().getBucketCount();

                            int count = Integer.parseInt(cou);






                        }

                        @Override
                        public void onFailure(Call<AddBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                            Log.d("hmm" , t.toString());


                        }
                    });

                }
            });


            holder.close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    bar.setVisibility(View.VISIBLE);
                    Bean b = (Bean)context.getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    allAPIs cr = retrofit.create(allAPIs.class);
                    Call<GetBean> call = cr.remove(b.userid ,b.cartid ,  item.getProductId());
                    call.enqueue(new Callback<GetBean>() {
                        @Override
                        public void onResponse(Call<GetBean> call, Response<GetBean> response) {

                            Toast.makeText(BucketCart.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);

                            adapter.Setgrid(response.body().getData());
                        }

                        @Override
                        public void onFailure(Call<GetBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });

                }
            });

        }

        public void Setgrid(List<Datum> list){

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name , price , quantity;
            ImageButton up , down ;
            ImageButton close;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                price = (TextView)itemView.findViewById(R.id.price);
                up = (ImageButton)itemView.findViewById(R.id.up);
                down = (ImageButton)itemView.findViewById(R.id.down);
                close = (ImageButton) itemView.findViewById(R.id.close);



            }
        }
    }

}


