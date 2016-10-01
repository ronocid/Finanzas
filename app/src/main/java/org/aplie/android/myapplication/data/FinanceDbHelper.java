package org.aplie.android.myapplication.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.aplie.android.myapplication.data.FinanceContract.CategotyEntry;
import org.aplie.android.myapplication.data.FinanceContract.OperationEntry;
import org.aplie.android.myapplication.data.FinanceContract.TypeOperationEntry;
import org.aplie.android.myapplication.data.FinanceContract.UserEntry;
import org.aplie.android.myapplication.utils.FinanceConstants;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FinanceDbHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "finance.db";
    private static final int DATABASE_VERSION =1;


    public FinanceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "+
                UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL, "+
                UserEntry.COLUMN_EMAIL + " TEXT NOT NULL); ";

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + CategotyEntry.TABLE_NAME + " (" +
                CategotyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategotyEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "+
                CategotyEntry.COLUMN_USER_ID+ " INTEGER NOT NULL); ";

        final String SQL_CREATE_TYPE_OPERATION_TABLE = "CREATE TABLE "+ TypeOperationEntry.TABLE_NAME+" ("+
                TypeOperationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TypeOperationEntry.COLUMN_DESCRIPTION  + " TEXT NOT NULL); ";

        final String SQL_CREATE_OPERATION_TABLE = "CREATE TABLE " + OperationEntry.TABLE_NAME + " (" +
                OperationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OperationEntry.COLUMN_ID_CATEGORY + " INTEGER NOT NULL, " +
                OperationEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                OperationEntry.COLUMN_ID_TYPE_OPERATION + " INTEGER NOT NULL, " +
                OperationEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                OperationEntry.COLUMN_DATE + " INTEGER NOT NULL, " +
                OperationEntry.COLUMN_USER_ID+ " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + OperationEntry.COLUMN_ID_CATEGORY + ") REFERENCES " +
                CategotyEntry.TABLE_NAME + " (" + CategotyEntry._ID + "), " +
                " FOREIGN KEY (" + OperationEntry.COLUMN_ID_TYPE_OPERATION + ") REFERENCES " +
                TypeOperationEntry.TABLE_NAME + " (" + TypeOperationEntry._ID + ")); ";

        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_TYPE_OPERATION_TABLE);
        db.execSQL(SQL_CREATE_OPERATION_TABLE);
        db.execSQL("INSERT INTO "+ TypeOperationEntry.TABLE_NAME +
                "("+TypeOperationEntry.COLUMN_DESCRIPTION+") "+
                " values ('"+FinanceConstants.GASTO+"')");
        db.execSQL("INSERT INTO "+ TypeOperationEntry.TABLE_NAME +
                "("+TypeOperationEntry.COLUMN_DESCRIPTION+") "+
                " values ('"+FinanceConstants.INGRESO+"')");
        //db.execSQL("INSERT INTO categories (catdescription) values ('Metro')");
        //db.execSQL("INSERT INTO categories (catdescription) values ('Metro2')");
        //db.execSQL("INSERT INTO categories (catdescription) values ('Metro3')");
        //db.execSQL("INSERT INTO categories (catdescription) values ('Metro4')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OperationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TypeOperationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CategotyEntry.TABLE_NAME);
        onCreate(db);
    }

    public static Object fillObject(Cursor cursor, Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
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
    }
}
