package com.example.medisearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medisearch.APIsInterface.UserLogin;
import com.example.medisearch.APIsInterface.UserRegister;
import com.example.medisearch.WebService.Service;
import com.example.medisearch.WebService.User.UserLoginRequest;
import com.example.medisearch.WebService.User.UserLoginResponse;
import com.example.medisearch.WebService.User.UserRegisterRequest;
import com.example.medisearch.WebService.User.UserRegisterResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

import okhttp3.ResponseBody;
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

        findViewById(R.id.btnSignIn).setOnClickListener(view -> {
            signIn();
        });

        findViewById(R.id.btnSignUp).setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }

    private void signIn()
    {
        //Declare progressDialog before so we can use .hide() later!
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING . . .");
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
                    Toast.makeText(MainActivity.this, "Successfully login, Redirecting...", Toast.LENGTH_SHORT).show();
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
