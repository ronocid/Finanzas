package org.aplie.android.myapplication.operations_year;

import org.aplie.android.myapplication.bean.Day;

import java.util.List;

public class GroupItemYear {
    private String group;
    private List<Day> children;

    public GroupItemYear(String group, List<Day> children) {
        this.group = group;
        this.children = children;
    }

    public String getGroup() {
        return group;
    }

    public List<Day> getChildren() {
        return children;
    }

}
