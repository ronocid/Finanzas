package org.aplie.android.myapplication.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

public class FinanceProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private FinanceDbHelper mOpenHelper;

    static final int CATEGOTY = 100;
    static final int TYPE_OPERATION = 200;
    static final int OPERATION = 300;
    @Override
    public boolean onCreate() {
        mOpenHelper = new FinanceDbHelper(getContext());
        return true;
    }

    private static final SQLiteQueryBuilder sOperationByCategorySettingQueryBuild;

    static{
        sOperationByCategorySettingQueryBuild = new SQLiteQueryBuilder();

        sOperationByCategorySettingQueryBuild.setTables(
                FinanceContract.OperationEntry.TABLE_NAME + " INNER JOIN " +
                        FinanceContract.CategotyEntry.TABLE_NAME +
                        " ON " + FinanceContract.OperationEntry.TABLE_NAME +
                        "." + FinanceContract.OperationEntry.COLUMN_ID_CATEGORY +
                        " = " + FinanceContract.CategotyEntry.TABLE_NAME +
                        "." + FinanceContract.CategotyEntry._ID+ " "+
                        " INNER JOIN " +
                        FinanceContract.TypeOperationEntry.TABLE_NAME +
                        " ON " + FinanceContract.OperationEntry.TABLE_NAME +
                        "." + FinanceContract.OperationEntry.COLUMN_ID_TYPE_OPERATION+
                        " = " + FinanceContract.TypeOperationEntry.TABLE_NAME +
                        "." + FinanceContract.TypeOperationEntry._ID);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case CATEGOTY:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(FinanceContract.CategotyEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case TYPE_OPERATION: {
                retCursor = mOpenHelper.getReadableDatabase().query(FinanceContract.TypeOperationEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case OPERATION: {
                retCursor = sOperationByCategorySettingQueryBuild.query(mOpenHelper.getReadableDatabase(),projection,selection,selectionArgs,null,null,sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case CATEGOTY:
                return FinanceContract.CategotyEntry.CONTENT_ITEM_TYPE;
            case TYPE_OPERATION:
                return FinanceContract.TypeOperationEntry.CONTENT_TYPE;
            case OPERATION:
                return FinanceContract.OperationEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case CATEGOTY: {
                long _id = db.insert(FinanceContract.CategotyEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = FinanceContract.CategotyEntry.buildCategoryUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case TYPE_OPERATION: {
                long _id = db.insert(FinanceContract.TypeOperationEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = FinanceContract.TypeOperationEntry.buildTypeOperationUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case OPERATION: {
                long _id = db.insert(FinanceContract.OperationEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = FinanceContract.OperationEntry.buildOperationUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDelete;

        if(null==selection)selection ="1";
        switch (match) {
            case CATEGOTY: {
                rowsDelete = db.delete(FinanceContract.CategotyEntry.TABLE_NAME,selection,selectionArgs);
                break;
            }
            case TYPE_OPERATION: {
                rowsDelete = db.delete(FinanceContract.TypeOperationEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case OPERATION: {
                rowsDelete = db.delete(FinanceContract.OperationEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rowsDelete !=0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsDelete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdate;

        switch (match) {
            case CATEGOTY: {
                rowsUpdate = db.update(FinanceContract.CategotyEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case TYPE_OPERATION: {
                rowsUpdate = db.update(FinanceContract.TypeOperationEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case OPERATION: {
                rowsUpdate = db.update(FinanceContract.OperationEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rowsUpdate !=0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdate;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        sUriMatcher.addURI(FinanceContract.CONTENT_AUTHORITY,FinanceContract.PATH_CATEGOTY,CATEGOTY);
        sUriMatcher.addURI(FinanceContract.CONTENT_AUTHORITY,FinanceContract.PATH_TYPE_OPERATION,TYPE_OPERATION);
        sUriMatcher.addURI(FinanceContract.CONTENT_AUTHORITY,FinanceContract.PATH_OPERATION,OPERATION);

        return sUriMatcher;
    }
}
