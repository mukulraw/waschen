package com.TBX.tvs.waschen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.FaqPOJO.FaqBean;
import com.TBX.tvs.waschen.GetZipPOJO.DeliveryMethod;
import com.TBX.tvs.waschen.GetZipPOJO.GetZipBean;
import com.TBX.tvs.waschen.UpdateProfilePOJO.UpdateProfileBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;


public class PickUp extends Fragment {

    EditText city  , address  , email ,state , contact  , shipdiff , edit , name , count , land;
    CheckBox ship;
    ViewPager pager;
    TextView cont  , birth;
    ProgressBar bar;
    Spinner pincode;
    Button save;
    List<String> codes;
    String code = "";
    String date1 = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.billinf_layout, container, false);

        city = (EditText) view.findViewById(R.id.city);

        codes = new ArrayList<>();

        //edit = (TextView)view.findViewById(R.id.edit);

        address = (EditText) view.findViewById(R.id.address);

        pincode = (Spinner) view.findViewById(R.id.pincode);

        email = (EditText) view.findViewById(R.id.email);

        state = (EditText) view.findViewById(R.id.state);
        land = (EditText) view.findViewById(R.id.landmark);

        count = (EditText) view.findViewById(R.id.country);

        contact = (EditText) view.findViewById(R.id.contact);

        ship = (CheckBox) view.findViewById(R.id.shipadd);

        name = (EditText) view.findViewById(R.id.name);

        cont = (TextView) view.findViewById(R.id.cont);

        birth = (TextView) view.findViewById(R.id.birthday);

        bar = (ProgressBar) view.findViewById(R.id.progress);
        save = (Button) view.findViewById(R.id.save);

        pager = (ViewPager) ((Checkout) getContext()).findViewById(R.id.pager);


        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getContext().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<GetZipBean> call = cr.bean();

        call.enqueue(new Callback<GetZipBean>() {
            @Override
            public void onResponse(Call<GetZipBean> call, Response<GetZipBean> response) {


                for (int i = 0; i < response.body().getDeliveryMethod().size(); i++) {
                    codes.add(response.body().getDeliveryMethod().get(i).getCoupon());
                }


                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, codes);

                pincode.setAdapter(dataAdapter);


                //Toast.makeText(getContext(), response.body().getDeliveryMethod().get(0).getCoupon(), Toast.LENGTH_SHORT).show();
                //adapter.setdata(response.body().getDeliveryMethod());

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetZipBean> call, Throwable t) {

                bar.setVisibility(View.GONE);


            }
        });


    birth.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            final Dialog dialog = new Dialog(getActivity());
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

                    birth.setText(day + "-" + month + "-" + year);

                    dialog.dismiss();

                }
            });
        }
    });

    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            bar.setVisibility(View.VISIBLE);

            String f = name.getText().toString();
            String c = contact.getText().toString();
            String ci = city.getText().toString();
            String s = state.getText().toString();
            String a = address.getText().toString();
            String  em = email.getText().toString();
//            String coun = count.getText().toString();
            String bir = birth.getText().toString();
            String lan = land.getText().toString();

            Bean b = (Bean)getContext().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            allAPIs cr = retrofit.create(allAPIs.class);
            Call<UpdateProfileBean> call2 = cr.updateprofile(b.userid , f , c , a , ci , code ,"" , bir , lan , s);
            call2.enqueue(new Callback<UpdateProfileBean>() {
                @Override
                public void onResponse(Call<UpdateProfileBean> call, Response<UpdateProfileBean> response) {


                    Toast.makeText(getContext(),"Address Saved Successfully", Toast.LENGTH_SHORT).show();


                    bar.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<UpdateProfileBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });





        }
    });



        pincode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                code = codes.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String n = name.getText().toString();
                String e = email.getText().toString();
                String a = address.getText().toString();
                String c = city.getText().toString();
                String s = state.getText().toString();
               // String p = pincode.getText().toString();
                String  co = contact.getText().toString();
//                String  coun = count.getText().toString();


                if (n.length()>0)
                {


                    if (Utils.isValidMail(e))
                    {


                        if (Utils.isValidMobile(co))
                        {

                            if (a.length()>0)

                            {
                                if (c.length()>0)
                                {
                                    if (s.length()>0)
                                    {


                                               if (ship.isChecked())
                                               {

                                                   Bean b = (Bean)getContext().getApplicationContext();

                                                   b.billname = n;
                                                   b.billemail = e;
                                                   b.billphone = co;
                                                   b.billaddress = a;
                                                   b.billcity = c;
                                                   b.billstate = s;
                                                   b.billzip = code;
                                                   //b.billcountry = coun;


                                                   b.shipfname = n;
                                                   b.shipphone = co;
                                                   b.shipaddress = a;
                                                   b.shipcity = c;
                                                   b.shipstate = s;
                                                   b.shipzip = code;
                                                   //b.shipcountry = coun;


                                                   final Dialog dialog = new Dialog(getActivity());
                                                   dialog.setContentView(R.layout.continue_dialog);
                                                   dialog.setCancelable(true);

                                                   dialog.show();

                                                   RadioGroup radioGroup = (RadioGroup)dialog.findViewById(R.id.radio);
                                                   Button ok = (Button)dialog.findViewById(R.id.ok);
                                                   ok.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View view) {

                                                           pager.setCurrentItem(2);

                                                           dialog.dismiss();

                                                       }
                                                   });



                                               }
                                               else
                                               {
                                                   Bean b = (Bean)getContext().getApplicationContext();

                                                   b.billname = n;
                                                   b.billemail = e;
                                                   b.billphone = co;
                                                   b.billaddress = a;
                                                   b.billcity = c;
                                                   b.billstate = s;
                                                   b.billzip = code;
                                                   //b.billcountry = coun;


                                                   pager.setCurrentItem(1);

                                               }
                                           }





                                    else {
                                        Toast.makeText(getContext(), "Invalid State", Toast.LENGTH_SHORT).show();

                                    }

                                }
                                else
                                    {


                                        Toast.makeText(getContext(), "Invalid City", Toast.LENGTH_SHORT).show();
                                }

                            }

                            else
                                {

                                Toast.makeText(getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(getContext() , "Please Enter a Valid Phone" , Toast.LENGTH_SHORT).show();
                        }

                    }

                    else
                    {
                        Toast.makeText(getContext(), "Please Enter a Valid Email" , Toast.LENGTH_SHORT).show();
                    }


                }
                else
                {
                    Toast.makeText(getContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bar = (ProgressBar)view.findViewById(R.id.progress);
        //shipdiff = (TextView)view.findViewById(R.id.shipdiff);
       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });*/


        bar.setVisibility(View.VISIBLE);


        Call<ViewBean> call2 = cr.view(b.userid);

        call2.enqueue(new Callback<ViewBean>() {
            @Override
            public void onResponse(Call<ViewBean> call, Response<ViewBean> response) {




               bar.setVisibility(View.GONE);

               address.setText(response.body().getData().getAddress());
               contact.setText(response.body().getData().getPhone());
               email.setText(response.body().getData().getEmail());
               name.setText(response.body().getData().getUserName());
               state.setText(response.body().getData().getState());
               city.setText(response.body().getData().getCity());



            }

            @Override
            public void onFailure(Call<ViewBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

        return  view;
    }
}
