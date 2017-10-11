package com.TBX.tvs.waschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.TBX.tvs.waschen.OrderDetailPOJO.DetailBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewMore extends AppCompatActivity {

    TextView date , id  , address , zip ;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        id = (TextView) findViewById(R.id.id);
        date = (TextView) findViewById(R.id.date);
        address = (TextView) findViewById(R.id.address);
        zip = (TextView) findViewById(R.id.zip);
        linear = (LinearLayout) findViewById(R.id.linear);


    }
}
