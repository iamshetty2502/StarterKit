package com.app.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.models.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DatabaseStorage extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
