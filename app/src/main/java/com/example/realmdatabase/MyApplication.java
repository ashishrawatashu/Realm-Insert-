package com.example.realmdatabase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("StudentData.realm")
                .schemaVersion(42)
                .migration(new MyMigration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        Realm.getInstance(realmConfiguration);

    }

    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();

    }
}
