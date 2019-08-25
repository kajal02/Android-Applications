package com.kajal.kajal_contactapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by 987747 on 9/24/2015.
 */
public class AddCustomerActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText e1, e2, e3;
    ImageView imageView;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString, stateIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        toolbar = (Toolbar) findViewById(R.id.add_customer_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        e1 = (EditText) findViewById(R.id.editNameContact);
        e2 = (EditText) findViewById(R.id.editNumberContact);
        e3 = (EditText) findViewById(R.id.editEmailContact);
        imageView = (ImageView) findViewById(R.id.imgPerson);

        stateIntent = getIntent().getStringExtra("state");
        if("edit".equals(stateIntent))
        {
            Bundle bundle = getIntent().getExtras();
            ContactBean cb = (ContactBean) bundle.getSerializable("edit_bundle");
            e1.setText(cb.getName());
            e2.setText(cb.getNumber());
            e3.setText(cb.getEmail());
            imgDecodableString = cb.getImgDecodableString();
            imageView.setImageBitmap(BitmapFactory.decodeFile(cb.getImgDecodableString()));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // When an Image is picked
        if (requestCode == RESULT_LOAD_IMG &&  null != data) {
            // Get the Image from data

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            // Get the cursor
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            // Set the Image in ImageView after decoding the String
            imageView.setImageBitmap(BitmapFactory
                    .decodeFile(imgDecodableString));

        } else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.action_add) {


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Save..");

            alertDialogBuilder
                    .setMessage("Click yes to save contact")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            String name = e1.getText().toString();
                            String number = e2.getText().toString();
                            String email = e3.getText().toString();
                            ContactBean cb = new ContactBean(name, number, email, imgDecodableString);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("contact_bundle", cb);
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            setResult(5, intent);
                            finish();
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
