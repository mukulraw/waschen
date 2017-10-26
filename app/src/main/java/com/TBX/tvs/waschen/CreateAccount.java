package com.TBX.tvs.waschen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateAccount extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Toolbar toolbar;
    EditText user,email,pwd,retype , phone;
    Button create;

    TextView facebook , google;

    int RC_SIGN_IN = 12;

    GoogleApiClient googleApiClient ;

    CallbackManager mCallbackManager;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this);

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        Log.d("asdasdasd" , object.toString());

                        try {
                            final String pass = object.getString("id");
                            final String email1 = object.getString("email");
                            final String name = object.getString("name");


                            final Bean b = (Bean)getApplicationContext();

                            bar.setVisibility(View.VISIBLE);


                            final Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final allAPIs cr = retrofit.create(allAPIs.class);
                            Call<LoginBean> call = cr.login(email1 , pass );

                            call.enqueue(new Callback<LoginBean>() {
                                @Override
                                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                                    if (Objects.equals(response.body().getStatus(), 1))
                                    {

                                        Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        Bean b = (Bean)getApplicationContext();
                                        b.userid = response.body().getData().getUserId();
                                        b.email = response.body().getData().getEmail();
                                        b.name = response.body().getData().getUserName();

                                        Log.d("skjg" , "response");


                                        edit.putString("email" , email1);
                                        edit.putString("pass" , pass);
                                        edit.apply();


                                        Intent i = new Intent(CreateAccount .this , MainActivity.class);
                                        startActivity(i);
                                        finish();

                                        bar.setVisibility(View.GONE);
                                    }
                                    else
                                    {

                                        bar.setVisibility(View.VISIBLE);





                                        Call<CreateBean> call2 = cr.social(name , email1 , pass , "");
                                        call2.enqueue(new Callback<CreateBean>() {
                                            @Override
                                            public void onResponse(Call<CreateBean> call, Response<CreateBean> response) {

                                                if (Objects.equals(response.body().getStatus(), "1")){

                                                    Log.d("fhs" , "response");

                                                    if (Objects.equals(response.body().getMessage(), "Registered Successfully"))
                                                    {

                                                        Call<LoginBean> call3 = cr.login(email1 , pass );

                                                        call3.enqueue(new Callback<LoginBean>() {
                                                            @Override
                                                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                                                                if (Objects.equals(response.body().getStatus(), 1))

                                                                {

                                                                    Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                                    Bean b = (Bean)getApplicationContext();
                                                                    b.userid = response.body().getData().getUserId();
                                                                    b.email = response.body().getData().getEmail();
                                                                    b.name = response.body().getData().getUserName();

                                                                    Log.d("skjg" , "response");


                                                                    edit.putString("email" , email1);
                                                                    edit.putString("pass" , pass);
                                                                    edit.apply();


                                                                    Intent i = new Intent(CreateAccount.this , MainActivity.class);
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

                                    Log.d("dfjsdg" , response.body().getMessage());




                                }

                                @Override
                                public void onFailure(Call<LoginBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);

                                    Log.d("fgkjf" , t.toString());

                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email,id,name,picture");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {

                Toast.makeText(CreateAccount.this, "Login Cancel", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(CreateAccount.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



        setContentView(R.layout.activity_create_account);

        pref = getSharedPreferences("pref" , Context.MODE_PRIVATE);
        edit = pref.edit();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        facebook = (TextView)findViewById(R.id.facebook);
        google = (TextView)findViewById(R.id.google);

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





        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(CreateAccount.this , Arrays.asList("email"));


            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();




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

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.d("mail" , acct.getEmail());
            Log.d("name", acct.getDisplayName());
            Log.d("id", acct.getId());


            final String name = acct.getDisplayName();
            final String pass = acct.getId();
            final String email1 = acct.getEmail();



            bar.setVisibility(View.VISIBLE);

            final Bean b = (Bean)getApplicationContext();


            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final allAPIs cr = retrofit.create(allAPIs.class);
            Call<LoginBean> call = cr.login(email1 , pass );

            call.enqueue(new Callback<LoginBean>() {
                @Override
                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                    if (Objects.equals(response.body().getStatus(), 1))
                    {

                        Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Bean b = (Bean)getApplicationContext();
                        b.userid = response.body().getData().getUserId();
                        b.email = response.body().getData().getEmail();
                        b.name = response.body().getData().getUserName();

                        Log.d("skjg" , "response");


                        edit.putString("email" , email1);
                        edit.putString("pass" , pass);
                        edit.apply();


                        Intent i = new Intent(CreateAccount .this , MainActivity.class);
                        startActivity(i);
                        finish();

                        bar.setVisibility(View.GONE);
                    }
                    else
                    {

                        bar.setVisibility(View.VISIBLE);





                        Call<CreateBean> call2 = cr.social(name , email1 , pass , "");
                        call2.enqueue(new Callback<CreateBean>() {
                            @Override
                            public void onResponse(Call<CreateBean> call, Response<CreateBean> response) {

                                if (Objects.equals(response.body().getStatus(), "1")){

                                    Log.d("fhs" , "response");

                                    if (Objects.equals(response.body().getMessage(), "Registered Successfully"))
                                    {

                                        Call<LoginBean> call3 = cr.login(email1 , pass );

                                        call3.enqueue(new Callback<LoginBean>() {
                                            @Override
                                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                                                if (Objects.equals(response.body().getStatus(), 1))

                                                {

                                                    Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                    Bean b = (Bean)getApplicationContext();
                                                    b.userid = response.body().getData().getUserId();
                                                    b.email = response.body().getData().getEmail();
                                                    b.name = response.body().getData().getUserName();

                                                    Log.d("skjg" , "response");


                                                    edit.putString("email" , email1);
                                                    edit.putString("pass" , pass);
                                                    edit.apply();


                                                    Intent i = new Intent(CreateAccount.this , MainActivity.class);
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

                    Log.d("dfjsdg" , response.body().getMessage());




                }

                @Override
                public void onFailure(Call<LoginBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                    Log.d("fgkjf" , t.toString());

                }
            });





        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
