package com.example.dsm2016.brain;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ghdth on 2018-06-27.
 */

public class DBActivity {
    static public Realm mRealm;
    static public void Realm(){
        try {
            mRealm=Realm.getDefaultInstance();

        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
    }
}
