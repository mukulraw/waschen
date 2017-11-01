package com.TBX.tvs.waschen;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        bar = (ProgressBar)findViewById(R.id.progress);

        pref = getSharedPreferences("pref" , Context.MODE_PRIVATE);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(Splash.this));

        if(hasPermissions(this , PERMISSIONS))
        {
            startApp();
        }
        else
        {
            ActivityCompat.requestPermissions(this , PERMISSIONS , REQUEST_CODE_ASK_PERMISSIONS);
        }














        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }

    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS)
        {
            if (ActivityCompat.checkSelfPermission(getApplicationContext() , android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {

                startApp();

            }
            else
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    Toast.makeText(getApplicationContext() , "Permissions are required for this app" , Toast.LENGTH_SHORT).show();
                    finish();

                }
                //permission is denied (and never ask again is  checked)
                //shouldShowRequestPermissionRationale will return false
                else {
                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                            .show();
                    finish();
                    //                            //proceed with logic by disabling the related features or quit the app.
                }
            }

        }


    }


    public void startApp()
    {
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
                        b.cartid = response.body().getData().getCartId();
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
    }


}
