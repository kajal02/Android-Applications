package com.kajal.kajal_contactapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 987747 on 9/24/2015.
 */
public class CustomGridAdapter extends BaseAdapter {
    List<ContactBean> list = new ArrayList<ContactBean>();
    Context context;

    CustomGridAdapter(Context context, List<ContactBean> myList)
    {
        this.context = context;
        this.list = myList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.
                    LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) row.getTag();
        }
        final ContactBean temp = list.get(position);
        if(temp.getImgDecodableString() == null)
        {
            viewHolder.imageView.setImageResource(R.drawable.grid_icon);
        }
        else {
            viewHolder.imageView.setImageBitmap(BitmapFactory.decodeFile(temp.getImgDecodableString()));
            viewHolder.textView.setText(temp.getName());

            viewHolder.callImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+temp.getNumber()));
                    context.startActivity(callIntent);
                }
            });
        }
        viewHolder.imageView.setTag(temp);

        return row;
    }
}
class ViewHolder
{
    ImageView imageView, callImage;
    TextView textView;

    ViewHolder(View v)
    {
        imageView = (ImageView) v.findViewById(R.id.gridImage);
        textView = (TextView) v.findViewById(R.id.gridName);
        callImage = (ImageView) v.findViewById(R.id.callButton);
    }
}
