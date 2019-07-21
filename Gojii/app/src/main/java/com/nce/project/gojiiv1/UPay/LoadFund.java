package com.nce.project.gojiiv1.UPay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nce.project.gojiiv1.HomePageActivity;
import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.Api;
import com.nce.project.gojiiv1.helper.ImageViewPagerAdapter;
import com.nce.project.gojiiv1.helper.RetrofitAPI;
import com.nce.project.gojiiv1.helper.ViewPagerAdapter;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoadFund extends AppCompatActivity {
    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Button loadFundBtn;
    private EditText cardEditText, amountEditText;
    ViewPager viewPager;
    Toolbar  toolbar;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_fund);
      //  loadFundBtn= findViewById(R.id.load);
        // cardEditText= findViewById(R.id.cardNumber);
        amountEditText=findViewById(R.id.amount);
        viewPager= findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Retrofit retrofitAPI = RetrofitAPI.getInstance();
        api = retrofitAPI.create(Api.class);

        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(this);

        viewPager.setAdapter(imageViewPagerAdapter);






     /*   loadFundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumberValue = cardEditText.getText().toString();
                String amountValue = amountEditText.getText().toString();

                loadFundViaCard(cardNumberValue,amountValue);

            }
        });*/



    }
/*
    private void loadFundViaCard(String cardNumberValue, String amountValue) {

        //SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken","");

        if (TextUtils.isEmpty(cardNumberValue)){
            Toast.makeText(this, "CardNumber cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(amountValue)) {
            Toast.makeText(this, "Amount cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable.add(api.load("Bearer "+accessToken,cardNumberValue,amountValue)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String response) throws Exception {
                JSONObject jsonObject= new JSONObject(response);
                JSONObject customer= jsonObject.getJSONObject("customer");
                String newLoadedAmount = customer.getString("balance");
                Toast.makeText(LoadFund.this, "Successfully loaded "+newLoadedAmount+" to your wallet.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoadFund.this, HomePageActivity.class);
                intent.putExtra("balance",newLoadedAmount);
                startActivity(intent);
            }
        }));



    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }*/
}
