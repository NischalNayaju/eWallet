package com.nce.project.gojiiv1.Authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nce.project.gojiiv1.HomePageActivity;
import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.Api;
import com.nce.project.gojiiv1.helper.RetrofitAPI;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    private static CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog dialog;
    EditText username, email ,phonenumber, password, confirmpassword ;
    Button proceed ;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        proceed= findViewById(R.id.proceed);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Please wait...");

        Retrofit retrofitAPI = RetrofitAPI.getInstance();
        api = retrofitAPI.create(Api.class);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String emailvalue = email.getText().toString();
                String phonenumberValue = phonenumber.getText().toString();
                String passwordValue = password.getText().toString();
                String confirmpasswordValue = confirmpassword.getText().toString();

                signUp(usernameValue,emailvalue,passwordValue);
                dialog.show();


            }
        });




    }

    private void signUp(String usernameValue, String emailvalue, String passwordValue) {

        disposable.add(api.register(emailvalue,passwordValue,usernameValue)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String response) throws Exception {
                JSONObject jsonObject= new JSONObject(response);
                JSONObject customer = jsonObject.getJSONObject("customer");
                JSONObject token = jsonObject.getJSONObject("token");
                String name = customer.getString("name").trim();
                String balance = customer.getString("balance");
                String accessToken = token.getString("accessToken");
                String email = customer.getString("email");

                /*SharedPreferences sharedPreferences = getSharedPreferences("CustomerInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("name", name);
                editor.putString("accessToken", accessToken);
                editor.putString("balance", balance);
                editor.apply();
*/
                Toast.makeText(SignupActivity.this, "Success ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();


                Intent intent= new Intent(SignupActivity.this, FingerPrintActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("balance",balance);
                bundle.putString("accessToken",accessToken);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        }));

    }
}

