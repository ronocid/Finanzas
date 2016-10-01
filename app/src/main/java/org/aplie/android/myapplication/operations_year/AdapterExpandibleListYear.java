package org.aplie.android.myapplication.operations_year;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Day;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.utils.FinanceConstants;

import java.util.List;

public class AdapterExpandibleListYear extends BaseExpandableListAdapter {
    private final Context contetx;
    private final List<GroupItemYear> listGroup;

    public AdapterExpandibleListYear(Context context, List<GroupItemYear> listGroup){
        this.contetx = context;
        this.listGroup = listGroup;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listGroup.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition).getGroup();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listGroup.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contetx);
        View view = inflater.inflate(R.layout.item_group_expandible, null);
        TextView title = (TextView) view.findViewById(R.id.title_group);
        title.setText((String) getGroup(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contetx);
        View view = inflater.inflate(R.layout.item_child_expandible, null);
        Day day = (Day) getChild(groupPosition, childPosition);

        TextView title = (TextView) view.findViewById(R.id.title_child);
        title.setText(day.getDate());
        List<Operation> operationList = day.getOperationsDay();
        int gasto = 0;
        int ingreso = 0;

        for(int count =0;count<operationList.size();count++){
            Operation operation = operationList.get(count);
            if(FinanceConstants.GASTO.equals(operation.getTypeOperation().getOperationDescription())){
                gasto += Integer.parseInt(operation.getQuantity());
            }else{
                ingreso += Integer.parseInt(operation.getQuantity());
            }
        }
        TextView spend = (TextView) view.findViewById(R.id.gasto);
        TextView deposit = (TextView) view.findViewById(R.id.ingreso);

        spend.setText(FinanceConstants.LESS+gasto + FinanceConstants.EURO);
        deposit.setText(ingreso+ FinanceConstants.EURO);


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
