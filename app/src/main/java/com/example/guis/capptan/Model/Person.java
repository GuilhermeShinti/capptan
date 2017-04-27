package com.example.guis.capptan.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pamel on 25/04/2017.
 */

public class Person extends SugarRecord{

    @Unique
    Long id;
    String name;
    int age;
    String cpf;
    Long userId;

    public Person(){}

    public Person(String name, int age, String cpf, Long userId) {
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.userId = userId;
    }

    public Person(Long id, String name, int age, String cpf, Long userId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.userId = userId;
    }

//    public static void populate() {
//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person("Guilherme", 23, "000.000.000-00"));
//        persons.add(new Person("Gabriel", 18, "111.111.111-11"));
//        persons.add(new Person("Pamela", 16, "222.222.222-22"));
//        SugarRecord.saveInTx(persons);
//    }

    public Long getId(){return id;};

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCpf() {
        return cpf;
    }

    public Long getUserId() {
        return userId;
    }

    public static List<Person> getAllById(Long userId)  {
        List<Person> listaDeUsers = new ArrayList<Person>();
//        listaDeUsers = Person.listAll(Person.class);
        listaDeUsers = Person.find(Person.class, "user_id = ?", userId.toString());
        return listaDeUsers;
    }

    public static List<Person> getAll()  {
        List<Person> listaDeUsers = new ArrayList<Person>();
        listaDeUsers = Person.listAll(Person.class);
        return listaDeUsers;
    }
}
