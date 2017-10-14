package com.TBX.tvs.waschen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.BucketCountPOJO.BucketCountBean;
import com.TBX.tvs.waschen.GetBucketPOJO.GetBean;
import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.TBX.tvs.waschen.ServicesPOJO.ServiceBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    TextView profile,modus,service,history,contactus,faqs , wallet , email , name  , buck , logout;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    ImageView men;
    ProgressBar bar;

    LinearLayout hide;
    TextView clear , proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("mypref" , MODE_PRIVATE);
        edit = pref.edit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        profile = (TextView) findViewById(R.id.profile);
        buck = (TextView) findViewById(R.id.Bucket);
        modus = (TextView) findViewById(R.id.modus);
        history = (TextView) findViewById(R.id.history);
        service = (TextView) findViewById(R.id.service);
        wallet = (TextView) findViewById(R.id.wallet);
        logout = (TextView) findViewById(R.id.logout);
        bar = (ProgressBar) findViewById(R.id.progress);
        faqs = (TextView) findViewById(R.id.faqs);
        contactus = (TextView) findViewById(R.id.contactus);
        drawer = (DrawerLayout) findViewById(R.id.activity_main);

        men = (ImageView) findViewById(R.id.user);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        proceed = (TextView) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this , BucketCart.class);
                startActivity(i);

            }
        });


        clear = (TextView) findViewById(R.id.clear);
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

                Log.d("nsdgnfsd" , b.userid);
                Log.d("mukul" , b.cartid);

                call.enqueue(new Callback<GetBean>() {
                    @Override
                    public void onResponse(Call<GetBean> call, Response<GetBean> response) {


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


        hide = (LinearLayout) findViewById(R.id.hide);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);

        drawer = (DrawerLayout) findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Home fragment = new Home();
        ft.replace(R.id.replace, fragment);
        //ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);

        }


        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<BucketCountBean> call = cr.getBucketCount();
        call.enqueue(new Callback<BucketCountBean>() {
            @Override
            public void onResponse(Call<BucketCountBean> call, Response<BucketCountBean> response) {


                int count = response.body().getBucketCount();


                if (count > 0)
                {
                    hide.setVisibility(View.VISIBLE);
                }
                else
                {
                    hide.setVisibility(View.GONE);
                }

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BucketCountBean> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ProfileFrag fragment = new ProfileFrag();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                toolbar.setTitle("My Profile");

            }
        });



        buck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(MainActivity.this , BucketCart.class);
               startActivity(i);
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }


            }
        });


        modus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ModusOperandiFrag fragment = new ModusOperandiFrag();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                toolbar.setTitle("Modus Operandi");

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                HistoryFrag fragment = new HistoryFrag();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }

                toolbar.setTitle("Orders History");
            }
        });

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Home fragment = new Home();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                toolbar.setTitle("Services");
            }
        });

        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                FaqFrag fragment = new FaqFrag();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();

                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                toolbar.setTitle("FAQs");
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ContactFrag fragment = new ContactFrag();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }

                toolbar.setTitle("Contact Us");
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Wallet fragment = new Wallet();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }

                toolbar.setTitle("Wallet");
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this , SignIn.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                edit.remove("user");
                edit.remove("type");
                edit.remove("pass");
                edit.apply();


                Bean b = (Bean) getApplicationContext();

                b.name = "";
                b.userid = "";
                b.email = "";

                startActivity(i);
                finish();

            }
        });



        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(b.image , men);

        name.setText(b.name);
        email.setText(b.email);


        Log.d("nisha" , b.name);
        Log.d("mukul" , b.email);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.cart)
        {

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("Services");


        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<BucketCountBean> call = cr.getBucketCount();
        call.enqueue(new Callback<BucketCountBean>() {
            @Override
            public void onResponse(Call<BucketCountBean> call, Response<BucketCountBean> response) {


                int count = response.body().getBucketCount();


                if (count > 0)
                {
                    hide.setVisibility(View.VISIBLE);
                }
                else
                {
                    hide.setVisibility(View.GONE);
                }

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BucketCountBean> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });

    }
}
