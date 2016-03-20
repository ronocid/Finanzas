package org.aplie.android.myapplication.settings;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import org.aplie.android.myapplication.R;
import org.aplie.android.myapplication.bean.User;
import org.aplie.android.myapplication.data.FinancePreferences;
import org.aplie.android.myapplication.data.UsersDB;

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_general);

        changeSumary();
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_email)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_userName)));
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private void changeSumary() {
        EditTextPreference email = (EditTextPreference) findPreference(getString(R.string.pref_key_email));
        EditTextPreference userName = (EditTextPreference) findPreference(getString(R.string.pref_key_userName));

        FinancePreferences pref = new FinancePreferences(getActivity());
        User user = pref.getUser();

        if(!TextUtils.isEmpty(user.getEmail())){
            email.setSummary(user.getEmail());
        }
        if(!TextUtils.isEmpty(user.getUserName())){
            userName.setSummary(user.getUserName());
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
        }

        changeBD(preference.getKey(), stringValue);
        return true;
    }

    private void changeBD(String key, String value) {
        User user = UsersDB.getCurrentUser(getActivity());
        if(key.equals(getString(R.string.pref_key_email))){
            user.setEmail(value);
        }
        if(key.equals(getString(R.string.pref_key_userName))){
            user.setUserName(value);
        }

        UsersDB.update(getActivity(), user);
    }
}
