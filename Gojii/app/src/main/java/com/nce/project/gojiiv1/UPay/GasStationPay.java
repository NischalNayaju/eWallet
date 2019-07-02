package com.nce.project.gojiiv1.UPay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nce.project.gojiiv1.R;

public class GasStationPay extends AppCompatActivity {
    private static EditText stationEditText, amountEditText;
    private static Button payButton, registerStationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_pay);

        stationEditText=findViewById(R.id.station);
        amountEditText=findViewById(R.id.amount);
        payButton=findViewById(R.id.pay);
        registerStationButton=findViewById(R.id.registerStation);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean response= false;
                final String stationValue = stationEditText.getText().toString().trim();
                final String amountValue= amountEditText.getText().toString().trim();

                response=validate(stationValue,amountValue);
                if (response==true){
                    /*pay the moneys*/
                }
            }
        });
    }

    private boolean validate(String stationValue, String amountValue) {
        return true;
    }
}
