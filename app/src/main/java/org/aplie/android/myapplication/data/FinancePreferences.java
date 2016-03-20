package org.aplie.android.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.User;

public class FinancePreferences {
    private SharedPreferences preferences;
    private Context context;

    public FinancePreferences (Context context){
        this.context = context;
        this.preferences= PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveNewUser(User user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.pref_key_userName), user.getUserName());
        editor.putString(context.getString(R.string.pref_key_email), user.getEmail());
        editor.commit();
    }

    public User getUser() {
        String name = preferences.getString(context.getString(R.string.pref_key_userName), "");
        String email = preferences.getString(context.getString(R.string.pref_key_email), "");
        User user = new User(0,name,"",email);
        return user;
    }

    public boolean isRememberEmail(){
        return preferences.getBoolean(context.getString(R.string.pref_key_rememberEmail),false);
    }

    public boolean isRememberName(){
        return preferences.getBoolean(context.getString(R.string.pref_key_rememberUserName),false);
    }
}
