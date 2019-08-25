package com.kajal.kajal_contactapp;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 987747 on 9/28/2015.
 */
public class PagerFragment extends Fragment {

    ContactBean mContactBean;
    ImageView imageView;
    TextView txt1,txt2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.activity_contact_detail,container,false);

        imageView = (ImageView) view.findViewById(R.id.imageView);
        txt1 = (TextView) view.findViewById(R.id.textNameDetail);
        txt2 = (TextView) view.findViewById(R.id.textNumberDetail);


        imageView.setImageBitmap(BitmapFactory.decodeFile(mContactBean.getImgDecodableString()));
        txt1.setText(mContactBean.getName());
        txt2.setText(mContactBean.getNumber());

        return view;
    }

    public static Fragment newInstace(int position)
    {
        ContactBean cb = ContactManager.getInstance().getContactList().get(position);
        PagerFragment pagerFragment= new PagerFragment();
        if(cb!=null)
        {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Obj",cb);
            pagerFragment.setArguments(bundle);
        }
        return pagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactBean = (ContactBean) getArguments().getSerializable("Obj");

    }
}
