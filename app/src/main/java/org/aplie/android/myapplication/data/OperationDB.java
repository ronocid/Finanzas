package org.aplie.android.myapplication.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import org.aplie.android.myapplication.bean.Category;
import org.aplie.android.myapplication.bean.Operation;
import org.aplie.android.myapplication.bean.TypeOperation;
import org.aplie.android.myapplication.data.FinanceContract.OperationEntry;
import org.aplie.android.myapplication.data.FinanceContract.CategotyEntry;
import org.aplie.android.myapplication.data.FinanceContract.TypeOperationEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OperationDB {
    private static Uri operationUri =  FinanceContract.OperationEntry.CONTENT_URI;
    private static String[] projection = new String[] {
            OperationEntry.TABLE_NAME+"."+OperationEntry._ID,
            OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_DESCRIPTION,
            OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_QUANTITY,
            OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_DATE,
            OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_ID_CATEGORY,
            CategotyEntry.TABLE_NAME+"."+CategotyEntry.COLUMN_DESCRIPTION,
            OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_ID_TYPE_OPERATION,
            CategotyEntry.TABLE_NAME+"."+TypeOperationEntry.COLUMN_DESCRIPTION};

    public static void insertOperation(Context context, Operation newOperation, int idUser){
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();

        values.put(OperationEntry.COLUMN_ID_CATEGORY, newOperation.getCategory().get_id());
        values.put(OperationEntry.COLUMN_DESCRIPTION, newOperation.getDescription());
        values.put(OperationEntry.COLUMN_ID_TYPE_OPERATION, newOperation.getTypeOperation().get_Id());
        values.put(OperationEntry.COLUMN_QUANTITY, newOperation.getQuantity());
        values.put(OperationEntry.COLUMN_DATE, newOperation.getDate());
        values.put(OperationEntry.COLUMN_USER_ID,idUser);

        cr.insert(operationUri,values);
    }

    public static List<Operation> getOperationsDay(Context context, String dateDay, String dateNextDay, int idUser) {
        List<Operation> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        String where= OperationEntry.COLUMN_DATE+">=? and "+OperationEntry.COLUMN_DATE+"<? and "+OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_USER_ID+"=?";
        String [] whereArgs = new String[]{dateDay,dateNextDay,String.valueOf(idUser)};
        String order = OperationEntry.COLUMN_DATE;

        Cursor cur = cr.query(operationUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                order);      //Orden de los resultados

        int colId = cur.getColumnIndex(FinanceContract.OperationEntry._ID);
        int colDescription = cur.getColumnIndex(OperationEntry.COLUMN_DESCRIPTION);
        int colQuantity = cur.getColumnIndex(OperationEntry.COLUMN_QUANTITY);
        int colDate = cur.getColumnIndex(OperationEntry.COLUMN_DATE);
        int colIdCategory = cur.getColumnIndex(OperationEntry.COLUMN_ID_CATEGORY);
        int colCategory = cur.getColumnIndex(CategotyEntry.COLUMN_DESCRIPTION);
        int colIdType = cur.getColumnIndex(OperationEntry.COLUMN_ID_TYPE_OPERATION);
        int colType = cur.getColumnIndex(TypeOperationEntry.COLUMN_DESCRIPTION);

        while(cur.moveToNext()){
            String id;
            String description;
            String quantity;
            String date;
            String idCategory;
            String desCategory;
            String idType;
            String desType;

            id = cur.getString(colId);
            description = cur.getString(colDescription);
            quantity  = cur.getString(colQuantity);
            date  = cur.getString(colDate);
            idCategory = cur.getString(colIdCategory);
            desCategory = cur.getString(colCategory);
            idType = cur.getString(colIdType);
            desType = cur.getString(colType);

            Category category = new Category(idCategory,desCategory);
            TypeOperation typeOperation = new TypeOperation(idType,desType);
            Operation operation = new Operation(id,description,quantity,date,category,typeOperation);
            list.add(operation);
        }
        return list;
    }

    public static List<Operation> getOperationsMonth(Context context, String beginningMonth, String beginningNextMonth, int idUser) {
        List<Operation> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        String where= OperationEntry.COLUMN_DATE+">=? and "+OperationEntry.COLUMN_DATE+"<? and "+OperationEntry.TABLE_NAME+"."+OperationEntry.COLUMN_USER_ID+"=?";
        String [] whereArgs = new String[]{beginningMonth,beginningNextMonth,String.valueOf(idUser)};
        String order = OperationEntry.COLUMN_DATE;

        Cursor cur = cr.query(operationUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                order);      //Orden de los resultados

        int colId = cur.getColumnIndex(FinanceContract.OperationEntry._ID);
        int colDescription = cur.getColumnIndex(OperationEntry.COLUMN_DESCRIPTION);
        int colQuantity = cur.getColumnIndex(OperationEntry.COLUMN_QUANTITY);
        int colDate = cur.getColumnIndex(OperationEntry.COLUMN_DATE);
        int colIdCategory = cur.getColumnIndex(OperationEntry.COLUMN_ID_CATEGORY);
        int colCategory = cur.getColumnIndex(CategotyEntry.COLUMN_DESCRIPTION);
        int colIdType = cur.getColumnIndex(OperationEntry.COLUMN_ID_TYPE_OPERATION);
        int colType = cur.getColumnIndex(TypeOperationEntry.COLUMN_DESCRIPTION);

        while(cur.moveToNext()){
            String id;
            String description;
            String quantity;
            String date;
            String idCategory;
            String desCategory;
            String idType;
            String desType;

            id = cur.getString(colId);
            description = cur.getString(colDescription);
            quantity  = cur.getString(colQuantity);
            date  = cur.getString(colDate);
            idCategory = cur.getString(colIdCategory);
            desCategory = cur.getString(colCategory);
            idType = cur.getString(colIdType);
            desType = cur.getString(colType);

            Category category = new Category(idCategory,desCategory);
            TypeOperation typeOperation = new TypeOperation(idType,desType);
            Operation operation = new Operation(id,description,quantity,date,category,typeOperation);
            list.add(operation);
        }
        return list;
    }
}
