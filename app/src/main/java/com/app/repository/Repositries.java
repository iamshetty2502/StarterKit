package com.app.repository;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.os.AsyncTask;

import com.app.App;
import com.app.database.DatabaseStorage;
import com.app.models.User;
import com.app.utils.Constants;

import java.io.Serializable;


@SuppressLint("StaticFieldLeak")
public class Repositries implements Serializable {

    private static Repositries repositries;
    private DatabaseStorage databaseStorage;

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //database.execSQL("CREATE TABLE IF NOT EXISTS `Contacts` (`contactNumber` TEXT , `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `contactId` TEXT NOT NULL , `gatewayId` TEXT)");
        }
    };

    private Repositries() {
        databaseStorage = Room.databaseBuilder(App.getAppContext(), DatabaseStorage.class, Constants.DatabaseName).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigration().build();
    }

    public static Repositries getInstance() {
        if (repositries == null) {
            repositries = new Repositries();
        }
        return repositries;
    }

    //Gateway tasks starts

    void insertUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                databaseStorage.daoAccess().insertUser(user);
                return null;
            }
        }.execute();
    }

    public void updateUser(final User user) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    databaseStorage.daoAccess().updateUser(user);
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(final String username) {
        try {

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    databaseStorage.daoAccess().deleteUser(username);
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Gateway tasks ends

}