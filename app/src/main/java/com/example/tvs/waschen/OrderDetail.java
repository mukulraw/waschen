package com.example.tvs.waschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetail extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        tv1 = (TextView) findViewById(R.id.orderno);
        tv2 = (TextView) findViewById(R.id.quantity);
        tv3 = (TextView) findViewById(R.id.service);
        tv4 = (TextView) findViewById(R.id.bookingdetail);
        tv5 = (TextView) findViewById(R.id.delieverydate);
        tv6 = (TextView) findViewById(R.id.tobepaid);
        tv7 = (TextView) findViewById(R.id.name);
        tv8 = (TextView) findViewById(R.id.date);
        tv9 = (TextView) findViewById(R.id.mobile);
        tv10 = (TextView) findViewById(R.id.datet);
        tv11 = (TextView) findViewById(R.id.email);
        tv12 = (TextView) findViewById(R.id.emaill);
    }
}
