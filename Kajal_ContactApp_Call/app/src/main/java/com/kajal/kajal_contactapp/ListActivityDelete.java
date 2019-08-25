package com.kajal.kajal_contactapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class ListActivityDelete extends AppCompatActivity {
    Toolbar toolbar;
    private ListView listView1;
    private CustomListAdapterDelete customListAdapterDelete;
    List<ContactBean> contactBeanList;
    ContactBean a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_list);

        toolbar = (Toolbar) findViewById(R.id.delete_tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView1 = (ListView)findViewById(R.id.list1);
        contactBeanList = ContactManager.getInstance().getContactList();
        customListAdapterDelete = new CustomListAdapterDelete(this, contactBeanList);
        listView1.setAdapter(customListAdapterDelete);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a = (ContactBean) parent.getItemAtPosition(position);
                Log.i("clicked", "item");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.action_delete) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Delete..");

            alertDialogBuilder
                    .setMessage("Click yes to delete selected contacts")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            for(int i=0; i<ContactManager.getInstance().getContactList().size(); i++)
                            {
                                if(ContactManager.getInstance().getContactList().get(i).isChecked())
                                {
                                    ContactManager.getInstance().deleteContact(i);
                                    i--;
                                }
                            }
                            Intent intent = new Intent(ListActivityDelete.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
