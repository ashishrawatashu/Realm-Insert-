package Helper;

import android.util.Log;

import java.util.ArrayList;

import Model.Student;
import io.realm.Realm;
import io.realm.RealmResults;

public class MyHelper {

    Realm realm;
    RealmResults<Student> studentRealmResults;

    public MyHelper(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB(){

        studentRealmResults = realm.where(Student.class).findAll();


    }

    public ArrayList<Student> refresh (){
        ArrayList<Student> listData = new ArrayList<>();
        for (Student student : studentRealmResults){
            listData.add(student);
        }

        Log.e("data",listData.toString());

        return listData;
    }
}
