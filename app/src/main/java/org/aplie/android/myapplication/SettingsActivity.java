package org.aplie.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.aplie.android.myapplication.settings.SettingsFragment;

public class SettingsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
