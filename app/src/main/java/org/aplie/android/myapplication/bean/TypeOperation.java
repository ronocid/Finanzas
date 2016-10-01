package org.aplie.android.myapplication.bean;

public class TypeOperation extends  BasicBean{
    private String _id;
    private String operationDescription;

    public TypeOperation(String id, String description) {
        this._id = id;
        this.operationDescription = description;
    }

    public TypeOperation() {

    }

    public String get_Id() {
        return _id;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }
}
