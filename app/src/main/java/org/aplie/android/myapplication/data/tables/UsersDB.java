package org.aplie.android.myapplication.data.tables;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.data.FinanceContract.UserEntry;
import org.aplie.android.myapplication.data.FinanceDbHelper;
import org.aplie.android.myapplication.data.FinancePreferences;

public class UsersDB {
    private static Uri usersUri = UserEntry.CONTENT_URI;
    /*private static String[] projection = new String[] {
            UserEntry._ID,
            UserEntry.COLUMN_USER_NAME,
            UserEntry.COLUMN_PASSWORD,
            UserEntry.COLUMN_EMAIL};*/

    public static User getUserByNameEmail(Context context, String fieldName, String fieldEmail) {
        ContentResolver cr = context.getContentResolver();
        String where = UserEntry.COLUMN_USER_NAME +"=? and " + UserEntry.COLUMN_EMAIL+"=?";
        String [] whereArgs = new String []{fieldName, fieldEmail};
        Cursor cursor = cr.query(usersUri,
                null, //Columnas a devolver
                where,       //Condición de la query
                whereArgs,       //Argumentos variables de la query
                null);      //Orden de los resultados
        User user = null;
        if(cursor.moveToFirst()){
            /*int id;
            String userName;
            String password;
            String email;

            int colId = cur.getColumnIndex(UserEntry._ID);
            int colUserName = cur.getColumnIndex(UserEntry.COLUMN_USER_NAME);
            int colPassword = cur.getColumnIndex(UserEntry.COLUMN_PASSWORD);
            int colEmail = cur.getColumnIndex(UserEntry.COLUMN_EMAIL);

            id = cur.getInt(colId);
            userName = cur.getString(colUserName);
            password = cur.getString(colPassword);
            email = cur.getString(colEmail);

            user = new User(id,userName,password,email);*/
            user = (User) FinanceDbHelper.fillObject(cursor,new User());
        }

        return user;
    }

    public static void insertUser(Context context, User user) {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = user.getContentValues();
        values.remove(UserEntry._ID);

        cr.insert(usersUri,values);
    }

    public static User getCurrentUser(Context context) {
        FinancePreferences pref = new FinancePreferences(context);
        User user = pref.getUser();
        user = getUserByNameEmail(context,user.getUserName(),user.getEmail());

        return user;
    }

    public static void update(Context context, User user) {
        ContentResolver cr = context.getContentResolver();
        String where = UserEntry._ID+"=?";
        String whereArgs [] = new String[]{String.valueOf(user.get_id())};
        cr.update(usersUri,user.getContentValues(),where,whereArgs);
    }
}
