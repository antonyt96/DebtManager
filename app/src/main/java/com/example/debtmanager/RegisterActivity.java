package com.example.debtmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class RegisterActivity extends AppCompatActivity {

    //register_activity.xml elements
    EditText first_name, last_name, dob, email, password;
    Button complete_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        first_name = (EditText)findViewById(R.id.registerFirstName);
        last_name = (EditText)findViewById(R.id.registerLastName);
        dob =(EditText)findViewById(R.id.registerDob);
        email = (EditText)findViewById(R.id.registerEmail);
        password = (EditText)findViewById(R.id.registerPassword);

        complete_reg = (Button)findViewById(R.id.completeSignupButton);

        //checks form then registers user
        complete_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateRegForm();
            }
        });
    }

    protected void validateRegForm(){

        int first = first_name.getText().length();
        int last = last_name.getText().length();
        //first name validation
        if(TextUtils.isEmpty(first_name.getText())){
            first_name.setError("Invalid field");
        }
        else if(first < 3 || first >30){
            first_name.setError("Invalid field length");

        }
        // last name velidation
        else if(TextUtils.isEmpty(last_name.getText())){
            last_name.setError("Invalid field");

        }
        else if(last <3 || last >30){
            last_name.setError("Invalid field length");

        }
        else if(TextUtils.isEmpty(dob.getText())){
            dob.setError("Invalid field");

        }
        //email validation
        else if(TextUtils.isEmpty(email.getText()) || !email.getText().toString().contains("@") || !email.getText().toString().endsWith(".com")) {
            email.setError("Invalid field");

        }
        else if(TextUtils.isEmpty(password.getText())){
            password.setError("Invalid field");

        }
        else {
            final Bundle userInfo = new Bundle();

            userInfo.putString("FIRST_NAME", first_name.getText().toString());
            userInfo.putString("LAST_NAME", last_name.getText().toString());
            userInfo.putString("DOB", dob.getText().toString());
            userInfo.putString("EMAIL", email.getText().toString());
            userInfo.putString("PASSWORD", password.getText().toString());

            Intent backToLogin = new Intent(this, MainActivity.class);
            backToLogin.putExtras(userInfo);
            startActivity(backToLogin);

        }

    }
}
