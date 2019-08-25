package com.kajal.kajal_contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class ListSpeedDialActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    List<ContactBean> contactBeanList, contactSpeedList;
    CustomListAdapter customListAdapter;
    ContactBean a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_speed_dial);

        toolbar = (Toolbar) findViewById(R.id.speed_list_tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.list1);
        contactBeanList = ContactManager.getInstance().getContactList();
        contactSpeedList = ContactManager.getInstance().getSpeedContactList();

        customListAdapter = new CustomListAdapter(this, contactBeanList);
        listView.setAdapter(customListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a = (ContactBean) parent.getItemAtPosition(position);

                Intent intent1 = getIntent();
                int pos = intent1.getIntExtra("position", 0);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putSerializable("speed_contact", a);
                intent.putExtras(bundle);
                intent.putExtra("position", pos);
                setResult(12, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_speed_dial, menu);
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
        if(id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
