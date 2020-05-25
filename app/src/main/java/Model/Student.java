package Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {

    @PrimaryKey
    private int student_id;
    private String name;

    public int getId() {
        return student_id;
    }

    public void setId(int id) {
        this.student_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
