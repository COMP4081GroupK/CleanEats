package com.example.cleaneats.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reports")
public class Report {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "account_id")
    private int accountId;

    @ColumnInfo(name = "restaurant_name")
    private String restaurantName;

    @ColumnInfo(name = "restaurant_address")
    private String restaurantAddress;

    @ColumnInfo(name = "report_issue")
    private String reportIssue;

    public Report(int accountId,
                  String restaurantName,
                  String restaurantAddress,
                  String reportIssue) {
        this.accountId = accountId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.reportIssue = reportIssue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getReportIssue() {
        return reportIssue;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setReportIssue(String reportIssue) {
        this.reportIssue = reportIssue;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
