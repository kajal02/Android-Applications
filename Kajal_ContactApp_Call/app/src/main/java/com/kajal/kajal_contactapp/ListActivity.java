package com.kajal.kajal_contactapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class ListActivity extends Fragment {
    Toolbar toolbar;
    private ListView listView1;
    private CustomListAdapter customListAdapter;
    List<ContactBean> contactBeanList;
    ContactBean a;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, null);

        listView1 = (ListView)view.findViewById(R.id.list1);

        contactBeanList = ContactManager.getInstance().getContactList();
        Log.i("size", String.valueOf(ContactManager.getInstance().getContactList().size()));
        customListAdapter = new CustomListAdapter((Activity) inflater.getContext(),
                contactBeanList);

        listView1.setAdapter(customListAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a = (ContactBean) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact_detail", a);
                Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("position",position);
                startActivityForResult(intent, 6);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 6 && resultCode == 11 )
        {
            ContactBean cb = (ContactBean) data.getExtras().getSerializable("contact_bundle");
            int pos = data.getIntExtra("position", 0);
            ((MainActivity)getActivity()).editList(cb, pos);
        }
    }
}
