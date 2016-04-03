package org.aplie.android.myapplication.components;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.new_operation.DatePickerFragment;
import org.aplie.android.myapplication.operations_day.FragmentOperationsDay;
import org.aplie.android.myapplication.utils.DateUtils;

import java.util.Calendar;

public class ElementDate extends LinearLayout {
    private TextView tvDate;
    private ImageButton ibCalendar;
    private Calendar calendar;
    private FragmentOperationsDay fragmentOperations;

    public ElementDate(Context context) {
        super(context);
        inicialize();
    }

    public ElementDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicialize();
    }

    private void inicialize() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.element_date,this,true);

        tvDate = (TextView) findViewById(R.id.tvDate);
        ibCalendar = (ImageButton)findViewById(R.id.ibCalendar);
        calendar = Calendar.getInstance();
        tvDate.setText(DateUtils.dayMonthYear(calendar));
        addListenerSelectDate();
    }

    private void addListenerSelectDate() {
        ibCalendar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new DatePickerFragment();
                ((DatePickerFragment) dialog).setElementDate(ElementDate.this);
                dialog.show(((Activity) getContext()).getFragmentManager(), "datePicker");
            }
        });
    }

    public void updateDate(int year, int month, int day) {
        calendar.set(year,month,day);
        tvDate.setText(DateUtils.dayMonthYear(calendar));
        if(this.fragmentOperations != null){
            this.fragmentOperations.reload();
        }
    }

    public String getDate(){
        return String.valueOf(calendar.getTimeInMillis());
    }

    public String getDateDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.calendar.get(Calendar.YEAR),this.calendar.get(Calendar.MONTH),this.calendar.get(Calendar.DAY_OF_MONTH),0,0,0);
        return String.valueOf(calendar.getTimeInMillis());
    }

    public void addOperationsView(FragmentOperationsDay fragmentOperations) {
        this.fragmentOperations = fragmentOperations;
    }

    public String getDateNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.calendar.get(Calendar.YEAR),this.calendar.get(Calendar.MONTH),this.calendar.get(Calendar.DAY_OF_MONTH),0,0,0);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        return String.valueOf(calendar.getTimeInMillis());
    }
}
