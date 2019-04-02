package com.app.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.app.models.User;

import java.util.List;

@Dao
public interface DaoAccess {

//Gateway

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Update
    void updateUser(User user);


    @Query("DELETE  FROM User  WHERE username =:username")
    void deleteUser(String username);


}
