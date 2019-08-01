package com.example.medisearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medisearch.APIsInterface.UserRegister;
import com.example.medisearch.WebService.Service;
import com.example.medisearch.WebService.User.UserLoginResponse;
import com.example.medisearch.WebService.User.UserRegisterErrorResponse;
import com.example.medisearch.WebService.User.UserRegisterRequest;
import com.example.medisearch.WebService.User.UserRegisterResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextEmail, editTextFullname, editTextContactNo, editTextPassword, editTextAddress , editTextConfirmPassword;
    private ProgressDialog progressDialog;

    private boolean validationResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editTextEmail           = findViewById(R.id.txtEmail);
        editTextFullname        = findViewById(R.id.txtFullname);
        editTextAddress         = findViewById(R.id.txtAddress);
        editTextContactNo       = findViewById(R.id.txtContact);
        editTextPassword        = findViewById(R.id.txtPassword);
        editTextConfirmPassword = findViewById(R.id.txtConfirmPassword);

        findViewById(R.id.btnSignUp).setOnClickListener(view -> signUp());
        this.forDevelopmentPurpose();
    }

    private void forDevelopmentPurpose() {
        editTextEmail.setText("christopher51@example.com");
        editTextFullname.setText("christopher");
        editTextAddress.setText("Awasian Tandag City");
        editTextContactNo.setText("09193693499");
        editTextPassword.setText("123456");
        editTextConfirmPassword.setText("123456");
    }

    // TODO refactor this.
    private boolean validate()
    {
        validationResult = true;
        String emailPattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if ( !editTextEmail.getText().toString().matches(emailPattern) ) {
            editTextEmail.setError("Email is invalid!");
            validationResult = false;
        }

        if ( editTextAddress.getText().toString().isEmpty() ) {
            editTextAddress.setError("Address field is required");
            validationResult = false;
        }

        if ( editTextContactNo.getText().toString().isEmpty() ) {
            editTextContactNo.setError("Contact no. field is required");
            validationResult = false;
        }

        if ( editTextFullname.getText().toString().isEmpty() ) {
            editTextFullname.setError("Fullname field is required");
            validationResult = false;
        }

        if ( editTextPassword.getText().toString().isEmpty() ) {
            editTextPassword.setError("Password field is required");
            validationResult = false;
        }

        if ( !(editTextPassword.getText().toString().length() >= 6 && editTextPassword.getText().toString().length() <= 20) ) {
            editTextPassword.setError("Password must be minimum of 6 and maximum of 20 characters.");
            validationResult = false;
        }

        if ( !editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString()) ) {
            editTextConfirmPassword.setError("Re-type password field must be equal to password field.");
            validationResult = false;
        }

        return validationResult;
    }

    private void signUp()
    {
        if  ( this.validate() ) {
            this.signUpRequest();
        }
    }

    private void signUpRequest() {
        //Declare progressDialog before so we can use .hide() later!
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Retrofit retrofit        = Service.RetrofitInstance(getApplicationContext());
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
                    if ( response.code() == 201 && response.isSuccessful() ) {
                        Toast.makeText(RegisterActivity.this, "Successfully Registered..", Toast.LENGTH_SHORT).show();
                    } else {
                        APIValidationResponder(response);
                    }

                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void APIValidationResponder(Response<UserRegisterResponse> response) {
        Gson gson = new Gson();
        Type type = new TypeToken<UserRegisterErrorResponse>() {}.getType();
        UserRegisterErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(),type);

        // TODO Refactor this.
        if ( errorResponse.getAddress() != null ) {
            editTextAddress.setError(errorResponse.getAddress().get(0));
        }

        if ( errorResponse.getContactNo() != null ) {
            editTextContactNo.setError(errorResponse.getContactNo().get(0));
        }

        if ( errorResponse.getEmail() != null ) {
            editTextEmail.setError(errorResponse.getEmail().get(0));
        }

        if ( errorResponse.getFullName() != null ) {
            editTextFullname.setError(errorResponse.getFullName().get(0));
        }

        if ( errorResponse.getPassword() != null ) {
            editTextPassword.setError(errorResponse.getPassword().get(0));
        }
    }

}
