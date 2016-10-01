package org.aplie.android.myapplication.data.tables;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.aplie.android.myapplication.bean.Category;
import org.aplie.android.myapplication.data.FinanceContract.CategotyEntry;
import org.aplie.android.myapplication.data.FinanceDbHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDB {
    private static Uri categoriesUri =  CategotyEntry.CONTENT_URI;
    /*private static String[] projection = new String[] {
            CategotyEntry._ID,
            CategotyEntry.COLUMN_DESCRIPTION};*/

    public static List<Category> getAllCategories(Context context, int idUser){
        List<Category> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();

        String where = CategotyEntry.COLUMN_USER_ID+"=?";
        String [] whereArgs = new String []{String.valueOf(idUser)};
        Cursor cursor = cr.query(categoriesUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                null);      //Orden de los resultados

        while(cursor.moveToNext()){
            /*String id;
            String description;

            int colId = cur.getColumnIndex(CategotyEntry._ID);
            int colDescription = cur.getColumnIndex(CategotyEntry.COLUMN_DESCRIPTION);

            id = cur.getString(colId);
            description = cur.getString(colDescription);

            list.add(new Category(id,description));*/

            Category category = (Category) FinanceDbHelper.fillObject(cursor,new Category());
            list.add(category);
        }
        return list;
    }

    public static void insertCategory(Context context, Category categoty) {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = categoty.getContentValues();

        values.remove(CategotyEntry._ID);
        cr.insert(categoriesUri,values);
    }

    public static Category getCategoriyByDescription(Context context, String fieldDescription, int idUser) {
        ContentResolver cr = context.getContentResolver();
        String where = CategotyEntry.COLUMN_DESCRIPTION +"=? and "+CategotyEntry.COLUMN_USER_ID+"=?";
        String [] whereArgs = new String []{fieldDescription,String.valueOf(idUser)};
        Cursor cursor = cr.query(categoriesUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                null);      //Orden de los resultados
        Category category = null;
        if(cursor.moveToFirst()){
            /*String id;
            String description;

            int colId = cur.getColumnIndex(CategotyEntry._ID);
            int colDescription = cur.getColumnIndex(CategotyEntry.COLUMN_DESCRIPTION);

            id = cur.getString(colId);
            description = cur.getString(colDescription);

            category = new Category(id,description);*/
            category = (Category) FinanceDbHelper.fillObject(cursor,new Category());
        }

        return category;
    }
}
