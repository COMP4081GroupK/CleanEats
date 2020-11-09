package com.example.cleaneats.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accounts")
public class Account {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "num_of_reports")
    private int numOfReports;

    public Account(String userName,
                   String password,
                   int numOfReports) {
        this.userName = userName;
        this.password = password;
        this.numOfReports = numOfReports;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumOfReports() {
        return numOfReports;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setNumOfReports(int numOfReports) {
        this.numOfReports = numOfReports;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
