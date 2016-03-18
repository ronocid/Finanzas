package org.aplie.android.myapplication.bean;

public class User extends BasicBean {
    private int _id;
    private String userName;
    private String password;
    private String email;

    public User(int id, String userName, String password, String email) {
        this._id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public User( String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int get_id() {
        return _id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
