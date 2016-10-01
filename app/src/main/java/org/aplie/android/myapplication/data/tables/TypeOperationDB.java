package org.aplie.android.myapplication.data.tables;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import org.aplie.android.myapplication.data.FinanceContract.TypeOperationEntry;
import org.aplie.android.myapplication.bean.TypeOperation;
import org.aplie.android.myapplication.data.FinanceDbHelper;

public class TypeOperationDB {
    private static Uri typeOperationUri =  TypeOperationEntry.CONTENT_URI;
    /*private static String[] projection = new String[] {
            TypeOperationEntry._ID,
            TypeOperationEntry.COLUMN_DESCRIPTION};*/

    public static TypeOperation getTypeByName(Context context, String typeOperaton) {
        ContentResolver cr = context.getContentResolver();
        String where = TypeOperationEntry.COLUMN_DESCRIPTION+"=?";
        String whereArgs[] = new String[]{typeOperaton};
        Cursor cursor = cr.query(typeOperationUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                null);      //Orden de los resultados

        TypeOperation typeOperation = null;
        if(cursor.moveToFirst()){
            /*String id;
            String description;

            int colId = cursor.getColumnIndex(TypeOperationEntry._ID);
            int colDescription = cursor.getColumnIndex(TypeOperationEntry.COLUMN_DESCRIPTION);

            id = cursor.getString(colId);
            description = cursor.getString(colDescription);

            typeOperation = new TypeOperation(id,description);*/
            typeOperation = (TypeOperation) FinanceDbHelper.fillObject(cursor,new TypeOperation());
        }
        return typeOperation;
    }
}
