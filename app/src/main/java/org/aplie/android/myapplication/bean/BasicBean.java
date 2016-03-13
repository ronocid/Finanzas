package org.aplie.android.myapplication.bean;

import android.content.ContentValues;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BasicBean implements Serializable{
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExcludeField{

    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        Field[] fields = getClass().getDeclaredFields();


        for (Field field : fields) {
            try {
                java.lang.annotation.Annotation[] annotations = field.getAnnotations();
                field.getAnnotation(ExcludeField.class);
                if(annotations.length==0){
                    if(!field.getName().equals("serialVersionUID")&&!field.getName().equals("dateEntry")&&!field.getName().equals("entry")
                            &&!field.getName().equals("operations")&&!field.getName().equals("subscription")){
                        String fieldName = field.getName();
                        fieldName = fieldName.substring(0,1).toUpperCase()+field.getName().substring(1);
                        Method m = getClass().getMethod("get"+fieldName);
                        Object value = m.invoke(this);
                        if(value==null){
                            values.putNull(field.getName());
                        }else{
                            if(value instanceof Integer){
                                values.put(field.getName(), (Integer)value);
                            }else if(value instanceof String){
                                values.put(field.getName(), (String)value);
                            }else if(value instanceof Double){
                                values.put(field.getName(), (Double)value);
                            }else if(value instanceof Boolean){
                                values.put(field.getName(),(Boolean) value);
                            }
                        }
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
        return values;
    }
}
