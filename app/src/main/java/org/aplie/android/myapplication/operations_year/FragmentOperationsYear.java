package org.aplie.android.myapplication.operations_year;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aplie.android.myapplication.R;

public class FragmentOperationsYear extends Fragment {

    public FragmentOperationsYear() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_year, container, false);

        setTitle();
        return view;
    }

    private void setTitle() {
        getActivity().setTitle("Operaciones Anuales");
    }
}
