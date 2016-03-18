package org.aplie.android.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;

import org.aplie.android.myapplication.bean.User;

public class FinancePreferences {
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL = "email";
    public static final String PREFERENCES_NAME = "finanzas";

    private static SharedPreferences getPreferences (Context context){
        return context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public static void saveNewUser(Context context, User user) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME, user.getUserName());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.commit();
    }

    public static User getUser(Context context) {
        SharedPreferences preferences = getPreferences(context);
        String name = preferences.getString(USER_NAME, "");
        String email = preferences.getString(USER_EMAIL, "");
        User user = new User(0,name,"",email);
        return user;
    }
}
