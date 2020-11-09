package com.example.cleaneats.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.cleaneats.database.Account;

@Dao
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Report report);


}
