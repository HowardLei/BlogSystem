package com.blogSystem.entity;

/**
 * User class
 *
 * @author apple
 * @date 2019-08-13
 */
public class User {
    private String account;
    private String pwd;

    public User(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}