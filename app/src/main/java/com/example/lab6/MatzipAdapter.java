package com.example.lab6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
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

public class MatzipAdapter extends BaseAdapter implements Filterable{
    ArrayList<Matzip> data = new ArrayList<Matzip>();
    private ArrayList<Matzip> filteredItemList = data ;
    Context context;
    Filter listFilter;

    public MatzipAdapter(ArrayList<Matzip> data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    public void setCheckBoxVisibility() {

        for(int i =0; i<data.size() ; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_layout,null);
            CheckBox cb;
            cb = (CheckBox)view.findViewById(R.id.checkBox);
            cb.setVisibility(View.VISIBLE);
        }
    }
    public void setSearchData(String s){
        ArrayList<Matzip> backuphMatzipList = new ArrayList<>();
        backuphMatzipList.addAll(data);
        if(s.length() > 0 ) {
            for (int i = 0; i < data.size(); i++) {
                if (!data.get(i).name.contains(s)) {
                    data.remove(i);
                    i--;
                }
            }
            notifyDataSetChanged();
        }
        else {
            for(int i =0; i < backuphMatzipList.size() ; i++) data.add(backuphMatzipList.get(i));
            notifyDataSetChanged();
        }

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
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);

        t_name.setText(data.get(position).name);
        t_tel.setText(data.get(position).call_num);
        if(data.get(position).menu_kind == 1 ) imageView.setImageResource(R.drawable.chicken);
        else if(data.get(position).menu_kind==2) imageView.setImageResource(R.drawable.piz);
        else if (data.get(position).menu_kind==3) imageView.setImageResource(R.drawable.ham);

        return convertView;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = data ;
                results.count = data.size() ;
            } else {
                ArrayList<Matzip> itemList = new ArrayList<Matzip>() ;

                for (Matzip item : data) {
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase()) )
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredItemList = (ArrayList<Matzip>) results.values ;

            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;


    }
}
