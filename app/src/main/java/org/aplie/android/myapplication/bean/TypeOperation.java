package org.aplie.android.myapplication.bean;

public class TypeOperation {
    private String id;
    private String description;

    public TypeOperation(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
