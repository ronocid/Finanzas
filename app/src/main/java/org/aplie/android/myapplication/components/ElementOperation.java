package org.aplie.android.myapplication.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.utils.FinanceConstants;

public class ElementOperation extends LinearLayout {
    private static final int OPERATION_SPENDING = 1;
    private static final int OPERATION_DEPOSIT = 2;
    private final Context context;
    private ImageView ivSpending;
    private ImageView ivDeposit;
    private EditText etQuantity;
    private int state;

    public ElementOperation(Context context) {
        super(context);
        this.context  = context;
        inicialize();
    }

    public ElementOperation(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context  = context;
        inicialize();
    }

    private void inicialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.element_operation,this,true);
        this.state = OPERATION_SPENDING;
        ivSpending = (ImageView)findViewById(R.id.ivSpending);
        ivDeposit = (ImageView)findViewById(R.id.ivDeposit);
        etQuantity = (EditText)findViewById(R.id.etQuantity);
        etQuantity.setTextColor(context.getResources().getColor(R.color.spending));

        ivSpending.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == OPERATION_SPENDING) {
                    state = OPERATION_DEPOSIT;
                    ivSpending.setImageResource(R.drawable.gasto);
                    ivDeposit.setImageResource(R.drawable.ingreso_press);
                    etQuantity.setTextColor(context.getResources().getColor(R.color.deposit));
                } else {
                    state = OPERATION_SPENDING;
                    ivSpending.setImageResource(R.drawable.gasto_press);
                    ivDeposit.setImageResource(R.drawable.ingreso);
                    etQuantity.setTextColor(context.getResources().getColor(R.color.spending));
                }
            }
        });

        ivDeposit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == OPERATION_DEPOSIT) {
                    state = OPERATION_SPENDING;
                    ivDeposit.setImageResource(R.drawable.ingreso);
                    ivSpending.setImageResource(R.drawable.gasto_press);
                    etQuantity.setTextColor(context.getResources().getColor(R.color.spending));
                } else {
                    state = OPERATION_DEPOSIT;
                    ivSpending.setImageResource(R.drawable.gasto);
                    ivDeposit.setImageResource(R.drawable.ingreso_press);
                    etQuantity.setTextColor(getResources().getColor(R.color.deposit));
                }
            }
        });
    }

    public String getQuantity(){
        return this.etQuantity.getText().toString();
    }

    public String getTypeOperaton(){
        if(state == OPERATION_DEPOSIT){
            return FinanceConstants.INGRESO;
        }else{
            return FinanceConstants.GASTO;
        }
    }
}
