package com.kajal.kajal_contactapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 987747 on 9/24/2015.
 */
public class ContactDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textName, textNumber, textEmail;
    Toolbar toolbar;
    ContactBean a;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        toolbar = (Toolbar) findViewById(R.id.contact_detail_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        a = (ContactBean) bundle.getSerializable("contact_detail");
        position = getIntent().getIntExtra("position", 0);

        imageView = (ImageView) findViewById(R.id.imageView);
        textName = (TextView) findViewById(R.id.textNameDetail);
        textNumber = (TextView) findViewById(R.id.textNumberDetail);
        textEmail = (TextView) findViewById(R.id.textEmailDetail);

        imageView.setImageBitmap(BitmapFactory.decodeFile(a.getImgDecodableString()));
        textName.setText(a.getName());
        textNumber.setText(a.getNumber());
        textEmail.setText(a.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_detail, menu);
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
        if (id == R.id.action_edit) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("edit_bundle", a);
            Intent intent = new Intent(ContactDetailActivity.this, AddCustomerActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("state", "edit");
            startActivityForResult(intent, 10);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == 5 )
        {
            if(data!=null)
            {
                data.putExtra("position", position);
                setResult(11, data);
                finish();
            }
        }
    }
}
