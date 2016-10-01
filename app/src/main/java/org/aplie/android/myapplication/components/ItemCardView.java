package org.aplie.android.myapplication.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.utils.FinanceConstants;


/**
 * Created by Roberto on 13/08/2016.
 */
public class ItemCardView extends LinearLayout {
    private Operation operation;
    private TextView tvText;
    private TextView tvDeposit;
    private TextView tvSpending;

    public ItemCardView(Context context, Operation operation) {
        super(context);
        this.operation = operation;
        inicialize();
    }

    public ItemCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicialize();
    }

    private void inicialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.item_cardview, this, true);

        tvText = (TextView) findViewById(R.id.tvtext);
        tvDeposit = (TextView) findViewById(R.id.tvDeposit);
        tvSpending = (TextView) findViewById(R.id.tvSpending);

        if(operation != null){
            tvText.setText(operation.getCategory().toString());
            if(FinanceConstants.INGRESO.equals(operation.getTypeOperation().getOperationDescription())){
                tvDeposit.setText(operation.getQuantity());
            }else{
                tvSpending.setText("-"+operation.getQuantity());
            }
        }
    }

}
