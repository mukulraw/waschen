package com.TBX.tvs.waschen;

import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignIn extends AppCompatActivity {

    EditText email,pass;
    TextView signin , create ,facebbok , google ;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = (EditText) findViewById(R.id.e);
        pass = (EditText) findViewById(R.id.p);

        bar = (ProgressBar) findViewById(R.id.progress);

        create = (TextView)findViewById(R.id.create);

        facebbok = (TextView)findViewById(R.id.facebook);


        google = (TextView)findViewById(R.id.google);

        signin = (TextView) findViewById(R.id.sign);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("fhsg" , "response");

                bar.setVisibility(View.VISIBLE);
                Bean b = (Bean)getApplicationContext();

                bar.setVisibility(View.VISIBLE);
                String e = email.getText().toString();
                String p = pass.getText().toString();

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

                            Intent i = new Intent(SignIn .this , MainActivity.class);
                            startActivity(i);

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
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SignIn.this , CreateAccount.class);
                startActivity(i);

            }
        });




    }
}
