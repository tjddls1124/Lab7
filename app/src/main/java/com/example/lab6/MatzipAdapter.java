package com.example.lab6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 최웅순 on 2017-04-13.
 */

public class MatzipAdapter extends BaseAdapter {
    ArrayList<Matzip> data = new ArrayList<Matzip>();
    Context context;

    public MatzipAdapter(ArrayList<Matzip> data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.list_layout,null);

        }
        TextView t_name = (TextView)convertView.findViewById(R.id.list_name);
        TextView t_tel = (TextView)convertView.findViewById(R.id.list_tel);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_listMenu);

        t_name.setText(data.get(position).name);
        t_tel.setText(data.get(position).call_num);
        if(data.get(position).menu_kind == 1 ) imageView.setImageResource(R.drawable.chicken);
        else if(data.get(position).menu_kind==2) imageView.setImageResource(R.drawable.piz);
        else if (data.get(position).menu_kind==3) imageView.setImageResource(R.drawable.ham);

        return convertView;
    }

    public void setVisible(View convertView){
        if(convertView == null){
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.list_layout,null);

        }
        CheckBox cb = (CheckBox)convertView.findViewById(R.id.checkBox);
        cb.setVisibility(View.VISIBLE);
    }
}
