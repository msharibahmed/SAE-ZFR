package com.example.android.zfr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


class MainAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private  String[] mItem;

    public MainAdapter(Context c,String[] mItem){
        this.context=c;
        this.mItem=mItem;

    }

    @Override
    public int getCount() {
        return mItem.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView= inflater.inflate(R.layout.row,null);
        }

        TextView itemName=convertView.findViewById(R.id.item_name);
        itemName.setText(mItem[position]);

        return convertView ;
    }
}


