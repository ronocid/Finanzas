package org.aplie.android.myapplication.operations_month;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.aplie.android.myapplication.bean.Operation;

import java.util.List;

public class AdapterExpandibleList extends BaseExpandableListAdapter{
    private final Context contetx;
    private final List<GroupItem> listGroup;

    public AdapterExpandibleList(Context context, List<GroupItem> listGroup){
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
        TextView textView = new TextView(contetx);
        textView.setText((String) getGroup(groupPosition));
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(contetx);
        Operation operation = (Operation) getChild(groupPosition, childPosition);
        textView.setText((operation.getCategory().getCatdescription()));
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
