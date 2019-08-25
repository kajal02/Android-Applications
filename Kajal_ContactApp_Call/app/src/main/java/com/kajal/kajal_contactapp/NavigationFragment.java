package com.kajal.kajal_contactapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 987747 on 9/24/2015.
 */
public class NavigationFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter listAdapter;
    private String[] listData = {"Home", "Add Contact", "Speed dials", "Log out"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_fragment_layout, null);
        View header_view = inflater.inflate(R.layout.header_layout, null);

        listView = (ListView) view.findViewById(R.id.list_view);
        listAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(listAdapter);
        listView.addHeaderView(header_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        ((MainActivity)getActivity()).loadList();
                        break;
                    case 2:

                        Intent intent = new Intent(getActivity(), AddCustomerActivity.class);
                        intent.putExtra("state","add");
                        startActivityForResult(intent, 4);
                        break;
                    case 3:
                        Intent intent1 = new Intent(getActivity(), SpeedDialActivity.class);
                        startActivity(intent1);
                        break;
                    case 4:
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                        alertDialogBuilder.setTitle("Logout..");

                        alertDialogBuilder
                                .setMessage("Sure you want to logout")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, close
                                        // current activity
                                        System.exit(0);
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

                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 4 && resultCode == 5 )
        {
            ContactBean cb = (ContactBean) data.getExtras().getSerializable("contact_bundle");
            ((MainActivity)getActivity()).update(cb);
        }
    }
}
