package com.TBX.tvs.waschen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.AddBucketPOJO.AddBean;
import com.TBX.tvs.waschen.SProductPOJO.Datum;

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
    List<Datum>list;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);
        bar = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        list = new ArrayList<>();
        adapter = new BucketAdapter(this , list);
        manager = new GridLayoutManager(this , 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


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
            View view = LayoutInflater.from(context).inflate(R.layout.mybucket_list_model,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            final Datum item = list.get(position);

            holder.name.setText(item.getProductName());
            holder.price.setText(item.getPrice());

            holder.down.setOnClickListener(new View.OnClickListener() {
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


            holder.up.setOnClickListener(new View.OnClickListener() {
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

                    bar.setVisibility(View.VISIBLE);
                    Bean b = (Bean)context.getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    allAPIs cr = retrofit.create(allAPIs.class);
                    Call<AddBean> call = cr.add(b.userid , item.getProductId() , holder.quantity.getText().toString());
                    call.enqueue(new Callback<AddBean>() {
                        @Override
                        public void onResponse(Call<AddBean> call, Response<AddBean> response) {

                            Toast.makeText(context, response.body().getBucketCount(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);


                        }

                        @Override
                        public void onFailure(Call<AddBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);


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
            Button add;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                price = (TextView)itemView.findViewById(R.id.price);
                up = (ImageButton)itemView.findViewById(R.id.up);
                down = (ImageButton)itemView.findViewById(R.id.down);
                add = (Button)itemView.findViewById(R.id.add);



            }
        }
    }

}


