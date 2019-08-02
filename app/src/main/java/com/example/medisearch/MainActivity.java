package com.example.medisearch;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medisearch.APIsInterface.UserLogin;
import com.example.medisearch.Models.Service;
import com.example.medisearch.Models.User.UserLoginRequest;
import com.example.medisearch.Models.User.UserLoginResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        this.forDevelopmentPurpose();

        findViewById(R.id.btnSignIn).setOnClickListener(view -> signIn());

        findViewById(R.id.btnSignUp).setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }

    private void forDevelopmentPurpose() {
        txtEmail.setText("christopher51@example.com");
        txtPassword.setText("123456");
    }


    // TODO refactor this.
    private boolean validate()
    {
        String emailPattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if  ( txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty() ) {
            return false;
        }

        if ( !txtEmail.getText().toString().matches(emailPattern) ) {
            return false;
        }

        return true;
    }

    private void signIn()
    {
        if ( this.validate() ) {
            this.requestSignIn();
        } else {
            Toast.makeText(this, "Please check your username or password", Toast.LENGTH_SHORT).show();
        }

    }

    private void requestSignIn()
    {
        //Declare progressDialog before so we can use .hide() later!
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing in...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Retrofit retrofit     = Service.RetrofitInstance(getApplicationContext());
        UserLogin services    = retrofit.create(UserLogin.class);
        UserLoginRequest userLoginRequest = new UserLoginRequest();

        userLoginRequest.setEmail(txtEmail.getText().toString());
        userLoginRequest.setPassword(txtPassword.getText().toString());

        Call<UserLoginResponse> userLoginResponseCall = services.login(userLoginRequest);

        userLoginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                // Successfully login and redirect to dashboard page
                if  (response.code() == 200 && response.isSuccessful() ) {
                    Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(i);
                } else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<UserLoginResponse>() {}.getType();
                    UserLoginResponse errorResponse = gson.fromJson(response.errorBody().charStream(),type);

                    Toast.makeText(MainActivity.this, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }




}
