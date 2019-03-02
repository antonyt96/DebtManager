package com.example.debtmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login_button;
    Button register_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.loginEmail);
        password =(EditText)findViewById(R.id.loginPassword);

        login_button =(Button)findViewById(R.id.loginButton);
        register_button=(Button)findViewById(R.id.registerButton);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLoginForm();
                validateUserAndLogin();

            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(v.getContext(),RegisterActivity.class);
                startActivity(registerIntent);

            }
        });
    }

    protected void validateLoginForm(){

        //Input validation
        if(TextUtils.isEmpty(email.getText()) || !email.getText().toString().contains("@") || !email.getText().toString().endsWith(".com")) {
            email.setError("Invalid field");
        }
        if(TextUtils.isEmpty(password.getText())){
            password.setError("Invalid field");
        }

    }

    public void validateUserAndLogin(){

        Intent intent = getIntent();
        Bundle regUserInfo = intent.getExtras();

        //if user has not registered, do not sign in.
        if (regUserInfo == null){

            Context context = getApplicationContext();
            CharSequence text = "Please Register First!";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();

            return;
        }

        //getting data from registration
        String first_name = regUserInfo.getString("FIRST_NAME");
        String last_name = regUserInfo.getString("LAST_NAME");
        String dob = regUserInfo.getString("DOB");
        String reg_email = regUserInfo.getString("EMAIL");
        String reg_password = regUserInfo.getString("PASSWORD");

        //Registration validation and sign
        if(!email.getText().toString().equals(reg_email) || !password.getText().toString().equals(reg_password)){

            Context context = getApplicationContext();
            CharSequence text = "Please Register!";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();

        }
        else if(email.getText().toString().equals(reg_email) && password.getText().toString().equals(reg_password)){

            Intent loginIntent = new Intent(this, LoginSuccess.class);
            startActivity(loginIntent);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Error has ocurred!";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();
        }

    }


}
