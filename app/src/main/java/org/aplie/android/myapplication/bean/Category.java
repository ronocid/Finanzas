package org.aplie.android.myapplication.bean;

public class Category extends BasicBean{
    private static final long serialVersionUID = 6003265453479117093L;
    private String _id;
    private String catdescription;
    private int id_user;

    public Category(String id, String description) {
        this._id = id;
        this.catdescription = description;
    }

    public Category(String id, String description,int idUser) {
        this._id = id;
        this.catdescription = description;
        this.id_user = idUser;
    }

    public Category() {

    }

    public int getId_user() {
        return id_user;
    }

    public String get_id() {
        return this._id;
    }

    public String getCatdescription() {
        return this.catdescription;
    }

    @Override
    public String toString() {
        return this.catdescription;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCatdescription(String catdescription) {
        this.catdescription = catdescription;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
