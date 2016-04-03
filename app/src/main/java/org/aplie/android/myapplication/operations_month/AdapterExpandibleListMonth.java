package org.aplie.android.myapplication.operations_month;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.utils.FinanceConstants;

import java.util.List;

public class AdapterExpandibleListMonth extends BaseExpandableListAdapter{
    private final Context contetx;
    private final List<GroupItem> listGroup;

    public AdapterExpandibleListMonth(Context context, List<GroupItem> listGroup){
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
        Operation operation = (Operation) getChild(groupPosition, childPosition);

        TextView title = (TextView) view.findViewById(R.id.title_child);
        title.setText((operation.getCategory().getCatdescription()));
        TextView spend = (TextView) view.findViewById(R.id.gasto);
        TextView deposit = (TextView) view.findViewById(R.id.ingreso);

        if(FinanceConstants.INGRESO.equals(operation.getTypeOperation().getDescription())){
            spend.setVisibility(View.GONE);
            deposit.setText(operation.getQuantity()+ FinanceConstants.EURO);
        }else{
            deposit.setVisibility(View.GONE);
            spend.setText(operation.getQuantity() + FinanceConstants.EURO);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
