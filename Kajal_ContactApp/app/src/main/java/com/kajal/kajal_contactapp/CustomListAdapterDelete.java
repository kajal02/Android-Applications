package com.kajal.kajal_contactapp;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class CustomListAdapterDelete extends BaseAdapter{
    private Activity mContext;
    private List<ContactBean> mList = new ArrayList<>();

    public CustomListAdapterDelete(Activity context, List<ContactBean> contactBeanList)
    {
        this.mContext = context;
        this.mList = contactBeanList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        TextView name;
        TextView number;
        ImageView imageView;
        CheckBox checkBox;

        if(convertView == null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.delete_list_item_view, null);
        }
        else {
            view = convertView;

        }
        name= (TextView) view.findViewById(R.id.tv_name);
        number = (TextView) view.findViewById(R.id.tv_number);
        imageView = (ImageView) view.findViewById(R.id.img1);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ContactBean contactBean = mList.get(position);
                contactBean.setChecked(cb.isChecked());
            }
        });

        ContactBean contactBean = mList.get(position);
        if(contactBean!=null)
        {
            name.setText(contactBean.getName());
            number.setText(contactBean.getNumber());
            imageView.setImageBitmap(BitmapFactory
                    .decodeFile(contactBean.getImgDecodableString()));
            checkBox.setChecked(contactBean.isChecked());
        }

        return view;
    }
}
