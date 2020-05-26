package com.ubtech.example_lib.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
@Entity(tableName = UserInfo.TABLE_USER_INFO)
public class UserInfo {
    public static final String TABLE_USER_INFO = "user_info";
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String token;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
