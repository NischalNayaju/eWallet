package com.nce.project.gojiiv1.Authentication;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.nce.project.gojiiv1.HomePageActivity;
import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.Api;
import com.nce.project.gojiiv1.helper.RetrofitAPI;
import com.nce.project.gojiiv1.security.TripleKeyDES;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    private static EditText emailEditText, passwordEditText;
    private static Button loginBtn, signUpbtn;
    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TripleKeyDES tripleKeyDES;
    Api api;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        signUpbtn = findViewById(R.id.signUp);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Please wait...");
        Retrofit retrofitAPI = RetrofitAPI.getInstance();
        api = retrofitAPI.create(Api.class);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String encryptedPassword = tripleKeyDES.harden(password);
                loginUser(email, encryptedPassword);
            }
        });


    }


    private void loginUser(final String email, final String password) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
         dialog.show();


        compositeDisposable.add(api.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(response -> {

                            Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                            JSONObject jsonObject = new JSONObject(response);


                            JSONObject customer = jsonObject.getJSONObject("customer");
                            JSONObject token = jsonObject.getJSONObject("token");
                            String name = customer.getString("name").trim();
                            String balance = customer.getString("balance");
                            String accessToken = token.getString("accessToken");

                            SharedPreferences sharedPreferences = getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putString("accessToken", accessToken);
                            editor.apply();
                            dialog.dismiss();


                            Toast.makeText(LoginActivity.this, "AccessToken: " + response, Toast.LENGTH_LONG).show();


                            // Toast.makeText(LoginActivity.this, "" + name+" "+balance, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                            Bundle extras = new Bundle();
                            extras.putString("name", name);
                            extras.putString("balance", balance);

                            intent.putExtras(extras);


                            startActivity(intent);
                            finish();



                        },
                        throwable -> {
                            Toast.makeText(this, "error" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        })


        );

    }



    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }


}
