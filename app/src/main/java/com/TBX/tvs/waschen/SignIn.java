package com.TBX.tvs.waschen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignIn extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText email,pass;
    TextView signin;
    TextView create;
    TextView facebbok , google;
    GoogleApiClient googleApiClient ;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    ProgressBar bar;
    int RC_SIGN_IN = 12;

    CallbackManager mCallbackManager;

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

                                        Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        Log.d("asdasd", "1");

                                        Bean b = (Bean)getApplicationContext();
                                        b.userid = response.body().getData().getUserId();
                                        b.email = response.body().getData().getEmail();
                                        b.name = response.body().getData().getUserName();

                                        Log.d("skjg" , "response");


                                        edit.putString("email" , email1);
                                        edit.putString("pass" , pass);
                                        edit.apply();


                                        Intent i = new Intent(SignIn .this , MainActivity.class);
                                        startActivity(i);
                                        finish();

                                        bar.setVisibility(View.GONE);
                                    }
                                    else
                                    {

                                        bar.setVisibility(View.VISIBLE);



                                        Log.d("asdasd", "2");

                                        Call<CreateBean> call2 = cr.social(name , email1 , pass , "");
                                        call2.enqueue(new Callback<CreateBean>() {
                                            @Override
                                            public void onResponse(Call<CreateBean> call, Response<CreateBean> response) {

                                                Log.d("asdasd", "3");

                                                if (Objects.equals(response.body().getStatus(), "1")){

                                                    Log.d("fhs" , "response");



                                                        Call<LoginBean> call3 = cr.login(email1 , pass );

                                                        call3.enqueue(new Callback<LoginBean>() {
                                                            @Override
                                                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {



                                                                if (Objects.equals(response.body().getStatus(), 1))

                                                                {

                                                                    Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                                    Bean b = (Bean)getApplicationContext();
                                                                    b.userid = response.body().getData().getUserId();
                                                                    b.email = response.body().getData().getEmail();
                                                                    b.name = response.body().getData().getUserName();

                                                                    Log.d("skjg" , "response");


                                                                    edit.putString("email" , email1);
                                                                    edit.putString("pass" , pass);
                                                                    edit.apply();


                                                                    Intent i = new Intent(SignIn.this , MainActivity.class);
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
                                                    Toast.makeText(SignIn.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

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

                Toast.makeText(SignIn.this, "Login Cancel", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(SignIn.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



        setContentView(R.layout.activity_sign_in);

        pref = getSharedPreferences("pref" , Context.MODE_PRIVATE);
        edit = pref.edit();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        bar = (ProgressBar) findViewById(R.id.progress);

        create = (TextView)findViewById(R.id.create);
        facebbok = (TextView)findViewById(R.id.facebook);
        google = (TextView)findViewById(R.id.google);

facebbok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        LoginManager.getInstance().logInWithReadPermissions(SignIn.this , Arrays.asList("email"));


    }
});
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();




            }
        });
        email = (EditText) findViewById(R.id.e);
        pass = (EditText) findViewById(R.id.p);


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
                                    b.cartid = response.body().getData().getCartId();
                                    b.email = response.body().getData().getEmail();
                                    b.name = response.body().getData().getUserName();

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

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
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

                        Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Bean b = (Bean)getApplicationContext();
                        b.userid = response.body().getData().getUserId();
                        b.email = response.body().getData().getEmail();
                        b.name = response.body().getData().getUserName();

                        Log.d("skjg" , "response");


                        edit.putString("email" , email1);
                        edit.putString("pass" , pass);
                        edit.apply();


                        Intent i = new Intent(SignIn .this , MainActivity.class);
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

                                                    Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                    Bean b = (Bean)getApplicationContext();
                                                    b.userid = response.body().getData().getUserId();
                                                    b.email = response.body().getData().getEmail();
                                                    b.name = response.body().getData().getUserName();

                                                    Log.d("skjg" , "response");


                                                    edit.putString("email" , email1);
                                                    edit.putString("pass" , pass);
                                                    edit.apply();


                                                    Intent i = new Intent(SignIn.this , MainActivity.class);
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
                                    Toast.makeText(SignIn.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
