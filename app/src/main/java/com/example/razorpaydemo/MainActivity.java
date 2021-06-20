package com.example.razorpaydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton pay=(FloatingActionButton) findViewById(R.id.bt_pay);

        String sAmount="100";

        int amount =Math.round(Float.parseFloat(sAmount)*100);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize RazorPay Checkout
                Checkout checkout=new Checkout();
                //Set Key Id
                checkout.setKeyID("rzp_test_odnb7AGWN11VEZ");
                //Set Image
                checkout.setImage(R.drawable.rzp_logo);

                JSONObject object=new JSONObject();
                //Put name
                try {
                    //Put name
                    object.put("Name","Utkarsh Kumar");
                    //Put Description
                    object.put("Description","Test PAY");
                    //Put theme color
                    object.put("theme.color","#0093DD");
                    //Put Currency Unit
                    object.put("Currency","INR");
                    //Put amount
                    object.put("prefill.contact","7890249463");
                    //Put email
                    object.put("prefill.email","basudebdashm@gmail.com");
                    //Open razorpay checkout activity
                    checkout.open(MainActivity.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        //Initialize Alert Dialog
        AlertDialog.Builder buider =new AlertDialog.Builder(this);
        //Set Title
        buider.setTitle("Payment ID");
        //Set message
        buider.setMessage(s);
        //Show Alert Dialog
        buider.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}