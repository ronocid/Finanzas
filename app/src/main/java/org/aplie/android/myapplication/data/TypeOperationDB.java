package org.aplie.android.myapplication.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import org.aplie.android.myapplication.data.FinanceContract.TypeOperationEntry;
import org.aplie.android.myapplication.bean.TypeOperation;

public class TypeOperationDB {
    private static Uri typeOperationUri =  TypeOperationEntry.CONTENT_URI;
    private static String[] projection = new String[] {
            TypeOperationEntry._ID,
            TypeOperationEntry.COLUMN_DESCRIPTION};

    public static TypeOperation getTypeByName(Context context, String typeOperaton) {
        ContentResolver cr = context.getContentResolver();
        String where = TypeOperationEntry.COLUMN_DESCRIPTION+"=?";
        String whereArgs[] = new String[]{typeOperaton};
        Cursor cur = cr.query(typeOperationUri,
                projection, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                null);      //Orden de los resultados

        TypeOperation typeOperation = null;
        while(cur.moveToNext()){
            String id;
            String description;

            int colId = cur.getColumnIndex(TypeOperationEntry._ID);
            int colDescription = cur.getColumnIndex(TypeOperationEntry.COLUMN_DESCRIPTION);

            id = cur.getString(colId);
            description = cur.getString(colDescription);

            typeOperation = new TypeOperation(id,description);
        }
        return typeOperation;
    }
}
