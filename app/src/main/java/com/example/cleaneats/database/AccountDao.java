package com.example.cleaneats.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cleaneats.database.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Report report);

    @Query("SELECT * FROM accounts")
    LiveData<List<Account>> getAllAccounts();
}
