package com.example.guis.capptan.Model;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by pamel on 25/04/2017.
 */

public class User extends SugarRecord {
    @Unique
    String email;
    String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static boolean existUser(String email){
        List<User> user = User.find(User.class, "email = ?", email);
        if (user.size() > 0) {
            if (user.get(0).getEmail().equals(email))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    public static String checkEmail(String param){
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

    public static String checkPass(String param){
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
