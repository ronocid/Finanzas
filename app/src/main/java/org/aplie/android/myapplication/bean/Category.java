package org.aplie.android.myapplication.bean;

public class Category extends BasicBean{
    private static final long serialVersionUID = 6003265453479117093L;
    private String id;
    private String description;

    public Category(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
