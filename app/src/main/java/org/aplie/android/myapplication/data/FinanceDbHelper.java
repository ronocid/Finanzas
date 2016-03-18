package org.aplie.android.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.aplie.android.myapplication.data.FinanceContract.CategotyEntry;
import org.aplie.android.myapplication.data.FinanceContract.OperationEntry;
import org.aplie.android.myapplication.data.FinanceContract.TypeOperationEntry;
import org.aplie.android.myapplication.data.FinanceContract.UserEntry;
import org.aplie.android.myapplication.utils.FinanceConstants;

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
}
