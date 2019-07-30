package com.example.medisearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medisearch.APIsInterface.UserRegister;
import com.example.medisearch.WebService.Service;
import com.example.medisearch.WebService.User.UserRegisterRequest;
import com.example.medisearch.WebService.User.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextEmail, editTextFullname, editTextContactNo, editTextPassword, editTextAddress;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail       = findViewById(R.id.txtEmail);
        editTextFullname    = findViewById(R.id.txtFullname);
        editTextAddress     = findViewById(R.id.txtAddress);
        editTextContactNo   = findViewById(R.id.txtContact);
        editTextPassword    = findViewById(R.id.txtPassword);

        findViewById(R.id.btnSignUp).setOnClickListener(view -> signUp());

    }

    private void signUp()
    {


        //Declare progressDialog before so we can use .hide() later!
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Retrofit retrofit           = Service.RetrofitInstance(getApplicationContext());
        UserRegister services    = retrofit.create(UserRegister.class);
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();

        userRegisterRequest.setEmail(editTextEmail.getText().toString());
        userRegisterRequest.setAddress(editTextAddress.getText().toString());
        userRegisterRequest.setContactNo(editTextContactNo.getText().toString());
        userRegisterRequest.setFullname(editTextFullname.getText().toString());
        userRegisterRequest.setPassword(editTextPassword.getText().toString());

        Call<UserRegisterResponse> userRegisterResponseCall = services.register(userRegisterRequest);

        userRegisterResponseCall.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, retrofit2.Response<UserRegisterResponse> response) {
                    if (response.isSuccessful() ) {
                        Toast.makeText(RegisterActivity.this, "Successfully Registered..", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });



    }

}
