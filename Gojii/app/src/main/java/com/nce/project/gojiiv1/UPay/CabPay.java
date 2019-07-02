package com.nce.project.gojiiv1.UPay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nce.project.gojiiv1.R;

public class CabPay extends AppCompatActivity {
    private static EditText cabNumberEditText, amountEditText;
    private static Button payButton, registerCabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_pay);

        cabNumberEditText=findViewById(R.id.cabNumber);
        amountEditText=findViewById(R.id.amount);
        payButton=findViewById(R.id.pay);
        registerCabButton=findViewById(R.id.registerCab);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean response = false;
                final String cabNumberValue = cabNumberEditText.getText().toString().trim();
                final String amountValue = amountEditText.getText().toString().trim();
                response=cabValidate(cabNumberValue,amountValue);

                if(response==true){
                    //code for pay
                }

            }
        });

    }

    private boolean cabValidate(String cabNumberValue, String amountValue) {

        /*
        validate cabNumber from the webserver fetching the api via retrofit,
        and validate the amount the sender has on his wallet:
        conditions met return true
        else{
        Toast error;
        }


        */
return true;
    }
}
