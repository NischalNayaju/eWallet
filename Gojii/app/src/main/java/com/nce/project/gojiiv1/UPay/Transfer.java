package com.nce.project.gojiiv1.UPay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nce.project.gojiiv1.R;

public class Transfer extends AppCompatActivity {

    private EditText customerIdEditText, amountEditText;
    private static Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        amountEditText = findViewById(R.id.amount);
        customerIdEditText = findViewById(R.id.customerId);
        sendButton = findViewById(R.id.send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String amount = amountEditText.getText().toString();
                final String customerNumber= customerIdEditText.getText().toString();
                    validateTransfer(customerNumber, amount);

            }
        });


    }

    private void validateTransfer(String customerNumber, String amount) {
    }
}
