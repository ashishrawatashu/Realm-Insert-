package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    RealmResults<Student> studentRealmResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        name = findViewById(R.id.nameET);
        id =findViewById(R.id.studentId);
        saveBT =findViewById(R.id.saveBT);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
                Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
                startActivity(intent);
            }
        });

    }
    private void saveData() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                Number number = bgRealm.where(Student.class).max("student_id");
                int newKey = (number == null) ? 1 : number.intValue() + 1;
                Student student = bgRealm.createObject(Student.class, newKey);
                student.setName(name.getText().toString().trim());
                student.setAge(id.getText().toString().trim());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                name.getText().clear();
                id.getText().clear();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this, "Failed"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }



}

