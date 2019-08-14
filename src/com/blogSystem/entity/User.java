package com.blogSystem.entity;

/**
 * User class
 *
 * @author apple
 * @date 2019-08-13
 */
public class User {
    private String userName;
    private String password;
    private String name;

    public User(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public String getUserName(boolean forSQL) {
        if (forSQL) {
            return String.join(userName, "\'", "\'");
        } else {
            return userName;
        }
    }

    public String getPassword(boolean forSQL) {
        if (forSQL) {
            return String.join(password, "\'", "\'");
        }
        return password;
    }

    public String getName(boolean forSQL) {
        if (forSQL) {
            return String.join(name, "\'", "\'");
        }
        return name;
    }
}