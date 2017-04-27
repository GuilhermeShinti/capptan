package com.example.guis.capptan;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guis.capptan.Model.Person;
import com.example.guis.capptan.Model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_email;
    EditText et_pass;
    Button bt_login;
    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = (EditText)findViewById(R.id.et_email);
        et_pass = (EditText)findViewById(R.id.et_pass);

        bt_login = (Button)findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        bt_register = (Button)findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        Intent it;
        switch (v.getId()){
            case R.id.bt_login:
                Log.d("ACTION", "bt_login");

                List<User> uAuth;
                uAuth = User.find(User.class,"email = ? and password = ?",email,pass);

                if (uAuth.size() > 0){
                    Log.d("CHECK AUTH", "OK");
                    it = new Intent(this, PersonsActivity.class);
                    it.putExtra("userId",uAuth.get(0).getId());
                    startActivity(it);
                    finish();
                } else {
                    Snackbar.make(getCurrentFocus(), "Dados incorretos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                break;
            case R.id.bt_register:
                Log.d("ACTION", "bt_register");
                it = new Intent(this, RegisterActivity.class);
                startActivity(it);
                break;
        }
    }
}
