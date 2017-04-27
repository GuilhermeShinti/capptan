package com.example.guis.capptan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.guis.capptan.Adapter.PersonAdapter;
import com.example.guis.capptan.Model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsActivity extends AppCompatActivity {

    static ListView personList;
    static PersonAdapter pAdapter;
    static Long userId = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        if (intent.getLongExtra("userId",0L)!=0L){
            userId = intent.getLongExtra("userId",0L);
            Log.d("CHECK USERID", userId.toString());
        }

//        Person.populate();

        personList = (ListView)findViewById(R.id.lv_personList);
        refreshList(getBaseContext());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent it = new Intent(getBaseContext(), NewPersonActivity.class);
                it.putExtra("userId", userId);
                startActivity(it);
            }
        });

        FloatingActionButton fab_delete = (FloatingActionButton) findViewById(R.id.fab_delete);
        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Person.deleteAll(Person.class);
                Person.deleteAll(Person.class, "user_id = ?", userId.toString());
                pAdapter.clear();
                pAdapter.notifyDataSetChanged();
                Snackbar.make(view, "Registro limpo com sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                PersonsActivity.refreshList(getApplicationContext());
            }
        });
    }

    public static void refreshList(Context context) {
        Log.d("ACTION", "LIST REFRESH");
        Log.d("CHECK", "USERID->"+userId);
        if (Person.getAllById(userId).size() > 0){
            pAdapter = new PersonAdapter(context, Person.getAllById(userId));
        }
        personList.setAdapter(pAdapter);
//        pAdapter = new PersonAdapter(context, Person.getAll());
//        personList.setAdapter(pAdapter);

    }

}
