package com.example.guis.capptan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guis.capptan.Model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_pass;
    String email;
    String pass;
    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = (EditText)findViewById(R.id.et_email);
        et_pass = (EditText)findViewById(R.id.et_pass);

        bt_register = (Button)findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = et_email.getText().toString();
                pass = et_pass.getText().toString();

                User user = new User(email, pass);

                if (!checkEmail(email).equals("OK")){
                    Toast.makeText(getBaseContext(), checkEmail(email), Toast.LENGTH_SHORT).show();
                } else
                if (!checkPass(pass).equals("OK")) {
                    Toast.makeText(getBaseContext(), checkPass(pass), Toast.LENGTH_LONG).show();
                } else {
                    user.save();

                    Intent it = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(it);
                    finish();
                }


            }
        });

    }

    private String checkEmail(String param){
        Log.d("EMAIL_PARAM",param);
        System.out.println("EMAIL_PARAM : "+param);
        if (param.isEmpty()){
            // Email está vazio
            return "Email não foi preenchido";
        }
        if (param.contains(" ")){
            // Não é permido espaços
            return "Remova os espaços em branco do Email";
        }
        return "OK";
    }

    private String checkPass(String param){
        Log.d("SENHA_PARAM",param);
        System.out.println("SENHA_PARAM : "+param);
        if (param.isEmpty()){
            // Email está vazio
            return "Senha não foi preenchida";
        }
        if (param.contains(" ")){
            // Não é permido espaços
            return "Remova os espaços em branco da Senha";
        }
        return "OK";
    }

}
