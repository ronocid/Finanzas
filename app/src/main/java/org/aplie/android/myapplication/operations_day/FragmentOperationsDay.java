package org.aplie.android.myapplication.operations_day;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.components.ElementDate;
import org.aplie.android.myapplication.components.ElementItemOperations;
import org.aplie.android.myapplication.data.OperationDB;
import org.aplie.android.myapplication.data.UsersDB;
import org.aplie.android.myapplication.new_operation.NewOperationActivity;

import java.util.List;

public class FragmentOperationsDay extends Fragment {
    private LinearLayout mLayoutContainer;
    private ElementDate elementDate;
    private User mCurrentUser;

    @Override
    public void onResume() {
        super.onResume();
        loadOperations();
    }

    public FragmentOperationsDay() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operaciones_day, container, false);
        this.mLayoutContainer = (LinearLayout) view.findViewById(R.id.container);
        this.elementDate = (ElementDate) view.findViewById(R.id.elementDate);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getActivity(), NewOperationActivity.class);
                startActivity(intent);
            }
        });

        mCurrentUser = UsersDB.getCurrentUser(getActivity());

        this.elementDate.addOperationsView(this);
        setTitle();
        return view;
    }

    private void setTitle() {
        getActivity().setTitle("Operaciones Diarias");
    }

    private void loadOperations() {
        this.mLayoutContainer.removeAllViews();
        List<Operation> operations = OperationDB.getOperationsDay(getActivity(), elementDate.getDateDay(), elementDate.getDateNextDay(), mCurrentUser.get_id());

        for(Operation operation : operations){
            ElementItemOperations eio = new ElementItemOperations(getActivity(),operation);
            this.mLayoutContainer.addView(eio);
        }
    }

    public void reload() {
        loadOperations();
    }
}
