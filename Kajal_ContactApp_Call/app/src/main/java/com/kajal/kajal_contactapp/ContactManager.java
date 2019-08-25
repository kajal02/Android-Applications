package com.kajal.kajal_contactapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class ContactManager {
    private static ContactManager mContactmanager;
    List<ContactBean> mContactBeanList;
    List<ContactBean> mSpeedContactList;

    private ContactManager(){
        mContactBeanList = new ArrayList<>();
        mSpeedContactList = new ArrayList<>();
    }

    public static ContactManager getInstance(){
        if(mContactmanager == null){
            mContactmanager = new ContactManager();
        }
        return mContactmanager;
    }

    public void setmSpeedContactList(List<ContactBean> mSpeedContactList) {
        this.mSpeedContactList = mSpeedContactList;
    }

    public void addContact(ContactBean contactBean){
        mContactBeanList.add(contactBean);
    }

    public List<ContactBean> getContactList(){
        return mContactBeanList;
    }

    public List<ContactBean> getSpeedContactList()
    {
        return mSpeedContactList;
    }

    public void deleteContact(int position){

        mContactBeanList.remove(position);

    }

    public void updateContact(int position, ContactBean updatedContact){

        mContactBeanList.remove(position);
        mContactBeanList.add(position, updatedContact);

    }
}
