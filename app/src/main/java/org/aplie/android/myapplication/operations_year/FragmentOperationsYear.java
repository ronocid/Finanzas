package org.aplie.android.myapplication.operations_year;

import android.content.res.Resources;
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
import org.aplie.android.myapplication.operations_month.AdapterExpandibleListMonth;
import org.aplie.android.myapplication.operations_month.GroupItem;
import org.aplie.android.myapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FragmentOperationsYear extends Fragment {

    private User mCurrentUser;
    private Calendar calendar;
    private List<GroupItem> listGroup;
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

        HashMap<String,List<Operation>> mapGroups = groups();
        Resources res = getActivity().getResources();
        String[] months = res.getStringArray(R.array.month);
        for(int count = 0;count<months.length;count++){
            List<Operation> operationList = mapGroups.get(months[count]);

            if(operationList != null){
                listGroup.add(new GroupItem(months[count],operationList));
            }
        }
    }

    private HashMap<String,List<Operation>> groups() {
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
}
