package org.aplie.android.myapplication.data;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class FinanceContract {
    public static final String CONTENT_AUTHORITY = "org.aplie.android.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USER = "user";
    public static final String PATH_CATEGOTY = "categoty";
    public static final String PATH_TYPE_OPERATION = "typeOperation";
    public static final String PATH_OPERATION = "operation";

    public static final class CategotyEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGOTY).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGOTY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGOTY;

        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_DESCRIPTION = "catdescription";
        public static final String COLUMN_USER_ID = "id_user";

        public static Uri buildCategoryUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }

    public static final class UserEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USER_NAME = "userName";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_EMAIL = "email";

        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }

    public static final class OperationEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_OPERATION).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_OPERATION;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_OPERATION;

        public static final String TABLE_NAME = "operations";
        public static final String COLUMN_ID_CATEGORY = "id_category";
        public static final String COLUMN_DESCRIPTION = "opdescription";
        public static final String COLUMN_ID_TYPE_OPERATION = "id_type_operation";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_USER_ID = "id_user";

        public static Uri buildOperationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TypeOperationEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TYPE_OPERATION).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TYPE_OPERATION;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TYPE_OPERATION;

        public static final String TABLE_NAME = "type_operation";
        public static final String COLUMN_DESCRIPTION = "operationDescription";

        public static Uri buildTypeOperationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
