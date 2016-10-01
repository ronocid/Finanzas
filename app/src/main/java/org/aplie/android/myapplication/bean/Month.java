package org.aplie.android.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class Month implements Serializable {
    private static final long serialVersionUID = -7828941063030678236L;
    private String name;
    private List<Operation> listOperations;

    public Month(String name, List<Operation> listOperations) {
        this.name = name;
        this.listOperations = listOperations;
    }

    public String getName() {
        return name;
    }

    public List<Operation> getListOperations() {
        return listOperations;
    }
}
