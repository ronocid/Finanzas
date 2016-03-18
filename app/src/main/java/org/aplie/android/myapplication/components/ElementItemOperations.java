package org.aplie.android.myapplication.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.utils.DateUtils;
import org.aplie.android.myapplication.utils.FinanceConstants;

public class ElementItemOperations  extends LinearLayout {
    private final Context context;
    private final Operation operation;

    public ElementItemOperations(Context context, Operation operation) {
        super(context);
        this.context  = context;
        this.operation = operation;
        inicialize();
    }

    public ElementItemOperations(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context  = context;
        this.operation = null;
        inicialize();
    }

    private void inicialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);

        if(this.operation != null){
            if(FinanceConstants.GASTO.equals(this.operation.getTypeOperation().getDescription())){
                li.inflate(R.layout.element_item_operations_red, this, true);
                TextView categotyOperation = (TextView) findViewById(R.id.tvCategory);
                TextView quantityOperation = (TextView) findViewById(R.id.tvQuantity);
                TextView dateOperation = (TextView) findViewById(R.id.tvDate);
                TextView descriptionOperation = (TextView) findViewById(R.id.tvDescription);
                final ElementDeployRed edr = (ElementDeployRed) findViewById(R.id.deployHoritzontal);
                final LinearLayout layoutDescription = (LinearLayout) findViewById(R.id.layoutDescription);

                edr.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(edr.isDesplegado()){
                            layoutDescription.setVisibility(View.GONE);
                        }else{
                            layoutDescription.setVisibility(View.VISIBLE);
                        }
                        edr.changeMode();
                    }
                });

                categotyOperation.setText(this.operation.getCategory().getCatdescription());
                quantityOperation.setText("-" + this.operation.getQuantity() + "€");
                descriptionOperation.setText(this.operation.getDescription());
                dateOperation.setText(DateUtils.formatearDBDate(this.operation.getDate()));
            }else{
                li.inflate(R.layout.element_item_operations_green, this, true);
                TextView categotyOperation = (TextView) findViewById(R.id.tvCategory);
                TextView quantityOperation = (TextView) findViewById(R.id.tvQuantity);
                TextView dateOperation = (TextView) findViewById(R.id.tvDate);
                TextView descriptionOperation = (TextView) findViewById(R.id.tvDescription);
                final ElementDeployGreen edg = (ElementDeployGreen) findViewById(R.id.deployHoritzontal);
                final LinearLayout layoutDescription = (LinearLayout) findViewById(R.id.layoutDescription);
                edg.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(edg.isDesplegado()){
                            layoutDescription.setVisibility(View.GONE);
                        }else{
                            layoutDescription.setVisibility(View.VISIBLE);
                        }
                        edg.changeMode();
                    }
                });
                categotyOperation.setText(this.operation.getCategory().getCatdescription());
                quantityOperation.setText(this.operation.getQuantity() + "€");
                descriptionOperation.setText(this.operation.getDescription());
                dateOperation.setText(DateUtils.formatearDBDate(this.operation.getDate()));
            }
        }
    }
}
