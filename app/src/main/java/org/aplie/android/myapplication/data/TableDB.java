package org.aplie.android.myapplication.data;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TableDB {
    /*public Object fillObject(Cursor cursor, Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for (DateFormat.Field field : fields) {
            try {

                if(!field.getName().equals("serialVersionUID")){
                    String fieldName = field.getName();
                    String fieldNameMethod = fieldName.substring(0,1).toUpperCase()+field.getName().substring(1);
                    Method m = object.getClass().getDeclaredMethod("set"+fieldNameMethod, field.getType());


                    if (field.getType().equals(int.class)) {
                        Integer intVal = cursor.getInt(cursor
                                .getColumnIndex(fieldName));
                        m.invoke(object, (Integer) intVal);
                    } else if (field.getType().isInstance(new String())) {
                        String stringVal = cursor.getString(cursor
                                .getColumnIndex(fieldName));
                        m.invoke(object, (String) stringVal);
                    } else if (field.getType().equals(double.class)) {
                        Double doubleVal = cursor.getDouble(cursor
                                .getColumnIndex(fieldName));
                        m.invoke(object, (Double) doubleVal);
                    } else if (field.getType().equals(boolean.class)) {
                        Integer intVal = cursor.getInt(cursor
                                .getColumnIndex(fieldName));
                        m.invoke(object, Boolean.valueOf(intVal > 0));
                    }

                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return object;
    }*/
}
