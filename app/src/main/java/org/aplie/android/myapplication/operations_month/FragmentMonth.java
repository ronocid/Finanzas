package org.aplie.android.myapplication.operations_month;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Month;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.components.ItemCardView;

import java.util.List;

public class FragmentMonth extends Fragment {
    private static final String MONTH_KEY = "month";
    private Month month;

    public static FragmentMonth newInstance(Month month) {
        FragmentMonth fragment = new FragmentMonth();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MONTH_KEY, month);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.month = (getArguments() != null) ? (Month) getArguments().getSerializable(
                MONTH_KEY) : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_month, container, false);

        TextView nameMonth = (TextView) rootView.findViewById(R.id.tvNameMonth);
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layoutOperations);

        nameMonth.setText(month.getName());
        List<Operation> listOperations = month.getListOperations();
        if(listOperations != null && listOperations.size()>0){
            for(Operation operation : listOperations){
                ItemCardView item = new ItemCardView(getContext(),operation);
                layout.addView(item);
            }
        }

        return rootView;
    }
}
