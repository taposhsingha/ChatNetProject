package com.example.chatnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText username, password, confirmpassword;
    Button login, createaccount;
    DBHelper DB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.log_username);
        password = (EditText) findViewById(R.id.log_password);
        confirmpassword = (EditText) findViewById(R.id.confirm_password);
        login = (Button) findViewById(R.id.log_button);
        createaccount = (Button) findViewById(R.id.reg_button);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loguser = username.getText().toString();
                String reg_password = password.getText().toString();
                String repassword = confirmpassword.getText().toString();

                if (loguser.equals("") || password.equals("")|| repassword.equals("")){
                    Toast.makeText(login.this,"Please input the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(repassword)) {
                        Boolean checkuser = DB.checkusername(loguser);
                        if (!checkuser) {
                            Toast.makeText(login.this, "Login failed",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(loginIntent);
                        }
                    }
                }
            }
        });

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reguser = username.getText().toString();
                String reg_password = password.getText().toString();
                String repassword = confirmpassword.getText().toString();

                if (reguser.equals("") || password.equals("")|| repassword.equals("")){
                    Toast.makeText(login.this,"Please input the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(repassword)) {
                        Boolean checkuser = DB.checkusername(reguser);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(reguser, reg_password);
                            if (insert){
                                Toast.makeText(login.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent regIntent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(regIntent);
                            }
                        }
                    }
                }
            }
        });
    }
}
