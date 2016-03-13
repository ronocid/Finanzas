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
import org.aplie.android.myapplication.utils.FinanceConstants;

import java.util.ArrayList;
import java.util.List;

public class OperationDB {
    private static Uri operationUri =  FinanceContract.OperationEntry.CONTENT_URI;
    private static String[] projection = new String[] {
            FinanceContract.OperationEntry.TABLE_NAME+"."+FinanceContract.OperationEntry._ID,
            FinanceContract.OperationEntry.TABLE_NAME+"."+FinanceContract.OperationEntry.COLUMN_DESCRIPTION,
            FinanceContract.OperationEntry.TABLE_NAME+"."+FinanceContract.OperationEntry.COLUMN_QUANTITY,
            FinanceContract.OperationEntry.TABLE_NAME+"."+FinanceContract.OperationEntry.COLUMN_DATE,
            FinanceContract.OperationEntry.TABLE_NAME+"."+FinanceContract.OperationEntry.COLUMN_ID_CATEGORY,
            FinanceContract.CategotyEntry.TABLE_NAME+"."+FinanceContract.CategotyEntry.COLUMN_DESCRIPTION,
            FinanceContract.OperationEntry.TABLE_NAME+"."+FinanceContract.OperationEntry.COLUMN_ID_TYPE_OPERATION,
            FinanceContract.CategotyEntry.TABLE_NAME+"."+FinanceContract.TypeOperationEntry.COLUMN_DESCRIPTION};

    public static void insertOperation(Context context, String idCategory, String description, String idOperationType, String quantity, String date){
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();

        values.put(FinanceContract.OperationEntry.COLUMN_ID_CATEGORY, idCategory);
        values.put(FinanceContract.OperationEntry.COLUMN_DESCRIPTION, description);
        values.put(FinanceContract.OperationEntry.COLUMN_ID_TYPE_OPERATION, idOperationType);
        values.put(FinanceContract.OperationEntry.COLUMN_QUANTITY, quantity);
        values.put(FinanceContract.OperationEntry.COLUMN_DATE, date);

        cr.insert(operationUri,values);
    }

    public static List<Operation> getOperations(Context context, String dateDay, String dateNextDay) {
        List<Operation> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        String where= FinanceContract.OperationEntry.COLUMN_DATE+">=? and "+FinanceContract.OperationEntry.COLUMN_DATE+"<?";
        String [] whereArgs = new String[]{dateDay,dateNextDay};

        Cursor cur = cr.query(operationUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                null);      //Orden de los resultados

        int colId = cur.getColumnIndex(FinanceContract.OperationEntry._ID);
        int colDescription = cur.getColumnIndex(FinanceContract.OperationEntry.COLUMN_DESCRIPTION);
        int colQuantity = cur.getColumnIndex(FinanceContract.OperationEntry.COLUMN_QUANTITY);
        int colDate = cur.getColumnIndex(FinanceContract.OperationEntry.COLUMN_DATE);
        int colIdCategory = cur.getColumnIndex(FinanceContract.OperationEntry.COLUMN_ID_CATEGORY);
        int colCategory = cur.getColumnIndex(FinanceContract.CategotyEntry.COLUMN_DESCRIPTION);
        int colIdType = cur.getColumnIndex(FinanceContract.OperationEntry.COLUMN_ID_TYPE_OPERATION);
        int colType = cur.getColumnIndex(FinanceContract.TypeOperationEntry.COLUMN_DESCRIPTION);

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
