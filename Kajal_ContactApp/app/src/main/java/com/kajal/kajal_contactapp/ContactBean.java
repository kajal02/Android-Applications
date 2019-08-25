package com.kajal.kajal_contactapp;

import java.io.Serializable;

/**
 * Created by 987747 on 9/24/2015.
 */
public class ContactBean implements Serializable{
    private String name, number, imgDecodableString, email;
    private boolean checked;

    public ContactBean() {
        this.name="name";
    }

    public ContactBean(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.checked = false;
    }

    public ContactBean(String name, String number, String email, String imgDecodableString) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.imgDecodableString = imgDecodableString;
        this.checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgDecodableString() {
        return imgDecodableString;
    }

    public void setImgDecodableString(String imgDecodableString) {
        this.imgDecodableString = imgDecodableString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

}
