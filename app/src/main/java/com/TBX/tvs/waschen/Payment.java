package com.TBX.tvs.waschen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.CashPOJO.CashBean;
import com.TBX.tvs.waschen.CouponPOJO.CouponBean;
import com.TBX.tvs.waschen.GetZipPOJO.GetZipBean;
import com.TBX.tvs.waschen.GrandTotalPOJO.GrandBean;

import java.lang.invoke.VolatileCallSite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 8/24/2017.
 */

public class Payment extends Fragment {

    TextView cont;

    ViewPager pager;

    EditText code;

    Button redeem;

    RadioGroup radio;

    ProgressBar bar;

    TextView sub , delivery , gst , grand , bucket;

    String total;

    String del_price = "0";
    String date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment , container , false);

        total = getArguments().getString("total");
        date = getArguments().getString("date");

        //pager = ((Checkout.this)getContext()).findViewById(R.id.pager);

        pager = (ViewPager)((Checkout)getContext()).findViewById(R.id.pager);

        cont = (TextView)view.findViewById(R.id.cont);

        sub = (TextView)view.findViewById(R.id.subtotal);

        delivery = (TextView)view.findViewById(R.id.delivery);

        gst = (TextView)view.findViewById(R.id.gst);

        grand = (TextView)view.findViewById(R.id.grand);

        bucket = (TextView)view.findViewById(R.id.bucket);

        redeem = (Button)view.findViewById(R.id.redeem);
        code = (EditText) view.findViewById(R.id.code);
        radio = (RadioGroup) view.findViewById(R.id.radio);

        bar = (ProgressBar)view.findViewById(R.id.progress);


        bucket.setText("INR " + total);

        sub.setText("INR " + total);

        delivery.setText("INR " + del_price);


        Double gstPrice = ((Double.parseDouble(total) + Double.parseDouble(del_price)) * 18) / 100;

        gst.setText("+ INR " + String.valueOf(gstPrice));

        Double grandTotal = (Double.parseDouble(total) + Double.parseDouble(del_price)) + gstPrice;

        grand.setText(String.valueOf(grandTotal));

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bar.setVisibility(View.VISIBLE);

                final String c = code.getText().toString();

                Log.d("gflkk" , code.getText().toString());

                if (c.length()>0)
                {


                    Bean b = (Bean)getContext().getApplicationContext();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final allAPIs cr = retrofit.create(allAPIs.class);
                    Call<CouponBean> call = cr.coupon(c ,b.userid , b.cartid);
                    call.enqueue(new Callback<CouponBean>() {
                        @Override
                        public void onResponse(Call<CouponBean> call, Response<CouponBean> response) {

                            Bean b = (Bean)getContext().getApplicationContext();

                            Call<GrandBean> call2 = cr.grand(response.body().getPrice() , b.userid , b.cartid , "0");

                            Log.d("hmmm" , "response1");

                            bar.setVisibility(View.GONE);

                            call2.enqueue(new Callback<GrandBean>() {
                                @Override
                                public void onResponse(Call<GrandBean> call, Response<GrandBean> response) {

                                    sub.setText("INR " + response.body().getCartSubTotal());
                                    delivery.setText("INR " + response.body().getDeliveryPrice());
                                    gst.setText("+ INR " + String.valueOf(response.body().getGstPrice()));
                                    grand.setText("INR " + String.valueOf(response.body().getGrandTotal()));

                                    bar.setVisibility(View.GONE);
                                    Log.d("bdjsg" , "response2");
                                }

                                @Override
                                public void onFailure(Call<GrandBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);

                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<CouponBean> call, Throwable t) {


                            bar.setVisibility(View.GONE);

                        }
                    });


                }
                  else {

                    Toast.makeText(getContext(), "Invalid Coupon", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar.setVisibility(View.VISIBLE);

                Bean b = (Bean)getContext().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final allAPIs cr = retrofit.create(allAPIs.class);
                Call<CashBean> call = cr.cash(
                        b.userid ,
                        b.cartid,
                        b.billname,
                        b.billemail,
                        b.billaddress,
                        b.billcity,
                        b.billname,
                        b.billstate,
                        b.billzip,
                        b.billphone,
                        b.shipfname,
                        b.shiplname,
                        b.shipphone,
                        b.shipaddress,
                        b.shipcity,
                        b.shipstate,
                        b.shipzip,
                        b.shipcountry,
                        b.dilivery_method,
                        b.dilivery_price,
                        b.cart_price,
                        b.gst_tax,
                        b.grand_total,
                        date
                );

                Log.d("asdasd" , "response");

                call.enqueue(new Callback<CashBean>() {
                    @Override
                    public void onResponse(Call<CashBean> call, Response<CashBean> response) {
                        Bean b = (Bean)getContext().getApplicationContext();

                        b.order_id = String.valueOf(response.body().getOrderId());

                                b.billname = "";
                                b.billemail = "";
                                b.billaddress = "";
                                b.billcity = "";
                                b.billname = "";
                                b.billstate = "";
                                b.billzip = "";
                                b.billphone = "";
                                b.shipfname = "";
                                b.shiplname = "";
                                b.shipphone = "";
                                b.shipaddress = "";
                                b.shipcity = "";
                                b.shipstate = "";
                                b.shipzip = "";
                                b.shipcountry = "";
                                b.dilivery_method = "";
                                b.dilivery_price = "";
                                b.cart_price = "";
                                b.gst_tax = "";
                                b.grand_total = "";

                        Log.d("asdasd" , "response");

                        pager.setCurrentItem(3);

                        bar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<CashBean> call, Throwable t) {
                        Log.d("asdasd" , t.toString());
                        bar.setVisibility(View.GONE);

                    }
                });




            }
        });
        return  view;
    }
}
