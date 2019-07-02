package com.nce.project.gojiiv1.Authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.Api;
import com.nce.project.gojiiv1.helper.RetrofitAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    private static EditText emailEditText, passwordEditText;
    private static Button loginBtn;
    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);

        Retrofit retrofitAPI = RetrofitAPI.getInstance();
        api = retrofitAPI.create(Api.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                loginUser(email, password);
            }
        });


    }

    private void loginUser(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable.add(api.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    }
                }));


    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
