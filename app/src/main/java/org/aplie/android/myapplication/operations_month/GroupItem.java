package org.aplie.android.myapplication.operations_month;

import org.aplie.android.myapplication.bean.Operation;

import java.util.List;

public class GroupItem {
    private String group;
    private List<Operation> children;

    public GroupItem(String group, List<Operation> children) {
        this.group = group;
        this.children = children;
    }

    public String getGroup() {
        return group;
    }

    public List<Operation> getChildren() {
        return children;
    }
}
