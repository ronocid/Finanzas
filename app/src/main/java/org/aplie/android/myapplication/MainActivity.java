package org.aplie.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.aplie.android.myapplication.operations_day.FragmentOperationsDay;
import org.aplie.android.myapplication.operations_month.FragmentOperationsMonth;
import org.aplie.android.myapplication.operations_year.FragmentOperationsYear;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new FragmentOperationsDay();
        FragmentManager fragmentManger = getSupportFragmentManager();
        fragmentManger.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_day) {
            Fragment fragment = new FragmentOperationsDay();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        } else if (id == R.id.nav_month) {
            Fragment fragment = new FragmentOperationsMonth();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        } else if (id == R.id.nav_year) {
            Fragment fragment = new FragmentOperationsYear();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(this,SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
