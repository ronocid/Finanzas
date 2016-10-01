package org.aplie.android.myapplication.operations_month;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Month;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.data.tables.OperationDB;
import org.aplie.android.myapplication.data.tables.UsersDB;
import org.aplie.android.myapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class FragmentOperationsMonth extends Fragment{
    public static final int MAX_MONTH = 12;
    private String [] months;
    Calendar calendar;
    private List<Operation> listOperations;
    private User mCurrentUser;
    private List<GroupItemMonth> listGroup;
    private ViewPager pager;

    public FragmentOperationsMonth() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_month, container, false);

        this.pager = (ViewPager) view.findViewById(R.id.pager);
        mCurrentUser = UsersDB.getCurrentUser(getActivity());
        months = new String [] {getString(R.string.january),
                getString(R.string.february),
                getString(R.string.march),
                getString(R.string.april),
                getString(R.string.may),
                getString(R.string.june),
                getString(R.string.july),
                getString(R.string.august),
                getString(R.string.september),
                getString(R.string.october),
                getString(R.string.november),
                getString(R.string.december),};

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(
                getActivity().getSupportFragmentManager());

        for(int count = 0; count< MAX_MONTH; count++){
            String name = months[count];
            List<Operation> list = requestOperation(count);
            adapter.addFragment(FragmentMonth.newInstance(new Month(name, list)));
        }
        this.pager.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();

        pager.setCurrentItem(calendar.get(Calendar.MONTH));
        /*ExpandableListView list = (ExpandableListView) view.findViewById(R.id.expandableListView);

        mCurrentUser = UsersDB.getCurrentUser(getActivity());
        calendar = Calendar.getInstance();
        requestOperation();
        createGroups();

        AdapterExpandibleListMonth adapter = new AdapterExpandibleListMonth(getActivity(),listGroup);
        list.setAdapter(adapter);

        setTitle();*/
        return view;
    }

    private List<Operation> requestOperation(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
        String beginningMonth = String.valueOf(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH,1);
        String beginningNextMonth = String.valueOf(calendar.getTimeInMillis());
        return OperationDB.getOperationsMonth(getActivity(),beginningMonth,beginningNextMonth,mCurrentUser.get_id());
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
                listGroup.add(new GroupItemMonth(key,operationList));
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_month, menu);
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
