package org.aplie.android.myapplication.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.aplie.android.myapplication.bean.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDB {
    private static Uri categoriesUri =  FinanceContract.CategotyEntry.CONTENT_URI;
    private static String[] projection = new String[] {
            FinanceContract.CategotyEntry._ID,
            FinanceContract.CategotyEntry.COLUMN_DESCRIPTION};

    public static List<Category> getAllCategories(Context context){
        List<Category> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();

        Cursor cur = cr.query(categoriesUri,
                projection, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        while(cur.moveToNext()){
            String id;
            String description;

            int colId = cur.getColumnIndex(FinanceContract.CategotyEntry._ID);
            int colDescription = cur.getColumnIndex(FinanceContract.CategotyEntry.COLUMN_DESCRIPTION);

            id = cur.getString(colId);
            description = cur.getString(colDescription);

            list.add(new Category(id,description));
        }
        return list;
    }

    public static void insertCategory(Context context, Category categoty) {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();

        values.put(FinanceContract.CategotyEntry.COLUMN_DESCRIPTION, categoty.getDescription());
        cr.insert(categoriesUri,values);
    }
}
