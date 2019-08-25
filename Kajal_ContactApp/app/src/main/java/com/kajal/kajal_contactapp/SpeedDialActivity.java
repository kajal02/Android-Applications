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
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class SpeedDialActivity extends AppCompatActivity{
    Toolbar toolbar;
    GridView gridView;
    List<ContactBean> defaultList;
    CustomGridAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_dial);

        toolbar = (Toolbar) findViewById(R.id.speed_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (GridView) findViewById(R.id.gridView);

        if(ContactManager.getInstance().getSpeedContactList().isEmpty())
        {
            defaultList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                defaultList.add(new ContactBean());
            }
        }
        else
            defaultList = ContactManager.getInstance().getSpeedContactList();

        ContactManager.getInstance().setmSpeedContactList(defaultList);
        customGridAdapter = new CustomGridAdapter(this, defaultList);
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SpeedDialActivity.this, ListSpeedDialActivity.class);
                intent.putExtra("position", position);
                Log.i("position of grid", String.valueOf(position));
                startActivityForResult(intent, 11);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speed_dial, menu);
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
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SpeedDialActivity.this, ListSpeedDialActivity.class);
        intent.putExtra("position", position);
        Log.i("position of grid", String.valueOf(position));
        startActivityForResult(intent, 11);

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==11 && resultCode==12)
        {
            Bundle bundle = data.getExtras();
            ContactBean a = (ContactBean) bundle.getSerializable("speed_contact");
            int pos = data.getIntExtra("position",0);
            defaultList.remove(pos);
            defaultList.add(pos, a);
            ContactManager.getInstance().setmSpeedContactList(defaultList);
            customGridAdapter.notifyDataSetChanged();
        }
    }
}
