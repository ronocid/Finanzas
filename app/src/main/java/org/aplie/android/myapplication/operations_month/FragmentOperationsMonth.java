package org.aplie.android.myapplication.operations_month;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.data.OperationDB;
import org.aplie.android.myapplication.data.UsersDB;
import org.aplie.android.myapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FragmentOperationsMonth extends Fragment{
    Calendar calendar;
    private List<Operation> listOperations;
    private User mCurrentUser;
    private List<GroupItem> listGroup;

    public FragmentOperationsMonth() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_month, container, false);

        ExpandableListView list = (ExpandableListView) view.findViewById(R.id.expandableListView);

        mCurrentUser = UsersDB.getCurrentUser(getActivity());
        calendar = Calendar.getInstance();
        requestOperation();
        createGroups();

        AdapterExpandibleListMonth adapter = new AdapterExpandibleListMonth(getActivity(),listGroup);
        list.setAdapter(adapter);

        setTitle();
        return view;
    }

    private void createGroups() {
        listGroup = new ArrayList<>();

        HashMap<String,List<Operation>> mapGroups = groups();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        currentCalendar.add(Calendar.MONTH, 1);
        currentCalendar.add(Calendar.DAY_OF_MONTH, -1);
        int lastDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        //Set<String> keys = mapGroups.keySet();
        for(int count =1;count<=lastDay;count++){
            String key = DateUtils.formatNumberLessTen(count)+"/"+DateUtils.formatNumberLessTen((currentCalendar.get(Calendar.MONTH)+1))+"/"+currentCalendar.get(Calendar.YEAR);
            List<Operation> operationList = mapGroups.get(key);

            if(operationList != null){
                listGroup.add(new GroupItem(key,operationList));
            }
        }
    }

    private HashMap<String,List<Operation>> groups() {
        HashMap<String,List<Operation>> mapGroups = new HashMap<>();
        for(Operation operation : listOperations){
            String date = DateUtils.dayMonthYear(operation.getDate());
            if(mapGroups.containsKey(date)){
                mapGroups.get(date).add(operation);
            }else{
                List<Operation> list = new ArrayList<>();
                list.add(operation);
                mapGroups.put(date,list);
            }
        }
        return mapGroups;
    }

    private void requestOperation() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        String beginningMonth = String.valueOf(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH,1);
        String beginningNextMonth = String.valueOf(calendar.getTimeInMillis());
        listOperations = OperationDB.getOperationsMonth(getActivity(),beginningMonth,beginningNextMonth,mCurrentUser.get_id());
    }

    private void setTitle() {
        getActivity().setTitle("Operaciones Mensuales");
    }
}
