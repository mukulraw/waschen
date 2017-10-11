package com.TBX.tvs.waschen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.CreatePOJO.CreateBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateAccount extends AppCompatActivity {

    Toolbar toolbar;
    EditText user,email,pwd,retype , phone;
    Button create;
    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        user = (EditText) findViewById(R.id.user);
        email = (EditText) findViewById(R.id.emailid);
        phone = (EditText) findViewById(R.id.mobileee);
        pwd = (EditText) findViewById(R.id.pwd);
        create = (Button) findViewById(R.id.createaccount);
        retype = (EditText) findViewById(R.id.retype);
        bar = (ProgressBar) findViewById(R.id.progress);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitle("SEARCH");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("sdhgfs" ,  "mukul");
                Bean b = (Bean)getApplicationContext();


                String u = user.getText().toString();
                String e = email.getText().toString();
                String p = pwd.getText().toString();
                String ph = phone.getText().toString();
                String r = retype.getText().toString();



                if (u.length()>0)
                {

                    if (Utils.isValidMail(e))
                    {

                        if (Utils.isValidMobile(ph))
                        {

                            if (p.length()>0)
                            {

                                if (Objects.equals(p, r))
                                {
                                    bar.setVisibility(View.VISIBLE);


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(b.baseURL)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    allAPIs cr = retrofit.create(allAPIs.class);
                                    Call<CreateBean> call = cr.create(u , e , p , ph);
                                    call.enqueue(new Callback<CreateBean>() {
                                        @Override
                                        public void onResponse(Call<CreateBean> call, Response<CreateBean> response) {

                                            if (Objects.equals(response.body().getStatus(), "1")){

                                                Log.d("fhs" , "response");

                                                if (Objects.equals(response.body().getMessage(), "Registered Successfully"))
                                                {
                                                    Toast.makeText(CreateAccount.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                    Intent i = new Intent(CreateAccount.this , SignIn.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                                else
                                                {
                                                    Toast.makeText(CreateAccount.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                }


                                            }
                                            else
                                            {
                                                Toast.makeText(CreateAccount.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                bar.setVisibility(View.GONE);
                                            }




                                        }


                                        @Override
                                        public void onFailure(Call<CreateBean> call, Throwable t) {

                                            bar.setVisibility(View.GONE);

                                            Log.d("bsdkfs" , t.toString());

                                        }
                                    });


                                }
                                else
                                {
                                    Toast.makeText(CreateAccount.this , "Password did not match" , Toast.LENGTH_SHORT).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(CreateAccount.this , "Please Enter a Valid Password" , Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(CreateAccount.this , "Please Enter a Valid Phone" , Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(CreateAccount.this , "Please Enter a Valid Email" , Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(CreateAccount.this , "Please Enter a Valid Username" , Toast.LENGTH_SHORT).show();
                }





            }
        });


    }
}
