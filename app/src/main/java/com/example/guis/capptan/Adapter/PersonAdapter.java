package com.example.guis.capptan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guis.capptan.Model.Person;
import com.example.guis.capptan.NewPersonActivity;
import com.example.guis.capptan.PersonsActivity;
import com.example.guis.capptan.R;

import java.util.List;

/**
 * Created by pamel on 25/04/2017.
 */

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, List<Person> listaDeUsers) {
        super(context, 0, listaDeUsers);
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Person person = getItem(position);
        final Long id = person.getId();

//        ViewHolder holder = null;
//        if (convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_person, null);
//            holder = new ViewHolder();
//            holder.tv_pName = (TextView)convertView.findViewById(R.id.tv_personName);
//            holder.tv_pAge = (TextView)convertView.findViewById(R.id.tv_personAge);
//            holder.tv_pCpf = (TextView)convertView.findViewById(R.id.tv_personCpf);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder)convertView.getTag();
//        }

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_person, null);
            ((TextView)convertView.findViewById(R.id.tv_personName)).setText(person.getName().toString());
            ((TextView)convertView.findViewById(R.id.tv_personAge)).setText(String.valueOf(person.getAge()));
            ((TextView)convertView.findViewById(R.id.tv_personCpf)).setText(person.getCpf().toString());
            (convertView.findViewById(R.id.bt_edit)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Person p = Person.findById(Person.class, id);
                    Intent it = new Intent(getContext(), NewPersonActivity.class);
                    it.putExtra("id",p.getId());
//                    it.putExtra("PersonAge",p.getAge());
//                    it.putExtra("PersonCpf",p.getCpf());
//                    it.putExtra("PersonName",p.getName());
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(it);
                    clear();
                    notifyDataSetChanged();
                    PersonsActivity.refreshList(getContext());
                }
            });
            (convertView.findViewById(R.id.bt_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Person p = Person.findById(Person.class, id);
                    p.delete();
                    clear();
                    notifyDataSetChanged();
                    PersonsActivity.refreshList(getContext());
                }
            });


        }
        return convertView;
    }

//    static class ViewHolder {
//        TextView tv_pName;
//        TextView tv_pAge;
//        TextView tv_pCpf;
//    }
}
