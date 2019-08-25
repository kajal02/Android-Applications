package com.kajal.kajal_contactapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private CustomListAdapter customListAdapter;
    List<ContactBean> contactBeanList = new ArrayList<ContactBean>();;
    ContactManager contactManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactManager = ContactManager.getInstance();

        customListAdapter = new CustomListAdapter(this, contactManager.getContactList());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        actionBarDrawerToggle.syncState();
        showMainFragment();
        showNavFragment();
    }

    private void showNavFragment() {
        NavigationFragment navigationFragment = new NavigationFragment();
        getFragmentManager().beginTransaction().add(R.id.nav_fragment_container, navigationFragment).commit();
    }

    private void showMainFragment() {
        ListActivity listFragment = new ListActivity();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();

    }

    public void loadList()
    {
        ListActivity frag1 = new ListActivity();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, frag1).commit();
    }

    public void update(ContactBean newContact)
    {
        ContactManager.getInstance().addContact(newContact);
        customListAdapter.notifyDataSetChanged();
        ListActivity frag1 = new ListActivity();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, frag1).commit();
    }

    public void editList(ContactBean cb, int position)
    {
        ContactManager.getInstance().updateContact(position, cb);
        customListAdapter.notifyDataSetChanged();

        ListActivity frag1 = new ListActivity();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, frag1).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_delete) {
            Intent intent = new Intent(MainActivity.this, ListActivityDelete.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
