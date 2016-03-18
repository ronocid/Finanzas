package org.aplie.android.myapplication.bean;

public class TypeOperation extends  BasicBean{
    private String _id;
    private String description;

    public TypeOperation(String id, String description) {
        this._id = id;
        this.description = description;
    }

    public String get_Id() {
        return _id;
    }

    public String getDescription() {
        return description;
    }
}
