package com.nce.project.gojiiv1.UPay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.Api;
import com.nce.project.gojiiv1.helper.Consumer;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class electricity extends AppCompatActivity {

    private EditText scEdtText, consumerIDEdtTxt, consumerEdtTxt, amountEdtTxt;
    private Button getDetBtn;
    private static Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        scEdtText=findViewById(R.id.scNumber);
        consumerEdtTxt=findViewById(R.id.consumerName);
        consumerIDEdtTxt=findViewById(R.id.consumerID);
        amountEdtTxt=findViewById(R.id.amount);
        getDetBtn=findViewById(R.id.getDetails);


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

      api = retrofit.create(Api.class);
        getDetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sc_value = scEdtText.getText().toString();
                String con_value = consumerIDEdtTxt.getText().toString();

                getDetails(sc_value,con_value);
            }
        });

    }

    private void getDetails(String s, String c) {
        Call<Consumer> call = api.getConsumer(s,c);
        call.enqueue(new Callback<Consumer>() {
            @Override
            public void onResponse(Call<Consumer> call, Response<Consumer> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(electricity.this, "Failed to load details", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(electricity.this, "result"+response.body(), Toast.LENGTH_SHORT).show();
              /* Consumer consumer = response.body();
                consumerEdtTxt.setText(consumer.getName());
                amountEdtTxt.setText(consumer.getAmount().toString());
                consumerEdtTxt.setVisibility(View.VISIBLE);
                amountEdtTxt.setVisibility(View.VISIBLE);
                getDetBtn.setText("Pay");*/


            }

            @Override
            public void onFailure(Call<Consumer> call, Throwable t) {
                Toast.makeText(electricity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
