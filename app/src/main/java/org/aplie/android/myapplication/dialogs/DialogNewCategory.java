package org.aplie.android.myapplication.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Category;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.data.CategoriesDB;
import org.aplie.android.myapplication.data.UsersDB;

public class DialogNewCategory{
    private User mCurrentUser;

    public void launchDialog(final Activity activity, final ArrayAdapter<Category> adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_new_category, null);
        final EditText editText = (EditText) view.findViewById(R.id.etNewCategory);

        mCurrentUser = UsersDB.getCurrentUser(activity);

        builder.setView(view)
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = editText.getText().toString();
                        Category category = new Category("0",text,mCurrentUser.get_id());
                        CategoriesDB.insertCategory(activity, category);
                        category = CategoriesDB.getCategoriyByDescription(activity,category.getCatdescription(),mCurrentUser.get_id());
                        if(adapter != null){
                            adapter.add(category);
                        }
                    }
                })
                .setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        addListenerChangeText(editText, dialog);
    }

    private void addListenerChangeText(EditText editText, final AlertDialog dialog) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });
    }
}
