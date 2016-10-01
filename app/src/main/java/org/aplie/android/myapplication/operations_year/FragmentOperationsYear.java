package org.aplie.android.myapplication.operations_year;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Day;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.data.tables.OperationDB;
import org.aplie.android.myapplication.data.tables.UsersDB;
import org.aplie.android.myapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class FragmentOperationsYear extends Fragment {

    private User mCurrentUser;
    private Calendar calendar;
    private List<GroupItemYear> listGroup;
    private List<Operation> listOperations;

    public FragmentOperationsYear() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_year, container, false);

        ExpandableListView list = (ExpandableListView) view.findViewById(R.id.expandableListView);

        mCurrentUser = UsersDB.getCurrentUser(getActivity());
        calendar = Calendar.getInstance();

        requestOperation();
        createGroups();

        AdapterExpandibleListYear adapter = new AdapterExpandibleListYear(getActivity(),listGroup);
        list.setAdapter(adapter);

        setTitle();
        return view;
    }

    private void createGroups() {
        listGroup = new ArrayList<>();

        HashMap<String,List<Operation>> mapGroups = groups(listOperations);
        Resources res = getActivity().getResources();
        String[] months = res.getStringArray(R.array.month);
        for(int count = 0;count<months.length;count++){
            List<Operation> operationList = mapGroups.get(months[count]);

            if(operationList != null){
                HashMap<String,List<Operation>> mapChilds = childs(operationList);
                Calendar currentCalendar = Calendar.getInstance();
                currentCalendar.set(calendar.get(Calendar.YEAR), count, 1);
                currentCalendar.add(Calendar.MONTH, 1);
                currentCalendar.add(Calendar.DAY_OF_MONTH, -1);
                int lastDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
                List<Day> days = new ArrayList<>();
                for(int countDay =1;countDay<=lastDay;countDay++){
                    String key = DateUtils.formatNumberLessTen(countDay)+"/"+DateUtils.formatNumberLessTen((currentCalendar.get(Calendar.MONTH)+1))+"/"+currentCalendar.get(Calendar.YEAR);
                    List<Operation> operationsDay = mapChilds.get(key);

                    if(operationsDay != null){
                        days.add(new Day(key,operationsDay));
                    }
                }

                listGroup.add(new GroupItemYear(months[count],days));
                //listGroup.add(new GroupItemMonth(months[count],operationList));
            }
        }
    }

    private HashMap<String,List<Operation>> childs(List<Operation> listOperations) {
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

    private HashMap<String,List<Operation>> groups(List<Operation> listOperations) {
        HashMap<String,List<Operation>> mapGroups = new HashMap<>();
        for(Operation operation : listOperations){
            String date = DateUtils.month(getActivity(), operation.getDate());
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
        calendar.set(this.calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0);
        String beginningMonth = String.valueOf(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR,1);
        String beginningNextMonth = String.valueOf(calendar.getTimeInMillis());
        listOperations = OperationDB.getOperationsMonth(getActivity(), beginningMonth, beginningNextMonth, mCurrentUser.get_id());
    }

    private void setTitle() {
        getActivity().setTitle("Operaciones Anuales");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_year, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
