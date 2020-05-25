package com.example.realmdatabase;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        final RealmSchema realmSchema  = realm.getSchema();

        if (oldVersion == 1){
            final RealmObjectSchema objectSchema = realmSchema.get("Student");
            objectSchema.addField("student_id", int.class);
        }

    }
}
