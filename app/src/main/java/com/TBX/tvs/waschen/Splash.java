package com.TBX.tvs.waschen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {

    Timer t;
    ProgressBar bar;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        bar = (ProgressBar)findViewById(R.id.progress);

        pref = getSharedPreferences("pref" , Context.MODE_PRIVATE);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(Splash.this));


        String em = pref.getString("email" , "");
        String pa = pref.getString("pass" , "");


        if (em.length()>0 && pa.length()>0)
        {

            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean)getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            allAPIs cr = retrofit.create(allAPIs.class);
            Call<LoginBean> call = cr.login(em , pa );

            call.enqueue(new Callback<LoginBean>() {
                @Override
                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                    if (Objects.equals(response.body().getStatus(), 1))

                    {

                        Toast.makeText(Splash.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Bean b = (Bean)getApplicationContext();
                        b.userid = response.body().getData().getUserId();
                        b.email = response.body().getData().getEmail();
                        b.name = response.body().getData().getUserName();

                        Log.d("skjg" , "response");




                        Intent i = new Intent(Splash.this , MainActivity.class);
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
            t = new Timer();

            t.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent intent = new Intent(Splash.this , SignIn.class);
                    startActivity(intent);
                    finish();



                }
            } , 1500);

        }



        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }*/

    }


}
