package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Helper.MyHelper;
import Model.ListAdapter;
import Model.Student;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText name, id;
    Button saveBT;
    RecyclerView listRV;
    MyHelper myHelper;
    RealmChangeListener realmChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        name = findViewById(R.id.nameET);
        id =findViewById(R.id.studentId);
        saveBT =findViewById(R.id.saveBT);
        listRV=findViewById(R.id.listRV);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        myHelper = new MyHelper(realm);
        myHelper.selectFromDB();
        ListAdapter listAdapter = new ListAdapter(MainActivity.this, myHelper.refresh());
        listRV.setLayoutManager(new LinearLayoutManager(this));
        listRV.setAdapter(listAdapter);
        refresh();

    }

    private void refresh(){

         realmChangeListener = new RealmChangeListener() {
             @Override
             public void onChange(Object o) {
                 ListAdapter listAdapter = new ListAdapter(MainActivity.this, myHelper.refresh());
                 listRV.setAdapter(listAdapter);

             }
         };
         realm.addChangeListener(realmChangeListener);
    }


    private void saveData() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Number number = bgRealm.where(Student.class).max("student_id");
                int newKey = (number == null) ? 1 : number.intValue() + 1;
                Student student = bgRealm.createObject(Student.class, newKey);
                student.setName(name.getText().toString().trim());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this, "Failed"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}

