package com.example.cleaneats.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {

    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAccounts;

    public AccountRepository(Application application) {
        AccountDatabase db = AccountDatabase.getDatabase(application);
        mAccountDao = db.accountDao();
    }

    public void insert(Account account) {
        AccountDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.insert(account);
        });
    }

    public void insert(Report report) {
        AccountDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.insert(report);
        });
    }
}
