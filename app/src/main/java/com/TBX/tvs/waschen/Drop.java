package com.TBX.tvs.waschen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.GetZipPOJO.GetZipBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Drop extends Fragment {

    EditText first,last,contact,address,city,state,country;

    TextView  cont;

    ViewPager pager;

    Spinner pincode;

    List<String> codes;

    String code = "";

    ProgressBar bar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.shipping,container , false);

        first = (EditText) view.findViewById(R.id.fn);

        last = (EditText)view.findViewById(R.id.ln);

        contact = (EditText)view.findViewById(R.id.cn);

        address = (EditText)view.findViewById(R.id.add);

        city = (EditText)view.findViewById(R.id.city);

        state = (EditText)view.findViewById(R.id.state);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        codes = new ArrayList<>();

        pincode = (Spinner) view.findViewById(R.id.pc);

        country = (EditText)view.findViewById(R.id.c);
        pager = (ViewPager)((Checkout)getContext()).findViewById(R.id.pager);
        cont = (TextView) view.findViewById(R.id.cont);


         bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getContext().getApplicationContext();
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


                for (int i = 0 ; i < response.body().getDeliveryMethod().size() ; i++)
                {
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

                final String f = first.getText().toString();
                String l = last.getText().toString();
                final String c = contact.getText().toString();
                final String a = address.getText().toString();
                final String cit = city.getText().toString();
                final String s = state.getText().toString();
                String p = first.getText().toString();
                final String coun = country.getText().toString();




                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.continue_dialog);
                dialog.setCancelable(true);

                dialog.show();

                RadioGroup radioGroup = (RadioGroup)dialog.findViewById(R.id.radio);
                Button ok = (Button)dialog.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        if (f.length()>0)
                        {

                            if (c.length()>0)
                            {

                                if (a.length()>0)
                                {

                                    if (cit.length()>0)
                                    {

                                        if (s.length()>0)
                                        {

                                            if (coun.length()>0)
                                            {


                                                Bean b = (Bean)getContext().getApplicationContext();

                                                b.shipfname = f;
                                                b.shipphone = c;
                                                b.shipaddress = a;
                                                b.shipcity = cit;
                                                b.shipstate = s;
                                                b.shipzip = code;
                                                b.shipcountry = coun;



                                                dialog.dismiss();
                                                pager.setCurrentItem(2);

                                            }
                                            else {

                                                Toast.makeText(getContext(), "Invalid Country", Toast.LENGTH_SHORT).show();
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
                                else {

                                    Toast.makeText(getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {

                                Toast.makeText(getContext(), "Invalid Phone", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {

                            Toast.makeText(getContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
                        }






                    }
                });


            }
        });
        return view;
    }
}
