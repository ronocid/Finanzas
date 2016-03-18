package org.aplie.android.myapplication.new_operation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Category;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.bean.TypeOperation;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.components.ElementDate;
import org.aplie.android.myapplication.components.ElementOperation;
import org.aplie.android.myapplication.data.CategoriesDB;
import org.aplie.android.myapplication.data.OperationDB;
import org.aplie.android.myapplication.data.TypeOperationDB;
import org.aplie.android.myapplication.data.UsersDB;
import org.aplie.android.myapplication.dialogs.DialogNewCategory;

import java.util.List;

public class FragmentNewOperation extends Fragment{
    private static final String TAG = "dialog";
    private Spinner category;
    private ImageButton addCategory;
    private ElementOperation quantity;
    private EditText description;
    private ArrayAdapter<Category> adapter;
    private Button save;
    private ElementDate elementDate;
    private User mCurrentUser;

    public FragmentNewOperation() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_operation, container, false);
        this.elementDate  = (ElementDate) view.findViewById(R.id.elementDate);
        this.category = (Spinner) view.findViewById(R.id.spinnerCategory);
        this.addCategory = (ImageButton) view.findViewById(R.id.ibAddCategory);
        this.quantity = (ElementOperation) view.findViewById(R.id.elementOperation);
        this.description = (EditText) view.findViewById(R.id.etDescription);
        this.save = (Button) view.findViewById(R.id.buttonSave);

        mCurrentUser = UsersDB.getCurrentUser(getActivity());

        addAdapterCategories();
        addListenerAddCategory();
        addListenerSave();
        return view;
    }

    private void addListenerSave() {
        this.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category  currentCategory = (Category) category.getSelectedItem();
                String valorQuantity = quantity.getQuantity();
                String valorDescription = description.getText().toString();
                if(currentCategory!=null && hasData(valorQuantity)) {
                    quantity.getTypeOperaton();
                    TypeOperation typeOperation = TypeOperationDB.getTypeByName(getActivity(),quantity.getTypeOperaton());
                    Operation newOperation = new Operation(valorDescription,valorQuantity,elementDate.getDate(),currentCategory,typeOperation);
                    OperationDB.insertOperation(getActivity(),newOperation,mCurrentUser.get_id());
                    getActivity().finish();
                }
            }
        });
    }

    private boolean hasData(String valorQuantity) {
        if(valorQuantity.length()>0) {
            return true;
        }else{
            return false;
        }
    }

    private void addListenerAddCategory() {
        this.addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewCategory dialog = new DialogNewCategory();
                dialog.launchDialog(getActivity(), adapter);
            }
        });
    }

    private void addAdapterCategories() {
        List<Category> listaCategorias = CategoriesDB.getAllCategories(getActivity(),mCurrentUser.get_id());
        if(listaCategorias.size()>0){
            adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item, listaCategorias);
            this.category.setAdapter(adapter);
        }
    }

}
