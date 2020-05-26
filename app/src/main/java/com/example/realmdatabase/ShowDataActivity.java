package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import Model.ListAdapter;
import Model.Student;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ShowDataActivity extends AppCompatActivity {
    RecyclerView listRV;
    RealmResults<Student> studentRealmResults;
    Realm realm;
    RealmChangeListener realmChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        realm = Realm.getDefaultInstance();
        listRV=findViewById(R.id.listRV);
        studentRealmResults = realm.where(Student.class).findAll();
        listRV.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(this,fresh());
        listRV.setAdapter(listAdapter);
        refresh();

    }

    public void refresh(){

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                 ListAdapter listAdapter = new ListAdapter(ShowDataActivity.this,fresh());
                 listRV.setAdapter(listAdapter);

            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    public ArrayList<Student> fresh (){
        ArrayList<Student> listData = new ArrayList<>();
        for (Student student : studentRealmResults){
            listData.add(student);
        }
        Log.e("data",listData.toString());

        return listData;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
