package org.aplie.android.myapplication.bean;

public class Operation {
    private String id;
    private String description;
    private String quantity;
    private String date;
    private Category category;
    private TypeOperation typeOperation;

    public Operation(String id, String description, String quantity, String date, Category category, TypeOperation typeOperation) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.date = date;
        this.category = category;
        this.typeOperation = typeOperation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
}
