package com.example.guis.capptan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guis.capptan.Model.Person;

public class NewPersonActivity extends AppCompatActivity {

    EditText et_personName;
    EditText et_personAge;
    EditText et_personCpf;
    Button bt_cadastrar;
    Long id = 0L;
    Long userId;
    String name;
    int age;
    String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        Intent intent = getIntent();

        if (intent.getLongExtra("id",0L)!=0L) {
            id = intent.getLongExtra("id",0L);
            Log.d("CHECK ID", id.toString());
        }

        if (intent.getLongExtra("userId",0L)!=0L){
            userId = intent.getLongExtra("userId",0L);
            Log.d("CHECK USERID", userId.toString());
        }

        et_personName = (EditText) findViewById(R.id.et_personName);
        et_personAge = (EditText)findViewById(R.id.et_personAge);
        et_personCpf = (EditText)findViewById(R.id.et_personCpf);
        bt_cadastrar = (Button)findViewById(R.id.bt_registerPerson);
        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id > 0) {
                    name = et_personName.getText().toString();
                    age = Integer.parseInt(et_personAge.getText().toString());
                    cpf = et_personCpf.getText().toString();
                    Person pEdit = new Person(id,name,age,cpf,userId);
                    pEdit.update();

                } else {
                    name = et_personName.getText().toString();
                    age = Integer.parseInt(et_personAge.getText().toString());
                    cpf = et_personCpf.getText().toString();
                    Person person = new Person(name,age,cpf,userId);
                    person.save();
                }



                Intent it = new Intent(getBaseContext(), PersonsActivity.class);
                startActivity(it);
                finish();
            }
        });

            if (id > 0) {
                Person person = Person.findById(Person.class, id);
                Log.d("CHECK PERSON", person.getName()+" "+String.valueOf(person.getAge())+""+person.getCpf());
                et_personName.setText(person.getName());
                et_personAge.setText(String.valueOf(person.getAge()));
                et_personCpf.setText(person.getCpf());
            }


    }
}
