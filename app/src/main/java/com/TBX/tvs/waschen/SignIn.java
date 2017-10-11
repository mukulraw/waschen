package com.TBX.tvs.waschen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.CreatePOJO.CreateBean;
import com.TBX.tvs.waschen.LoginPOJO.LoginBean;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignIn extends AppCompatActivity {

    EditText email,pass;
    TextView signin , create ,facebbok , google ;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = (EditText) findViewById(R.id.e);
        pass = (EditText) findViewById(R.id.p);

        pref = getSharedPreferences("pref" , Context.MODE_PRIVATE);
        edit = pref.edit();

        bar = (ProgressBar) findViewById(R.id.progress);

        create = (TextView)findViewById(R.id.create);

        facebbok = (TextView)findViewById(R.id.facebook);


        google = (TextView)findViewById(R.id.google);

        signin = (TextView) findViewById(R.id.sign);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("fhsg" , "response");

                Bean b = (Bean)getApplicationContext();

                final String e = email.getText().toString();
                final String p = pass.getText().toString();


                if (Utils.isValidMail(e))
                {

                    if (p.length()>0)
                    {

                        bar.setVisibility(View.VISIBLE);


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        allAPIs cr = retrofit.create(allAPIs.class);
                        Call<LoginBean> call = cr.login(e , p );

                        call.enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                                if (Objects.equals(response.body().getStatus(), 1))

                                {

                                    Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    Bean b = (Bean)getApplicationContext();
                                    b.userid = response.body().getData().getUserId();
                                    Log.d("skjg" , "response");


                                    edit.putString("email" , e);
                                    edit.putString("pass" , p);
                                    edit.apply();


                                    Intent i = new Intent(SignIn .this , MainActivity.class);
                                    startActivity(i);

                                    finish();

                                    bar.setVisibility(View.GONE);
                                }

                                Log.d("dfjsdg" , response.body().getMessage());




                            }

                            @Override
                            public void onFailure(Call<LoginBean> call, Throwable t) {

                                bar.setVisibility(View.GONE);

                                Log.d("fgkjf" , t.toString());

                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(SignIn.this , "Please Enter a Password" , Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(SignIn.this , "Please Enter Valid Email" , Toast.LENGTH_SHORT).show();
                }







            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SignIn.this , CreateAccount.class);
                startActivity(i);

            }
        });




    }

    private boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();


        return check;
    }


    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;

            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

}
