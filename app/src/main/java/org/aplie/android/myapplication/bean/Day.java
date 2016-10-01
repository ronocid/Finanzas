package org.aplie.android.myapplication.bean;

import java.util.List;

public class Day {
    private String date;
    private List<Operation> operationsDay;

    public Day(String date, List<Operation> operationsDay) {
        this.date = date;
        this.operationsDay = operationsDay;
    }

    public String getDate() {
        return date;
    }

    public List<Operation> getOperationsDay() {
        return operationsDay;
    }
}
