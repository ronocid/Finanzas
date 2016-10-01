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

    public User() {

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
